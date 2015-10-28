package presenter;

import boot.PropertiesPath;
import commands.*;
import model.MazeModel;
import networking.ClientHandler;
import notifications.CommandNotFoundNotification;
import notifications.ObservableNotification;
import view.ViewCLI;

import java.io.FileNotFoundException;
import java.util.*;


/**
 * The presenter class (in MVP), handles various objects recieved from model/view/client handler
 * @author  Artiom,Yoav
 */
public class Presenter implements Observer {

    private  MazeModel model;
    private  ViewCLI view;
    private  ClientHandler clientHandler;
    private  HashMap<String, Command> commandNameToCommand = new HashMap<String,Command>();

    public Presenter(ClientHandler clientHandler) {
        this.model = new MazeModel();
        this.view = new ViewCLI();
        model.addObserver(this);
        view.addObserver(this);
        try {
            model.setProperties(PropertiesPath.getInstance().getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.clientHandler = clientHandler;
        commandNameToCommand.put("Generate", new Generate3dMazeCommand(model));
        commandNameToCommand.put("Display Maze", new DisplayMazeCommand(model));
        commandNameToCommand.put("Display cross selection by", new DisplayCrossSelectionCommand(model));
        commandNameToCommand.put("Save", new SaveCommand(model));
        commandNameToCommand.put("Load", new LoadCommand(model));
        commandNameToCommand.put("File Size", new FileSizeCommand(model));
        commandNameToCommand.put("Solve", new SolveMazeCommand(model));
        commandNameToCommand.put("Display Solution", new DisplaySolutionCommand(model));
    }

    public Presenter(MazeModel model,ViewCLI view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Translates the object sent from client handler into notifications/commands
     * @param object
     */

    public void handleData(Object object)
    {
        if(object instanceof ObservableNotification)
        {
            ObservableNotification observableNotification = (ObservableNotification)object;
            observableNotification.init(model);
            observableNotification.apply();
        }
        else
        {
            String command = (String)object;
            try {
                doCommandIfContains(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Overrides observer's update method, sends object to observables
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o == model) {
                ObservableNotification observableNotification = (ObservableNotification)arg;
                observableNotification.nullModel();
                clientHandler.toClient(observableNotification);
                view.display();

        }
        else if(o == view)
        {
            ObservableNotification observableNotification = (ObservableNotification)arg;
            observableNotification.init(model);
            observableNotification.apply();
        }
    }
    private void doCommandIfContains(final String command) throws Exception {
        Boolean isContains = false;

        for (String commandsName : commandNameToCommand.keySet()) {
            if (command.startsWith(commandsName)) {
                List<String> args = convertToArrayBy(command);

                commandNameToCommand.get(commandsName).doCommand(args);

                isContains = true;
            }
        }

        if (!isContains)
        {
            clientHandler.toClient(new CommandNotFoundNotification());
        }
    }

    private List<String> convertToArrayBy (String command)
    {

        String substring = substring(command);

        String[] split = substring.split("\\s+");
        return Arrays.asList(split);
    }

    private String substring(String command) {
        if(command.contains("<")) {
            int start = command.indexOf('<');
            int end = command.lastIndexOf('>');
            return command.substring(start + 1, end);
        }
        return command;
    }
}
