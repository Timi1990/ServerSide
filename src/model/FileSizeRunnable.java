package model;

import algorithms.mazeGenerators.Maze3d;
import notifications.FileSizeNotification;

/**
 * Displays and calculates the maze size in a thread
 * @author Artiom,Yoav
 */
public class FileSizeRunnable implements Runnable
{
    private final MazeModel model;
    private final String mazeName;

    public FileSizeRunnable(MazeModel model, String mazeName)
    {
        this.model = model;
        this.mazeName = mazeName;
    }

    @Override
    public void run()
    {

        Maze3d maze3d = model.getMazeByName(mazeName);
        Long size = Long.valueOf(maze3d.toByteArray().length);
        FileSizeNotification fileSizeNotification = new FileSizeNotification( mazeName);
        fileSizeNotification.setData(size);
        model.notifyObservers(fileSizeNotification);
    }
}
