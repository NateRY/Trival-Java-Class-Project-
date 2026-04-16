# Trivia Game -- "Smarty Party"
Java Project Spring 2026

This is our trivia game, "smarty Party." It is a game where 1-2 players compete to rack up points to get to a set number of points to win the game. 


Home screan:
![Smarty Party](https://github.com/user-attachments/assets/f133d9c9-080e-4931-a075-cc4fadccfaed)


Game screen:
![Smarty Party](https://github.com/user-attachments/assets/a2544dae-77c5-4c73-a156-00729e6569aa)

Progress Screen:
![Smarty Party (1)](https://github.com/user-attachments/assets/cf737e91-b33c-42be-9b88-6b4192211187)

Win Screen:
![Smarty Party (2)](https://github.com/user-attachments/assets/7612ac28-9c89-4e2b-a3bb-5364047bbcda)

Highscore Screen:
![Smarty Party (3)](https://github.com/user-attachments/assets/9d344a22-781a-45f2-9e63-ba23ab71f9f9)


When the project is completed there will be a link/or download file that can be accessed on this page.

Database Structures
Tables:

* entries:
	- id int auto_increment
	- question varchar(255)
	- answer varchar(64)
	- option2 varchar(64)
	- option3 varchar(64)
	- option4 varchar(64)
	- catogery varchar(32)
	- hint	varchar(64)
	- level enum('easy', 'medium', 'hard')

* games:
	- id int auto_increment
	- user varchar(32)
	- score real
	- category_id int
	- level enum('easy', 'medium', 'hard')

* users:
	- id int auto_increment
	- name varchar(32)

* catogories:
	- id int auto_increment
	- category varchar(32)





