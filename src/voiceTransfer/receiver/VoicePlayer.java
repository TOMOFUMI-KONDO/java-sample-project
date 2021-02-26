package voiceTransfer.receiver;

import javax.sound.sampled.*;

public class VoicePlayer extends Thread {
    private static final int HZ = 8000;
    private static final int BITS = 16;
    private static final int MONO = 1;

    private final SourceDataLine SOURCE;
    private boolean isPlaying;
    private byte[] voice = new byte[HZ * BITS / 8 * MONO];

    public VoicePlayer() throws LineUnavailableException {
        this.isPlaying = true;

        AudioFormat linear = new AudioFormat(HZ, BITS, MONO, true, false);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, linear);

        this.SOURCE = (SourceDataLine) AudioSystem.getLine(info);
        this.SOURCE.open(linear);
        this.SOURCE.start();
    }

    public void run() {
        while (true) {
            if (!this.isPlaying) return;

            this.SOURCE.write(this.voice, 0, this.voice.length);
        }
    }

    public void setVoice(byte[] voice) {
        this.voice = voice;
    }

    public void end() {
        this.isPlaying = false;
        this.SOURCE.stop();
        this.SOURCE.close();
    }

    public boolean getIsPlaying() {
        return this.isPlaying;
    }
}