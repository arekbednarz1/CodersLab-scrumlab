package pl.coderslab.model;

public class Recipe {
    public int id;
    public String name;
    private String ingredients;
    private String created;
    private String description;
    private String updated;
    private int preparation_time;
    private String preparation;
    private String admin_id;


    public Recipe() {
    }

    public Recipe(String name, String ingredients, String created, String description, String updated, int preparation_time, String preparation, String admin_id) {
        this.name = name;
        this.ingredients = ingredients;
        this.created = created;
        this.description = description;
        this.updated = updated;
        this.preparation_time = preparation_time;
        this.preparation = preparation;
        this.admin_id = admin_id;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(int preparation_time) {
        this.preparation_time = preparation_time;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }
}
