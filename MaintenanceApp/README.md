
# 🏘️ Layout Management System

A robust, console-based Java application designed to manage real estate layouts, site owners, maintenance payments, and administrative requests. This project utilizes a modular **Layered Architecture** with a **PostgreSQL** backend for data persistence.

## 🚀 Project Overview

The system divides functionality into two secure roles:
* **Administrator:** Manages the entire layout, registers owners, assigns sites, and oversees financial collections.
* **Owner:** A restricted user who can view their specific sites, pay maintenance dues, and request site type changes (e.g., upgrading from an Open Site to a Villa).

---

## ✨ Key Features

### 👤 Admin Module
* **Site Management:** View details of all sites, including dimensions, area, and current status.
* **Owner Registration:** Register new owners with validated phone numbers.
* **Site Assignment:** Map specific sites to registered owners.
* **Financials:**
    * Generate monthly maintenance fees for all sites.
    * Collect funds from the system into the Admin Wallet.
* **Request Handling:** Review and Approve/Reject site modification requests.

### 🏠 Owner Module
* **My Sites:** View a personalized dashboard of owned sites and their specific dues.
* **Payments:** Securely pay maintenance fees (supports partial payments).
* **Modification Requests:** Submit requests to change a site's type (e.g., *Open Site* -> *Villa*).

---

## 🛠️ Tech Stack & Architecture

We utilized a **Modular Layered Architecture** to ensure separation of concerns and maintainability.

* **Language:** Java (JDK 17+)
* **Database:** PostgreSQL
* **Connectivity:** JDBC (`postgresql-42.7.5.jar`)
* **IDE:** VS Code / Eclipse

### 📂 Project Structure
The code is organized into packages under `src/com/layoutapp/`:

```text
src/com/layoutapp/
├── config/           # DatabaseConnection (JDBC Setup)
├── model/            # POJOs (User, Site) representing DB entities
├── dao/              # Data Access Objects (Interfaces)
│   └── impl/         # SQL Implementations (UserDAOImpl, etc.)
├── service/          # Business Logic (Validation, Calculations)
├── ui/               # Console UI (Menu systems, Input handling)
└── Main.java         # Application Entry Point

```

---

## 📝 Database Setup

To set up the database, run the provided SQL script in **pgAdmin 4**. This script handles clean slate creation (dropping old tables) and seeding initial data.

**Quick Setup Summary:**

1. **Database Name:** `layoutmanagement`
2. **Tables Created:** `users`, `sites`, `payments`, `site_change_requests`
3. **Default Admin Credentials:**
* **Username:** `admin`
* **Password:** `admin`



> **Note:** Ensure your `src/com/layoutapp/config/DatabaseConnection.java` file matches your local PostgreSQL password!

---

## 🏃 How to Run

**Prerequisites:**

* Ensure the `lib` folder containing `postgresql-42.7.5.jar` is located in the project root (next to `src`, not inside it).

**1. Open Terminal**
Navigate to the `src` directory of the project:

```powershell
cd src

```

**2. Compile the Project**
Use the following command to compile the entire modular structure:

```powershell
javac -cp ".;../lib/postgresql-42.7.5.jar" com/layoutapp/Main.java

```

**3. Run the Application**
Launch the application using the fully qualified name of the Main class:

```powershell
java -cp ".;../lib/postgresql-42.7.5.jar" com.layoutapp.Main

```



