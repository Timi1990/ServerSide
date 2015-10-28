package view;

import boot.PropertiesPath;
import notifications.PropertiesNotification;

import java.util.Date;
import java.util.Observable;

/**
 * An implementation of IView , using CLI, displaying sent data on server
 * @author  Artiom,Yoav
 */
public class ViewCLI extends Observable implements IView {

    @Override
    public void display() {
        Date date = new Date();
        System.out.println("Data was sent to client on "+date);
    }

    @Override
    public void start() {
        PropertiesNotification propertiesNotification = new PropertiesNotification(PropertiesPath.getInstance().getPath());
        setChanged();
        notifyObservers(propertiesNotification);
    }
}
