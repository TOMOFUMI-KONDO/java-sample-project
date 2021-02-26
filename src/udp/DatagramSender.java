package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class DatagramSender {
    private static final int SERVER_PORT = 10007;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        InetSocketAddress remoteAddress = new InetSocketAddress(host, SERVER_PORT);

        try (DatagramSocket socket = new DatagramSocket()) {
            BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while ((message = keyIn.readLine()).length() > 0) {
                byte[] buf = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, remoteAddress);
                socket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}