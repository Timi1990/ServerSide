package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The server class, runs when commanded, using sockets connection.
 * Runs all the time, waiting for connection.
 * @param port
 * @param number of clients
 * @author  Artiom,Yoav
 */
public class MyTCPIPServer {

    private final int port;
    private ServerSocket server;
    private final int numOfClients;
    private Thread thread;
    private Boolean killServer = true;
    private ClientHandler clientHandler;
    private ExecutorService threadPool;

    public MyTCPIPServer(int port, int numOfClients) {
        this.port = port;
        this.numOfClients = numOfClients;
    }

    public void start() throws IOException {

        System.out.println("Server started");
        server = new ServerSocket(port);
        threadPool = Executors.newFixedThreadPool(numOfClients);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(killServer)
                {
                    try {
                        final Socket socket = server.accept();
                        if(socket!=null)
                        {
                            threadPool.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println("Connection established");
                                    clientHandler = new MazeHandler(socket);
                                    clientHandler.handleClient();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
    public void stop()
    {
        System.out.println("Disconnecting...");
        killServer = false;
        threadPool.shutdown();
        System.exit(1);
    }
}
