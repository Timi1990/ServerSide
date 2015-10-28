package notifications;

import model.IModel;

import java.io.IOException;
import java.io.Serializable;

/**
 * Holds the file path to load soolutions from
 * @author  Artiom,Yoav
 */

public class LoadSolutionsNotification extends ObservableNotification<String> implements Serializable {

    private final String filePath;


    public LoadSolutionsNotification(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void apply() {
        try {
            model.setNotification(this);
            model.loadSolutionsForMazes(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void init(IModel model) {
        this.model = model;
    }


    @Override
    public String getData() {
        return filePath;
    }

}
