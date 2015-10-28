package notifications;

import model.IModel;

import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * Holds the file path which has the XML propeties file
 * @author  Artiom,Yoav
 */

public class PropertiesNotification extends ObservableNotification implements Serializable
{
    private Boolean viewCLI = false;
    private final String filePath;

    public PropertiesNotification(String filePath)
    {
        this.filePath = filePath;
    }


    @Override
    public void apply()
    {
        try {
            model.setNotification(this);
            model.setProperties(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void init(IModel model)
    {
        this.model = model;
    }

}
