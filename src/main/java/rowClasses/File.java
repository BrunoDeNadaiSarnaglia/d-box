package rowClasses;

/**
 * Created by Bruno on 4/21/2015.
 */
public class File {

    private Integer id;
    private String URL;
    private String name;

    public File(Integer id, String URL, String name) {
        setId(id);
        setURL(URL);
        setName(name);
    }

    public Integer getId() {
        return id;
    }

    public String getURL() {
        return URL;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setName(String name) {
        this.name = name;
    }
}
