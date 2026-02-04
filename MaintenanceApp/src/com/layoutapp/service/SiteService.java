package com.layoutapp.service;

import com.layoutapp.dao.SiteDAO;
import com.layoutapp.dao.impl.SiteDAOImpl;
import com.layoutapp.model.Site;
import java.util.*;

public class SiteService {
        private final SiteDAO siteDAO = new SiteDAOImpl();

        public List<Site> getAllSites() { return siteDAO.findAll(0); }
        public List<Site> getOwnerSites(int ownerId) { return siteDAO.findAll(ownerId); }

        public void assignOwnerToSite(int siteId, String username) {
            if (siteDAO.updateOwner(siteId, username)) {
                System.out.println("✅ Site Assigned.");
            } else {
                System.out.println("❌ Failed. Check Site ID or Username.");
            }
        }

        public void generateMonthlyFees() {
            int count = siteDAO.applyMonthlyFees();
            System.out.println("✅ Monthly fees added to " + count + " sites.");
        }
    }

    