package com.layoutapp.dao;


public interface PaymentDAO {
    void recordPayment(int siteId, double amount);
    double getUncollectedFunds(int siteId);
    void markAsCollected(int siteId);
}