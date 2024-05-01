-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: poste
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `agent` bit(1) NOT NULL,
  `cin` int NOT NULL,
  `datene` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `tel` int NOT NULL,
  `valide` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,_binary '\0',11111111,'1990-01-01 00:00:00.000000','user1@pi.tn','Samira','1234','harabi',21896982,_binary ''),(2,_binary '',23456789,'2001-02-02 00:00:00.000000','email2@example.com','Akrem','password2','trabelsi',23456789,_binary ''),(3,_binary '\0',34567890,'2002-03-03 00:00:00.000000','email3@example.com','Nom3','password3','Prenom3',34567890,_binary ''),(4,_binary '',45678901,'2003-04-04 00:00:00.000000','email4@example.com','Nom4','password4','Prenom4',45678901,_binary '\0'),(5,_binary '\0',56789012,'2004-05-05 00:00:00.000000','email5@example.com','Nom5','password5','Prenom5',56789012,_binary ''),(6,_binary '',33333333,'2024-04-26 00:00:00.000000','Abir@pi.tn','abir','1234','sabri',28348503,_binary ''),(8,_binary '\0',12345678,'1995-12-12 00:00:00.000000','Assem@pi.tn','Assem','1234','Bjeoui',55471369,_binary ''),(9,_binary '',123456789,'1997-03-01 00:00:00.000000','c.bdiri22380@pi.tn','chokri','1234','bdiri',21896982,_binary ''),(16,_binary '\0',10101010,'1990-01-01 00:00:00.000000','amir@pi.tn','Amir','1234','kouni',44896257,_binary ''),(17,_binary '\0',1960898,'1996-02-22 00:00:00.000000','jakie@pi.tn','jakie','1234','bdiri',36974558,_binary ''),(18,_binary '\0',95146320,'1996-02-22 00:00:00.000000','karim@pi.tn','Karim','1234','gharbi',26347841,_binary ''),(19,_binary '\0',19257496,'1996-01-15 00:00:00.000000','hela@pi.tn','hela','12345','ajleni',97247136,_binary '');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-01 20:21:25
