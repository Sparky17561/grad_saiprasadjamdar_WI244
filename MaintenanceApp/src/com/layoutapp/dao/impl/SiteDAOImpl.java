package com.layoutapp.dao.impl;
import com.layoutapp.dao.SiteDAO;
import com.layoutapp.model.Site;
import com.layoutapp.config.DatabaseConnection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
public class SiteDAOImpl implements SiteDAO {
        @Override
        public List<Site> findAll(int ownerIdFilter) {
            List<Site> list = new ArrayList<>();
            String sql = "SELECT * FROM sites " + (ownerIdFilter > 0 ? "WHERE owner_id = ?" : "") + " ORDER BY site_id";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                if(ownerIdFilter > 0) ps.setInt(1, ownerIdFilter);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) list.add(new Site(rs.getInt("site_id"), rs.getInt("area"), rs.getString("current_type"), rs.getInt("owner_id"), rs.getDouble("maintenance_due")));
            } catch (SQLException e) { e.printStackTrace(); }
            return list;
        }

        @Override
        public boolean updateOwner(int siteId, String username) {
            String sql = "UPDATE sites SET owner_id = (SELECT user_id FROM users WHERE username = ?) WHERE site_id = ?";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setInt(2, siteId);
                return ps.executeUpdate() > 0;
            } catch (SQLException e) { e.printStackTrace(); }
            return false;
        }

        @Override
        public void updateType(int siteId, String newType) {
            String sql = "UPDATE sites SET current_type = ?::site_type_enum WHERE site_id = ?";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, newType);
                ps.setInt(2, siteId);
                ps.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }

        @Override
        public int applyMonthlyFees() {
            String sql = "UPDATE sites SET maintenance_due = maintenance_due + (area * CASE WHEN current_type = 'OPEN_SITE' THEN 6 ELSE 9 END)";
            try (Connection conn = DatabaseConnection.getConnection(); Statement st = conn.createStatement()) {
                return st.executeUpdate(sql);
            } catch (SQLException e) { e.printStackTrace(); return 0; }
        }
    }
