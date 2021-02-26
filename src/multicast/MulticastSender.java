package multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender {
    private static final int ECHO_PORT = 10007;
    private static final String MULTICAST_ADDRESS = "224.0.1.1";

    public static void main(String[] args) {

        try (MulticastSocket socket = new MulticastSocket()) {
            InetAddress multicastAddress = InetAddress.getByName(MULTICAST_ADDRESS);
            BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while ((message = keyIn.readLine()).length() > 0) {
                byte[] bytes = message.getBytes();
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length, multicastAddress, ECHO_PORT);
                socket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}