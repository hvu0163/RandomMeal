package com.example.project.model;

public class DiskCategory {
    private int CategoryID;
    private String CategoryName;
    private String Description;

    public DiskCategory() {
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public DiskCategory(int categoryID, String categoryName, String description) {
        CategoryID = categoryID;
        CategoryName = categoryName;
        Description = description;
    }
}
