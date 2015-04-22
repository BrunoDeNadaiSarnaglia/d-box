package rowClasses;

/**
 * Created by Bruno on 4/21/2015.
 */
public class Folder {

    private Integer id;
    private String name;

    public Folder(Integer id, String name) {
        setId(id);
        setName(name);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
