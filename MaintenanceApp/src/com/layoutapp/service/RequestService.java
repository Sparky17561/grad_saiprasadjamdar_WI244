package com.layoutapp.service;
import com.layoutapp.dao.RequestDAO;
import com.layoutapp.dao.impl.RequestDAOImpl;
import com.layoutapp.dao.SiteDAO;
import com.layoutapp.dao.impl.SiteDAOImpl;

public class RequestService {
        private final RequestDAO requestDAO = new RequestDAOImpl();
        private final SiteDAO siteDAO = new SiteDAOImpl(); 

        public void createRequest(int siteId, int ownerId, String type) {
            requestDAO.create(siteId, ownerId, type);
            System.out.println("✅ Request Submitted.");
        }

        public void viewPending() {
            requestDAO.printPendingRequests();
        }

        public void processApproval(int requestId, boolean isApproved) {
            if (!isApproved) {
                requestDAO.updateStatus(requestId, "REJECTED");
                System.out.println("❌ Request Rejected.");
                return;
            }

            String[] details = requestDAO.getRequestDetails(requestId);
            if (details != null) {
                int siteId = Integer.parseInt(details[0]);
                String newType = details[1];
                siteDAO.updateType(siteId, newType);
                requestDAO.updateStatus(requestId, "APPROVED");
                System.out.println("✅ Request Approved & Site Updated.");
            } else {
                System.out.println("❌ Invalid Request ID.");
            }
        }
    }