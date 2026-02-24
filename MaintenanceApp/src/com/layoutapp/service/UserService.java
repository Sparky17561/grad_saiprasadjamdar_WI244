package com.layoutapp.service;
import com.layoutapp.dao.UserDAO;
import com.layoutapp.dao.impl.UserDAOImpl;
import com.layoutapp.model.User;
import java.util.regex.Pattern;

 public class UserService {
        private final UserDAO userDAO = new UserDAOImpl(); 

        public User login(String username, String password) {
            if (username == null || password == null) return null;
            return userDAO.findByUsernameAndPassword(username, password);
        }

        public User refreshUser(int id) {
            return userDAO.findById(id);
        }

        public void registerOwner(String username, String phone) {
            if (!Pattern.matches("^\\d{10}$", phone)) {
                System.out.println("❌ Invalid Phone Format. Must be 10 digits.");
                return;
            }
            if (userDAO.create(username, phone)) {
                System.out.println("✅ Owner Registered Successfully.");
            } else {
                System.out.println("❌ Registration Failed (Username likely taken).");
            }
        }
    }
