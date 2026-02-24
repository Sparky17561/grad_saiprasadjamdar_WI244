-- 1. DROP EXISTING OBJECTS (CLEAN SLATE)
DROP TABLE IF EXISTS site_change_requests;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS sites;
DROP TABLE IF EXISTS users;
DROP TYPE IF EXISTS site_type_enum;
DROP TYPE IF EXISTS role_enum;
DROP TYPE IF EXISTS request_status_enum;

-- 2. CREATE ENUMS
CREATE TYPE site_type_enum AS ENUM ('VILLA', 'APARTMENT', 'INDEPENDENT_HOUSE', 'OPEN_SITE');
CREATE TYPE role_enum AS ENUM ('ADMIN', 'OWNER');
CREATE TYPE request_status_enum AS ENUM ('PENDING', 'APPROVED', 'REJECTED');

-- 3. USERS TABLE
-- Added 'phone' for owners (acts as password). 
-- Added 'password' column for Admin (or future flexibility).
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    phone VARCHAR(15), -- Validation handled in App, storing as string
    password VARCHAR(255), -- For Admin: 'admin', For Owner: Same as phone
    role role_enum NOT NULL,
    admin_wallet DECIMAL(15, 2) DEFAULT 0.00 -- Total collected by Admin
);

-- 4. SITES TABLE
-- Removed 'is_maintenance_paid'. 
-- Added 'maintenance_due': The dynamic balance the owner owes.
CREATE TABLE sites (
    site_id INT PRIMARY KEY,
    width INT NOT NULL,
    depth INT NOT NULL,
    area INT GENERATED ALWAYS AS (width * depth) STORED,
    current_type site_type_enum DEFAULT 'OPEN_SITE',
    owner_id INT REFERENCES users(user_id),
    maintenance_due DECIMAL(15, 2) DEFAULT 0.00 -- Logic: Fee Generated (+), Owner Pays (-)
);

-- 5. PAYMENTS TABLE (The Ledger)
-- Tracks variable partial payments made by owners.
CREATE TABLE payments (
    payment_id SERIAL PRIMARY KEY,
    site_id INT REFERENCES sites(site_id),
    amount DECIMAL(15, 2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_collected_by_admin BOOLEAN DEFAULT FALSE -- FALSE = Money with Gateway, TRUE = In Admin Wallet
);

-- 6. REQUESTS TABLE
CREATE TABLE site_change_requests (
    request_id SERIAL PRIMARY KEY,
    site_id INT REFERENCES sites(site_id),
    owner_id INT REFERENCES users(user_id),
    proposed_type site_type_enum NOT NULL,
    status request_status_enum DEFAULT 'PENDING'
);

-- ==========================================
-- SEED DATA
-- ==========================================

-- 1. Create Admin (User: admin, Pass: admin)
INSERT INTO users (username, password, role) VALUES ('admin', 'admin', 'ADMIN');

-- 2. Create Owners (Pass = Phone)
INSERT INTO users (username, phone, password, role) 
VALUES ('rohit', '9876543210', '9876543210', 'OWNER');

INSERT INTO users (username, phone, password, role) 
VALUES ('priya', '9988776655', '9988776655', 'OWNER');

-- 3. Insert Sites
-- First 10 (40x60)
INSERT INTO sites (site_id, width, depth) SELECT generate_series(1, 10), 40, 60;
-- Next 10 (30x50)
INSERT INTO sites (site_id, width, depth) SELECT generate_series(11, 20), 30, 50;
-- Last 15 (30x40)
INSERT INTO sites (site_id, width, depth) SELECT generate_series(21, 35), 30, 40;

-- 4. Assign Site & Initialize Fee
-- Assign Site 5 to Rohit
UPDATE sites SET owner_id = (SELECT user_id FROM users WHERE username='rohit') WHERE site_id = 5;

-- Assign Site 12 to Priya
UPDATE sites SET owner_id = (SELECT user_id FROM users WHERE username='priya') WHERE site_id = 12;

-- Initial Logic: Let's assume bills are generated initially based on current type
-- Open Site (6rs) * 2400 area = 14400
UPDATE sites SET maintenance_due = area * 6 WHERE current_type = 'OPEN_SITE';