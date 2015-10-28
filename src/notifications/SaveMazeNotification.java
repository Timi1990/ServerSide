package notifications;

import model.IModel;

import java.io.Serializable;

/**
 * Holds maze's name and file path to save
 * @author  Artiom,Yoav
 */

public class SaveMazeNotification extends ObservableNotification<String> implements Serializable
{
    private final String mazeName;
    private String filePath;

    public SaveMazeNotification(String mazeName)
    {
        this.mazeName = mazeName;
    }

    @Override
    public void apply()
    {
        model.setNotification(this);
    }

    @Override
    public void print()
    {
        System.out.println("Maze " + mazeName + " Saved");
    }

    @Override
    public void init(IModel model)
    {
        this.model = model;
    }


    @Override
    public void setData(String data) {
        this.filePath = data;
    }

    @Override
    public String getData() {
        return filePath;
    }
}
