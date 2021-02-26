package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class DaytimeClient {
    private static final int SERVER_PORT = 10008;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        InetSocketAddress remoteAddress = new InetSocketAddress(host, SERVER_PORT);
        byte[] receivedBuf = new byte[PACKET_SIZE];
        DatagramPacket receivedPacket = new DatagramPacket(receivedBuf, receivedBuf.length);

        try (DatagramSocket socket = new DatagramSocket()) {
            // 5秒間レスポンスが帰ってこなかったらエラーを吐く
            socket.setSoTimeout(5000);

            byte[] buf = new byte[0];
            DatagramPacket packet = new DatagramPacket(buf, buf.length, remoteAddress);
            socket.send(packet);

            socket.receive(receivedPacket);
            String message = new String(receivedBuf, 0, receivedPacket.getLength());
            System.out.println(receivedPacket.getSocketAddress() + "受信:" + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}