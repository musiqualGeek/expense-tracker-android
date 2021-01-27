package com.ptkzonelabs.rupeefy.Model;

public class CategoryModel {

    private int id;
    private String description;
    private int amount;

    //constructors
    public CategoryModel(int id, String description, int amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }

    public CategoryModel() {
    }

    //toStrings is necessary for printing the contents of the class objects

    @Override
    public String toString() {
        return "CategoryModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
