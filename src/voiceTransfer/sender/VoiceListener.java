package voiceTransfer.sender;

import javax.sound.sampled.*;
import java.io.IOException;

public class VoiceListener extends Thread {
    private static final int HZ = 8000;
    private static final int BITS = 16;
    private static final int MONO = 1;

    private final TargetDataLine TARGET;
    private final AudioInputStream STREAM;
    private boolean isListening;
    private final byte[] VOICE = new byte[HZ * BITS / 8 * MONO];

    public VoiceListener() throws IOException, LineUnavailableException {
        AudioFormat linear = new AudioFormat(HZ, BITS, MONO, true, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, linear);

        this.TARGET = (TargetDataLine) AudioSystem.getLine(info);
        this.TARGET.open(linear);
        this.TARGET.start();

        this.STREAM = new AudioInputStream(TARGET);
        this.isListening = true;
    }

    public void run() {
        while (true) {
            if (!this.isListening) return;

            try {
                this.STREAM.read(this.VOICE, 0, this.VOICE.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getVOICE() {
        return this.VOICE;
    }

    public void end() {
        this.isListening = false;
        this.TARGET.stop();
        this.TARGET.close();
    }

    public boolean getIsListening() {
        return this.isListening;
    }
}
