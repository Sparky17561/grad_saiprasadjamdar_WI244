package com.layoutapp.model;

public class User {
    private int id;
    private String username;
    private String role;
    private double adminWallet;

    public User(int id, String username, String role, double adminWallet) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.adminWallet = adminWallet;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public double getAdminWallet() { return adminWallet; }
    public void setAdminWallet(double adminWallet) { this.adminWallet = adminWallet; }
}