package model;

import algorithms.mazeGenerators.Maze3d;
import io.MyCompressorOutputStream;
import notifications.SaveMazeNotification;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Saves chosen maze by client to a specific file (in bytes)
 * @author  Artiom,Yoav
 */
public class SaveMazeRunnable implements Runnable
{
    private final String mazeName;
    private final MazeModel model;
    private final String filePath;

    public SaveMazeRunnable(String filePath, String mazeName, MazeModel model)
    {
        this.filePath = filePath;
        this.mazeName = mazeName;
        this.model = model;
    }

    @Override
    public void run()
    {
        try
        {
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(filePath));
            Maze3d maze = model.getMazeByName(mazeName);

            out.write(maze.toByteArray());

            out.close();
            model.notifyObservers(new SaveMazeNotification(mazeName));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
