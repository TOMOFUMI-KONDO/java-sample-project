import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DaytimeClient {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            String host = args[0];
            int port = args.length > 1 ? Integer.parseInt(args[1]) : 13;

            socket = new Socket(host, (port));
            System.out.println("Connected " + host + ":" + port);

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