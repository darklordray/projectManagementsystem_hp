-- Host: localhost    Database: poisepms
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `projectNumber` varchar(50) DEFAULT NULL,
  `projectName` varchar(50) DEFAULT NULL,
  `buildingType` varchar(50) DEFAULT NULL,
  `buildingAddress` varchar(250) DEFAULT NULL,
  `erfNumber` varchar(50) DEFAULT NULL,
  `totalFee` decimal(10,2) DEFAULT NULL,
  `totalPaid` decimal(10,2) DEFAULT NULL,
  `deadline` varchar(50) DEFAULT NULL,
  `architect` varchar(50) DEFAULT NULL,
  `customer` varchar(50) DEFAULT NULL,
  `projectManager` varchar(50) DEFAULT NULL,
  `structuralEngineer` varchar(50) DEFAULT NULL,
  `completed` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES ('Proj-4','Motel Fred','Motel','3 Flemming way; Fish hoek; Cape Town; 7800','503',1234567.00,10000.00,'2021-12-21','Donald Trump','Bill Clinton','Barack Obama','Nelson Mandela','false'),('Proj-5','Hotel Jim','Hotel','4 Flemming way; Fish hoek; Cape Town; 7800','510',1234567.00,10000.00,'2021-12-31','Donald Trump','Bill Clinton','Barack Obama','Nelson Mandela','false');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

