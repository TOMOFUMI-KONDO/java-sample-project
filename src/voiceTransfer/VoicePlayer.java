package voiceTransfer;

import javax.sound.sampled.*;

public class VoicePlayer extends Thread {
    private final int HZ;
    private final int BITS;
    private final int MONO;
    private byte[] voice;

    private SourceDataLine source;

    public boolean g_bPlayer;

    public VoicePlayer(int hz, int bits, int mono) {
        this.HZ = hz;
        this.BITS = bits;
        this.MONO = mono;
        voice = new byte[this.HZ * this.BITS / 8 * this.MONO];

        this.g_bPlayer = true;

        try {
            AudioFormat linear = new AudioFormat(hz, bits, mono, true, false);

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, linear);
            this.source = (SourceDataLine) AudioSystem.getLine(info);

            this.source.open(linear);
            this.source.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            if (!this.g_bPlayer) return;

            this.source.write(this.voice, 0, this.voice.length);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setVoice(byte[] bytes) {
        this.voice = bytes;
    }

    public void end() {
        this.g_bPlayer = false;
        source.stop();
        source.close();
    }
}