import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final int ECHO_PORT = 10007;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(ECHO_PORT); Socket socket = serverSocket.accept()) {
            System.out.println("EchoServerが起動しました(port=" + serverSocket.getLocalPort() + ")");
            System.out.println("接続されました" + socket.getRemoteSocketAddress());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("受信" + line);
                String message = "from tomo. " + line;
                out.println(message);
                System.out.println("送信" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}