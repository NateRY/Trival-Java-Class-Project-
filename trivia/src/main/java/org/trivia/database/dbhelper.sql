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

CREATE INDEX idx_category ON entries(category_id);

INSERT INTO categories(category) values('Math');
INSERT INTO categories(category) values('History');
INSERT INTO categories(category) values('TV');
INSERT INTO categories(category) values('Sports');
INSERT INTO categories(category) values('Java');
INSERT INTO categories(category) values('C++');




-- Math (category_id: 1)
INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('What is the square root of 144?', '12', '14', '10', '16', 'It is a dozen.', 1, 'easy'),
('What is the value of Pi to two decimal places?', '3.14', '3.16', '3.12', '3.18', 'Think of March 14th.', 1, 'easy'),
('What is the derivative of sin(x)?', 'cos(x)', '-cos(x)', 'tan(x)', 'sin(x)', 'It is a trigonometric companion.', 1, 'hard');

INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('What is the value of 7 cubed (7^3)?', '343', '243', '49', '512', 'It is 49 times 7.', 1, 'medium'),
('In a right triangle, what is the side opposite the right angle called?', 'Hypotenuse', 'Adjacent', 'Opposite', 'Radius', 'It is the longest side.', 1, 'easy'),
('What is the only even prime number?', '2', '4', '0', '6', 'It is the first prime number.', 1, 'easy');

-- History (category_id: 2)
INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('In what year did World War II end?', '1945', '1944', '1946', '1941', 'Mid-40s.', 2, 'medium'),
('Who was the first President of the United States?', 'George Washington', 'Thomas Jefferson', 'John Adams', 'Benjamin Franklin', 'He is on the $1 bill.', 2, 'easy');

INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('Who was the primary author of the Declaration of Independence?', 'Thomas Jefferson', 'Benjamin Franklin', 'John Adams', 'George Washington', 'He was the 3rd US President.', 2, 'medium'),
('The "Renaissance" period began in which country?', 'Italy', 'France', 'Germany', 'England', 'Think of Florence.', 2, 'medium'),
('Which empire was ruled by Julius Caesar?', 'Roman Empire', 'Ottoman Empire', 'Greek Empire', 'Persian Empire', 'Veni, vidi, vici.', 2, 'easy');

-- TV & Entertainment (category_id: 3)
INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('Which TV show features a chemistry teacher named Walter White?', 'Breaking Bad', 'The Wire', 'Mad Men', 'Better Call Saul', 'Think "Blue Sky".', 3, 'easy'),
('In "Friends", how many seasons were produced?', '10', '8', '12', '9', 'A decade of episodes.', 3, 'medium');

INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('In the office, what is the name of the paper company?', 'Dunder Mifflin', 'Wernham Hogg', 'Prince Paper', 'Sabre', 'D_nd_r M_ffl_n.', 3, 'easy'),
('Which show features a "Upside Down" dimension?', 'Stranger Things', 'Dark', 'The Witcher', 'Black Mirror', 'Eleven is a main character.', 3, 'easy'),
('What is the longest-running animated US prime-time series?', 'The Simpsons', 'Family Guy', 'South Park', 'King of the Hill', 'They live in Springfield.', 3, 'easy');



-- Sports (category_id: 4)
INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('How many players are on a standard soccer team on the field?', '11', '10', '12', '9', 'Ten plus the goalie.', 4, 'easy'),
('Which country won the first-ever FIFA World Cup in 1930?', 'Uruguay', 'Brazil', 'Argentina', 'Italy', 'It took place in South America.', 4, 'hard');


INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('Which golfer has the most Major championship wins?', 'Jack Nicklaus', 'Tiger Woods', 'Arnold Palmer', 'Phil Mickelson', 'The Golden Bear.', 4, 'hard'),
('In bowling, what is the term for three consecutive strikes?', 'Turkey', 'Chicken', 'Ham', 'Triple', 'A common Thanksgiving bird.', 4, 'medium'),
('How many rings are on the Olympic flag?', '5', '4', '6', '7', 'Representing the five continents.', 4, 'easy');

-- Computer Science: Java & C++ (category_id: 5 & 6)
INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('In Java, which keyword is used to inherit a class?', 'extends', 'implements', 'inherits', 'includes', 'It expands the class.', 5, 'medium'),
('Which of the following is used for a single-line comment in C++?', '//', '/*', '--', '#', 'Double slashes.', 6, 'easy'),
('What is the size of an int in Java?', '4 bytes', '2 bytes', '8 bytes', 'Depends on OS', 'It is 32 bits.', 5, 'medium');

INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('Which Java version introduced the "Lambda Expressions"?', 'Java 8', 'Java 7', 'Java 9', 'Java 6', 'Released in 2014.', 5, 'medium'),
('What is the superclass of all classes in Java?', 'Object', 'Main', 'Class', 'System', 'The root of the hierarchy.', 5, 'medium'),
('Which access modifier makes a member accessible only within its own class?', 'private', 'public', 'protected', 'default', 'The most restrictive level.', 5, 'easy');

INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('Who created the C++ programming language?', 'Bjarne Stroustrup', 'James Gosling', 'Dennis Ritchie', 'Guido van Rossum', 'A Danish computer scientist.', 6, 'hard'),
('Which operator is used to de-reference a pointer?', '*', '&', '->', '.', 'The asterisk.', 6, 'medium'),
('What is the default access specifier for members of a "struct" in C++?', 'public', 'private', 'protected', 'internal', 'Unlike a class, it defaults to open.', 6, 'hard');

INSERT INTO users (name) VALUES 
('Alice Smith'),
('Bob Johnson'),
('Charlie Dev'),
('Dana Data');

INSERT INTO games (user_id, game_date, category_id, level, score) VALUES 
(1, '2026-04-10', 1, 'easy', 90.0),
(2, '2026-04-12', 4, 'medium', 75.5),
(3, '2026-04-15', 5, 'hard', 100.0),
(1, '2026-04-17', 2, 'easy', 85.0),
(4, '2026-04-17', 6, 'medium', 60.0);


-- Add Categories
INSERT INTO categories(category) VALUES('Music');
INSERT INTO categories(category) VALUES('Art');
INSERT INTO categories(category) VALUES('Movies');


-- =========================
-- MUSIC (category_id: 7)
-- =========================
INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('Who is known as the King of Pop?', 'Michael Jackson', 'Elvis Presley', 'Prince', 'Justin Bieber', 'Thriller artist.', 7, 'easy'),
('How many keys are on a standard piano?', '88', '76', '64', '100', 'Common full piano.', 7, 'medium'),
('What symbol means to play loudly in music?', 'f', 'p', 'mf', 'pp', 'Forte.', 7, 'easy'),
('What is the term for a group of four musicians?', 'Quartet', 'Trio', 'Duet', 'Solo', 'Four performers.', 7, 'easy'),
('Which instrument has strings and a bow?', 'Violin', 'Drums', 'Flute', 'Trumpet', 'Orchestral string instrument.', 7, 'easy'),
('What does BPM stand for in music?', 'Beats Per Minute', 'Bars Per Measure', 'Bass Per Melody', 'Beat Pitch Mode', 'Tempo measurement.', 7, 'medium');


-- =========================
-- ART (category_id: 8)
-- =========================
INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('Who painted the Mona Lisa?', 'Leonardo da Vinci', 'Vincent van Gogh', 'Pablo Picasso', 'Claude Monet', 'Renaissance artist.', 8, 'easy'),
('What art style uses small dots of color?', 'Pointillism', 'Cubism', 'Surrealism', 'Baroque', 'Tiny dot technique.', 8, 'medium'),
('The Starry Night was painted by?', 'Vincent van Gogh', 'Picasso', 'Da Vinci', 'Rembrandt', 'Dutch painter.', 8, 'easy'),
('Which artist cut off part of his ear?', 'Vincent van Gogh', 'Michelangelo', 'Dali', 'Monet', 'Famous mental health story.', 8, 'medium'),
('What material is commonly used in sculpture?', 'Marble', 'Paper', 'Fabric', 'Glass', 'Greek statues often used it.', 8, 'easy'),
('Which famous painting shows a melting clock?', 'The Persistence of Memory', 'Mona Lisa', 'The Scream', 'Guernica', 'Salvador Dali artwork.', 8, 'hard');


-- =========================
-- MOVIES (category_id: 9)
-- =========================
INSERT INTO entries (question, answer, option2, option3, option4, hint, category_id, level) VALUES
('Who directed Titanic?', 'James Cameron', 'Spielberg', 'Nolan', 'Scorsese', 'Also made Avatar.', 9, 'easy'),
('Which movie features Iron Man?', 'The Avengers', 'Batman', 'Superman', 'Spider-Man', 'Marvel team film.', 9, 'easy'),
('What is the highest-grossing film (unadjusted)?', 'Avatar', 'Titanic', 'Endgame', 'Star Wars', 'Blue aliens.', 9, 'medium'),
('Who played Jack in Titanic?', 'Leonardo DiCaprio', 'Brad Pitt', 'Tom Cruise', 'Johnny Depp', 'Famous 90s actor.', 9, 'easy'),
('What studio made Toy Story?', 'Pixar', 'DreamWorks', 'Disney Animation', 'Universal', '3D animation pioneer.', 9, 'easy'),
('Which movie features a wizard named Harry?', 'Harry Potter', 'Lord of the Rings', 'Narnia', 'Percy Jackson', 'Magic school series.', 9, 'easy');