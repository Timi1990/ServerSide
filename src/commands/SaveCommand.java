package commands;

import model.IModel;

import java.io.IOException;
import java.util.List;


public class SaveCommand implements Command
{
    private final IModel model;

    public SaveCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {
        String mazeName = args.get(0);
        String filePath = args.get(1);

        try
        {
            model.save(filePath, mazeName);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
