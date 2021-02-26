package voiceTransfer;

import javax.sound.sampled.*;
import java.io.IOException;

public class VoiceListener extends Thread {
    private final byte[] VOICE;

    private TargetDataLine target;
    private AudioInputStream stream;

    public boolean g_bListener;

    public VoiceListener(int hz, int bits, int mono) {
        this.VOICE = new byte[hz * bits / 8 * mono];

        try {
            AudioFormat linear = new AudioFormat(hz, bits, mono, true, false);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, linear);
            this.target = (TargetDataLine) AudioSystem.getLine(info);

            this.target.open(linear);
            this.target.start();

            this.stream = new AudioInputStream(this.target);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        this.g_bListener = true;
    }

    public void run() {
        while (true) {
            if (!this.g_bListener) return;

            try {
                stream.read(this.VOICE, 0, this.VOICE.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getVoice() {
        return this.VOICE;
    }

    public void end() {
        this.g_bListener = false;
        this.target.stop();
        this.target.close();
    }
}
