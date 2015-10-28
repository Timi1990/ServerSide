package networking;

import java.io.IOException;

/**
 * Server's static data
 * @author  Artiom,Yoav
 */
public class ServerData {
    private static ServerData ourInstance = new ServerData();
    private int port,numOfClients;
    private String host;

    public static ServerData getInstance() {
        return ourInstance;
    }

    private ServerData() {
    }

    public void setServer(int port,String host,int numOfClients) throws IOException {
        this.port = port;
        this.host = host;
        this.numOfClients = numOfClients;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }
    public int getClients()
    {
        return numOfClients;
    }


}
