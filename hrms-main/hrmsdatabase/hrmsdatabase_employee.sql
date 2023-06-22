-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hrmsdatabase
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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `aadhar_card_number` varchar(255) NOT NULL,
  `age` varchar(2) NOT NULL,
  `bank_account_number` varchar(255) DEFAULT NULL,
  `blood_group` varchar(255) NOT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `emergency_contact_name` varchar(255) NOT NULL,
  `emergency_contact_number` varchar(255) NOT NULL,
  `emergency_contact_relation` varchar(255) NOT NULL,
  `employee_code` varchar(255) DEFAULT NULL,
  `esi_number` varchar(255) NOT NULL,
  `father_name` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `highest_qualification` varchar(255) NOT NULL,
  `mail_id` varchar(255) DEFAULT NULL,
  `mother_name` varchar(255) NOT NULL,
  `pan_number` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `permanent_address` varchar(255) NOT NULL,
  `personal_mail_id` varchar(255) DEFAULT NULL,
  `pf_number` varchar(255) NOT NULL,
  `postal_address` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `uan_number` varchar(255) NOT NULL,
  `university_name` varchar(255) NOT NULL,
  `year_of_passing` date DEFAULT NULL,
  `account_type_id` bigint DEFAULT NULL,
  `department_id` bigint DEFAULT NULL,
  `designation_id` bigint DEFAULT NULL,
  `employee_type_id` bigint DEFAULT NULL,
  `gender_id` bigint DEFAULT NULL,
  `martial_status_id` bigint DEFAULT NULL,
  `shifts_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UniqueEsiNumber` (`esi_number`),
  UNIQUE KEY `UniquePfNumber` (`pf_number`),
  UNIQUE KEY `UniqueUanNumber` (`uan_number`),
  UNIQUE KEY `UniquePanNumber` (`pan_number`),
  UNIQUE KEY `UniqueAadharCardNumber` (`aadhar_card_number`),
  UNIQUE KEY `UK_fmtvenr426h7vfffp8bxqkuwb` (`emergency_contact_number`),
  UNIQUE KEY `UniqueBankAccountNumber` (`bank_account_number`),
  UNIQUE KEY `UniqueEmployeeCode` (`employee_code`),
  UNIQUE KEY `UK_cl27c3v8r4kkna6bqxds18cq7` (`contact_number`),
  UNIQUE KEY `UK_87p9x4qm1qknfu4a5os4huc5i` (`mail_id`),
  UNIQUE KEY `UK_kgo33452lyeou21ts19hxgqkg` (`personal_mail_id`),
  KEY `FK350yfleixq62y2cco4qal9n9d` (`account_type_id`),
  KEY `FKbejtwvg9bxus2mffsm3swj3u9` (`department_id`),
  KEY `FK5vd2yco7en4nguyi0jgudgjey` (`designation_id`),
  KEY `FKks0jnjwhw9tjwa2b1l0klv1fb` (`employee_type_id`),
  KEY `FKe0w26qjedr99vwno4pqauw9i9` (`gender_id`),
  KEY `FKnw1ko7tvsoxe5rrgdcgjkjkgp` (`martial_status_id`),
  KEY `FKtbn5e4i1vrigi1k9hdwbqtwa7` (`shifts_id`),
  CONSTRAINT `FK350yfleixq62y2cco4qal9n9d` FOREIGN KEY (`account_type_id`) REFERENCES `bank_account_type` (`id`),
  CONSTRAINT `FK5vd2yco7en4nguyi0jgudgjey` FOREIGN KEY (`designation_id`) REFERENCES `designation` (`id`),
  CONSTRAINT `FKbejtwvg9bxus2mffsm3swj3u9` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKe0w26qjedr99vwno4pqauw9i9` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `FKks0jnjwhw9tjwa2b1l0klv1fb` FOREIGN KEY (`employee_type_id`) REFERENCES `employee_type` (`id`),
  CONSTRAINT `FKnw1ko7tvsoxe5rrgdcgjkjkgp` FOREIGN KEY (`martial_status_id`) REFERENCES `martial_status` (`id`),
  CONSTRAINT `FKtbn5e4i1vrigi1k9hdwbqtwa7` FOREIGN KEY (`shifts_id`) REFERENCES `shifts` (`id`),
  CONSTRAINT `employee_chk_1` CHECK (((`age` >= 18) and (`age` <= 60)))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'823371846321','25','213125684111','O+','9014680021','1994-03-01','mikey','9014680021','Father','DB001','21641312423294221',' forman','Jayashree T M','M Tech','jayashree.tm@dollarbirdinc.com','marry','BYZWU7221U','$2a$10$uPgE0130K1L64mj00D.H4OTQzSF28K4EAxH3AtAquPIPk0tTrHToy','#562 France','personalmail01@gmail.com','31310q8aa7345848022521','Argentina','Active','167739108421','VTU','2022-01-01',1,3,3,1,2,2,1),(2,'823371846300','25','213125684100','O+','9014680022','1994-03-01','mikey','9014680000','Father','DB002','21641312423294200',' forman','Ravi Kiran','M Tech','ravi.k@dollarbirdinc.com','marry','BYZWU7001U','$2a$10$FzVWtdyXpaTenMeUJv.21.bkEpwSUNurab0..iowwVHuzDeVDmqDO','#562 France','personalmail010@gmail.com','31310q8aa7345848022500','Argentina','Active','167739108400','VTU','2022-01-01',1,3,1,1,1,2,1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-22 18:44:12
