package model;

import algorithms.mazeGenerators.Maze3d;
import io.MyDecompressorInputStream;
import notifications.LoadMazeNotification;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Loads a specific maze by clients request in a seperate thred
 * @author Artiom,Yoav
 */
public class LoadMazeRunnable implements Runnable
{
    private final MazeModel model;
    private final String filePath;
    private final String mazeName;

    public LoadMazeRunnable(MazeModel model, String filePath, String mazeName)
    {
        this.model = model;
        this.mazeName = mazeName;
        this.filePath = filePath;
    }

    @Override
    public void run()
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStream in = new MyDecompressorInputStream(fileInputStream);

            byte mazeBytes[] = new byte[5000];
            in.read(mazeBytes);
            in.close();

            Maze3d maze3d = new Maze3d(mazeBytes);
            model.putMazeAndName(mazeName, maze3d);

            LoadMazeNotification loadMazeNotification = new LoadMazeNotification(mazeName);
            model.notifyObservers(loadMazeNotification);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
