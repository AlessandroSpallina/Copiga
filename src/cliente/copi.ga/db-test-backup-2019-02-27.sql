-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: copiga
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

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
-- Table structure for table `bookbindings`
--

DROP TABLE IF EXISTS `bookbindings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookbindings` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `tipo` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descrizione` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `prezzo` double(8,2) DEFAULT NULL,
  `selezionato` tinyint(1) NOT NULL DEFAULT '0',
  `printshop_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bookbindings_printshop_id_tipo_unique` (`printshop_id`,`tipo`),
  CONSTRAINT `bookbindings_printshop_id_foreign` FOREIGN KEY (`printshop_id`) REFERENCES `printshops` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookbindings`
--

LOCK TABLES `bookbindings` WRITE;
/*!40000 ALTER TABLE `bookbindings` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookbindings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credits`
--

DROP TABLE IF EXISTS `credits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credits` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `disponibile` double(8,2) NOT NULL,
  `totale` double(8,2) NOT NULL,
  `printshop_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `credits_printshop_id_user_id_unique` (`printshop_id`,`user_id`),
  KEY `credits_user_id_foreign` (`user_id`),
  CONSTRAINT `credits_printshop_id_foreign` FOREIGN KEY (`printshop_id`) REFERENCES `printshops` (`id`),
  CONSTRAINT `credits_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credits`
--

LOCK TABLES `credits` WRITE;
/*!40000 ALTER TABLE `credits` DISABLE KEYS */;
/*!40000 ALTER TABLE `credits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `categoria` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estensione` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `selezionato` tinyint(1) NOT NULL DEFAULT '0',
  `printshop_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `files_categoria_unique` (`categoria`),
  KEY `files_printshop_id_foreign` (`printshop_id`),
  CONSTRAINT `files_printshop_id_foreign` FOREIGN KEY (`printshop_id`) REFERENCES `printshops` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `migrations`
--

DROP TABLE IF EXISTS `migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `migrations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `migrations`
--

LOCK TABLES `migrations` WRITE;
/*!40000 ALTER TABLE `migrations` DISABLE KEYS */;
INSERT INTO `migrations` VALUES (1,'2014_10_12_000000_create_users_table',1),(2,'2014_10_12_100000_create_password_resets_table',1),(3,'2019_01_26_193017_create_printshops_table',2),(7,'2019_02_02_165658_create_files_table',3),(10,'2019_02_08_191636_create_papers_table',4),(12,'2019_02_08_191721_create_bookbindings_table',5),(25,'2019_02_13_175204_create_credits_table',6),(27,'2019_02_16_122044_create_orders_table',7),(28,'2014_10_12_000000_create_users_table',1),(29,'2014_10_12_100000_create_password_resets_table',1),(30,'2019_01_26_193017_create_printshops_table',1),(31,'2019_02_02_165658_create_files_table',1),(32,'2019_02_08_191636_create_papers_table',1),(33,'2019_02_08_191721_create_bookbindings_table',1),(34,'2019_02_13_175204_create_credits_table',1),(35,'2019_02_16_122044_create_orders_table',1);
/*!40000 ALTER TABLE `migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `filename` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `filehash` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `filesize` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bothSides` tinyint(1) NOT NULL,
  `color` tinyint(1) NOT NULL,
  `pagesForSide` int(10) unsigned NOT NULL,
  `price` double(8,2) DEFAULT NULL,
  `confirmed` tinyint(1) NOT NULL DEFAULT '0',
  `accepted` tinyint(1) NOT NULL DEFAULT '0',
  `printed` tinyint(1) NOT NULL DEFAULT '0',
  `paper_id` int(10) unsigned NOT NULL,
  `bookbinding_id` int(10) unsigned NOT NULL,
  `credit_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orders_paper_id_foreign` (`paper_id`),
  KEY `orders_bookbinding_id_foreign` (`bookbinding_id`),
  KEY `orders_credit_id_foreign` (`credit_id`),
  CONSTRAINT `orders_bookbinding_id_foreign` FOREIGN KEY (`bookbinding_id`) REFERENCES `bookbindings` (`id`),
  CONSTRAINT `orders_credit_id_foreign` FOREIGN KEY (`credit_id`) REFERENCES `credits` (`id`),
  CONSTRAINT `orders_paper_id_foreign` FOREIGN KEY (`paper_id`) REFERENCES `papers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `papers`
--

DROP TABLE IF EXISTS `papers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `papers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `formato` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descrizione` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `prezzoBN` double(8,2) DEFAULT NULL,
  `prezzoC` double(8,2) DEFAULT NULL,
  `selezionato` tinyint(1) NOT NULL DEFAULT '0',
  `printshop_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `papers_printshop_id_formato_unique` (`printshop_id`,`formato`),
  CONSTRAINT `papers_printshop_id_foreign` FOREIGN KEY (`printshop_id`) REFERENCES `printshops` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `papers`
--

LOCK TABLES `papers` WRITE;
/*!40000 ALTER TABLE `papers` DISABLE KEYS */;
/*!40000 ALTER TABLE `papers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_resets`
--

DROP TABLE IF EXISTS `password_resets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  KEY `password_resets_email_index` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_resets`
--

LOCK TABLES `password_resets` WRITE;
/*!40000 ALTER TABLE `password_resets` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_resets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `printshops`
--

DROP TABLE IF EXISTS `printshops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `printshops` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `is_printshop` tinyint(1) NOT NULL DEFAULT '0',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `vatnumber` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `printshops_email_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `printshops`
--

LOCK TABLES `printshops` WRITE;
/*!40000 ALTER TABLE `printshops` DISABLE KEYS */;
INSERT INTO `printshops` VALUES (1,'da danilo','dadanilo@a.a',NULL,'$2y$10$ykrRnNjUV5w.i8FXn4dHcuOJ2b6lsd4dBCh9.EBuF2ckX.3Q8CYtS','wkpCNL4ZouB4uQ4I4EJ2Q055Wy9bVwaWzk08jkPx8yYhYsLRWdHbcErSKeWk','2019-01-26 21:46:47','2019-01-26 21:46:47',0,'Via Passo Gravina, 83, 95125 Catania','04483330876','09545265'),(2,'Mario@m.m','mario@m.m',NULL,'$2y$10$wb4ZtarBjfQdUJun7Idl7OKl8k812Aoho8f4J/tGe0skgl2euGFwe',NULL,'2019-01-28 21:25:11','2019-01-28 21:25:11',0,'Via Giannone 7','Hhhh','391772885645423'),(3,'aistain','aistain@a.a',NULL,'$2y$10$ThOtZWa/mqfW8fU8HxNDXOZs/Xs7pxttmJXpsZ8euLmEjGN4jwI5G',NULL,'2019-02-08 19:55:55','2019-02-08 19:55:55',0,'aaa','aaaa','00000'),(4,'AbusivoMarcio','abusivo@gmail.it',NULL,'$2y$10$8jllPUNgj832/zoPjiwYJu8lYbY2Evt2gbqXIll1SAPuU7uJy7wdq',NULL,'2019-02-13 00:32:55','2019-02-13 00:32:55',0,'via del marciume, 6','04483330876','093595959'),(5,'roberto','solar@hotmail.com',NULL,'$2y$10$pAKCp2Andhm/Wjlv8xpjHO4UUznfzU//pY8Wc4QIgFG3Govs9cuzG',NULL,'2019-02-13 16:30:07','2019-02-13 16:30:07',0,'robbert','156746413','333333333330098'),(6,'damanlio','manlio@m.m',NULL,'$2y$10$ABRGCmSKtqV.vDnoNUVfJu6kihPOr1f6UYOUdpvKJJYtJ8vg5hWmK',NULL,'2019-02-25 13:07:16','2019-02-25 13:07:16',0,'manlio pelato','alessandro puppo','123455667');
/*!40000 ALTER TABLE `printshops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-27 13:16:50
