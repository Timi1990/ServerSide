package notifications;

import algorithms.mazeGenerators.Maze3d;
import model.IModel;

import java.io.Serializable;

/**
 * Holds the maze, generated via server's model
 * @author  Artiom,Yoav
 */
public class GenerateMazeNotification extends ObservableNotification<Maze3d> implements Serializable
{
    private final String mazeName;
    private final int dimension;
    private final int rows;
    private final int columns;
    private Maze3d maze;


    public GenerateMazeNotification(String mazeName, int dimension, int rows, int columns)
    {
        this.mazeName = mazeName;
        this.dimension = dimension;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void apply()
    {
        try
        {
            model.setNotification(this);
            model.generateMaze(mazeName,dimension,rows,columns);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void print() {
        System.out.println("Maze : "+mazeName+" is ready.");
    }

    @Override
    public void init(IModel model) {
        this.model = model;
    }

    @Override
    public Maze3d getData()
    {
        return maze;
    }

    @Override
    public void setData(Maze3d data)
    {
        this.maze = data;
    }

}
