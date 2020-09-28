package pl.coderslab.model;

public class DayName {
    private int id;
    private String name;

    public DayName(){}
    public DayName(String name) {
        this.name = name;
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
}
