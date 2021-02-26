package voiceTransfer.sender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class VoiceSender {
    private final InetSocketAddress ADDRESS;
    private final DatagramSocket SOCKET;

    public VoiceSender(String host, int port) throws IOException {
        this.ADDRESS = new InetSocketAddress(host, port);
        this.SOCKET = new DatagramSocket();
    }


    public void send(byte[] voice) {
        DatagramPacket packet = new DatagramPacket(voice, voice.length, this.ADDRESS);

        try {
            this.SOCKET.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}