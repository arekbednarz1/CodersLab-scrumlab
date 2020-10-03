package pl.coderslab.model;

public class DayName {
    public int id;
    public String name;
    private int display_order;

    public DayName(){}
    public DayName(String name, int display_order) {
        this.display_order = display_order;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Day [id=" + id + ", dayname =" + name + " oredr =" + display_order + "]";
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
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
