package model;

import algorithms.mazeGenerators.Maze3d;
import notifications.DisplayMazeNotification;

/**
 * Displays the maze via thread
 * @author Artiom,Yoav
 */
public class DisplayMazeRunnable implements Runnable
{
    private final String mazeName;
    private final MazeModel model;

    public DisplayMazeRunnable(String mazeName, MazeModel model)
    {
        this.mazeName = mazeName;
        this.model = model;
    }

    @Override
    public void run()
    {
        Maze3d maze = model.getMazeByName(mazeName);

        DisplayMazeNotification displayMazeNotification = new DisplayMazeNotification(maze);

        model.notifyObservers(displayMazeNotification);
    }
}
