package model;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.Maze3d;
import notifications.DisplayCrossSelectionNotification;

/**
 * Displays mazes cross selection by axis, runs as thread
 * @author  Artiom,Yoav
 */
public class DisplayCrossSelectionRunnable implements Runnable
{
    private final MazeModel model;
    private final Maze3d maze;
    private final String axis;
    private final Integer index;

    public DisplayCrossSelectionRunnable(MazeModel model, String axis, Maze3d maze, Integer index)
    {
        this.model = model;
        this.maze = maze;
        this.axis = axis;
        this.index = index;
    }

    @Override
    public void run()
    {
        try
        {
            int[][] crossSelectionBy = getCrossSelectionBy(axis, maze, index);

            DisplayCrossSelectionNotification notification = new DisplayCrossSelectionNotification(maze,index,axis);
            notification.setData(crossSelectionBy);

            model.notifyObservers(notification);
        }
        catch (IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Private method, returns cross selection
     * @param axis
     * @param maze3d
     * @param index
     * @throws IndexOutOfBoundsException
     * @throws algorithms.mazeGenerators.IndexOutOfBoundsException
     * @author  Artiom,Yoav
     */
    private int[][] getCrossSelectionBy(String axis, Maze3d maze3d, Integer index) throws IndexOutOfBoundsException, algorithms.mazeGenerators.IndexOutOfBoundsException {
        switch (axis)
        {
            case "X":
            {
                return maze3d.getCrossSectionByX(index);
            }
            case "Y":
            {
                return maze3d.getCrossSectionByY(index);
            }
            case "Z":
            {
                return maze3d.getCrossSectionByZ(index);
            }
        }
        throw new IndexOutOfBoundsException(axis);
    }
}
