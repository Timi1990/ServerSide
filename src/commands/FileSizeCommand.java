package commands;

import model.IModel;

import java.util.List;


/**
 * Displays and calculates a specific maze's size via server model
 */
public class FileSizeCommand implements Command
{
    private final IModel model;

    public FileSizeCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {
        String mazeName = args.get(0);

        model.fileSize(mazeName);
    }
}
