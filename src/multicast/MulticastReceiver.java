package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver {
    private static final int ECHO_PORT = 10007;
    private static final int PACKET_SIZE = 1024;
    private static final String MULTICAST_ADDRESS = "224.0.1.1";

    public static void main(String[] args) {
        byte[] buf = new byte[PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        try (MulticastSocket socket = new MulticastSocket(ECHO_PORT)) {
            InetAddress multicastAddress = InetAddress.getByName(MULTICAST_ADDRESS);
            socket.joinGroup(multicastAddress);
            System.out.println("MulticastReceiverを起動しました(" + "address=" + multicastAddress + ",port=" + socket.getLocalPort() + ")");

            while (true) {
                socket.receive(packet);
                String message = new String(buf, 0, packet.getLength());
                System.out.println(packet.getSocketAddress() + ":" + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}