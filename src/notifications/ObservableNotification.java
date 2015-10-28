package notifications;

import model.IModel;

/**
 * Abstract class for notifications (events), used in a MVP architecture, holds different data
 * that's being transfered between the model/view, also between client/server
 * @param <T>
 * @author  Artiom,Yoav
 */

public abstract class ObservableNotification<T>
{
    protected IModel model;
    public abstract void apply();

    public void print(){}

    public void init(IModel model){}

    public T getData()
    {
        return null;
    }

    public void setData(T data){}

    public void nullModel()
    {
        this.model = null;
    }
}
