package networking;

import notifications.ObservableNotification;

/**
 * Global interface for communicating and data transfer between server and clients
 */
public interface ClientHandler {

    void handleClient() throws Exception;

    void toClient(ObservableNotification observableNotification);

}
