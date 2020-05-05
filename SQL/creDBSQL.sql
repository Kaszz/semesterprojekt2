-- Database: creDB

-- DROP DATABASE "creDB";

CREATE DATABASE "creDB"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C'
    LC_CTYPE = 'C'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
	-- Creating the account table

CREATE TABLE accounts (
	account_id SERIAL PRIMARY KEY,
	email VARCHAR (100) UNIQUE NOT NULL,
	password VARCHAR (50) UNIQUE NOT NULL
);

-- Creating the admin table

CREATE TABLE admin (
	admin_id SERIAL PRIMARY KEY,
	account_id INT NOT NULL REFERENCES accounts (account_id)
);

-- Creating the user table

CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	account_id INT NOT NULL REFERENCES accounts (account_id) 
);

-- Creating the broadcast table

CREATE TABLE broadcasts (
	broadcast_id SERIAL PRIMARY KEY,
	title VARCHAR (200) UNIQUE NOT NULL
);

-- Creating the series table

CREATE TABLE series (
	series_id SERIAL PRIMARY KEY
);

-- Creating the season table

CREATE TABLE seasons (
	season_id SERIAL PRIMARY KEY,
	series_id INT REFERENCES series (series_id)
);

CREATE TABLE episodes (
	episode_id INT REFERENCES seasons (season_id)
	ON DELETE CASCADE
);

DROP TABLE episodes;


-- Creating the credit table

CREATE TABLE credits (
	credit_id SERIAL PRIMARY KEY,
	first_name VARCHAR (50) NOT NULL,
	last_name VARCHAR (50) NOT NULL,
	type INT REFERENCES credittype (credittype_id)
);

-- Creating the junction table between broadcasts and credits

CREATE TABLE broadcasts_credits (
	broadcast_id INT REFERENCES broadcasts (broadcast_id),
	credit_id INT REFERENCES credits (credit_id),
	PRIMARY KEY (broadcast_id, credit_id)
);

-- Creating the credit type table

CREATE TABLE credittype (
	credittype_id SERIAL PRIMARY KEY,
	name VARCHAR (50) NOT NULL
);

-- Inserting into the credit type table

INSERT INTO credittype (name)
VALUES (
	('Instruktør'),
	('Producent'),
	('Executive producer'),
	('Hovedrolle'),
	('Birolle'),
	('Kamera'),
	('Musik'),
	('Klipper')
);

-- Creating the notification table

CREATE TABLE notifications (
	notification_id SERIAL PRIMARY KEY,
	created DATE  NOT NULL,
	content VARCHAR (500) NOT NULL
);

-- Creating the junction table between broadcasts and notifications

CREATE TABLE broadcasts_notifications (
	broadcast_id INT REFERENCES broadcast (broadcast_id),
	notification_id INT REFERENCES notifications (notification_id),
	PRIMARY KEY (broadcast_id, notification_id)
);

-- Creating the junction table between credits and notifications

CREATE TABLE credits_notifications (
	credit_id INT REFERENCES credits (credit_id),
	notification_id INT REFERENCES notifications (notification_id),
	PRIMARY KEY (credit_id, notification_id)
);