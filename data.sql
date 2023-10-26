CREATE SCHEMA IF NOT EXISTS demo;

USE demo;

CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  first_name varchar(20) DEFAULT NULL,
  last_name varchar(20) DEFAULT NULL,
  username varchar(250) DEFAULT NULL,
  password varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO users (first_name, last_name, username, password )
values ('first', 'last', 'admin', 'admin123');


CREATE TABLE IF NOT EXISTS todos (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  is_done BOOLEAN NOT NULL,
  target_date DATE DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
