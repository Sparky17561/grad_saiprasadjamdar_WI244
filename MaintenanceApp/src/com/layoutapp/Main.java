package com.layoutapp;
import com.layoutapp.ui.UI;

public class Main {
    public static void main(String[] args) {
        try { 
            Class.forName("org.postgresql.Driver"); 
        } catch (ClassNotFoundException e) { 
            System.out.println("❌ Driver Missing"); 
            return; 
        }
        while(true) { 
            UI.start(); 
        }
    }
}