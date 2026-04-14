USE trivia;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS entries;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

CREATE TABLE categories (id int AUTO_INCREMENT PRIMARY KEY, category VARCHAR(32));
CREATE TABLE users (id int AUTO_INCREMENT PRIMARY KEY, name VARCHAR(32));
CREATE TABLE entries(id int AUTO_INCREMENT PRIMARY KEY,
                     question VARCHAR(255),
                     answer VARCHAR(64),
                     option2 VARCHAR(64),
                     option3 VARCHAR(64),
                     option4 VARCHAR(64),
                     hint VARCHAR(64),
                     category_id int,
                     level ENUM('easy', 'medium', 'hard') NOT NULL DEFAULT 'easy',
                     FOREIGN KEY (category_id) REFERENCES categories(id));

CREATE TABLE games(id INT AUTO_INCREMENT PRIMARY KEY,
                   user_id INT,
                   game_date DATE,
                   category_id int,
                   level ENUM('easy', 'medium', 'hard'),
                   score REAL,
                   FOREIGN KEY (user_id) REFERENCES users(id));


INSERT INTO categories(category) values('Math');
INSERT INTO categories(category) values('History');
INSERT INTO categories(category) values('TV');
INSERT INTO categories(category) values('Sports');
INSERT INTO categories(category) values('Java');
INSERT INTO categories(category) values('C++');