package com.layoutapp.service;
import com.layoutapp.dao.PaymentDAO;
import com.layoutapp.dao.impl.PaymentDAOImpl;

import com.layoutapp.dao.impl.UserDAOImpl;

public class PaymentService {
        private final PaymentDAO paymentDAO = new PaymentDAOImpl();
        
        // UPDATED: Using Getter instead of direct access
        public void processOwnerPayment(int siteId, double amount, int ownerId, SiteService siteService) {
            boolean owns = siteService.getOwnerSites(ownerId).stream()
                            .anyMatch(s -> s.getSiteId() == siteId); // Using Getter
            
            if (!owns) {
                System.out.println("❌ Access Denied: You do not own this site.");
                return;
            }
            if (amount <= 0) {
                System.out.println("❌ Invalid Amount.");
                return;
            }
            paymentDAO.recordPayment(siteId, amount);
            System.out.println("✅ Payment Recorded. Balance Updated.");
        }

        public void collectAdminFunds(int siteId, UserService userService) {
            double amount = paymentDAO.getUncollectedFunds(siteId);
            if (amount <= 0) {
                System.out.println("⚠️ No pending funds to collect.");
                return;
            }
            paymentDAO.markAsCollected(siteId);
            // We need to call a method in UserService/UserDAO to update wallet
            // Ideally we pass an Admin ID, but for this simple app, we just update the DB directly via DAO
             new UserDAOImpl().addToAdminWallet(amount);
            System.out.printf("✅ Collected Rs. %.2f to Admin Wallet.\n", amount);
        }
    }