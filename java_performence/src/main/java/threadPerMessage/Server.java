package threadPerMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;

/**
 * @ClassName Server
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/3 9:59
 * @Version 1.0
 **/
public class Server {

    private static int DEFAULT_PORT = 12345;
    private static ServerSocket server;

    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    public static void start(int port) throws IOException {
        if (server != null) {
            return;
        }

        try {
            server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                Instant start = Instant.now();
                new Thread(new ServerHandler(socket)).start();
                System.out.println(Duration.between(start, Instant.now()).toMillis());
            }
        } finally {
            if (server != null) {
                server.close();
                System.out.println("服务器已关闭");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
