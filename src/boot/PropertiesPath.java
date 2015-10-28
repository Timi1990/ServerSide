package boot;


/**
 * Sinlgeton server's properties file path
 * @author Artiom,Yoav
 */
public class PropertiesPath {
    private final String filePath = "resources\\ServerProperties.xml";

    private static PropertiesPath ourInstance = new PropertiesPath();

    public static PropertiesPath getInstance() {
        return ourInstance;
    }

    private PropertiesPath() {
    }
    public String getPath()
    {
        return filePath;
    }
}
