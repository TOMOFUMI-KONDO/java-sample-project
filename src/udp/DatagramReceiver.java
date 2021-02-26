package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramReceiver {
    private static final int SERVER_PORT = 10007;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {
        byte[] buf = new byte[PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        try (DatagramSocket socket = new DatagramSocket(SERVER_PORT)) {
            System.out.println("DatagramReceiverが起動しました(" + socket.getLocalPort() + ")");

            while (true) {
                socket.receive(packet);
                String message = new String(buf, 0, packet.getLength());
                System.out.println(packet.getSocketAddress() + "受信" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}