package voiceTransfer.receiver;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class VoiceReceiver {
    private static final int HZ = 8000;
    private static final int BITS = 16;
    private static final int MONO = 1;

    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) throws LineUnavailableException, IOException {
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 10007;
        byte[] buffer = new byte[PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        VoicePlayer player = new VoicePlayer(HZ, BITS, MONO);

        DatagramSocket socket = new DatagramSocket(port);
        System.out.println("VoiceReceiverが起動しました。(" + socket.getLocalPort() + ")");

        int count = 0;
        while (true) {
            socket.receive(packet);
            player.play(buffer);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count++;
            if (count > 10 * 100) {
                player.end();
                break;
            }
        }

        System.out.println("VoiceReceiverが終了しました。");
    }
}