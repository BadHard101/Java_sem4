CREATE TABLE IF NOT EXISTS levels
(
    id SERIAL NOT NULL PRIMARY KEY,
    level_name VARCHAR(128) NOT NULL,
    complexity INTEGER NOT NULL,
    game_id INTEGER
);

CREATE TABLE IF NOT EXISTS games
(
    id SERIAL NOT NULL PRIMARY KEY,
    game_name VARCHAR(128) NOT NULL,
    creation_date VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id SERIAL NOT NULL PRIMARY KEY ,
    username VARCHAR(128) NOT NULL ,
    password VARCHAR(128) NOT NULL,
    userrole VARCHAR(128) NOT NULL
    );