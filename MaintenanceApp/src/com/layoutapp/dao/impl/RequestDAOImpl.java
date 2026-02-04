package com.layoutapp.dao.impl;
import com.layoutapp.dao.RequestDAO;
import com.layoutapp.config.DatabaseConnection;
import java.sql.*;

public class RequestDAOImpl implements RequestDAO {
        @Override
        public void create(int siteId, int ownerId, String type) {
            String sql = "INSERT INTO site_change_requests (site_id, owner_id, proposed_type) VALUES (?, ?, ?::site_type_enum)";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, siteId); ps.setInt(2, ownerId); ps.setString(3, type);
                ps.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }

        @Override
        public void updateStatus(int requestId, String status) {
            String sql = "UPDATE site_change_requests SET status = ?::request_status_enum WHERE request_id = ?";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, status);
                ps.setInt(2, requestId);
                ps.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }

        @Override
        public void printPendingRequests() {
            String sql = "SELECT r.request_id, r.site_id, u.username, r.proposed_type FROM site_change_requests r " +
                         "JOIN users u ON r.owner_id = u.user_id WHERE r.status = 'PENDING'";
            try(Connection conn = DatabaseConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                System.out.println("--- PENDING REQUESTS ---");
                while(rs.next()) System.out.printf("ID: %d | Site: %d | Owner: %s | To: %s\n", rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
            } catch(SQLException e) { e.printStackTrace(); }
        }

        @Override
        public String[] getRequestDetails(int requestId) {
            String sql = "SELECT site_id, proposed_type FROM site_change_requests WHERE request_id = ?";
            try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, requestId);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) return new String[]{String.valueOf(rs.getInt(1)), rs.getString(2)};
            } catch(SQLException e) { e.printStackTrace(); }
            return null;
        }
    }
