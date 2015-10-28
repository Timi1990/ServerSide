package notifications;

import algorithms.search.Solution;
import model.IModel;

import java.io.Serializable;

/**
 * Holds the solution recieved from server's model
 * @author  Artiom,Yoav
 */

public class DisplaySolutionNotification extends ObservableNotification<Solution> implements Serializable
{
    private Solution solution;

    public DisplaySolutionNotification(Solution solution)
    {
        this.solution = solution;
    }


    @Override
    public void apply() {
        model.setNotification(this);
    }

    @Override
    public void print()
    {
        System.out.print("Displaying solution: ");

        solution.printSolution();
    }

    @Override
    public void init(IModel model) {
        this.model = model;
    }

    @Override
    public Solution getData()
    {
        return solution;
    }

    @Override
    public void setData(Solution data)
    {
        this.solution = data;
    }

}
