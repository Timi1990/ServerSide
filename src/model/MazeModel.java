package model;

import algorithms.mazeGenerators.*;
import algorithms.search.*;
import boot.GlobalThreadPool;
import networking.ServerData;
import notifications.LoadSolutionsNotification;
import notifications.ObservableNotification;
import notifications.SaveSolutionsNotification;

import java.beans.XMLDecoder;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * MazeModel implementation for IModel, implements all methods, runs all of them in seperated threads.
 * @author  Artiom,Yoav
 */
public class MazeModel extends Observable implements IModel{

    private HashMap<Tuple<Maze3d, Position>, Solution> mazeAndSolution = new HashMap<Tuple<Maze3d, Position>, Solution>();
    private HashMap<String, Maze3d> mazeAndName = new HashMap<String, Maze3d>();
    private Maze3dGenerator mazeGenerator;
    private Searcher searcher;
    private ObservableNotification notification;


    @Override
    public <T> void setNotification(ObservableNotification<T> notification) {
        this.notification = notification;
    }

    @Override
    public <T> ObservableNotification<T> getNotification() {
        return notification;
    }

    /**
     * Generates maze from recieved parameters,using cache
     * @param mazeName
     * @param dimension
     * @param rows
     * @param columns
     * @throws Exception
     */
    @Override
    public void generateMaze(String mazeName, Integer dimension, Integer rows, Integer columns) throws Exception {
        setChanged();

        if (mazeAndName.containsKey(mazeName))
        {
            Maze3d maze3d = mazeAndName.get(mazeName);

            ObservableNotification<Object> objectObservableNotification = getNotification();

            objectObservableNotification.setData(maze3d);
            notifyObservers(objectObservableNotification);

        } else
        {
            MazeArgumentsForInit mazeArgumentsForInit = new MazeArgumentsForInit(dimension, rows, columns);

            GenerateMazeCall generateMazeCall = new GenerateMazeCall(mazeArgumentsForInit, this);

            Future<Maze3d> future = GlobalThreadPool.getInstance().addCallableToPool(generateMazeCall);

            Maze3d maze = future.get();

            mazeAndName.put(mazeName, maze);

        }
    }

    /**
     * Displays the maze requested by name
     * @param mazeName
     * @throws Exception
     */
    @Override
    public void displayMaze(String mazeName) throws Exception {
        setChanged();

        DisplayMazeRunnable displayMazeRunnable = new DisplayMazeRunnable(mazeName, this);

        Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(displayMazeRunnable);

        future.get();
    }

    @Override
    public Maze3d getMazeByName(String mazeName) {
        return mazeAndName.get(mazeName);
    }


    @Override
    public Solution getSolutionByTuple(Tuple<Maze3d, Position> maze) {
        if(mazeAndSolution.containsKey(maze))
        {
            return mazeAndSolution.get(maze);
        }
        else
            System.out.println("zain");
        return null;
    }

    /**
     * Returns cross selection as requested
     * @param axis
     * @param mazeName
     * @param index
     * @throws Exception
     */
    @Override
    public void getCrossSelectionBy(String axis, String mazeName, Integer index) throws Exception {
        setChanged();

        if (mazeAndName.containsKey(mazeName))
        {
            Maze3d maze = getMazeByName(mazeName);

            DisplayCrossSelectionRunnable displayCrossSelectionRunnable = new DisplayCrossSelectionRunnable(this, axis, maze, index);

            Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(displayCrossSelectionRunnable);

            future.get();
        }
    }

    @Override
    public void getCrossSelectionBy(Maze3d maze, Integer index, String axis) throws Exception {
        setChanged();

        DisplayCrossSelectionRunnable displayCrossSelectionRunnable = new DisplayCrossSelectionRunnable(this, axis, maze, index);

        Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(displayCrossSelectionRunnable);

        future.get();
    }

    /**
     * Saves specific maze to given filePath (in bytes)
     * @param filePath
     * @param mazeName
     * @throws Exception
     */
    @Override
    public void save(String filePath, String mazeName) throws Exception {
        setChanged();

        if (mazeAndName.containsKey(mazeName))
        {
            SaveMazeRunnable saveMazeRunnable = new SaveMazeRunnable(filePath, mazeName, this);

            Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(saveMazeRunnable);

            future.get();
        }
    }

    /**
     * Loads a specific maze from a given file path (in bytes)
     * @param filePath
     * @param mazeName
     * @throws Exception
     */
    @Override
    public void load(String filePath, String mazeName) throws Exception {
        setChanged();

        LoadMazeRunnable loadMazeRunnable = new LoadMazeRunnable(this, filePath, mazeName);

        Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(loadMazeRunnable);

        future.get();
    }

    @Override
    public void putMazeAndName(String mazeName, Maze3d maze) {
        mazeAndName.put(mazeName, maze);
    }

    /**
     * Solves maze by chosen searching algorithm, using cache.
     * @param maze3d
     * @param startPosition
     * @throws Exception
     */
    @Override
    public void solve(Maze3d maze3d,Position startPosition) throws Exception {
        setChanged();
        Tuple<Maze3d, Position> maze3dPositionTuple = new Tuple<>(maze3d, startPosition);

            if (mazeAndSolution.containsKey(maze3dPositionTuple))
            {
                Solution solution = mazeAndSolution.get(maze3dPositionTuple);

                ObservableNotification<Object> notification = getNotification();
                notification.setData(solution);
                notifyObservers(notification);
            } else
            {
                SolveMazeCall solveMazeCall = new SolveMazeCall(maze3d,this,startPosition);

                Future<Solution> future = GlobalThreadPool.getInstance().addCallableToPool(solveMazeCall);

                Solution solution = future.get();

                mazeAndSolution.put(maze3dPositionTuple, solution);
            }

    }

    @Override
    public Searcher getAlgorithm() {
        return searcher;
    }


    /**
     * Displays the solution for the specific maze
     * @param mazeName
     * @throws Exception
     */
    @Override
    public void displaySolution(String mazeName) throws Exception {
        setChanged();

        DisplaySolutionRunnable displaySolutionRunnable = new DisplaySolutionRunnable(this, mazeName);

        Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(displaySolutionRunnable);

        future.get();
    }

    /**
     * Calculates file size in memory (long)
     * @param mazeName
     * @throws Exception
     */
    @Override
    public void fileSize(String mazeName) throws Exception {
        setChanged();

        if (mazeAndName.containsKey(mazeName)) {
            FileSizeRunnable fileSizeRunnable = new FileSizeRunnable(this, mazeName);

            Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(fileSizeRunnable);

            future.get();
        }
    }

    /**
     * Saves solutions to a GZ. file before exit
     * @param path
     * @throws IOException
     */
    @Override
    public void saveSolutionsBeforeExit(String path) throws IOException {

            ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(path)));

            out.writeObject(mazeAndSolution);
            out.flush();
            out.close();
        setChanged();
        notifyObservers(new SaveSolutionsNotification(path));
    }

    /**
     * Loads solutions from a GZ. file
     * @param filePath
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void loadSolutionsForMazes(String filePath) throws IOException, ClassNotFoundException {

        ObjectInputStream in = new ObjectInputStream(new GZIPInputStream((new FileInputStream(filePath))));

        mazeAndSolution.putAll((HashMap<Tuple<Maze3d,Position>, Solution>) in.readObject());

        setChanged();
        notifyObservers(new LoadSolutionsNotification(filePath));



    }


    @Override
    public Maze3dGenerator getMazeGenerator() {
        return mazeGenerator;
    }

    @Override
    public void setMazeGenerator(Maze3dGenerator mazeGenerator) {
        this.mazeGenerator = mazeGenerator;
    }

    @Override
    public void setSearcher(Searcher searcher) {
        this.searcher = searcher;
    }

    /**
     * Sets server's properties, such as: port, number of clients etc. via XML
     * @param filePath
     * @throws FileNotFoundException
     */
    @Override
    public void setProperties(String filePath) throws FileNotFoundException {
        setChanged();
        XMLDecoder xmlDecoder = null;
        try
        {
            xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        Properties properties = (Properties) xmlDecoder.readObject();

        xmlDecoder.close();


        List<String> propertiesList = properties.getPropertiesList();

        HashMap<String, Searcher> searcherFactory = new HashMap<String, Searcher>();
        HashMap<String, Maze3dGenerator> generatorsFactory = new HashMap<String, Maze3dGenerator>();

        searcherFactory.put("BFS", new BFS());
        searcherFactory.put("Astar", new Astar(new MazeManhattanDistance()));
        generatorsFactory.put("Simple maze generator", new SimpleMaze3dGenerator());
        generatorsFactory.put("My maze generator", new MyMaze3dGenerator());


        int port = Integer.parseInt(propertiesList.get(0));
        String host = propertiesList.get(1);
        int numOfClients = Integer.parseInt(propertiesList.get(2));

        try {
            ServerData.getInstance().setServer(port,host,numOfClients);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer numOfThreads = Integer.decode(propertiesList.get(3));
        GlobalThreadPool.getInstance().setAndCreateNumOfThreads(numOfThreads);
        String generator = propertiesList.get(4);
        String searcherName = propertiesList.get(5);



        if (generatorsFactory.containsKey(generator) && searcherFactory.containsKey(searcherName))
        {
            setMazeGenerator(generatorsFactory.get(generator));

            setSearcher(searcherFactory.get(searcherName));
        }
    }
}
