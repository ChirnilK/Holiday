-- MySQL dump 10.13  Distrib 5.7.29, for Win64 (x86_64)
--
-- Host: localhost    Database: holidaymaker
-- ------------------------------------------------------
-- Server version	5.7.29-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `customer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `social_secnr` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `telephonenr` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `social_secnr_UNIQUE` (`social_secnr`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Tobius','7352728','07066432356'),(2,'Florind','93837363','0732722356'),(3,'Hassan','38336723','0727342356'),(4,'Ammer','940483773','070722356244'),(5,'Pontos','8112183947','0709388739'),(6,'Chiharu','338374383','0702472778'),(7,'Mantas','99383774','0732827262'),(8,'Johan','00293746','0738556733'),(9,'Taki','0907136840','0738372628'),(10,'Ryu','0706240413','070655433'),(11,'Mami','7408049983','0907653347'),(12,'Patrik','88473837838','09083736363'),(13,'Masatoshi','7938374837','09077363522'),(14,'Kalle','8876538272','0706765222'),(15,'Kaisa','8344759382','0732569780'),(16,'Ingrid','56837488483','0765377734'),(17,'Ulf','5302205548','0764839222'),(18,'Elin','8907228837','092828834'),(19,'Alex','9904290725','0763548875'),(20,'Yuka','7209083811','0783457726'),(21,'Christian','7411138362','0762526373'),(22,'Victor','9808047382','0763846282'),(23,'Sugi','6683726494','0703373663');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-23 19:35:32
