package com.layoutapp.ui;
import com.layoutapp.model.User;
import com.layoutapp.model.Site;
import com.layoutapp.service.*;
import java.util.Scanner;
import java.util.List;

public class UI {
        private static final Scanner sc = new Scanner(System.in);
        private static final UserService userService = new UserService();
        private static final SiteService siteService = new SiteService();
        private static final PaymentService paymentService = new PaymentService();
        private static final RequestService requestService = new RequestService();

        public static void start() {
            System.out.println("\n=== 🏘️ LAYOUT MANAGEMENT SYSTEM ===");
            System.out.println("1. Login");
            System.out.println("2. Exit System");
            System.out.print("👉 Select Option: ");
            
            int mainChoice;
            try { mainChoice = sc.nextInt(); } 
            catch (Exception e) { System.out.println("Invalid Input."); sc.next(); return; }

            if (mainChoice == 2) {
                System.out.println("Exiting Application... Goodbye! 👋");
                System.exit(0);
            }

            if (mainChoice == 1) {
                System.out.print("Username: "); String user = sc.next();
                System.out.print("Password: "); String pass = sc.next();

                User currentUser = userService.login(user, pass);
                if (currentUser == null) {
                    System.out.println("❌ Invalid Credentials.");
                    return;
                }

                // Using Getters
                System.out.println("👋 Welcome, " + currentUser.getUsername().toUpperCase());
                if (currentUser.getRole().equals("ADMIN")) {
                    adminMenu(currentUser);
                } else {
                    ownerMenu(currentUser);
                }
            } else {
                System.out.println("❌ Invalid Option.");
            }
        }

        private static void adminMenu(User admin) {
            while (true) {
                User refreshed = userService.refreshUser(admin.getId()); // Using Getter
                System.out.printf("\n[ ADMIN MENU ] Wallet: Rs. %.2f\n", refreshed.getAdminWallet()); // Using Getter
                System.out.println("1. View All Sites");
                System.out.println("2. Register New Owner");
                System.out.println("3. Assign Owner to Site");
                System.out.println("4. Manage Requests");
                System.out.println("5. Collect Funds");
                System.out.println("6. Generate Monthly Fees");
                System.out.println("7. Logout");
                System.out.print("Choice: ");
                
                int ch = sc.nextInt();
                switch (ch) {
                    case 1: for (Site s : siteService.getAllSites()) System.out.println(s); break;
                    case 2:
                        System.out.print("Name: "); String name = sc.next();
                        System.out.print("Phone: "); String phone = sc.next();
                        userService.registerOwner(name, phone);
                        break;
                    case 3:
                        System.out.print("Site ID: "); int sid = sc.nextInt();
                        System.out.print("Username: "); String uname = sc.next();
                        siteService.assignOwnerToSite(sid, uname);
                        break;
                    case 4:
                        requestService.viewPending();
                        System.out.print("Req ID (0 to cancel): "); int rid = sc.nextInt();
                        if (rid > 0) requestService.processApproval(rid, true);
                        break;
                    case 5:
                        System.out.print("Site ID to Collect: "); int csid = sc.nextInt();
                        paymentService.collectAdminFunds(csid, userService);
                        break;
                    case 6: siteService.generateMonthlyFees(); break;
                    case 7: return; 
                }
            }
        }

        private static void ownerMenu(User owner) {
            while (true) {
                System.out.println("\n[ OWNER MENU ]");
                System.out.println("1. View My Sites");
                System.out.println("2. Make Payment");
                System.out.println("3. Request Upgrade");
                System.out.println("4. Logout");
                System.out.print("Choice: ");
                
                int ch = sc.nextInt();
                switch (ch) {
                    case 1: 
                        List<Site> sites = siteService.getOwnerSites(owner.getId()); // Using Getter
                        if(sites.isEmpty()) System.out.println("No sites.");
                        for(Site s : sites) System.out.println(s);
                        break;
                    case 2:
                        System.out.print("Site ID: "); int sid = sc.nextInt();
                        System.out.print("Amount: "); double amt = sc.nextDouble();
                        paymentService.processOwnerPayment(sid, amt, owner.getId(), siteService); // Using Getter
                        break;
                    case 3:
                        System.out.print("Site ID: "); int rsid = sc.nextInt();
                        System.out.print("Type (VILLA/APARTMENT/etc): "); String type = sc.next();
                        requestService.createRequest(rsid, owner.getId(), type); // Using Getter
                        break;
                    case 4: return; 
                }
            }
        }
    }