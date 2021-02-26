import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DaytimeClient {
    private static final int PORT = 13;

    public static void main(String[] args) {
        Socket socket = null;

        try {
            String host = args[0];
            socket = new Socket(host, (PORT));
            System.out.println("Connected " + host + ":" + PORT);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ignored) {
            }
            System.out.println("切断されました" + (socket != null ? socket.getRemoteSocketAddress() : ""));
        }
    }
}