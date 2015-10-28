package notifications;

import algorithms.mazeGenerators.Maze3d;
import model.IModel;

import java.io.Serializable;

/**
 * Holds the maze data which needs to be displayed
 * @author  Artiom,Yoav
 */
public class DisplayMazeNotification extends ObservableNotification<Maze3d> implements Serializable
{
    private Maze3d maze;

    public DisplayMazeNotification(Maze3d maze)
    {
        this.maze = maze;
        model = null;
    }


    @Override
    public void apply() {

    }

    @Override
    public void print()
    {
        maze.printMaze();
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
