package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DaytimeServer {
    private static final int SERVER_PORT = 10008;
    private static final int PACKET_SIZE = 1024;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒");

    public static void main(String[] args) {
        byte[] receivedBuf = new byte[PACKET_SIZE];
        DatagramPacket receivedPacket = new DatagramPacket(receivedBuf, receivedBuf.length);

        try (DatagramSocket socket = new DatagramSocket(SERVER_PORT)) {
            System.out.println("udp.DaytimeServerが起動しました(" + socket.getLocalPort() + ")");

            while (true) {
                socket.receive(receivedPacket);
                System.out.println(receivedPacket.getSocketAddress() + "受信");
                InetSocketAddress clientAddress = new InetSocketAddress(receivedPacket.getAddress(), receivedPacket.getPort());

                LocalDateTime now = LocalDateTime.now();
                String formattedNow = now.format(FORMATTER);

                byte[] buf = formattedNow.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, clientAddress);

                socket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}