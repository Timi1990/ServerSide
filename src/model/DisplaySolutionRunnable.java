package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import notifications.DisplaySolutionNotification;

public class DisplaySolutionRunnable implements Runnable
{
    private final MazeModel model;
    private final String mazeName;

    public DisplaySolutionRunnable(MazeModel model, String mazeName)
    {
        this.model = model;
        this.mazeName = mazeName;
    }

    @Override
    public void run()
    {
        Maze3d maze = model.getMazeByName(mazeName);
        Position position = maze.getStartPosition();
        Tuple<Maze3d,Position> tuple = new Tuple<>(maze,position);
        Solution solution = model.getSolutionByTuple(tuple);
        DisplaySolutionNotification displaySolutionNotification = new DisplaySolutionNotification(solution);

        displaySolutionNotification.setData(solution);

        model.notifyObservers(displaySolutionNotification);
    }
}
