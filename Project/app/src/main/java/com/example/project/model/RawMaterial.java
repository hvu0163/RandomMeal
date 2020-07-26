package com.example.project.model;

public class RawMaterial {
    private int STT;
    private String Content;
    private int DiskID;

    public RawMaterial() {
    }

    public RawMaterial(int STT, String content, int diskID) {
        this.STT = STT;
        Content = content;
        DiskID = diskID;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getDiskID() {
        return DiskID;
    }

    public void setDiskID(int diskID) {
        DiskID = diskID;
    }
}
