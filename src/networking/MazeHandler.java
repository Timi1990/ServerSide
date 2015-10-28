package networking;

import notifications.ObservableNotification;
import presenter.Presenter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Local implementation for client handler, translates the stream's output and input, in charge
 * of the streams flow.
 * @author  Artiom,Yoav
 */
public class MazeHandler implements ClientHandler {
    private final Presenter presenter;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public MazeHandler(Socket socket) {
        this.presenter = new Presenter(this);
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleClient() throws Exception {
        Object readObj;
        while(true){
            readObj = in.readObject();
            presenter.handleData(readObj);
        }
    }

    @Override
    public void toClient(ObservableNotification observableNotification) {
        try {
            out.writeObject(observableNotification);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

