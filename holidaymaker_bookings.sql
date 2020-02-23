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
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookings` (
  `book_id` int(10) unsigned NOT NULL,
  `customer_id` int(10) unsigned NOT NULL,
  `hotel_id` int(10) unsigned NOT NULL,
  `room_id` int(10) unsigned NOT NULL,
  `option_id` int(10) unsigned NOT NULL,
  `number_of_people` int(10) unsigned NOT NULL,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  PRIMARY KEY (`book_id`),
  KEY `bookinghotel-hotel_idx` (`hotel_id`),
  KEY `bookinghtotel-hotei_idx` (`hotel_id`),
  KEY `booking-customer_idx` (`customer_id`),
  KEY `bookings-room_idx` (`room_id`),
  KEY `bookings-option_idx` (`option_id`),
  CONSTRAINT `bookings-customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookings-hotel` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`hotel_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookings-option` FOREIGN KEY (`option_id`) REFERENCES `options` (`option_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookings-room` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,6,1,4,2,4,'2020-06-20','2020-06-30'),(2,5,5,1,2,1,'2020-06-12','2020-06-14'),(3,1,2,3,2,2,'2020-07-13','2020-07-23'),(4,2,4,2,3,2,'2020-07-01','2020-07-05'),(5,3,6,5,5,3,'2020-06-09','2020-06-28'),(6,4,3,2,6,2,'2020-07-20','2020-07-30'),(7,10,4,4,2,4,'2020-06-23','2020-07-14'),(8,9,1,2,2,2,'2020-07-20','2020-07-23'),(9,8,2,2,2,2,'2020-06-29','2020-07-03'),(10,7,4,3,3,2,'2020-07-15','2020-07-18'),(11,15,2,1,3,1,'2020-07-10','2020-07-12'),(12,2,3,3,2,2,'2020-07-08','2020-07-10'),(13,6,5,5,2,3,'2020-07-17','2020-07-19'),(14,16,6,3,6,2,'2020-06-29','2020-07-01'),(15,5,6,3,2,2,'2020-06-01','2020-06-03'),(16,4,5,1,3,1,'2020-06-09','2020-06-10'),(17,11,2,4,2,3,'2020-06-20','2020-06-24'),(18,13,2,2,2,2,'2020-06-16','2020-06-22'),(19,14,5,5,4,3,'2020-07-03','2020-07-07'),(20,12,4,4,2,3,'2020-06-25','2020-07-01'),(21,17,5,3,2,2,'2020-06-18','2020-06-21'),(22,18,3,2,2,2,'2020-06-28','2020-06-30'),(23,19,5,4,6,4,'2020-06-25','2020-06-27'),(24,20,4,4,2,3,'2020-07-28','2020-07-30'),(25,21,5,3,2,2,'2020-07-17','2020-07-20'),(26,22,6,3,2,2,'2020-07-07','2020-07-10');
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
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
