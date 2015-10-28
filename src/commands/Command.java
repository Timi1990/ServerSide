package commands;

import java.util.List;

/**
 * Commands general interface, indicates which command does what.
 * @author  Artiom,Yoav
 */
public interface Command
{
    void doCommand(List<String> args) throws Exception;
}
