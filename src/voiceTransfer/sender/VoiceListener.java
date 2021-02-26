package voiceTransfer.sender;

import javax.sound.sampled.*;
import java.io.IOException;

public class VoiceListener {
    private static final int HZ = 8000;
    private static final int BITS = 16;
    private static final int MONO = 1;

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 10007;
        byte[] voice = new byte[HZ * BITS / 8 * MONO];

        VoiceSender sender = null;
        try {
            sender = new VoiceSender(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioFormat linear = new AudioFormat(HZ, BITS, MONO, true, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, linear);

        TargetDataLine target = null;
        try {
            target = (TargetDataLine) AudioSystem.getLine(info);
            target.open(linear);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        assert target != null;
        target.start();

        System.out.println("音声入力を開始しました。(" + host + ":" + port + ")");

        AudioInputStream stream = new AudioInputStream(target);

        int count = 0;
        while (true) {
            try {
                stream.read(voice, 0, voice.length);
                sender.send(voice);
                Thread.sleep(100);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            count++;
            if (count > 10 * 10) {
                target.stop();
                target.close();
                break;
            }
        }

        System.out.println("音声入力を終了しました。");
    }
}
