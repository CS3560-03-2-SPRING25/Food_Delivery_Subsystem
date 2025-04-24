-- CREATE TABLE drivers (
-- 	driver_id INT PRIMARY KEY,
--     status VARCHAR(50),
--     rating DECIMAL(3,2),
--     FOREIGN KEY (driver_id) REFERENCES users(user_id)
-- );

-- CREATE TABLE restaurant_workers (
-- 	worker_id INT PRIMARY KEY,
--     FOREIGN KEY (worker_id) REFERENCES users(user_id)
-- );

-- CREATE TABLE customers (
-- 	customer_id INT PRIMARY KEY,
--     address VARCHAR(200),
--     FOREIGN KEY (customer_id) REFERENCES users(user_id)
-- );

-- CREATE TABLE cars (
-- 	car_id INT AUTO_INCREMENT PRIMARY KEY,
--     driver_id INT,
--     make VARCHAR(50),
--     model VARCHAR(50),
--     license_plate VARCHAR(20),
--     FOREIGN KEY (driver_id) REFERENCES drivers(driver_id)
-- );

-- CREATE TABLE orders (
-- 	order_id INT AUTO_INCREMENT PRIMARY KEY,
--     customer_id INT,
--     order_time DATETIME,
--     status VARCHAR(50),
--     FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
-- );

-- CREATE TABLE order_items (
-- 	item_id INT AUTO_INCREMENT PRIMARY KEY,
--     order_id INT,
--     item_time VARCHAR(100),
--     status VARCHAR(50),
--     FOREIGN KEY (order_id) REFERENCES orders(order_id)
-- );

-- CREATE TABLE deliveries (
--     delivery_id INT AUTO_INCREMENT PRIMARY KEY,
--     order_id INT,
--     driver_id INT,
--     delivery_start_time DATETIME,
--     delivery_end_time DATETIME,
--     status VARCHAR(50),
--     FOREIGN KEY (order_id) REFERENCES orders(order_id),
--     FOREIGN KEY (driver_id) REFERENCES drivers(driver_id)
-- );

-- CREATE TABLE reviews (
--     review_id INT AUTO_INCREMENT PRIMARY KEY,
--     customer_id INT,
--     order_id INT,
--     rating INT,
--     comments TEXT,
--     FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
--     FOREIGN KEY (order_id) REFERENCES orders(order_id)
-- );

-- ALTER TABLE users
-- ADD COLUMN email VARCHAR(100) UNIQUE NOT NULL,
-- ADD COLUMN password_hash VARCHAR(255) NOT NULL;

-- ALTER TABLE orders
-- ADD COLUMN driver_id INT;

-- ALTER TABLE orders
-- ADD CONSTRAINT fk_driver
-- FOREIGN KEY (driver_id) REFERENCES drivers(driver_id);

-- ALTER TABLE users
-- RENAME COLUMN password_hash to password;

-- ALTER TABLE customers
-- DROP COLUMN address;

-- ALTER TABLE deliveries
-- ADD COLUMN address VARCHAR(200);