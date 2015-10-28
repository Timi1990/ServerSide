package model;

import algorithms.demo.Maze3dDomain;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import notifications.ObservableNotification;

import java.util.concurrent.Callable;


/**
 * Solves a specific maze according to client's requests in a thread
 * @author Artiom,Yoav
 */
public class SolveMazeCall implements Callable<Solution>
{
    private final Maze3d maze3d;
    private final MazeModel model;
    private final Position startPosition;

    public SolveMazeCall(Maze3d maze3d, MazeModel model, Position startPosition) {
        this.maze3d = maze3d;
        this.model = model;
        this.startPosition = startPosition;
    }


    @Override
    public Solution call() throws Exception
    {

        Maze3dDomain  maze3dDomain = new Maze3dDomain(maze3d);
        maze3dDomain.setStartState(startPosition);

        Searcher searcher = model.getAlgorithm();
        searcher.clean();

        Solution solution = searcher.search(maze3dDomain);

        ObservableNotification<Solution> notification = model.<Solution>getNotification();

        notification.setData(solution);
        model.notifyObservers(notification);

        return solution;
    }
}
