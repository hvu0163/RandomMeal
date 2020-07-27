package com.example.project.model;

public class Disk {
    private int DiskID;
    private String DiskName;
    private String Description;
    private String Content;
    private float RateAVG;
    private int CategoryID;
    private String material;

    public Disk() {
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Disk(int diskID, String diskName, String description, String content, float rateAVG, int categoryID, String material) {
        DiskID = diskID;
        DiskName = diskName;
        Description = description;
        Content = content;
        RateAVG = rateAVG;
        CategoryID = categoryID;
        material = material;
    }

    public int getDiskID() {
        return DiskID;
    }

    public void setDiskID(int diskID) {
        DiskID = diskID;
    }

    public String getDiskName() {
        return DiskName;
    }

    public void setDiskName(String diskName) {
        DiskName = diskName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public float getRateAVG() {
        return RateAVG;
    }

    public void setRateAVG(float rateAVG) {
        RateAVG = rateAVG;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }
}
