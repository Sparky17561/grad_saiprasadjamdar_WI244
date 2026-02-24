package com.layoutapp.dao;


public interface RequestDAO {
    void create(int siteId, int ownerId, String type);
    void updateStatus(int requestId, String status);
    void printPendingRequests(); 
    String[] getRequestDetails(int requestId); 
}
