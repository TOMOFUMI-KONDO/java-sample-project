import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MultiDaytimeServer {
    private static final int PORT = 10008;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("MultiDaytimeServerが起動しました(" + serverSocket.getLocalPort() + ")");

            while (true) {
                Socket socket = serverSocket.accept();
                new EchoDaytimeThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class EchoDaytimeThread extends Thread {
    Socket SOCKET;
    final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public EchoDaytimeThread(Socket socket) {
        this.SOCKET = socket;
        System.out.println("接続を開始しました。" + socket.getRemoteSocketAddress());
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(SOCKET.getOutputStream(), true);

            LocalDateTime now = LocalDateTime.now();
            String formattedNow = now.format(this.FORMATTER);

            System.out.println("送信:" + formattedNow);
            out.println(formattedNow);
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