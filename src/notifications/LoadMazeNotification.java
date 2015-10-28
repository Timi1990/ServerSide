package notifications;

import model.IModel;

import java.io.Serializable;

/**
 * Holds the file path which the maze to be loaded from
 * @author  Artiom,Yoav
 */

public class LoadMazeNotification extends ObservableNotification<String> implements Serializable
{
    private final String mazeName;
    private String filePath;

    public LoadMazeNotification(String mazeName)
    {
        this.mazeName = mazeName;
    }

    @Override
    public void apply() {

    }

    @Override
    public void print() {

    }

    @Override
    public void init(IModel model) {
        this.model = model;
    }


    @Override
    public String getData() {
        return filePath;
    }

    @Override
    public void setData(String data) {
        this.filePath = data;
    }
}
