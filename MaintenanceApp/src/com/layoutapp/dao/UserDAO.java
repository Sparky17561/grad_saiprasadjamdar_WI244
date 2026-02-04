package com.layoutapp.dao;
import com.layoutapp.model.User;

public interface UserDAO {
    User findByUsernameAndPassword(String username, String password);
    User findById(int id);
    boolean create(String username, String phone);
    void addToAdminWallet(double amount);
}