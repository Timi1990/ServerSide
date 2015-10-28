package commands;

import algorithms.mazeGenerators.Maze3d;
import model.IModel;
import notifications.DisplayCrossSelectionNotification;

import java.util.List;

/**
 * Displays cross selection by client's parameters via server model
 * @author  Artiom,Yoav
 */
public class DisplayCrossSelectionCommand implements Command
{
    private final IModel model;

    public DisplayCrossSelectionCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {

        String axis = args.get(0);
        Integer index = Integer.decode(args.get(1));
        String name = args.get(2);

        Maze3d maze3d = model.getMazeByName(name);
        DisplayCrossSelectionNotification displayCrossSelectionNotification = new DisplayCrossSelectionNotification(maze3d,index,axis);
        model.setNotification(displayCrossSelectionNotification);

        model.getCrossSelectionBy(axis, name, index);
    }
}
