package com.layoutapp.dao;
import com.layoutapp.model.Site;
import java.util.*;

public interface SiteDAO {
    List<Site> findAll(int ownerIdFilter);
    boolean updateOwner(int siteId, String username);
    void updateType(int siteId, String newType);
    int applyMonthlyFees(); 
}