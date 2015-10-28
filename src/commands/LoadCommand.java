package commands;

import model.IModel;

import java.util.List;


/**
 * Loads a spesific maze chosen by client via server model
 */
public class LoadCommand implements Command
{
    private final IModel model;

    public LoadCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {
        String fileName = args.get(0);
        String mazeName = args.get(1);

        model.load(fileName, mazeName);
    }
}
