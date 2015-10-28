package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import notifications.ObservableNotification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


/**
 * Model interface (server side), includes all calculation methods related to mazes and algorithms
 * @author  Artiom,Yoav
 */
public interface IModel {

    <T> void setNotification(ObservableNotification<T> notification);

    <T> ObservableNotification getNotification();

    void generateMaze(String mazeName, Integer dimension, Integer rows, Integer columns) throws Exception;

    void displayMaze(String mazeName) throws Exception;

    Maze3d getMazeByName(String mazeName);

    Solution getSolutionByTuple(Tuple<Maze3d, Position> maze);

    void getCrossSelectionBy(String axis, String mazeName, Integer index) throws Exception;

    void getCrossSelectionBy(Maze3d maze, Integer index, String axis) throws Exception;

    void save(String filePath, String mazeName) throws Exception;

    void load(String filePath, String mazeName) throws Exception;

    void putMazeAndName(String mazeName, Maze3d maze);

    void solve(Maze3d maze3d, Position startPosition) throws Exception;

    Searcher getAlgorithm();

    void displaySolution(String mazeName) throws Exception;

    void fileSize(String mazeName) throws Exception;

    void saveSolutionsBeforeExit(String path) throws IOException;

    void loadSolutionsForMazes(String filePath) throws IOException, ClassNotFoundException;

    Maze3dGenerator getMazeGenerator();

    void setMazeGenerator(Maze3dGenerator mazeGenerator);

    void setSearcher(Searcher searcher);

    void setProperties(String filePath) throws FileNotFoundException;
}
