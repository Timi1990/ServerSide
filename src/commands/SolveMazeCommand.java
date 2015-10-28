package commands;

import algorithms.mazeGenerators.Maze3d;
import model.IModel;
import notifications.SolveMazeNotification;

import java.util.List;

/**
 * Solves a specific maze chosen by client via server model
 * @author  Artiom,Yoav
 */
public class SolveMazeCommand implements Command
{
    private final IModel model;

    public SolveMazeCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {
        String name = args.get(0);

        Maze3d maze3d = model.getMazeByName(name);
        SolveMazeNotification solveMazeNotification = new SolveMazeNotification(maze3d,maze3d.getStartPosition());
        model.setNotification(solveMazeNotification);
        model.solve(maze3d,maze3d.getStartPosition());
    }
}
