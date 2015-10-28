package notifications;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.IModel;

import java.io.Serializable;

/**
 * Holds the maze which needs to be solved, also the solution thats being calculated in server
 * @author  Artiom,Yoav
 */

public class SolveMazeNotification extends ObservableNotification<Solution> implements Serializable
{

    private final Maze3d maze3d;
    private final Position startPosition;
    private Solution solution;

    public SolveMazeNotification(Maze3d maze3d, Position startPosition)
    {
        this.maze3d = maze3d;
        this.startPosition = startPosition;
    }

    @Override
    public void apply()
    {
        try
        {
            model.setNotification(this);
            model.solve(maze3d,startPosition);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void print()
    {
        System.out.println("Maze: " + maze3d.toString() + " solved with " + model.getAlgorithm().toString());
    }

    @Override
    public void init(IModel model)
    {
        this.model = model;
    }

    @Override
    public Solution getData()
    {
        return solution;
    }

    @Override
    public void setData(Solution solution)
    {
        this.solution = solution;
    }

}
