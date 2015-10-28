package boot;

import model.MazeModel;
import networking.MyTCPIPServer;
import networking.ServerData;
import presenter.Presenter;
import view.ViewCLI;

import java.io.IOException;
import java.util.Scanner;


/**
 * Main class. initializes server's properties, MVP, and finally, starts the server via CLI.
 * @author Artiom,Yoav
 */
public class Run {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Enter 'Start' to start the server, 'Close' to exit");
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        if(action.toLowerCase().equals("start")) {

            MazeModel mazeModel = new MazeModel();
            ViewCLI viewCLI = new ViewCLI();
            Presenter presenter = new Presenter(mazeModel,viewCLI);

            viewCLI.addObserver(presenter);
            mazeModel.addObserver(presenter);

            viewCLI.start();

            MyTCPIPServer server = new MyTCPIPServer(ServerData.getInstance().getPort(),ServerData.getInstance().getClients());
            server.start();
            action = scanner.nextLine();
            if(action.toLowerCase().equals("Close"));
            server.stop();

        }
        else
            System.out.println("Command not found");

        //C:\Users\Timi\Desktop\ServerCLI.txt



    }
}
