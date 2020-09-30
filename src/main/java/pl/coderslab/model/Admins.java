package pl.coderslab.model;

public class Admins {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private int superadmin;
    private int enable = 1;

    @Override
    public String toString() {
        return "Admin [id=" + id + ", first name=" + first_name + ", last name=" + last_name + ", email=" + email +
                "password =" + password + "superadmin =" + superadmin + ", enable = " + enable +"]";
    }

public Admins(){}
    public Admins(String first_name, String last_name, String email,
                  String password, int superadmin, int enable) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.superadmin = superadmin;
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(int superadmin) {
        this.superadmin = superadmin;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}

