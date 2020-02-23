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
-- Temporary table structure for view `all_room_booked_and_unbooked`
--

DROP TABLE IF EXISTS `all_room_booked_and_unbooked`;
/*!50001 DROP VIEW IF EXISTS `all_room_booked_and_unbooked`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `all_room_booked_and_unbooked` AS SELECT 
 1 AS `book_id`,
 1 AS `hotelroom_id`,
 1 AS `hotel_id`,
 1 AS `hotel_name`,
 1 AS `room_type`,
 1 AS `room_id`,
 1 AS `room_price_per_night`,
 1 AS `number_of_people`,
 1 AS `check_in`,
 1 AS `check_out`,
 1 AS `km_to_beach`,
 1 AS `km_to_city`,
 1 AS `guest_rating`,
 1 AS `pool`,
 1 AS `evening_entertainment`,
 1 AS `kids_club`,
 1 AS `restaurant`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `booked_list`
--

DROP TABLE IF EXISTS `booked_list`;
/*!50001 DROP VIEW IF EXISTS `booked_list`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `booked_list` AS SELECT 
 1 AS `book_id`,
 1 AS `hotelroom_id`,
 1 AS `hotel_id`,
 1 AS `hotel_name`,
 1 AS `room_type`,
 1 AS `room_id`,
 1 AS `room_price_per_night`,
 1 AS `number_of_people`,
 1 AS `check_in`,
 1 AS `check_out`,
 1 AS `km_to_beach`,
 1 AS `km_to_city`,
 1 AS `guest_rating`,
 1 AS `pool`,
 1 AS `evening_entertainment`,
 1 AS `kids_club`,
 1 AS `restaurant`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `all_room`
--

DROP TABLE IF EXISTS `all_room`;
/*!50001 DROP VIEW IF EXISTS `all_room`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `all_room` AS SELECT 
 1 AS `book_id`,
 1 AS `hotelroom_id`,
 1 AS `hotel_id`,
 1 AS `hotel_name`,
 1 AS `room_type`,
 1 AS `room_id`,
 1 AS `room_price_per_night`,
 1 AS `number_of_people`,
 1 AS `check_in`,
 1 AS `check_out`,
 1 AS `km_to_beach`,
 1 AS `km_to_city`,
 1 AS `guest_rating`,
 1 AS `pool`,
 1 AS `evening_entertainment`,
 1 AS `kids_club`,
 1 AS `restaurant`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `all_room_booked_and_unbooked`
--

/*!50001 DROP VIEW IF EXISTS `all_room_booked_and_unbooked`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `all_room_booked_and_unbooked` AS select `all_room`.`book_id` AS `book_id`,`all_room`.`hotelroom_id` AS `hotelroom_id`,`all_room`.`hotel_id` AS `hotel_id`,`all_room`.`hotel_name` AS `hotel_name`,`all_room`.`room_type` AS `room_type`,`all_room`.`room_id` AS `room_id`,`all_room`.`room_price_per_night` AS `room_price_per_night`,`all_room`.`number_of_people` AS `number_of_people`,`all_room`.`check_in` AS `check_in`,`all_room`.`check_out` AS `check_out`,`all_room`.`km_to_beach` AS `km_to_beach`,`all_room`.`km_to_city` AS `km_to_city`,`all_room`.`guest_rating` AS `guest_rating`,`all_room`.`pool` AS `pool`,`all_room`.`evening_entertainment` AS `evening_entertainment`,`all_room`.`kids_club` AS `kids_club`,`all_room`.`restaurant` AS `restaurant` from `all_room` union all select `booked_list`.`book_id` AS `book_id`,`booked_list`.`hotelroom_id` AS `hotelroom_id`,`booked_list`.`hotel_id` AS `hotel_id`,`booked_list`.`hotel_name` AS `hotel_name`,`booked_list`.`room_type` AS `room_type`,`booked_list`.`room_id` AS `room_id`,`booked_list`.`room_price_per_night` AS `room_price_per_night`,`booked_list`.`number_of_people` AS `number_of_people`,`booked_list`.`check_in` AS `check_in`,`booked_list`.`check_out` AS `check_out`,`booked_list`.`km_to_beach` AS `km_to_beach`,`booked_list`.`km_to_city` AS `km_to_city`,`booked_list`.`guest_rating` AS `guest_rating`,`booked_list`.`pool` AS `pool`,`booked_list`.`evening_entertainment` AS `evening_entertainment`,`booked_list`.`kids_club` AS `kids_club`,`booked_list`.`restaurant` AS `restaurant` from `booked_list` order by `hotelroom_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `booked_list`
--

/*!50001 DROP VIEW IF EXISTS `booked_list`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `booked_list` AS select `bookings`.`book_id` AS `book_id`,`all_room`.`hotelroom_id` AS `hotelroom_id`,`bookings`.`hotel_id` AS `hotel_id`,`all_room`.`hotel_name` AS `hotel_name`,`all_room`.`room_type` AS `room_type`,`bookings`.`room_id` AS `room_id`,`all_room`.`room_price_per_night` AS `room_price_per_night`,`bookings`.`number_of_people` AS `number_of_people`,`bookings`.`check_in` AS `check_in`,`bookings`.`check_out` AS `check_out`,`all_room`.`km_to_beach` AS `km_to_beach`,`all_room`.`km_to_city` AS `km_to_city`,`all_room`.`guest_rating` AS `guest_rating`,`all_room`.`pool` AS `pool`,`all_room`.`evening_entertainment` AS `evening_entertainment`,`all_room`.`kids_club` AS `kids_club`,`all_room`.`restaurant` AS `restaurant` from (`bookings` join `all_room`) where ((`bookings`.`hotel_id` = `all_room`.`hotel_id`) and (`bookings`.`room_id` = `all_room`.`room_id`)) order by `bookings`.`book_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `all_room`
--

/*!50001 DROP VIEW IF EXISTS `all_room`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `all_room` AS select `hotelrooms`.`book_id` AS `book_id`,`hotelrooms`.`hotelroom_id` AS `hotelroom_id`,`hotels`.`hotel_id` AS `hotel_id`,`hotels`.`hotel_name` AS `hotel_name`,`rooms`.`room_type` AS `room_type`,`rooms`.`room_id` AS `room_id`,`rooms`.`room_price_per_night` AS `room_price_per_night`,`hotelrooms`.`number_of_people` AS `number_of_people`,`hotelrooms`.`check_in` AS `check_in`,`hotelrooms`.`check_out` AS `check_out`,`hotels`.`km_to_beach` AS `km_to_beach`,`hotels`.`km_to_city` AS `km_to_city`,`hotels`.`guest_rating` AS `guest_rating`,`hotels`.`pool` AS `pool`,`hotels`.`evening_entertainment` AS `evening_entertainment`,`hotels`.`kids_club` AS `kids_club`,`hotels`.`restaurant` AS `restaurant` from ((`hotelrooms` left join `rooms` on((`hotelrooms`.`room` = `rooms`.`room_id`))) left join `hotels` on((`hotelrooms`.`hotel` = `hotels`.`hotel_id`))) order by `hotelrooms`.`hotelroom_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Dumping events for database 'holidaymaker'
--

--
-- Dumping routines for database 'holidaymaker'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-23 21:04:30
