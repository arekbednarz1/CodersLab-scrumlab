package pl.coderslab.model;



import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Plan {

    public int id;
    public String name;
    private String description;
    private String created;
    private int adminId;
    private Admins admin;

    public Plan() {
    }

    public Plan(String name, String description, String created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public Plan(String name, String description, Admins admin) {
        this.name = name;
        this.description = description;
        this.admin = admin;
    }

    public Admins getAdmin() {
        return admin;
    }

    public void setAdmin(Admins admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Plan [id=" + id + ", name=" + name + ", description=" + description + ", created=" + created + " " + adminId+"]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}

