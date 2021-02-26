package voiceTransfer.receiver;

import javax.sound.sampled.*;

public class VoicePlayer {
    private byte[] voice;

    private SourceDataLine source;

    public VoicePlayer(int hz, int bits, int mono) throws LineUnavailableException {
        voice = new byte[hz * bits / 8 * mono];

        AudioFormat linear = new AudioFormat(hz, bits, mono, true, false);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, linear);

        this.source = (SourceDataLine) AudioSystem.getLine(info);
        this.source.open(linear);
        this.source.start();
    }

    public void play(byte[] bytes) {
        this.voice = bytes;
        this.source.write(this.voice, 0, this.voice.length);
    }

    public void end() {
        source.stop();
        source.close();
    }
}