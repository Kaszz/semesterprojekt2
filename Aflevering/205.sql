CREATE TABLE accounts (
	account_id SERIAL PRIMARY KEY,
	email VARCHAR (100) UNIQUE NOT NULL,
	password VARCHAR (50) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL
);

CREATE TABLE admin (
	admin_id SERIAL PRIMARY KEY,
	account_id INT NOT NULL REFERENCES accounts (account_id)
);

CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	account_id INT NOT NULL REFERENCES accounts (account_id)
);

CREATE TABLE broadcast_types (
	broadcast_type_id SERIAL PRIMARY KEY,
	broadcast_type VARCHAR (10)
);

INSERT INTO broadcast_types
SELECT 1, 'Series' UNION ALL
SELECT 2, 'Movie' UNION ALL
SELECT 3, 'Liveshow';

CREATE TABLE broadcasts (
	broadcast_id SERIAL PRIMARY KEY,
	broadcast_type_id INT REFERENCES broadcast_types (broadcast_type_id),
	title VARCHAR (200) UNIQUE NOT NULL,
	bio TEXT NOT NULL,
	launchYear INT NOT NULL,
	account_id int NOT NULL REFERENCES accounts,
	CONSTRAINT broadcasts_AltPK UNIQUE (broadcast_id, broadcast_type_id)
);

CREATE TABLE series (
	series_id SERIAL UNIQUE NOT NULL,
	broadcast_id INT REFERENCES broadcasts (broadcast_id) ON DELETE CASCADE,
	broadcast_type_id INT NOT NULL DEFAULT 1 CHECK (broadcast_type_id = 1), -- series
	PRIMARY KEY (broadcast_id, broadcast_type_id)
);

CREATE TABLE seasons (
	season_id SERIAL UNIQUE NOT NULL,
	broadcast_id INT REFERENCES broadcasts (broadcast_id) ON DELETE CASCADE,
	season_no INT NOT NULL,
	PRIMARY KEY (season_id, broadcast_id)
);

CREATE TABLE episodes (
	episode_id SERIAL NOT NULL,
	season_id INT REFERENCES seasons (season_id),
	broadcast_id INT REFERENCES broadcasts (broadcast_id) ON DELETE CASCADE,
	episode_name VARCHAR (50) NOT NULL,
	episode_no INT NOT NULL,
	PRIMARY KEY (episode_id, season_id, broadcast_id)
);

CREATE TABLE movies (
	movie_id SERIAL UNIQUE NOT NULL,
	broadcast_id INT REFERENCES broadcasts (broadcast_id) ON DELETE CASCADE,
	broadcast_type_id INT NOT NULL DEFAULT 2 CHECK (broadcast_type_id = 2), -- movies
	PRIMARY KEY (broadcast_id, broadcast_type_id)
);

CREATE TABLE liveshow (
	liveshow_id SERIAL UNIQUE NOT NULL,
	broadcast_id INT REFERENCES broadcasts (broadcast_id) ON DELETE CASCADE,
	broadcast_type_id INT NOT NULL DEFAULT 3 CHECK (broadcast_type_id = 3), -- liveshow
	location VARCHAR(100),
	PRIMARY KEY (broadcast_id, broadcast_type_id)
);

CREATE TABLE credits (
	credit_id SERIAL PRIMARY KEY,
	first_name VARCHAR (50) NOT NULL,
	last_name VARCHAR (50) NOT NULL,
	credit_type VARCHAR(50) NOT NULL
);

CREATE TABLE broadcasts_credits (
	broadcast_id INT REFERENCES broadcasts (broadcast_id) ON DELETE CASCADE,
	credit_id INT REFERENCES credits (credit_id) ON DELETE CASCADE,
	PRIMARY KEY (broadcast_id, credit_id)
);

CREATE TABLE notifications (
	notification_id SERIAL PRIMARY KEY,
	created DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
	change VARCHAR (500) NOT NULL,
	seen boolean NOT NULL,
	user_name VARCHAR (100) NOT NULL
);


INSERT INTO accounts (email, password, first_name, last_name) VALUES ('test@gmail.com', '123', 'John', 'Doe');
INSERT INTO users (account_id) VALUES (1);

INSERT INTO broadcasts (broadcast_type_id, title, bio, launchyear, account_id) VALUES (2, 'Avatar', 'alienmand', 2009, 1);
INSERT INTO broadcasts (broadcast_type_id, title, bio, launchyear, account_id) VALUES (2, 'Terminator', 'robotmand', 1984, 1);
INSERT INTO movies (broadcast_id) VALUES (1);
INSERT INTO movies (broadcast_id) VALUES (2);

INSERT INTO broadcasts (broadcast_type_id, title, bio, launchyear, account_id) VALUES (3, 'X-Factor', 'musik', 2020, 1);
INSERT INTO broadcasts (broadcast_type_id, title, bio, launchyear, account_id) VALUES (3, 'F1', 'sport', 2019, 1);
INSERT INTO liveshow (broadcast_id, location) VALUES (3, 'DR-Byen');
INSERT INTO liveshow (broadcast_id, location) VALUES (4, 'Racerbanen');

INSERT INTO broadcasts (broadcast_type_id, title, bio, launchyear, account_id) VALUES (1, 'Badehotellet', 'Drama', 2016, 1);
INSERT INTO series (broadcast_id) VALUES (5);
INSERT INTO seasons (broadcast_id, season_no) VALUES (5, 1);
INSERT INTO seasons (broadcast_id, season_no) VALUES (5, 2);

INSERT INTO episodes (season_id, broadcast_id, episode_name, episode_no) VALUES (1, 5, 'En sommer ved Vesterhavet', 1);
INSERT INTO episodes (season_id, broadcast_id, episode_name, episode_no) VALUES (1, 5, 'Herrebesøg', 2);
INSERT INTO episodes (season_id, broadcast_id, episode_name, episode_no) VALUES (1, 5, 'Fatale følger', 3);
INSERT INTO episodes (season_id, broadcast_id, episode_name, episode_no) VALUES (2, 5, 'Amandas valg', 1);
INSERT INTO episodes (season_id, broadcast_id, episode_name, episode_no) VALUES (2, 5, 'Den store fest', 2);
INSERT INTO episodes (season_id, broadcast_id, episode_name, episode_no) VALUES (2, 5, 'Sønnen fra Amerika', 3);