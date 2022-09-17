CREATE TABLE `airports` (
  `id` int NOT NULL AUTO_INCREMENT,
  `abreviation` varchar(45) NOT NULL,
  `name` varchar(80) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
