package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MazeArgumentsForInit;
import notifications.GenerateMazeNotification;
import notifications.ObservableNotification;

import java.util.concurrent.Callable;

/**
 * Generates maze by cliens requests, runs as thread, returns maze
 * @author Artiom,Yoav
 */
public class GenerateMazeCall implements Callable<Maze3d>
{
    private final MazeArgumentsForInit mazeArgumentsForInit;
    private final MazeModel mazeModel;

    public GenerateMazeCall(MazeArgumentsForInit mazeArgumentsForInit, MazeModel mazeModel)
    {
        this.mazeArgumentsForInit = mazeArgumentsForInit;
        this.mazeModel = mazeModel;
    }

    @Override
    public Maze3d call() throws Exception
    {
        Maze3dGenerator mazeGenerator = mazeModel.getMazeGenerator();

        Maze3d maze3d = mazeGenerator.generate(mazeArgumentsForInit);

        ObservableNotification<Maze3d> notification = mazeModel.<Maze3d>getNotification();
        notification.setData(maze3d);

        mazeModel.notifyObservers(notification);

        return maze3d;
    }
}
