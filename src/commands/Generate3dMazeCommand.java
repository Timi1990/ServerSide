package commands;

import model.IModel;
import notifications.GenerateMazeNotification;

import java.util.List;

/**
 * Generates Maze3d object, by parameters chosen by client via server model
 * @author  Artiom,Yoav
 */
public class Generate3dMazeCommand implements Command
{
    private final IModel model;

    public Generate3dMazeCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {
        String mazeName = args.get(0);
        Integer dimension = Integer.decode(args.get(1));
        Integer rows = Integer.decode(args.get(2));
        Integer columns = Integer.decode(args.get(3));

        GenerateMazeNotification generateMazeNotification = new GenerateMazeNotification(mazeName,dimension,rows,columns);
        model.setNotification(generateMazeNotification);
        model.generateMaze(mazeName,dimension,rows,columns);
    }
}
