CREATE TABLE users
(
    id         int         NOT NULL AUTO_INCREMENT,
    name       varchar(50) NOT NULL,
    username   varchar(20) NOT NULL,
    password   varchar(1000) NOT NULL,
    email      varchar(50) NOT NULL,
    age        int         NOT NULL,
    location   varchar(50) NOT NULL,
    bio        varchar(50) NOT NULL,
    permission int         default (0),
    PRIMARY KEY (id),
    UNIQUE (username),
    UNIQUE (email)
);