CREATE TABLE `user_score`
(
    id              int         NOT NULL AUTO_INCREMENT,
    time_played     int         NOT NULL,
    attempts        int         NOT NULL,
    result          int         NOT NULL,
    user_id         int         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);