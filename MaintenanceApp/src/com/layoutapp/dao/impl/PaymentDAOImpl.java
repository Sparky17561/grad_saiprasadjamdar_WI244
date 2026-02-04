package com.layoutapp.dao.impl;
import com.layoutapp.dao.PaymentDAO;
import com.layoutapp.config.DatabaseConnection;
import java.sql.*;

public class PaymentDAOImpl implements PaymentDAO {
        @Override
        public void recordPayment(int siteId, double amount) {
            String insert = "INSERT INTO payments (site_id, amount) VALUES (?, ?)";
            String update = "UPDATE sites SET maintenance_due = maintenance_due - ? WHERE site_id = ?";
            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.setAutoCommit(false);
                try (PreparedStatement p1 = conn.prepareStatement(insert); PreparedStatement p2 = conn.prepareStatement(update)) {
                    p1.setInt(1, siteId); p1.setDouble(2, amount); p1.executeUpdate();
                    p2.setDouble(1, amount); p2.setInt(2, siteId); p2.executeUpdate();
                    conn.commit();
                } catch (SQLException e) { conn.rollback(); e.printStackTrace(); }
            } catch (SQLException e) { e.printStackTrace(); }
        }

        @Override
        public double getUncollectedFunds(int siteId) {
            String sql = "SELECT SUM(amount) FROM payments WHERE site_id = ? AND is_collected_by_admin = FALSE";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, siteId);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) return rs.getDouble(1);
            } catch (SQLException e) { e.printStackTrace(); }
            return 0;
        }

        @Override
        public void markAsCollected(int siteId) {
            String sql = "UPDATE payments SET is_collected_by_admin = TRUE WHERE site_id = ? AND is_collected_by_admin = FALSE";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, siteId);
                ps.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    