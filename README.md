
🏘️ Layout Management System
A console-based Java application to manage real estate layouts, site owners, maintenance payments, and administrative requests. This project uses a modular architecture with a PostgreSQL backend.

🚀 Project Overview
This system allows an Administrator to manage a layout of plots (sites), assign them to Owners, and collect maintenance fees. Owners can view their sites, pay pending dues, and request site modifications (e.g., changing from an Open Site to a Villa).

✨ Key Features
👤 Admin Module:

Site Management: View all sites, dimensions, and current status.

Owner Registration: Register new owners with phone number validation.

Assignment: Assign specific sites to registered owners.

Financials: Generate monthly maintenance fees and collect funds into the Admin Wallet.

Requests: Approve or reject site type change requests (e.g., "Open Site" → "Villa").

🏠 Owner Module:

Dashboard: View owned sites and their current maintenance dues.

Payments: Make partial or full payments towards maintenance.

Requests: Request a change in site type (subject to Admin approval).

🛠️ Tech Stack & Architecture
We built this project using a Layered Architecture to separate concerns and make the code maintainable.

Language: Java (JDK 17+)

Database: PostgreSQL

Connectivity: JDBC (postgresql-42.7.5.jar)

IDE: VS Code / Eclipse

📂 Project Structure
We moved from a monolithic file to a modular package structure:

Plaintext
src/com/layoutapp/
├── config/           # DatabaseConnection (JDBC Setup)
├── model/            # POJOs (User, Site) representing DB tables
├── dao/              # Data Access Interfaces (CRUD operations)
│   └── impl/         # SQL implementations (UserDAOImpl, etc.)
├── service/          # Business Logic (Calculations, Validation)
├── ui/               # Console UI (Menus, Input handling)
└── Main.java         # Application Entry Point
⚙️ How We Built It (Development Process)
Database First Design: We designed a relational schema in PostgreSQL using Enums for strict type safety (site_type_enum, role_enum) and Foreign Keys to link Sites to Users.

DAO Pattern: We created Data Access Objects (DAOs) to isolate all SQL code. This means the main app never sees a raw SQL query—it just calls methods like userDAO.findByUsername().

Service Layer: We added a Service layer to handle logic. For example, PaymentService doesn't just add money; it checks if the user owns the site and updates the maintenance_due balance transactionally.

Modularization: We refactored the code into packages (com.layoutapp...) to fix scope issues and allow the compiler to handle dependencies cleanly.

📝 Database Setup
Run the following SQL script in pgAdmin 4 to initialize the system:

SQL
-- Clean previous tables
DROP TABLE IF EXISTS site_change_requests, payments, sites, users CASCADE;
DROP TYPE IF EXISTS site_type_enum, role_enum, request_status_enum CASCADE;

-- Create Enums & Tables
CREATE TYPE role_enum AS ENUM ('ADMIN', 'OWNER');
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    phone VARCHAR(15), 
    password VARCHAR(255), 
    role role_enum NOT NULL,
    admin_wallet DECIMAL(15, 2) DEFAULT 0.00
);
-- (See full SQL script in project docs)
Default Admin Credentials:

Username: admin

Password: admin

🏃 How to Run
Since the project uses external libraries (Postgres Driver) and packages, use the following commands from the src folder.

1. Navigate to Source Directory:

PowerShell
cd src
2. Compile (Windows):

PowerShell
javac -cp ".;../lib/postgresql-42.7.5.jar" com/layoutapp/Main.java
3. Run:

PowerShell
java -cp ".;../lib/postgresql-42.7.5.jar" com.layoutapp.Main
(Note: Ensure the lib folder containing the jar file is next to the src folder, not inside it.)