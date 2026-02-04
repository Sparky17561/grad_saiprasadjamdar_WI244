package com.layoutapp.model;

public class Site {
    private int siteId;
    private int area;
    private String currentType;
    private int ownerId;
    private double maintenanceDue;

    public Site(int siteId, int area, String currentType, int ownerId, double maintenanceDue) {
        this.siteId = siteId;
        this.area = area;
        this.currentType = currentType;
        this.ownerId = ownerId;
        this.maintenanceDue = maintenanceDue;
    }

    public int getSiteId() { return siteId; }
    public int getArea() { return area; }
    public String getCurrentType() { return currentType; }
    public int getOwnerId() { return ownerId; }
    public double getMaintenanceDue() { return maintenanceDue; }

    @Override
    public String toString() {
        return String.format("Site #%-2d | Area: %-5d | Type: %-18s | Due: Rs. %.2f", 
            siteId, area, currentType, maintenanceDue);
    }
}