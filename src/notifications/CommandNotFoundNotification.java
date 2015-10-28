package notifications;

import model.IModel;

import java.io.Serializable;

/**
 * Notification for exception catching, when command's invalid
 * @author  Artiom,Yoav
 */
public class CommandNotFoundNotification extends ObservableNotification implements Serializable {


    @Override
    public void apply() {

    }

    @Override
    public void print() {
        System.out.println("Command not found!");
    }

    @Override
    public void init(IModel model) {

    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public void setData(Object data) {

    }
}
