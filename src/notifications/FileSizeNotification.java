package notifications;

import model.IModel;

import java.io.Serializable;

/**
 * Holds the size of the file requested
 * @author  Artiom,Yoav
 */
public class FileSizeNotification extends ObservableNotification<Long> implements Serializable
{
    private Long size;
    private final String mazeName;

    public FileSizeNotification( String mazeName)
    {
        this.mazeName = mazeName;
    }


    @Override
    public void apply() {
        try {
            model.fileSize(mazeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void print()
    {
        System.out.println("The file size of maze: " + mazeName + " is " + size);
    }

    @Override
    public void init(IModel model) {
        this.model = model;
    }

    @Override
    public Long getData()
    {
        return size;
    }

    @Override
    public void setData(Long data)
    {
        this.size = data;
    }

}
