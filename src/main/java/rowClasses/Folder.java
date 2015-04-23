package rowClasses;

/**
 * @author Bruno de Nadai Sarnaglia <denadai2@illinois.edu>
 * @version 1.0
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
