DROP TABLE IF EXISTS `football_objects`;
CREATE TABLE football_objects( 
id long AUTO_INCREMENT, 
name VARCHAR(20) NOT NULL,
price int NOT NULL,
age int NOT NULL,
star_player boolean NOT NULL,
PRIMARY KEY (id)
);
INSERT INTO `football_objects` (`name`, `price`, `age`, `star_player`) VALUES ('player1', 1, 25, true);
INSERT INTO `football_objects` (`name`, `price`, `age`, `star_player`) VALUES ('player2', 3, 32, false);

