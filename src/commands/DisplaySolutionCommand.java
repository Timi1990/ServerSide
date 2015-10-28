package commands;

import model.IModel;

import java.util.List;

/**
 * Displays solution requested by client via server model
 * @author  Artiom,Yoav
 *
 */

public class DisplaySolutionCommand implements Command
{
    private final IModel model;

    public DisplaySolutionCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {
        String name = args.get(0);

        model.displaySolution(name);
    }
}
