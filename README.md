# spring-security-jwt
Learning Spring Security and JWT
--------------------------------

The code here is inspired from JWT/OAuth + Spring Security playlists from JavaBrains youtube channel. An excellent resource to learn Spring.
Do check out the 'notes' file for links to all the videos used to study and summary of the concepts.

V1 - JWT token generation and validation for the single static user. No database integration.

V2 - integrating MySQL database and JPA with the existing spring security app.

MySQL DB Queries:
CREATE TABLE user (
    id int NOT NULL AUTO_INCREMENT,
    active int,
    username varchar(255),
    password varchar(255),
    roles varchar(255),
	PRIMARY KEY (id)
);

INSERT INTO user (active, username, password, roles)
VALUES (0, 'jerry', 'pwd', 'ROLE_ADMIN');

INSERT INTO user (active, username, password, roles)
VALUES (1, 'tom', 'pwd', 'ROLE_USER');