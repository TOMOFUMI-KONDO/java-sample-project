import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiEchoServer {
    public static final int ECHO_PORT = 10007;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(ECHO_PORT)) {
            System.out.println("EchoServerが起動しました(port=" + serverSocket.getLocalPort() + ")");

            while (true) {
                Socket socket = serverSocket.accept();
                new EchoThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class EchoThread extends Thread {
    private final Socket SOCKET;

    public EchoThread(Socket socket) {
        this.SOCKET = socket;
        System.out.println("接続されました" + socket.getRemoteSocketAddress());
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.SOCKET.getInputStream()));
            PrintWriter out = new PrintWriter(this.SOCKET.getOutputStream(), true);

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("受信" + line);
                String message = "from tomo. " + line;
                out.println(message);
                System.out.println("送信" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (SOCKET != null) {
                    SOCKET.close();
                }
            } catch (IOException ignored) {
            }

            System.out.println("切断されました" + (SOCKET != null ? SOCKET.getRemoteSocketAddress() : ""));
        }
    }
}