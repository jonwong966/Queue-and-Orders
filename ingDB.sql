DROP DATABASE IF EXIST barDB;
CREATE DATABASE barDB;
use barDB;

CREATE TABLE ingredients (
    ingredient_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    is_mixable BOOLEAN NOT NULL DEFAULT TRUE,
    category_id INT NULL,
    alcohol_type_id INT NULL,
    UNIQUE(name),
    FOREIGN KEY (category_id) REFERENCES ingredient_categories(category_id) ON DELETE SET NULL,
    FOREIGN KEY (alcohol_type_id) REFERENCES alcohol_types(alcohol_type_id) ON DELETE SET NULL
);

CREATE TABLE ingredient_categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    UNIQUE(category_name)
);

CREATE TABLE ingredient_inventory (
    inventory_id INT AUTO_INCREMENT PRIMARY KEY,
    ingredient_id INT NOT NULL,
    quantity DECIMAL(10,2) NULL, -- Allows NULL values for unspecified quantities
    unit_id INT NULL, -- Allows NULL, accommodating cases where a unit doesn't apply
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    status ENUM('available', 'out of stock') NOT NULL DEFAULT 'available',
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id) ON DELETE CASCADE,
    FOREIGN KEY (unit_id) REFERENCES measurement_units(unit_id) ON DELETE RESTRICT
);

CREATE TABLE alcohol_types (
    alcohol_type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL,
    UNIQUE(type_name)
);

CREATE TABLE cocktails (
    cocktail_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    version INT NOT NULL DEFAULT 1,
    additional_info JSON NULL,
    UNIQUE(name)
);

CREATE TABLE cocktail_ingredients (
    cocktail_id INT,
    ingredient_id INT,
    quantity DECIMAL(5,2) NOT NULL,
    unit_id INT NOT NULL,
    PRIMARY KEY(cocktail_id, ingredient_id),
    FOREIGN KEY(cocktail_id) REFERENCES cocktails(cocktail_id) ON DELETE CASCADE,
    FOREIGN KEY(ingredient_id) REFERENCES ingredients(ingredient_id) ON DELETE CASCADE,
    FOREIGN KEY(unit_id) REFERENCES measurement_units(unit_id) ON DELETE RESTRICT
);

CREATE TABLE measurement_units (
    unit_id INT AUTO_INCREMENT PRIMARY KEY,
    unit_name VARCHAR(50) NOT NULL,
    UNIQUE(unit_name)
);

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role ENUM('user', 'bartender', 'admin') NOT NULL DEFAULT 'user',
    UNIQUE(username),
    UNIQUE(email)
);

CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    cocktail_id INT,
    order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY(cocktail_id) REFERENCES cocktails(cocktail_id)
);
