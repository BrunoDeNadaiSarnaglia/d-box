package rowClasses;

/**
 * Created by Bruno on 4/24/2015.
 */
public class User {
    private String email;
    private String name;
    private Integer id;
    private String password;

    public User(String email, String name, Integer id) {
        this.email = email;
        this.name = name;
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
