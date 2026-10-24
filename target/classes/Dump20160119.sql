-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sg
-- ------------------------------------------------------
-- Server version	5.6.26-enterprise-commercial-advanced-log

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
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country_code` varchar(2) DEFAULT NULL,
  `country_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jare70vqqti665ds3b2eh7rk8` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,'StarCraft II','account','sc2'),(2,'Warcraft III: The Frozen Throne','w3arena account','war3'),(3,'Heroes of the Storm','battleTag','hots'),(4,'League of Legends','account','lol'),(5,'Counter-Strike: Global Offensive','steam account','cs'),(6,'Dota 2','steam account','dota'),(7,'World of Tanks','official account','wot'),(8,'Hearthstone: Heroes of Warcraft','battleTag','hs'),(9,'Diablo III','battleTag','diablo');
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_user`
--

DROP TABLE IF EXISTS `game_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_user` (
  `account` varchar(30) NOT NULL,
  `id_user` bigint(20) NOT NULL DEFAULT '0',
  `id_game` bigint(20) NOT NULL DEFAULT '0',
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_game`,`id_user`),
  KEY `FK_99c3snpckyw7bjgr4s0bghlet` (`id_user`),
  CONSTRAINT `FK_8nprfl8j4b1waacgdawl683p0` FOREIGN KEY (`id_game`) REFERENCES `game` (`id`),
  CONSTRAINT `FK_99c3snpckyw7bjgr4s0bghlet` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_user`
--

LOCK TABLES `game_user` WRITE;
/*!40000 ALTER TABLE `game_user` DISABLE KEYS */;
INSERT INTO `game_user` VALUES ('sadsa',4,1,'1'),('dsadsa',5,1,'dsads'),('dsads',4,2,'3'),('dsadsads',5,2,'fdsfdsf'),('dsds',4,3,'1'),('выфв',5,3,'ыфвы'),('22222',1,7,'2222'),('111',1,9,'wew');
/*!40000 ALTER TABLE `game_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `game` varchar(255) DEFAULT NULL,
  `invitation` bit(1) DEFAULT NULL,
  `message` varchar(250) NOT NULL,
  `read_ed` bit(1) DEFAULT NULL,
  `team_id` bigint(20) DEFAULT NULL,
  `user_from` varchar(255) DEFAULT NULL,
  `id_user` bigint(20) NOT NULL,
  `is_show` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_iq3x04y95yx3j80m9e3t20gmf` (`id_user`),
  CONSTRAINT `FK_iq3x04y95yx3j80m9e3t20gmf` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=609 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'2015-12-18 14:31:56',NULL,'\0','ddd','',NULL,'Lightning3',4,''),(2,'2015-12-18 14:31:58',NULL,'\0','1112312312','',NULL,'Lightning3',4,''),(3,'2015-12-18 14:32:04',NULL,'\0','fdfdsfds','',NULL,'Lightning3',4,''),(5,'2015-12-18 14:32:22',NULL,'\0','fdfds','',NULL,'Lightning3',4,''),(6,'2015-12-18 14:32:57',NULL,'\0','fdsfdsf','\0',NULL,'Lightning2',5,''),(7,'2015-12-18 14:32:59',NULL,'\0','dasdsadsa','\0',NULL,'Lightning2',5,''),(8,'2015-12-18 14:33:01',NULL,'\0','gfgfd','\0',NULL,'Lightning2',5,''),(9,'2015-12-18 14:33:07',NULL,'\0','111','\0',NULL,'Lightning2',5,''),(10,'2015-12-18 14:40:04',NULL,'\0','sdsadsadsa','',NULL,'Lightning3',4,''),(11,'2015-12-18 14:40:06',NULL,'\0','fdsfds','',NULL,'Lightning3',4,''),(12,'2015-12-18 14:40:20',NULL,'\0','111','\0',NULL,'Lightning2',5,''),(13,'2015-12-18 14:40:23',NULL,'\0','1','\0',NULL,'Lightning2',5,''),(14,'2015-12-18 14:40:28',NULL,'\0','22','',NULL,'Lightning3',4,''),(15,'2015-12-18 14:40:30',NULL,'\0','22','',NULL,'Lightning3',4,''),(16,'2015-12-18 14:44:53',NULL,'\0','222','',NULL,'Lightning3',4,''),(17,'2015-12-18 14:44:55',NULL,'\0','222','',NULL,'Lightning3',4,''),(18,'2015-12-18 14:44:58',NULL,'\0','11','\0',NULL,'Lightning2',5,''),(19,'2015-12-18 14:45:00',NULL,'\0','11','\0',NULL,'Lightning2',5,''),(20,'2015-12-18 14:45:04',NULL,'\0','sdsad','',NULL,'Lightning3',4,''),(21,'2015-12-18 14:45:06',NULL,'\0','fdsfds','',NULL,'Lightning3',4,''),(22,'2015-12-18 14:45:25',NULL,'\0','11','',NULL,'Lightning3',4,''),(23,'2015-12-18 14:45:27',NULL,'\0','111','',NULL,'Lightning3',4,''),(24,'2015-12-18 14:45:32',NULL,'\0','11','\0',NULL,'Lightning2',5,''),(25,'2015-12-18 14:45:34',NULL,'\0','111','\0',NULL,'Lightning2',5,''),(26,'2015-12-18 14:50:57',NULL,'\0','32','',NULL,'Lightning3',4,''),(27,'2015-12-18 14:50:58',NULL,'\0','32','',NULL,'Lightning3',4,''),(28,'2015-12-18 14:52:11','StarCraft II','','<img height=\"20\" src=\"/user/avatarimage?name=Lightning3\"> <a href=\'/profile/Lightning3\'>Lightning3</a> invites you to a clan! <a href=\'/team/58\'>dsdsadsa</a> ','',58,'Lightning3',4,''),(29,'2015-12-18 14:52:15',NULL,'\0','fdsfsdfds','',NULL,'Lightning3',4,''),(30,'2015-12-18 14:52:19',NULL,'\0','dsad asd asdas','',NULL,'Lightning3',4,''),(31,'2015-12-18 14:52:21',NULL,'\0','dsad asd asdasdsad asd asdas','',NULL,'Lightning3',4,''),(32,'2015-12-18 14:52:23',NULL,'\0','dsad asd asdasdsad asd asdas','',NULL,'Lightning3',4,'\0'),(33,'2015-12-18 14:54:54',NULL,'\0','ew','',NULL,'Lightning3',4,''),(34,'2015-12-18 14:54:56',NULL,'\0','ewq','',NULL,'Lightning3',4,''),(35,'2015-12-18 14:56:24',NULL,'\0','dsadas','',NULL,'Lightning',4,''),(36,'2015-12-18 14:56:26',NULL,'\0','dsadsa','',NULL,'Lightning',4,''),(37,'2015-12-18 14:56:44',NULL,'\0','dsadsa','',NULL,'Lightning2',1,''),(38,'2015-12-18 14:56:46',NULL,'\0','fsdfsdf','',NULL,'Lightning2',1,''),(39,'2015-12-18 14:56:48',NULL,'\0','fdsfdf','',NULL,'Lightning2',1,''),(40,'2015-12-18 15:03:51',NULL,'\0','1','',NULL,'Lightning',4,''),(41,'2015-12-18 15:03:55',NULL,'\0','2','',NULL,'Lightning',4,''),(42,'2015-12-18 15:11:08',NULL,'\0','выв','',NULL,'Lightning',4,''),(43,'2015-12-18 15:11:47',NULL,'\0','11','',NULL,'Lightning2',1,''),(44,'2015-12-18 15:12:14',NULL,'\0','dsadsa','',NULL,'Lightning2',1,''),(45,'2015-12-18 15:23:15',NULL,'\0','dsadsa','',NULL,'Lightning2',1,''),(46,'2015-12-18 15:23:22',NULL,'\0','fsdf','',NULL,'Lightning2',1,''),(47,'2015-12-18 15:28:00',NULL,'\0','2222','',NULL,'Lightning2',1,''),(48,'2015-12-18 18:13:15',NULL,'\0','Deniska deiska','',NULL,'Lightning2',1,''),(49,'2015-12-18 18:17:10',NULL,'\0','fdsfds','',NULL,'Lightning',4,''),(50,'2015-12-18 18:18:33',NULL,'\0','dsadsa','',NULL,'Lightning',4,''),(51,'2015-12-18 18:18:35',NULL,'\0','fdsf','',NULL,'Lightning',4,''),(52,'2015-12-18 18:20:52',NULL,'\0','111','',NULL,'Lightning',4,''),(53,'2015-12-18 18:21:18',NULL,'\0','222','',NULL,'Lightning2',1,''),(54,'2015-12-18 18:21:56',NULL,'\0','2','',NULL,'Lightning',4,''),(55,'2015-12-18 18:23:56',NULL,'\0','22','',NULL,'Lightning',4,''),(56,'2015-12-18 18:24:29',NULL,'\0','fdsfds','',NULL,'Lightning',4,''),(57,'2015-12-18 18:27:24',NULL,'\0','222','',NULL,'Lightning',4,''),(58,'2015-12-18 18:30:09',NULL,'\0','dsadsadsa','',NULL,'Lightning2',1,''),(59,'2015-12-18 18:30:18',NULL,'\0','dsadsa','',NULL,'Lightning',4,''),(60,'2015-12-18 18:30:40',NULL,'\0','fsdfsdfds','',NULL,'Lightning',4,''),(61,'2015-12-18 18:31:02',NULL,'\0','fsdfsd','',NULL,'Lightning',4,''),(62,'2015-12-18 18:34:18',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(63,'2015-12-18 18:54:40',NULL,'\0','dsdsa','',NULL,'Lightning2',1,''),(64,'2015-12-18 18:54:47',NULL,'\0','вфыв','',NULL,'Lightning',4,''),(65,'2015-12-18 18:54:52',NULL,'\0','dsads','',NULL,'Lightning2',1,''),(66,'2015-12-18 19:07:42',NULL,'\0','fdsfsd','',NULL,'Lightning2',1,''),(67,'2015-12-18 19:07:52',NULL,'\0','fdsfds','',NULL,'Lightning2',1,''),(68,'2015-12-18 19:13:09',NULL,'\0','dsds','',NULL,'Lightning2',1,''),(69,'2015-12-18 19:15:02',NULL,'\0','dsads','',NULL,'Lightning',4,''),(70,'2015-12-18 19:16:38',NULL,'\0','dsds','',NULL,'Lightning',4,''),(71,'2015-12-18 19:16:46',NULL,'\0','fdsfds','',NULL,'Lightning',4,''),(72,'2015-12-18 19:17:02',NULL,'\0','fdsfdsf','',NULL,'Lightning2',1,''),(73,'2015-12-18 19:17:11',NULL,'\0','gfdgfdg','',NULL,'Lightning2',1,''),(74,'2015-12-18 19:17:26',NULL,'\0','gfdgfd','',NULL,'Lightning2',1,''),(75,'2015-12-18 19:18:09',NULL,'\0','fdsfds','',NULL,'Lightning2',1,''),(76,'2015-12-18 19:18:11',NULL,'\0','dsadsa','',NULL,'Lightning2',1,''),(77,'2015-12-18 19:20:54',NULL,'\0','fdfd','',NULL,'Lightning2',1,''),(78,'2015-12-18 19:21:19',NULL,'\0','fdsfds','',NULL,'Lightning',4,''),(79,'2015-12-18 19:21:22',NULL,'\0','fdsf','',NULL,'Lightning',4,''),(80,'2015-12-18 20:04:44',NULL,'\0','fdsfd','',NULL,'Lightning2',1,''),(81,'2015-12-18 20:04:58',NULL,'\0','fdsfds','',NULL,'Lightning2',1,''),(82,'2015-12-18 20:04:59',NULL,'\0','fdsfd','',NULL,'Lightning2',1,''),(83,'2015-12-18 20:05:12',NULL,'\0','fdsfd','',NULL,'Lightning2',1,''),(84,'2015-12-18 20:05:21',NULL,'\0','s','',NULL,'Lightning2',1,''),(85,'2015-12-18 20:05:22',NULL,'\0','s','',NULL,'Lightning2',1,''),(86,'2015-12-18 20:05:24',NULL,'\0','s','',NULL,'Lightning2',1,''),(87,'2015-12-18 20:44:19',NULL,'\0','dsfsdf','',NULL,'Lightning',4,''),(88,'2015-12-18 20:44:32',NULL,'\0','dfdsfdsfds','',NULL,'Lightning2',1,''),(89,'2015-12-18 20:44:34',NULL,'\0','fdsfd','',NULL,'Lightning2',1,''),(90,'2015-12-18 21:02:28',NULL,'\0','fdfds','',NULL,'Lightning2',1,''),(91,'2015-12-18 21:03:10',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(92,'2015-12-18 21:04:07',NULL,'\0','fdsfd','',NULL,'Lightning2',1,''),(93,'2015-12-18 21:04:08',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(94,'2015-12-18 21:04:22',NULL,'\0','111','',NULL,'Lightning2',1,''),(95,'2015-12-18 21:04:23',NULL,'\0','222','',NULL,'Lightning2',1,''),(96,'2015-12-18 21:04:57',NULL,'\0','fd','',NULL,'Lightning2',1,''),(97,'2015-12-18 21:07:56',NULL,'\0','dd','',NULL,'Lightning2',1,''),(98,'2015-12-18 21:09:09',NULL,'\0','1','',NULL,'Lightning2',1,''),(99,'2015-12-18 21:10:16',NULL,'\0','ss','',NULL,'Lightning2',1,''),(100,'2015-12-18 21:20:06',NULL,'\0','1','',NULL,'Lightning2',1,''),(101,'2015-12-18 21:31:26',NULL,'\0','1','',NULL,'Lightning2',1,''),(102,'2015-12-18 21:31:36',NULL,'\0','1','',NULL,'Lightning2',1,''),(103,'2015-12-18 21:31:37',NULL,'\0','1','',NULL,'Lightning2',1,''),(104,'2015-12-18 21:31:39',NULL,'\0','1','',NULL,'Lightning2',1,''),(105,'2015-12-18 21:31:41',NULL,'\0','1','',NULL,'Lightning2',1,''),(106,'2015-12-18 21:32:16',NULL,'\0','2','',NULL,'Lightning2',1,''),(107,'2015-12-18 21:32:17',NULL,'\0','3','',NULL,'Lightning2',1,''),(108,'2015-12-18 21:32:19',NULL,'\0','3','',NULL,'Lightning2',1,''),(109,'2015-12-18 21:32:20',NULL,'\0','3','',NULL,'Lightning2',1,''),(110,'2015-12-18 21:32:45',NULL,'\0','1','',NULL,'Lightning2',1,''),(111,'2015-12-18 21:32:47',NULL,'\0','2','',NULL,'Lightning2',1,''),(112,'2015-12-18 21:32:48',NULL,'\0','3','',NULL,'Lightning2',1,''),(113,'2015-12-18 21:32:49',NULL,'\0','4','',NULL,'Lightning2',1,''),(114,'2015-12-18 21:32:52',NULL,'\0','5','',NULL,'Lightning2',1,''),(115,'2015-12-18 21:32:54',NULL,'\0','2','',NULL,'Lightning2',1,''),(116,'2015-12-18 21:32:55',NULL,'\0','1','',NULL,'Lightning2',1,''),(117,'2015-12-18 21:39:23',NULL,'\0','1','',NULL,'Lightning2',1,''),(118,'2015-12-18 21:39:50',NULL,'\0','222','',NULL,'Lightning2',1,''),(119,'2015-12-18 21:39:52',NULL,'\0','333','',NULL,'Lightning2',1,''),(120,'2015-12-18 21:40:08',NULL,'\0','2','',NULL,'Lightning2',1,''),(121,'2015-12-18 21:40:18',NULL,'\0','1','',NULL,'Lightning2',1,''),(122,'2015-12-18 21:40:26',NULL,'\0','3','',NULL,'Lightning2',1,''),(123,'2015-12-18 21:40:33',NULL,'\0','4','',NULL,'Lightning2',1,''),(124,'2015-12-18 21:41:38',NULL,'\0','2','',NULL,'Lightning2',1,''),(125,'2015-12-18 21:41:39',NULL,'\0','3','',NULL,'Lightning2',1,''),(126,'2015-12-18 21:41:40',NULL,'\0','3','',NULL,'Lightning2',1,''),(127,'2015-12-18 21:41:45',NULL,'\0','1','',NULL,'Lightning2',1,''),(128,'2015-12-18 21:41:47',NULL,'\0','2','',NULL,'Lightning2',1,''),(129,'2015-12-18 21:41:48',NULL,'\0','3','',NULL,'Lightning2',1,''),(130,'2015-12-18 21:41:49',NULL,'\0','4','',NULL,'Lightning2',1,''),(131,'2015-12-18 21:50:07',NULL,'\0','1','',NULL,'Lightning2',1,''),(132,'2015-12-18 21:50:09',NULL,'\0','1','',NULL,'Lightning2',1,''),(133,'2015-12-18 21:50:10',NULL,'\0','1','',NULL,'Lightning2',1,''),(134,'2015-12-18 21:50:11',NULL,'\0','1','',NULL,'Lightning2',1,''),(135,'2015-12-18 21:50:15',NULL,'\0','1','',NULL,'Lightning2',1,''),(136,'2015-12-18 21:50:17',NULL,'\0','1','',NULL,'Lightning2',1,''),(137,'2015-12-18 21:50:28',NULL,'\0','1','',NULL,'Lightning2',1,''),(138,'2015-12-18 21:55:39',NULL,'\0','1','',NULL,'Lightning2',1,''),(139,'2015-12-18 21:55:46',NULL,'\0','1','',NULL,'Lightning2',1,''),(140,'2015-12-18 21:55:47',NULL,'\0','2','',NULL,'Lightning2',1,''),(141,'2015-12-18 21:55:49',NULL,'\0','3','',NULL,'Lightning2',1,''),(142,'2015-12-18 21:55:51',NULL,'\0','4','',NULL,'Lightning2',1,''),(143,'2015-12-18 21:55:53',NULL,'\0','5','',NULL,'Lightning2',1,''),(144,'2015-12-18 21:58:44',NULL,'\0','312321','',NULL,'Lightning2',1,''),(145,'2015-12-18 21:58:52',NULL,'\0','1312321','',NULL,'Lightning2',1,''),(146,'2015-12-18 21:58:54',NULL,'\0','123213','',NULL,'Lightning2',1,''),(147,'2015-12-18 21:59:09',NULL,'\0','1','',NULL,'Lightning2',1,''),(148,'2015-12-18 21:59:11',NULL,'\0','1','',NULL,'Lightning2',1,''),(149,'2015-12-18 22:00:28',NULL,'\0','2','',NULL,'Lightning2',1,''),(150,'2015-12-18 22:00:32',NULL,'\0','432','',NULL,'Lightning2',1,''),(151,'2015-12-18 22:00:34',NULL,'\0','4324','',NULL,'Lightning2',1,''),(152,'2015-12-18 22:01:31',NULL,'\0','1','',NULL,'Lightning2',1,''),(153,'2015-12-18 22:01:33',NULL,'\0','2','',NULL,'Lightning2',1,''),(154,'2015-12-18 22:01:34',NULL,'\0','3','',NULL,'Lightning2',1,''),(155,'2015-12-18 22:01:36',NULL,'\0','4','',NULL,'Lightning2',1,''),(156,'2015-12-18 22:01:37',NULL,'\0','5','',NULL,'Lightning2',1,''),(157,'2015-12-18 22:01:39',NULL,'\0','6','',NULL,'Lightning2',1,''),(158,'2015-12-18 22:01:40',NULL,'\0','7','',NULL,'Lightning2',1,''),(159,'2015-12-18 22:01:42',NULL,'\0','8','',NULL,'Lightning2',1,''),(160,'2015-12-18 22:01:57',NULL,'\0','1','',NULL,'Lightning2',1,''),(161,'2015-12-18 22:02:08',NULL,'\0','ewrwer','',NULL,'Lightning2',1,''),(162,'2015-12-18 22:02:10',NULL,'\0','ww','',NULL,'Lightning2',1,''),(163,'2015-12-18 22:03:56',NULL,'\0','2','',NULL,'Lightning2',1,''),(164,'2015-12-18 22:05:51',NULL,'\0','2','',NULL,'Lightning2',1,''),(165,'2015-12-18 22:07:32',NULL,'\0','22','',NULL,'Lightning2',1,''),(166,'2015-12-18 22:08:21',NULL,'\0','212','',NULL,'Lightning2',1,''),(167,'2015-12-18 22:08:29',NULL,'\0','123','',NULL,'Lightning2',1,''),(168,'2015-12-18 22:09:17',NULL,'\0','2313','',NULL,'Lightning2',1,''),(169,'2015-12-18 22:09:23',NULL,'\0','1','',NULL,'Lightning2',1,''),(170,'2015-12-18 22:09:29',NULL,'\0','3','',NULL,'Lightning2',1,''),(171,'2015-12-18 22:11:44',NULL,'\0','2','',NULL,'Lightning2',1,''),(172,'2015-12-18 22:12:04',NULL,'\0','11','',NULL,'Lightning2',1,''),(173,'2015-12-18 22:12:10',NULL,'\0','3232','',NULL,'Lightning2',1,''),(174,'2015-12-18 22:12:12',NULL,'\0','3232','',NULL,'Lightning2',1,''),(175,'2015-12-18 22:13:51',NULL,'\0','ewqewq','',NULL,'Lightning2',1,''),(176,'2015-12-18 22:13:57',NULL,'\0','q','',NULL,'Lightning2',1,''),(177,'2015-12-18 22:13:58',NULL,'\0','q','',NULL,'Lightning2',1,''),(178,'2015-12-18 22:13:59',NULL,'\0','q','',NULL,'Lightning2',1,''),(179,'2015-12-18 22:14:01',NULL,'\0','q','',NULL,'Lightning2',1,''),(180,'2015-12-18 22:16:20',NULL,'\0','1','',NULL,'Lightning2',1,''),(181,'2015-12-18 22:19:21',NULL,'\0','rew','',NULL,'Lightning2',1,''),(182,'2015-12-18 22:19:22',NULL,'\0','rwer','',NULL,'Lightning2',1,''),(183,'2015-12-18 22:20:36',NULL,'\0','2','',NULL,'Lightning2',1,''),(184,'2015-12-18 22:20:38',NULL,'\0','2','',NULL,'Lightning2',1,''),(185,'2015-12-18 22:22:23',NULL,'\0','11','',NULL,'Lightning2',1,''),(186,'2015-12-18 22:22:24',NULL,'\0','22','',NULL,'Lightning2',1,''),(187,'2015-12-18 22:25:32',NULL,'\0','1','',NULL,'Lightning2',1,''),(188,'2015-12-18 22:25:33',NULL,'\0','2','',NULL,'Lightning2',1,''),(189,'2015-12-18 22:25:35',NULL,'\0','3','',NULL,'Lightning2',1,''),(190,'2015-12-18 22:27:52',NULL,'\0','dsd','',NULL,'Lightning2',1,''),(191,'2015-12-18 22:28:44',NULL,'\0','ewqe','',NULL,'Lightning2',1,''),(192,'2015-12-18 22:28:46',NULL,'\0','ewqew','',NULL,'Lightning2',1,''),(193,'2015-12-18 22:31:34',NULL,'\0','fsdf','',NULL,'Lightning2',1,''),(194,'2015-12-18 22:31:39',NULL,'\0','dsad','',NULL,'Lightning2',1,''),(195,'2015-12-18 22:31:40',NULL,'\0','dsads','',NULL,'Lightning2',1,''),(196,'2015-12-18 22:31:42',NULL,'\0','dsds','',NULL,'Lightning2',1,''),(197,'2015-12-18 22:31:48',NULL,'\0','1','',NULL,'Lightning2',1,''),(198,'2015-12-18 22:31:49',NULL,'\0','2','',NULL,'Lightning2',1,''),(199,'2015-12-18 22:31:51',NULL,'\0','3','',NULL,'Lightning2',1,''),(200,'2015-12-18 22:31:58',NULL,'\0','1','',NULL,'Lightning2',1,''),(201,'2015-12-18 22:33:14',NULL,'\0','1','',NULL,'Lightning2',1,''),(202,'2015-12-18 22:34:07',NULL,'\0','w','',NULL,'Lightning2',1,''),(203,'2015-12-18 22:34:09',NULL,'\0','w','',NULL,'Lightning2',1,''),(204,'2015-12-18 22:34:11',NULL,'\0','w','',NULL,'Lightning2',1,''),(205,'2015-12-18 22:34:13',NULL,'\0','rer','',NULL,'Lightning2',1,''),(206,'2015-12-18 22:34:26',NULL,'\0','2','',NULL,'Lightning2',1,''),(207,'2015-12-18 22:34:28',NULL,'\0','3','',NULL,'Lightning2',1,''),(208,'2015-12-18 22:34:29',NULL,'\0','4','',NULL,'Lightning2',1,''),(209,'2015-12-18 22:34:35',NULL,'\0','1','',NULL,'Lightning2',1,''),(210,'2015-12-18 22:35:36',NULL,'\0','ew','',NULL,'Lightning2',1,''),(211,'2015-12-18 22:35:38',NULL,'\0','wew','',NULL,'Lightning2',1,''),(212,'2015-12-18 22:35:39',NULL,'\0','ewe','',NULL,'Lightning2',1,''),(213,'2015-12-18 22:35:45',NULL,'\0','qq','',NULL,'Lightning2',1,''),(214,'2015-12-18 22:35:52',NULL,'\0','qq','',NULL,'Lightning2',1,''),(215,'2015-12-18 22:35:54',NULL,'\0','ee','',NULL,'Lightning2',1,''),(216,'2015-12-18 22:35:56',NULL,'\0','www','',NULL,'Lightning2',1,''),(217,'2015-12-18 22:38:01',NULL,'\0','2','',NULL,'Lightning2',1,''),(218,'2015-12-18 22:38:22',NULL,'\0','sadsa','',NULL,'Lightning2',1,''),(219,'2015-12-18 22:38:23',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(220,'2015-12-18 22:38:24',NULL,'\0','dfd','',NULL,'Lightning2',1,''),(221,'2015-12-18 22:38:46',NULL,'\0','dfd','',NULL,'Lightning2',1,''),(222,'2015-12-18 22:38:47',NULL,'\0','dfd','',NULL,'Lightning2',1,''),(223,'2015-12-18 22:41:11',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(224,'2015-12-18 22:43:49',NULL,'\0','ew','',NULL,'Lightning2',1,''),(225,'2015-12-18 22:43:54',NULL,'\0','rewre','',NULL,'Lightning2',1,''),(226,'2015-12-18 22:43:55',NULL,'\0','rewre','',NULL,'Lightning2',1,''),(227,'2015-12-18 22:43:57',NULL,'\0','ewqew','',NULL,'Lightning2',1,''),(228,'2015-12-18 22:43:59',NULL,'\0','re','',NULL,'Lightning2',1,''),(229,'2015-12-18 22:44:09',NULL,'\0','13123','',NULL,'Lightning2',1,''),(230,'2015-12-18 22:44:29',NULL,'\0','ewqew','',NULL,'Lightning2',1,''),(231,'2015-12-18 22:44:31',NULL,'\0','ewqew','',NULL,'Lightning2',1,''),(232,'2015-12-18 22:46:54',NULL,'\0','fdf','',NULL,'Lightning2',1,''),(233,'2015-12-18 22:47:35',NULL,'\0','fsdf','',NULL,'Lightning2',1,''),(234,'2015-12-18 22:48:49',NULL,'\0','ww','',NULL,'Lightning2',1,''),(235,'2015-12-18 22:48:53',NULL,'\0','rewr','',NULL,'Lightning2',1,''),(236,'2015-12-18 22:48:55',NULL,'\0','qewq','',NULL,'Lightning2',1,''),(237,'2015-12-18 22:49:01',NULL,'\0','1','',NULL,'Lightning2',1,''),(238,'2015-12-18 22:49:03',NULL,'\0','3','',NULL,'Lightning2',1,''),(239,'2015-12-18 22:49:14',NULL,'\0','w','',NULL,'Lightning2',1,''),(240,'2015-12-18 22:49:19',NULL,'\0','wew','',NULL,'Lightning2',1,''),(241,'2015-12-18 22:49:33',NULL,'\0','w','',NULL,'Lightning2',1,''),(242,'2015-12-18 22:49:36',NULL,'\0','re','',NULL,'Lightning2',1,''),(243,'2015-12-18 22:49:41',NULL,'\0','321312','',NULL,'Lightning2',1,''),(244,'2015-12-18 22:50:12',NULL,'\0','213','',NULL,'Lightning2',1,''),(245,'2015-12-18 22:50:14',NULL,'\0','532','',NULL,'Lightning2',1,''),(246,'2015-12-18 22:50:24',NULL,'\0','ewe','',NULL,'Lightning2',1,''),(247,'2015-12-18 22:50:28',NULL,'\0','1','',NULL,'Lightning2',1,''),(248,'2015-12-18 22:50:45',NULL,'\0','dsds','',NULL,'Lightning2',1,''),(249,'2015-12-18 22:50:48',NULL,'\0','wewe','',NULL,'Lightning2',1,''),(250,'2015-12-18 22:50:57',NULL,'\0','11','',NULL,'Lightning2',1,''),(251,'2015-12-18 22:51:04',NULL,'\0','erer','',NULL,'Lightning2',1,''),(252,'2015-12-18 22:51:06',NULL,'\0','rere','',NULL,'Lightning2',1,''),(253,'2015-12-18 22:57:25',NULL,'\0','fds','',NULL,'Lightning2',1,''),(254,'2015-12-18 22:57:42',NULL,'\0','1','',NULL,'Lightning2',1,''),(255,'2015-12-18 22:57:43',NULL,'\0','1','',NULL,'Lightning2',1,''),(256,'2015-12-18 22:57:46',NULL,'\0','1','',NULL,'Lightning2',1,''),(257,'2015-12-18 22:57:48',NULL,'\0','1','',NULL,'Lightning2',1,''),(258,'2015-12-18 22:57:50',NULL,'\0','1','',NULL,'Lightning2',1,''),(259,'2015-12-18 22:57:52',NULL,'\0','1','',NULL,'Lightning2',1,''),(260,'2015-12-18 22:57:53',NULL,'\0','1','',NULL,'Lightning2',1,''),(261,'2015-12-18 22:57:55',NULL,'\0','1','',NULL,'Lightning2',1,''),(262,'2015-12-18 22:57:57',NULL,'\0','1','',NULL,'Lightning2',1,''),(263,'2015-12-18 23:03:39',NULL,'\0','1','',NULL,'Lightning2',1,''),(264,'2015-12-18 23:03:40',NULL,'\0','2','',NULL,'Lightning2',1,''),(265,'2015-12-18 23:07:19',NULL,'\0','dsa','',NULL,'Lightning2',1,''),(266,'2015-12-18 23:07:28',NULL,'\0','1','',NULL,'Lightning2',1,''),(267,'2015-12-18 23:07:30',NULL,'\0','2','',NULL,'Lightning2',1,''),(268,'2015-12-18 23:07:31',NULL,'\0','3','',NULL,'Lightning2',1,''),(269,'2015-12-18 23:07:35',NULL,'\0','fds','',NULL,'Lightning2',1,''),(270,'2015-12-18 23:07:37',NULL,'\0','dsa','',NULL,'Lightning2',1,''),(271,'2015-12-18 23:07:39',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(272,'2015-12-18 23:07:44',NULL,'\0','1','',NULL,'Lightning2',1,''),(273,'2015-12-18 23:07:51',NULL,'\0','1','',NULL,'Lightning2',1,''),(274,'2015-12-18 23:07:52',NULL,'\0','2','',NULL,'Lightning2',1,''),(275,'2015-12-18 23:07:53',NULL,'\0','3','',NULL,'Lightning2',1,''),(276,'2015-12-18 23:16:22',NULL,'\0','ewew','',NULL,'Lightning2',1,''),(277,'2015-12-18 23:16:23',NULL,'\0','ewew','',NULL,'Lightning2',1,''),(278,'2015-12-18 23:16:34',NULL,'\0','qew','',NULL,'Lightning2',1,''),(279,'2015-12-18 23:16:42',NULL,'\0','wew','',NULL,'Lightning2',1,''),(280,'2015-12-19 18:46:57',NULL,'\0','выфвы','',NULL,'Lightning2',1,''),(281,'2015-12-19 18:47:05',NULL,'\0','ц','',NULL,'Lightning2',1,''),(282,'2015-12-19 18:47:06',NULL,'\0','у','',NULL,'Lightning2',1,''),(283,'2015-12-19 18:47:08',NULL,'\0','у','',NULL,'Lightning2',1,''),(284,'2015-12-19 18:47:09',NULL,'\0','у','',NULL,'Lightning2',1,''),(285,'2015-12-19 18:47:11',NULL,'\0','у','',NULL,'Lightning2',1,''),(286,'2015-12-19 18:47:13',NULL,'\0','у','',NULL,'Lightning2',1,''),(287,'2015-12-19 18:47:14',NULL,'\0','у','',NULL,'Lightning2',1,''),(288,'2015-12-19 18:48:55',NULL,'\0','1','',NULL,'Lightning2',1,''),(289,'2015-12-19 18:49:01',NULL,'\0','2','',NULL,'Lightning2',1,''),(290,'2015-12-19 18:51:49',NULL,'\0','ы','',NULL,'Lightning2',1,''),(291,'2015-12-19 18:51:57',NULL,'\0','1','',NULL,'Lightning2',1,''),(292,'2015-12-19 18:52:04',NULL,'\0','2','',NULL,'Lightning2',1,''),(293,'2015-12-19 18:52:50',NULL,'\0','уцй','',NULL,'Lightning2',1,''),(294,'2015-12-19 18:52:52',NULL,'\0','уцйу','',NULL,'Lightning2',1,''),(295,'2015-12-19 18:55:28',NULL,'\0','авы','',NULL,'Lightning2',1,''),(296,'2015-12-19 18:55:37',NULL,'\0','1','',NULL,'Lightning2',1,''),(297,'2015-12-19 18:55:39',NULL,'\0','2','',NULL,'Lightning2',1,''),(298,'2015-12-19 18:55:40',NULL,'\0','3','',NULL,'Lightning2',1,''),(299,'2015-12-19 18:55:41',NULL,'\0','4','',NULL,'Lightning2',1,''),(300,'2015-12-19 18:55:42',NULL,'\0','5','',NULL,'Lightning2',1,''),(301,'2015-12-19 18:55:45',NULL,'\0','6','',NULL,'Lightning2',1,''),(302,'2015-12-19 18:55:46',NULL,'\0','7','',NULL,'Lightning2',1,''),(303,'2015-12-19 18:55:47',NULL,'\0','8','',NULL,'Lightning2',1,''),(304,'2015-12-19 18:55:49',NULL,'\0','1','',NULL,'Lightning2',1,''),(305,'2015-12-19 18:55:50',NULL,'\0','2','',NULL,'Lightning2',1,''),(306,'2015-12-19 18:55:52',NULL,'\0','3','',NULL,'Lightning2',1,''),(307,'2015-12-19 18:55:54',NULL,'\0','4','',NULL,'Lightning2',1,''),(308,'2015-12-19 19:38:41',NULL,'\0','выф','',NULL,'Lightning2',1,''),(309,'2015-12-19 19:38:51',NULL,'\0','выфвы','',NULL,'Lightning2',1,''),(310,'2015-12-19 19:54:00',NULL,'\0','ewew','',NULL,'Lightning3',1,''),(311,'2015-12-19 19:56:04',NULL,'\0','1','',NULL,'Lightning3',1,''),(312,'2015-12-19 20:05:32',NULL,'\0','wa','',NULL,'Lightning3',1,''),(313,'2015-12-19 20:10:22',NULL,'\0','dsa','',NULL,'Lightning3',1,''),(314,'2015-12-19 20:10:44',NULL,'\0','fsf','',NULL,'Lightning3',1,''),(315,'2015-12-19 20:10:46',NULL,'\0','fsd','',NULL,'Lightning3',1,''),(316,'2015-12-19 20:13:22',NULL,'\0','fdsf','',NULL,'Lightning3',1,''),(317,'2015-12-19 20:13:24',NULL,'\0','fdf','',NULL,'Lightning3',1,''),(318,'2015-12-19 20:13:29',NULL,'\0','a','',NULL,'Lightning3',1,''),(319,'2015-12-19 20:14:17',NULL,'\0','fdsfd','',NULL,'Lightning3',1,''),(320,'2015-12-19 20:14:19',NULL,'\0','fds','',NULL,'Lightning3',1,''),(321,'2015-12-19 20:14:49',NULL,'\0','1','',NULL,'Lightning3',1,''),(322,'2015-12-19 20:14:50',NULL,'\0','2','',NULL,'Lightning3',1,''),(323,'2015-12-19 20:14:51',NULL,'\0','3','',NULL,'Lightning3',1,''),(324,'2015-12-19 20:14:53',NULL,'\0','4','',NULL,'Lightning3',1,''),(325,'2015-12-19 20:14:54',NULL,'\0','5','',NULL,'Lightning3',1,''),(326,'2015-12-19 20:14:56',NULL,'\0','6','',NULL,'Lightning3',1,''),(327,'2015-12-19 20:14:57',NULL,'\0','7','',NULL,'Lightning3',1,''),(328,'2015-12-19 20:14:59',NULL,'\0','8','',NULL,'Lightning3',1,''),(329,'2015-12-19 20:15:00',NULL,'\0','9','',NULL,'Lightning3',1,''),(330,'2015-12-19 20:23:09',NULL,'\0','1','',NULL,'Lightning3',1,''),(331,'2015-12-19 20:23:16',NULL,'\0','232','',NULL,'Lightning3',1,''),(332,'2015-12-19 20:23:18',NULL,'\0','323','',NULL,'Lightning3',1,''),(333,'2015-12-19 20:23:19',NULL,'\0','3232','',NULL,'Lightning3',1,''),(334,'2015-12-19 20:24:36',NULL,'\0','1','',NULL,'Lightning3',1,''),(335,'2015-12-19 20:24:38',NULL,'\0','2','',NULL,'Lightning3',1,''),(336,'2015-12-19 20:24:39',NULL,'\0','3','',NULL,'Lightning3',1,''),(337,'2015-12-19 20:24:40',NULL,'\0','4','',NULL,'Lightning3',1,''),(338,'2015-12-19 20:24:42',NULL,'\0','5','',NULL,'Lightning3',1,''),(339,'2015-12-19 20:24:43',NULL,'\0','6','',NULL,'Lightning3',1,''),(340,'2015-12-19 20:24:51',NULL,'\0','1','',NULL,'Lightning3',1,''),(341,'2015-12-19 20:24:52',NULL,'\0','2','',NULL,'Lightning3',1,''),(342,'2015-12-19 20:24:54',NULL,'\0','3','',NULL,'Lightning3',1,''),(343,'2015-12-19 20:24:55',NULL,'\0','4','',NULL,'Lightning3',1,''),(344,'2015-12-19 20:24:57',NULL,'\0','5','',NULL,'Lightning3',1,''),(345,'2015-12-19 20:24:58',NULL,'\0','6','',NULL,'Lightning3',1,''),(346,'2015-12-19 20:25:00',NULL,'\0','7','',NULL,'Lightning3',1,''),(347,'2015-12-19 20:25:01',NULL,'\0','8','',NULL,'Lightning3',1,''),(348,'2015-12-19 20:25:02',NULL,'\0','9','',NULL,'Lightning3',1,''),(349,'2015-12-19 20:25:05',NULL,'\0','10','',NULL,'Lightning3',1,''),(350,'2015-12-19 20:26:11',NULL,'\0','1','',NULL,'Lightning3',1,''),(351,'2015-12-19 20:29:25',NULL,'\0','1','',NULL,'Lightning3',1,''),(352,'2015-12-19 20:29:30',NULL,'\0','1','',NULL,'Lightning3',1,''),(353,'2015-12-19 20:29:31',NULL,'\0','2','',NULL,'Lightning3',1,''),(354,'2015-12-19 20:29:33',NULL,'\0','3','',NULL,'Lightning3',1,''),(355,'2015-12-19 20:29:34',NULL,'\0','4','',NULL,'Lightning3',1,''),(356,'2015-12-19 20:29:35',NULL,'\0','5','',NULL,'Lightning3',1,''),(357,'2015-12-19 20:29:37',NULL,'\0','6','',NULL,'Lightning3',1,''),(358,'2015-12-19 20:29:38',NULL,'\0','7','',NULL,'Lightning3',1,''),(359,'2015-12-19 20:29:40',NULL,'\0','8','',NULL,'Lightning3',1,''),(360,'2015-12-19 20:29:51',NULL,'\0','1','',NULL,'Lightning3',1,''),(361,'2015-12-19 20:29:53',NULL,'\0','2','',NULL,'Lightning3',1,''),(362,'2015-12-19 20:29:54',NULL,'\0','3','',NULL,'Lightning3',1,''),(363,'2015-12-19 20:29:56',NULL,'\0','4','',NULL,'Lightning3',1,''),(364,'2015-12-19 20:29:57',NULL,'\0','5','',NULL,'Lightning3',1,''),(365,'2015-12-19 20:29:59',NULL,'\0','6','',NULL,'Lightning3',1,''),(366,'2015-12-19 20:30:01',NULL,'\0','7','',NULL,'Lightning3',1,''),(367,'2015-12-19 20:30:03',NULL,'\0','8','',NULL,'Lightning3',1,''),(368,'2015-12-19 20:30:04',NULL,'\0','9','',NULL,'Lightning3',1,''),(369,'2015-12-19 20:30:08',NULL,'\0','19','',NULL,'Lightning3',1,''),(370,'2015-12-19 20:31:26',NULL,'\0','ewqe','',NULL,'Lightning3',1,''),(371,'2015-12-19 20:36:27',NULL,'\0','1','',NULL,'Lightning3',1,''),(372,'2015-12-19 20:36:46',NULL,'\0','1s','',NULL,'Lightning3',1,''),(373,'2015-12-19 20:39:56',NULL,'\0','1','',NULL,'Lightning2',1,''),(374,'2015-12-19 20:40:01',NULL,'\0','1','',NULL,'Lightning2',1,''),(375,'2015-12-19 20:40:03',NULL,'\0','2','',NULL,'Lightning2',1,''),(376,'2015-12-19 20:40:04',NULL,'\0','3','',NULL,'Lightning2',1,''),(377,'2015-12-19 20:40:05',NULL,'\0','4','',NULL,'Lightning2',1,''),(378,'2015-12-19 20:40:06',NULL,'\0','5','',NULL,'Lightning2',1,''),(379,'2015-12-19 20:40:08',NULL,'\0','6','',NULL,'Lightning2',1,''),(380,'2015-12-19 20:40:09',NULL,'\0','7','',NULL,'Lightning2',1,''),(381,'2015-12-19 20:40:11',NULL,'\0','8','',NULL,'Lightning2',1,''),(382,'2015-12-19 20:40:12',NULL,'\0','9','',NULL,'Lightning2',1,''),(383,'2015-12-19 20:41:42',NULL,'\0','1','',NULL,'Lightning2',1,''),(384,'2015-12-19 20:41:44',NULL,'\0','2','',NULL,'Lightning2',1,''),(385,'2015-12-19 20:41:45',NULL,'\0','3','',NULL,'Lightning2',1,''),(386,'2015-12-19 20:41:46',NULL,'\0','4','',NULL,'Lightning2',1,''),(387,'2015-12-19 20:41:49',NULL,'\0','5','',NULL,'Lightning2',1,''),(388,'2015-12-19 20:41:50',NULL,'\0','6','',NULL,'Lightning2',1,''),(389,'2015-12-19 20:41:51',NULL,'\0','7','',NULL,'Lightning2',1,''),(390,'2015-12-19 20:41:54',NULL,'\0','8','',NULL,'Lightning2',1,''),(391,'2015-12-19 20:41:55',NULL,'\0','1','',NULL,'Lightning2',1,''),(392,'2015-12-19 20:41:56',NULL,'\0','1','',NULL,'Lightning2',1,''),(393,'2015-12-19 20:41:57',NULL,'\0','1','',NULL,'Lightning2',1,''),(394,'2015-12-19 20:41:59',NULL,'\0','2','',NULL,'Lightning2',1,''),(395,'2015-12-19 20:42:01',NULL,'\0','3','',NULL,'Lightning2',1,''),(396,'2015-12-19 20:42:02',NULL,'\0','4','',NULL,'Lightning2',1,''),(397,'2015-12-19 20:42:03',NULL,'\0','5','',NULL,'Lightning2',1,''),(398,'2015-12-19 20:42:05',NULL,'\0','6','',NULL,'Lightning2',1,''),(399,'2015-12-19 20:42:06',NULL,'\0','7','',NULL,'Lightning2',1,''),(400,'2015-12-19 20:42:08',NULL,'\0','7','',NULL,'Lightning2',1,''),(401,'2015-12-19 20:42:09',NULL,'\0','7','',NULL,'Lightning2',1,''),(402,'2015-12-19 20:42:10',NULL,'\0','7','',NULL,'Lightning2',1,''),(403,'2015-12-19 20:42:16',NULL,'\0','1','',NULL,'Lightning2',1,''),(404,'2015-12-19 20:42:17',NULL,'\0','2','',NULL,'Lightning2',1,''),(405,'2015-12-19 20:42:18',NULL,'\0','3','',NULL,'Lightning2',1,''),(406,'2015-12-19 20:42:20',NULL,'\0','4','',NULL,'Lightning2',1,''),(407,'2015-12-19 20:42:21',NULL,'\0','5','',NULL,'Lightning2',1,''),(408,'2015-12-19 20:42:23',NULL,'\0','6','',NULL,'Lightning2',1,''),(409,'2015-12-19 20:42:24',NULL,'\0','7','',NULL,'Lightning2',1,''),(410,'2015-12-19 20:43:44',NULL,'\0','1','',NULL,'Lightning2',1,''),(411,'2015-12-19 20:43:45',NULL,'\0','2','',NULL,'Lightning2',1,''),(412,'2015-12-19 20:43:46',NULL,'\0','3','',NULL,'Lightning2',1,''),(413,'2015-12-19 20:43:48',NULL,'\0','4','',NULL,'Lightning2',1,''),(414,'2015-12-19 20:43:49',NULL,'\0','5','',NULL,'Lightning2',1,''),(415,'2015-12-19 20:43:50',NULL,'\0','6','',NULL,'Lightning2',1,''),(416,'2015-12-19 20:43:51',NULL,'\0','1','',NULL,'Lightning2',1,''),(417,'2015-12-19 20:43:53',NULL,'\0','2','',NULL,'Lightning2',1,''),(418,'2015-12-19 20:43:54',NULL,'\0','3','',NULL,'Lightning2',1,''),(419,'2015-12-19 20:43:55',NULL,'\0','1','',NULL,'Lightning2',1,''),(420,'2015-12-19 20:43:56',NULL,'\0','1','',NULL,'Lightning2',1,''),(421,'2015-12-19 20:43:59',NULL,'\0','1','',NULL,'Lightning2',1,''),(422,'2015-12-19 20:44:00',NULL,'\0','2','',NULL,'Lightning2',1,''),(423,'2015-12-19 20:44:01',NULL,'\0','3','',NULL,'Lightning2',1,''),(424,'2015-12-19 20:44:03',NULL,'\0','4','',NULL,'Lightning2',1,''),(425,'2015-12-19 20:44:04',NULL,'\0','1','',NULL,'Lightning2',1,''),(426,'2015-12-19 20:44:05',NULL,'\0','1','',NULL,'Lightning2',1,''),(427,'2015-12-19 20:44:07',NULL,'\0','1','',NULL,'Lightning2',1,''),(428,'2015-12-19 20:44:08',NULL,'\0','1','',NULL,'Lightning2',1,''),(429,'2015-12-19 20:44:10',NULL,'\0','1','',NULL,'Lightning2',1,''),(430,'2015-12-19 20:44:17',NULL,'\0','1','',NULL,'Lightning2',1,''),(431,'2015-12-19 20:44:18',NULL,'\0','1','',NULL,'Lightning2',1,''),(432,'2015-12-19 20:44:20',NULL,'\0','1','',NULL,'Lightning2',1,''),(433,'2015-12-19 20:44:22',NULL,'\0','1','',NULL,'Lightning2',1,''),(434,'2015-12-19 20:44:23',NULL,'\0','1','',NULL,'Lightning2',1,''),(435,'2015-12-19 20:44:25',NULL,'\0','1','',NULL,'Lightning2',1,''),(436,'2015-12-19 20:44:26',NULL,'\0','1','',NULL,'Lightning2',1,''),(437,'2015-12-19 20:44:27',NULL,'\0','1','',NULL,'Lightning2',1,''),(438,'2015-12-19 20:44:29',NULL,'\0','1','',NULL,'Lightning2',1,''),(439,'2015-12-19 20:44:30',NULL,'\0','1','',NULL,'Lightning2',1,''),(440,'2015-12-19 20:44:32',NULL,'\0','1','',NULL,'Lightning2',1,''),(441,'2015-12-19 20:44:33',NULL,'\0','1','',NULL,'Lightning2',1,''),(442,'2015-12-19 20:44:34',NULL,'\0','1','',NULL,'Lightning2',1,''),(443,'2015-12-19 20:44:36',NULL,'\0','1','',NULL,'Lightning2',1,''),(444,'2015-12-19 20:44:38',NULL,'\0','1','',NULL,'Lightning2',1,''),(445,'2015-12-19 20:44:39',NULL,'\0','1','',NULL,'Lightning2',1,''),(446,'2015-12-19 20:44:41',NULL,'\0','1','',NULL,'Lightning2',1,''),(447,'2015-12-19 20:44:43',NULL,'\0','1','',NULL,'Lightning2',1,''),(448,'2015-12-19 20:44:44',NULL,'\0','1','',NULL,'Lightning2',1,''),(449,'2015-12-19 20:44:46',NULL,'\0','1','',NULL,'Lightning2',1,''),(450,'2015-12-19 20:47:40',NULL,'\0','1','',NULL,'Lightning2',1,''),(451,'2015-12-19 20:47:41',NULL,'\0','2','',NULL,'Lightning2',1,''),(452,'2015-12-21 13:08:23',NULL,'\0','dsads','',NULL,'Lightning2',1,''),(453,'2015-12-21 13:10:08',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(454,'2015-12-21 13:11:07',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(455,'2015-12-21 13:12:41',NULL,'\0','fdsffdsf','',NULL,'Lightning2',1,''),(456,'2015-12-21 13:13:39',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(457,'2015-12-21 13:14:24',NULL,'\0','fsdf','',NULL,'Lightning2',1,''),(458,'2015-12-21 13:16:08',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(459,'2015-12-21 13:17:19',NULL,'\0','fsdds','',NULL,'Lightning2',1,''),(460,'2015-12-21 13:18:19',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(461,'2015-12-21 13:19:23',NULL,'\0','fdsf','',NULL,'Lightning2',1,''),(462,'2015-12-21 13:19:43',NULL,'\0','ewqe','',NULL,'Lightning2',1,''),(463,'2015-12-21 13:20:28',NULL,'\0','ds','',NULL,'Lightning2',1,''),(464,'2015-12-21 13:22:34',NULL,'\0','dsad','',NULL,'Lightning2',1,''),(465,'2015-12-21 13:23:06',NULL,'\0','a','',NULL,'Lightning2',1,''),(466,'2015-12-21 13:25:45',NULL,'\0','dsa','',NULL,'Lightning2',1,''),(467,'2015-12-21 13:30:23',NULL,'\0','dsad','',NULL,'Lightning2',1,''),(468,'2015-12-21 13:35:30',NULL,'\0','ds','',NULL,'Lightning2',1,''),(469,'2015-12-21 13:35:45',NULL,'\0','aa','',NULL,'Lightning2',1,''),(470,'2015-12-21 13:36:08',NULL,'\0','ss','',NULL,'Lightning2',1,''),(471,'2015-12-21 13:36:57',NULL,'\0','a','',NULL,'Lightning2',1,''),(472,'2015-12-21 13:38:38',NULL,'\0','sd','',NULL,'Lightning2',1,''),(473,'2015-12-21 13:45:21',NULL,'\0','s','',NULL,'Lightning2',1,''),(474,'2015-12-21 13:45:37',NULL,'\0','a','',NULL,'Lightning2',1,''),(475,'2015-12-21 13:46:11',NULL,'\0','1','',NULL,'Lightning2',1,''),(476,'2015-12-21 13:46:12',NULL,'\0','2','',NULL,'Lightning2',1,''),(477,'2015-12-21 13:46:43',NULL,'\0','1','',NULL,'Lightning2',1,''),(478,'2015-12-21 13:46:45',NULL,'\0','2','',NULL,'Lightning2',1,''),(479,'2015-12-21 13:46:46',NULL,'\0','3','',NULL,'Lightning2',1,''),(480,'2015-12-21 13:46:47',NULL,'\0','4','',NULL,'Lightning2',1,''),(481,'2015-12-21 13:46:48',NULL,'\0','5','',NULL,'Lightning2',1,''),(482,'2015-12-21 13:54:13',NULL,'\0','1','',NULL,'Lightning2',1,''),(483,'2015-12-21 13:56:10',NULL,'\0','1','',NULL,'Lightning2',1,''),(484,'2015-12-21 13:56:18',NULL,'\0','1','',NULL,'Lightning2',1,''),(485,'2015-12-21 13:56:38',NULL,'\0','1','',NULL,'Lightning2',1,''),(486,'2015-12-21 13:56:56',NULL,'\0','1','',NULL,'Lightning2',1,''),(487,'2015-12-21 13:57:20',NULL,'\0','wqewqewq','',NULL,'Lightning2',1,''),(488,'2015-12-21 13:57:42',NULL,'\0','ewqe','',NULL,'Lightning2',1,''),(489,'2015-12-21 13:58:07',NULL,'\0','2','',NULL,'Lightning2',1,''),(490,'2015-12-21 13:58:15',NULL,'\0','22','',NULL,'Lightning2',1,''),(491,'2015-12-21 14:02:39',NULL,'\0','dsad','',NULL,'Lightning2',1,''),(492,'2015-12-21 14:02:50',NULL,'\0','ds','',NULL,'Lightning2',1,''),(493,'2015-12-21 14:03:11',NULL,'\0','dsad','',NULL,'Lightning2',1,''),(494,'2015-12-21 14:03:23',NULL,'\0','dsads','',NULL,'Lightning2',1,''),(495,'2015-12-21 14:03:36',NULL,'\0','dsads','',NULL,'Lightning2',1,''),(496,'2015-12-21 14:06:22',NULL,'\0','1','',NULL,'Lightning2',1,''),(497,'2015-12-21 14:10:56',NULL,'\0','fdf','',NULL,'Lightning2',1,''),(498,'2015-12-21 14:11:20',NULL,'\0','ds','',NULL,'Lightning2',1,''),(499,'2015-12-21 16:12:30',NULL,'\0','1','',NULL,'Lightning2',1,''),(500,'2015-12-21 16:12:42',NULL,'\0','1','',NULL,'Lightning2',1,''),(501,'2015-12-21 16:13:06',NULL,'\0','аывав','',NULL,'Lightning2',1,''),(502,'2015-12-21 16:13:22',NULL,'\0','авыа','',NULL,'Lightning2',1,''),(503,'2015-12-21 16:13:32',NULL,'\0','1','',NULL,'Lightning2',1,''),(504,'2015-12-21 16:13:33',NULL,'\0','1','',NULL,'Lightning2',1,''),(505,'2015-12-21 16:13:35',NULL,'\0','1','',NULL,'Lightning2',1,''),(506,'2015-12-21 16:13:36',NULL,'\0','1','',NULL,'Lightning2',1,''),(507,'2015-12-21 16:13:37',NULL,'\0','1','',NULL,'Lightning2',1,''),(508,'2015-12-21 16:22:26',NULL,'\0','1','',NULL,'Lightning2',1,''),(509,'2015-12-21 16:22:59',NULL,'\0','1','',NULL,'Lightning3',1,''),(510,'2015-12-21 16:23:15',NULL,'\0','авыав','',NULL,'Lightning2',1,''),(511,'2015-12-21 16:42:43',NULL,'\0','выфв','',NULL,'Lightning2',1,''),(512,'2015-12-21 16:51:19',NULL,'\0','авыа','',NULL,'Lightning2',1,''),(513,'2015-12-21 16:51:47',NULL,'\0','авыа','',NULL,'Lightning2',1,''),(514,'2015-12-21 16:54:42',NULL,'\0','аывав','',NULL,'Lightning2',1,''),(515,'2015-12-21 16:55:05',NULL,'\0','выав','',NULL,'Lightning2',1,''),(516,'2015-12-21 16:55:05',NULL,'\0','вы','',NULL,'Lightning2',1,''),(517,'2015-12-21 16:55:05',NULL,'\0','выав','',NULL,'Lightning2',1,''),(518,'2015-12-21 16:55:05',NULL,'\0','выав','',NULL,'Lightning2',1,''),(519,'2015-12-21 16:55:06',NULL,'\0','вы','',NULL,'Lightning2',1,''),(520,'2015-12-21 16:56:56',NULL,'\0','авы','',NULL,'Lightning2',1,''),(521,'2015-12-21 16:58:18',NULL,'\0','авыав','',NULL,'Lightning2',1,''),(522,'2015-12-21 17:01:07',NULL,'\0','авыав','',NULL,'Lightning2',1,''),(523,'2015-12-21 17:01:50',NULL,'\0','ава','',NULL,'Lightning2',1,''),(524,'2015-12-21 17:04:07',NULL,'\0','куцку','',NULL,'Lightning2',1,''),(525,'2015-12-21 17:04:18',NULL,'\0','авыа','',NULL,'Lightning2',1,''),(526,'2015-12-21 17:04:27',NULL,'\0','ку','',NULL,'Lightning3',1,''),(527,'2015-12-21 17:06:54',NULL,'\0','авыа','',NULL,'Lightning2',1,''),(528,'2015-12-21 17:08:04',NULL,'\0','выфвы','',NULL,'Lightning2',1,''),(529,'2015-12-21 17:08:05',NULL,'\0','аывав','',NULL,'Lightning2',1,''),(530,'2015-12-21 17:08:07',NULL,'\0','выфвы','',NULL,'Lightning2',1,''),(531,'2015-12-21 17:08:09',NULL,'\0','авыав','',NULL,'Lightning2',1,''),(532,'2015-12-21 17:08:18',NULL,'\0','авыавы','',NULL,'Lightning2',1,''),(533,'2015-12-21 17:08:20',NULL,'\0','выфвы','',NULL,'Lightning2',1,''),(534,'2015-12-21 17:34:04',NULL,'\0','выфвы','',NULL,'Lightning2',1,''),(535,'2015-12-21 17:45:56',NULL,'\0','вывыф','',NULL,'Lightning1',1,''),(536,'2015-12-21 17:45:57',NULL,'\0','вывыф','',NULL,'Lightning1',1,''),(537,'2015-12-21 17:49:06',NULL,'\0','2','',NULL,'Lightning3',1,''),(538,'2015-12-21 17:54:44',NULL,'\0','выв','',NULL,'Lightning3',1,''),(539,'2015-12-21 17:57:37',NULL,'\0','уцйу','',NULL,'Lightning3',1,''),(540,'2015-12-21 17:58:30',NULL,'\0','авыав','',NULL,'Lightning3',1,''),(541,'2015-12-21 17:59:23',NULL,'\0','авыав','',NULL,'Lightning3',1,''),(542,'2015-12-21 17:59:32',NULL,'\0','выфвы','',NULL,'Lightning3',1,''),(543,'2015-12-21 17:59:39',NULL,'\0','вывы','',NULL,'Lightning3',1,''),(544,'2015-12-21 18:02:45',NULL,'\0','выфв','',NULL,'Lightning3',1,''),(545,'2015-12-21 18:02:55',NULL,'\0','выв','',NULL,'Lightning3',1,''),(546,'2015-12-21 18:04:39',NULL,'\0','выфвы','',NULL,'Lightning3',1,''),(547,'2015-12-21 18:05:03',NULL,'\0','выфвы','',NULL,'Lightning3',1,''),(548,'2015-12-21 18:07:03',NULL,'\0','выфв','',NULL,'Lightning3',1,''),(549,'2015-12-21 18:07:17',NULL,'\0','вывы','',NULL,'Lightning3',1,''),(550,'2015-12-21 18:08:33',NULL,'\0','выфвы','',NULL,'Lightning3',1,''),(551,'2015-12-21 18:12:33',NULL,'\0','выфвы','',NULL,'Lightning3',1,''),(552,'2015-12-21 18:12:51',NULL,'\0','вывы','',NULL,'Lightning3',1,''),(553,'2015-12-21 18:13:50',NULL,'\0','ава','',NULL,'Lightning3',1,''),(554,'2015-12-21 19:42:31',NULL,'\0','уцй','',NULL,'Lightning3',1,''),(555,'2015-12-21 19:42:47',NULL,'\0','авыа','',NULL,'Lightning3',1,''),(556,'2015-12-21 19:44:41',NULL,'\0','вы','',NULL,'Lightning3',1,''),(557,'2015-12-21 19:55:37',NULL,'\0','выфв','',NULL,'Lightning3',1,''),(558,'2015-12-21 20:50:44',NULL,'\0','ывв','',NULL,'Lightning3',1,''),(559,'2015-12-21 20:51:19',NULL,'\0','выфв','',NULL,'Lightning3',1,''),(560,'2015-12-21 20:58:39',NULL,'\0','авыа','',NULL,'Lightning3',1,''),(561,'2015-12-21 20:58:56',NULL,'\0','авыа','',NULL,'Lightning3',1,''),(562,'2015-12-21 21:01:43',NULL,'\0','авыа','',NULL,'Lightning3',1,''),(563,'2015-12-21 21:03:06',NULL,'\0','11111','',NULL,'Lightning3',1,''),(564,'2015-12-21 21:05:58',NULL,'\0','выфвы','',NULL,'Lightning3',1,''),(565,'2015-12-21 21:25:56',NULL,'\0','111','',NULL,'Lightning3',1,''),(566,'2015-12-21 21:27:41',NULL,'\0','авыавыа','',NULL,'Lightning3',1,''),(567,'2015-12-21 21:27:43',NULL,'\0','1123123213','',NULL,'Lightning3',1,''),(568,'2015-12-21 21:28:12',NULL,'\0','2','',NULL,'Lightning3',1,''),(569,'2015-12-21 22:07:32',NULL,'\0','2','',NULL,'Lightning3',1,''),(570,'2015-12-21 22:07:59',NULL,'\0','232','',NULL,'Lightning3',1,''),(571,'2015-12-21 22:08:21',NULL,'\0','уйцуц','',NULL,'Lightning3',1,''),(572,'2015-12-21 22:08:23',NULL,'\0','куцку','',NULL,'Lightning3',1,''),(573,'2015-12-21 22:08:34',NULL,'\0','ййй','',NULL,'Lightning3',1,''),(574,'2015-12-21 22:08:44',NULL,'\0','авав','',NULL,'Lightning3',1,''),(575,'2015-12-21 22:09:07',NULL,'\0','DenisPenis','',NULL,'Lightning3',1,''),(576,'2015-12-21 22:09:20',NULL,'\0','dsds','',NULL,'Lightning3',1,''),(577,'2015-12-21 22:09:29',NULL,'\0','fdsfd','',NULL,'Lightning3',1,''),(578,'2015-12-21 22:13:00',NULL,'\0','Vera','',NULL,'Lightning3',1,''),(579,'2015-12-21 22:14:34',NULL,'\0','fdsfd','',NULL,'Lightning3',1,''),(580,'2015-12-21 22:14:47',NULL,'\0','ewe','',NULL,'Lightning3',1,''),(581,'2015-12-21 22:15:14',NULL,'\0','dsd','',NULL,'Lightning3',1,''),(582,'2015-12-21 22:15:30',NULL,'\0','fdsfd','',NULL,'Lightning3',1,''),(583,'2015-12-21 22:15:54',NULL,'\0','fdsfd','',NULL,'Lightning3',1,''),(584,'2015-12-21 22:17:49',NULL,'\0','dsa','',NULL,'Lightning3',1,''),(585,'2015-12-21 22:19:43',NULL,'\0','dsad','',NULL,'Lightning3',1,''),(586,'2015-12-21 22:20:09',NULL,'\0','123123','',NULL,'Lightning3',1,''),(587,'2015-12-21 22:20:16',NULL,'\0','32132','',NULL,'Lightning3',1,''),(588,'2015-12-21 22:20:42',NULL,'\0','ewqe','',NULL,'Lightning3',1,''),(589,'2015-12-21 22:21:14',NULL,'\0','fdsfd','',NULL,'Lightning3',1,''),(590,'2015-12-21 22:27:16',NULL,'\0','dsads','',NULL,'Lightning3',1,''),(591,'2015-12-21 22:34:14',NULL,'\0','fdsfd','',NULL,'Lightning3',1,''),(592,'2015-12-21 22:35:16',NULL,'\0','dsadsds','',NULL,'Lightning3',1,''),(593,'2015-12-21 22:36:28',NULL,'\0','dsd','',NULL,'Lightning3',1,''),(594,'2015-12-21 22:36:37',NULL,'\0','fdsfd','',NULL,'Lightning3',1,''),(595,'2015-12-21 22:36:51',NULL,'\0','Denis Penis','',NULL,'Lightning3',1,''),(596,'2015-12-21 22:44:57',NULL,'\0','GOPA GOPA','',NULL,'Lightning3',1,''),(597,'2015-12-21 22:45:01',NULL,'\0','LALALALA','',NULL,'Lightning3',1,''),(598,'2015-12-21 22:45:23',NULL,'\0','1','',NULL,'Lightning3',1,''),(599,'2015-12-21 22:45:24',NULL,'\0','2','',NULL,'Lightning3',1,''),(600,'2015-12-21 22:45:25',NULL,'\0','3','',NULL,'Lightning3',1,''),(601,'2015-12-21 22:45:58',NULL,'\0','dsadsad','',NULL,'Lightning3',1,''),(602,'2015-12-21 22:46:05',NULL,'\0','fdsfd','',NULL,'Lightning3',1,''),(603,'2015-12-21 22:46:07',NULL,'\0','23123','',NULL,'Lightning3',1,''),(604,'2015-12-21 22:46:14',NULL,'\0','dd','',NULL,'Lightning3',1,''),(605,'2015-12-27 16:07:28',NULL,'\0','������������<img height=\"20\" src=\"/user/avatarimage?name=Lightning\"> <a href=\'/profile/Lightning\'>Lightning</a> ����� �������������� � �������StarCraft II <a href=\'/team/58\'>dsdsadsa</a> </a>','',NULL,'Lightning',5,''),(606,'2015-12-29 18:53:40','StarCraft II','','<img height=\"20\" src=\"/user/avatarimage?name=Lightning3\"> <a href=\'/profile/Lightning3\'>Lightning3</a> ��������� � ����! <a href=\'/team/58\'>dsdsadsa</a> ','',58,'Lightning3',4,'\0'),(607,'2015-12-30 13:02:55',NULL,'\0','выфвыфвыф','',NULL,'Lightning3',1,''),(608,'2015-12-30 14:23:14',NULL,'\0','Пользователь<img height=\"20\" src=\"/user/avatarimage?name=Lightning2\"> <a href=\'/profile/Lightning2\'>Lightning2</a> Хочет присоединиться к командеHeroes of the Storm <a href=\'/team/67\'>ывыф</a> </a>','',NULL,'Lightning2',1,'');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new`
--

DROP TABLE IF EXISTS `new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `new` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `descirpion` longtext,
  `header` varchar(255) DEFAULT NULL,
  `is_show` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new`
--

LOCK TABLES `new` WRITE;
/*!40000 ALTER TABLE `new` DISABLE KEYS */;
INSERT INTO `new` VALUES (9,'Lightning','2016-01-05 12:01:37','<p>111</p>\r\n','111','\0');
/*!40000 ALTER TABLE `new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
INSERT INTO `persistent_logins` VALUES ('Lightning2','TVtRCTRYdfWbG5TRTh5aXQ==','7BiDt+3tA5VudMgG5VXQew==','2016-01-13 21:54:38');
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `about` varchar(2000) NOT NULL,
  `name` varchar(20) NOT NULL,
  `nation` varchar(2) NOT NULL,
  `date` datetime DEFAULT NULL,
  `has_avatar` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g2l9qqsoeuynt4r5ofdt1x2td` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (2,'ewqew','ewq','US',NULL,NULL),(34,'dsa','ds','US',NULL,NULL),(35,'fdsf','fds','US',NULL,NULL),(36,'fdsfds','dsad','US',NULL,NULL),(39,'fdsf','fdsfd','US',NULL,NULL),(42,'fsdfds','1111','US','2015-10-30 22:57:06',NULL),(43,'авыа','выфвфывыф','US','2015-11-05 16:47:09',NULL),(44,'fsdf','fsd','US','2015-11-11 19:43:54',NULL),(45,'adsa','fdsfdsf','US','2015-11-17 14:38:21','\0'),(46,'fsd','fsdaa','US','2015-11-17 14:49:45','\0'),(47,'q','qqqqqqqqqqqq','US','2015-11-17 15:36:15',''),(48,'f','dsfds','US','2015-11-18 19:03:30',''),(49,'ыфвыфв','выфв','US','2015-11-21 16:09:27',''),(50,'dsad','fsdaafsdf','US','2015-11-25 17:22:21',''),(51,'вфыв','выф','US','2015-12-03 23:50:50','\0'),(52,'выфвыфвыф','Virtus Pro','RU','2015-12-10 15:56:41',''),(53,'gfdgfd','gfd','US','2015-12-10 18:40:52','\0'),(54,'dsads','dsadsa','US','2015-12-10 18:55:49','\0'),(69,'dsad','fdsfdsfdsfds','US','2015-12-30 14:47:34','\0'),(70,'dsadsa','fdsfddsf','US','2016-01-03 19:12:45','\0'),(71,'fdfd','fdsfdfdfd','US','2016-01-11 18:21:11','\0');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_game`
--

DROP TABLE IF EXISTS `team_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_game` (
  `team_id` bigint(20) NOT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`team_id`,`game_id`),
  KEY `FK_tbldkq2f0tpt95d4df86yw5uv` (`game_id`),
  CONSTRAINT `FK_5wm7hdhvighg4urdbfn1ib6jg` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FK_tbldkq2f0tpt95d4df86yw5uv` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_game`
--

LOCK TABLES `team_game` WRITE;
/*!40000 ALTER TABLE `team_game` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `about_me` varchar(255) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `country` varchar(2) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `has_avatar` bit(1) DEFAULT NULL,
  `male` bit(1) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `surname` varchar(20) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'GOPA GOPA','rrr','TV','onesLightning@gmail.com','','','Denis','heroii22','Rozhkofdsfds','Lightning',NULL),(3,NULL,NULL,'US','veselchack@bk.ru','\0',NULL,NULL,'heroii22',NULL,'Lightning1',NULL),(4,NULL,NULL,'US','dsadsadsadsad@bk.ru','',NULL,NULL,'heroii22',NULL,'Lightning2',NULL),(5,NULL,NULL,'US','dsadsadsa@bk.ru','',NULL,'dasdas','heroii22','dы','Lightning3',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FK_g1uebn6mqk9qiaw45vnacmyo2` (`user_id`),
  CONSTRAINT `FK_g1uebn6mqk9qiaw45vnacmyo2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'ROLE_USER'),(1,'ROLE_ADMIN'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_team`
--

DROP TABLE IF EXISTS `user_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_team` (
  `post` int(11) DEFAULT NULL,
  `id_user` bigint(20) NOT NULL DEFAULT '0',
  `id_team` bigint(20) NOT NULL DEFAULT '0',
  `id_game` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_game`,`id_team`,`id_user`),
  KEY `FK_ji1hsnl96v2uqr0flsyetw4dv` (`id_user`),
  KEY `FK_i7npa6vtwkd7xh4mgt2rvlpif` (`id_team`),
  CONSTRAINT `FK_i7npa6vtwkd7xh4mgt2rvlpif` FOREIGN KEY (`id_team`) REFERENCES `team` (`id`),
  CONSTRAINT `FK_ji1hsnl96v2uqr0flsyetw4dv` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_tjfrr495njwgk8iq700qn2ytj` FOREIGN KEY (`id_game`) REFERENCES `game` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_team`
--

LOCK TABLES `user_team` WRITE;
/*!40000 ALTER TABLE `user_team` DISABLE KEYS */;
INSERT INTO `user_team` VALUES (0,1,69,3),(0,4,70,3),(0,1,71,9);
/*!40000 ALTER TABLE `user_team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-19 13:44:19
