package com.layoutapp.dao.impl;

import com.layoutapp.dao.UserDAO;
import com.layoutapp.model.User;
import com.layoutapp.config.DatabaseConnection;
import java.sql.*;

public class UserDAOImpl implements UserDAO {
        @Override
        public User findByUsernameAndPassword(String username, String password) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("role"), rs.getDouble("admin_wallet"));
            } catch (SQLException e) { e.printStackTrace(); }
            return null;
        }

        @Override
        public User findById(int id) {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("role"), rs.getDouble("admin_wallet"));
            } catch (SQLException e) { e.printStackTrace(); }
            return null;
        }

        @Override
        public boolean create(String username, String phone) {
            String sql = "INSERT INTO users (username, phone, password, role) VALUES (?, ?, ?, 'OWNER')";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, phone);
                ps.setString(3, phone);
                ps.executeUpdate();
                return true;
            } catch (SQLException e) { return false; }
        }

        @Override
        public void addToAdminWallet(double amount) {
            String sql = "UPDATE users SET admin_wallet = admin_wallet + ? WHERE role = 'ADMIN'";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setDouble(1, amount);
                ps.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
