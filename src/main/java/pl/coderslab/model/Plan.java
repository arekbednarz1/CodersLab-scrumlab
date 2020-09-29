package pl.coderslab.model;



import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Plan {

    private int id;
    private String name;
    private String description;
    private Date created = Date.valueOf(LocalDate.now());
    private int adminId;

    public Plan() {
    }

    public Plan(String name, String description, Date created) {
        this.name = name;
        this.description = description;
        this.created = created;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}

