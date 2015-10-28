package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Holds a List of the properties read from XML file
 * @author  Artiom,Yoav
 */

public class Properties implements Serializable
{
    private final List<String> propertiesList = new ArrayList<String>();

    public List<String> getPropertiesList()
    {
        return propertiesList;
    }

}
