-- MariaDB dump 10.19  Distrib 10.6.7-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: BBDD_PAISES
-- ------------------------------------------------------
-- Server version	10.6.7-MariaDB-2ubuntu1.1
DROP DATABASE IF EXISTS BBDD_PAISES;
CREATE DATABASE BBDD_PAISES;
USE BBDD_PAISES;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `IDIOMAS`
--

DROP TABLE IF EXISTS `IDIOMAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IDIOMAS` (
  `ID_IDIOMA` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(60) NOT NULL,
  PRIMARY KEY (`ID_IDIOMA`),
  UNIQUE KEY `AK_NOMBRE` (`NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=378 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IDIOMAS`
--

LOCK TABLES `IDIOMAS` WRITE;
/*!40000 ALTER TABLE `IDIOMAS` DISABLE KEYS */;
INSERT INTO `IDIOMAS` VALUES (19,'(Eastern) Punjabi'),(118,'Afar'),(237,'Afrikaans'),(5,'Albanian'),(120,'Amharic'),(6,'Arabic'),(17,'Armenian'),(38,'Aymara'),(22,'Azerbaijani'),(27,'Belarusian'),(25,'Bengali'),(116,'Bilen'),(366,'Bislama'),(41,'Bosnian'),(55,'Bulgarian'),(235,'Burmese'),(9,'Catalan'),(144,'Chamorro'),(211,'Chichewa'),(72,'Chinese'),(86,'Cook Islands Māori'),(42,'Croatian'),(96,'Czech'),(98,'Danish'),(213,'Divehi'),(18,'Dutch'),(36,'Dzongkha'),(7,'English'),(119,'Estonian'),(109,'Fang'),(122,'Faroese'),(125,'Fiji Hindi'),(124,'Fijian'),(127,'Finnish'),(30,'French'),(57,'Fula'),(135,'Georgian'),(21,'German'),(93,'Greek (modern)'),(16,'Guaraní'),(154,'Haitian'),(176,'Hebrew (modern)'),(165,'Hindi'),(161,'Hungarian'),(164,'Icelandic'),(167,'Indonesian'),(172,'Irish'),(157,'Italian'),(180,'Japanese'),(140,'Kalaallisut'),(184,'Kazakh'),(60,'Khmer'),(278,'Kinyarwanda'),(59,'Kirundi'),(82,'Kongo'),(250,'Korean'),(114,'Kunama'),(171,'Kurdish'),(190,'Kyrgyz'),(192,'Lao'),(156,'Latin'),(193,'Latvian'),(79,'Lingala'),(201,'Lithuanian'),(84,'Luba-Katanga'),(204,'Luxembourgish'),(207,'Macedonian'),(209,'Malagasy'),(54,'Malay'),(212,'Malaysian'),(215,'Maltese'),(175,'Manx'),(244,'Māori'),(218,'Marshallese'),(227,'Mongolian'),(117,'Nara'),(239,'Nauruan'),(240,'Nepali'),(377,'Northern Ndebele'),(46,'Norwegian'),(47,'Norwegian Bokmål'),(48,'Norwegian Nynorsk'),(1,'Pashto'),(169,'Persian (Farsi)'),(268,'Polish'),(10,'Portuguese'),(39,'Quechua'),(225,'Romanian'),(337,'Romansh'),(126,'Rotuman'),(13,'Russian'),(115,'Saho'),(8,'Samoan'),(68,'Sango'),(43,'Serbian'),(376,'Shona'),(324,'Sinhalese'),(97,'Slovak'),(307,'Slovene'),(309,'Somali'),(313,'Southern Ndebele'),(197,'Southern Sotho'),(15,'Spanish'),(83,'Swahili'),(315,'Swati'),(4,'Swedish'),(340,'Tajik'),(302,'Tamil'),(344,'Thai'),(113,'Tigre'),(110,'Tigrinya'),(349,'Tonga (Tonga Islands)'),(317,'Tsonga'),(45,'Tswana'),(94,'Turkish'),(3,'Turkmen'),(359,'Ukrainian'),(257,'Urdu'),(2,'Uzbek'),(318,'Venda'),(370,'Vietnamese'),(319,'Xhosa'),(320,'Zulu');
/*!40000 ALTER TABLE `IDIOMAS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IDIOMAS_PAISES`
--

DROP TABLE IF EXISTS `IDIOMAS_PAISES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IDIOMAS_PAISES` (
  `IDIOMA` int(10) unsigned NOT NULL,
  `PAIS` int(10) unsigned NOT NULL,
  PRIMARY KEY (`IDIOMA`,`PAIS`),
  KEY `FK_PAIS_IDIOMA` (`PAIS`),
  CONSTRAINT `FK_IDIOMA` FOREIGN KEY (`IDIOMA`) REFERENCES `IDIOMAS` (`ID_IDIOMA`) ON UPDATE CASCADE,
  CONSTRAINT `FK_PAIS_IDIOMA` FOREIGN KEY (`PAIS`) REFERENCES `PAISES` (`ID_PAIS`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IDIOMAS_PAISES`
--

LOCK TABLES `IDIOMAS_PAISES` WRITE;
/*!40000 ALTER TABLE `IDIOMAS_PAISES` DISABLE KEYS */;
INSERT INTO `IDIOMAS_PAISES` VALUES (1,1),(2,1),(2,242),(3,1),(3,233),(4,2),(4,77),(4,219),(5,3),(5,150),(5,183),(6,4),(6,18),(6,47),(6,53),(6,64),(6,68),(6,71),(6,109),(6,112),(6,117),(6,121),(6,125),(6,128),(6,142),(6,152),(6,169),(6,172),(6,182),(6,198),(6,208),(6,214),(6,221),(6,231),(6,238),(6,248),(7,5),(7,8),(7,9),(7,10),(7,14),(7,17),(7,20),(7,23),(7,25),(7,30),(7,33),(7,34),(7,35),(7,36),(7,42),(7,43),(7,45),(7,50),(7,51),(7,56),(7,60),(7,65),(7,71),(7,74),(7,76),(7,83),(7,86),(7,87),(7,90),(7,92),(7,94),(7,97),(7,99),(7,103),(7,105),(7,110),(7,111),(7,114),(7,116),(7,119),(7,120),(7,126),(7,127),(7,135),(7,139),(7,140),(7,143),(7,146),(7,151),(7,155),(7,156),(7,160),(7,163),(7,164),(7,165),(7,167),(7,170),(7,171),(7,174),(7,177),(7,178),(7,181),(7,187),(7,189),(7,190),(7,191),(7,192),(7,194),(7,195),(7,201),(7,202),(7,203),(7,204),(7,207),(7,209),(7,210),(7,214),(7,215),(7,218),(7,224),(7,228),(7,229),(7,230),(7,234),(7,235),(7,236),(7,239),(7,240),(7,243),(7,249),(7,250),(8,5),(8,195),(9,6),(10,7),(10,32),(10,44),(10,70),(10,96),(10,132),(10,153),(10,180),(10,197),(10,226),(13,9),(13,21),(13,118),(13,122),(13,186),(13,223),(13,233),(13,242),(15,11),(15,23),(15,27),(15,48),(15,52),(15,57),(15,59),(15,66),(15,67),(15,69),(15,70),(15,92),(15,93),(15,101),(15,145),(15,161),(15,173),(15,175),(15,176),(15,181),(15,212),(15,241),(15,244),(15,247),(16,11),(16,175),(17,12),(17,61),(18,13),(18,22),(18,28),(18,60),(18,158),(18,192),(18,204),(18,216),(19,13),(19,60),(21,15),(21,22),(21,85),(21,100),(21,129),(21,131),(21,220),(22,16),(25,19),(27,21),(30,22),(30,24),(30,39),(30,40),(30,42),(30,43),(30,46),(30,47),(30,53),(30,54),(30,55),(30,64),(30,70),(30,78),(30,79),(30,80),(30,81),(30,82),(30,91),(30,94),(30,95),(30,98),(30,100),(30,107),(30,116),(30,125),(30,131),(30,134),(30,138),(30,141),(30,144),(30,148),(30,159),(30,162),(30,184),(30,187),(30,188),(30,192),(30,193),(30,199),(30,201),(30,220),(30,227),(30,243),(30,246),(36,26),(38,27),(39,27),(41,29),(41,150),(42,29),(42,58),(42,150),(43,29),(43,150),(43,183),(43,200),(45,30),(45,209),(46,31),(46,168),(46,217),(47,31),(47,168),(48,31),(48,168),(54,37),(54,203),(55,38),(57,39),(57,95),(59,40),(60,41),(68,46),(72,49),(72,103),(72,132),(72,203),(72,222),(79,54),(79,55),(82,55),(83,55),(83,119),(83,224),(83,236),(84,55),(86,56),(93,61),(93,88),(94,61),(94,232),(96,62),(97,62),(97,205),(98,63),(109,70),(110,71),(113,71),(114,71),(115,71),(116,71),(117,71),(118,71),(119,72),(120,73),(122,75),(124,76),(125,76),(126,76),(127,77),(135,84),(140,89),(144,92),(144,167),(154,98),(156,100),(157,100),(157,113),(157,196),(157,220),(161,102),(164,104),(165,105),(167,106),(169,108),(171,109),(172,110),(175,111),(176,112),(180,115),(184,118),(190,122),(192,123),(193,124),(197,126),(197,209),(201,130),(204,131),(207,133),(209,134),(211,135),(212,136),(213,137),(215,139),(218,140),(225,147),(225,185),(227,149),(235,154),(237,155),(237,209),(239,156),(240,157),(244,160),(250,166),(250,211),(257,170),(268,179),(278,187),(302,203),(302,213),(307,206),(309,208),(313,209),(315,209),(315,218),(317,209),(318,209),(319,209),(320,209),(324,213),(337,220),(340,223),(344,225),(349,229),(359,237),(366,243),(370,245),(376,250),(377,250);
/*!40000 ALTER TABLE `IDIOMAS_PAISES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MONEDAS`
--

DROP TABLE IF EXISTS `MONEDAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MONEDAS` (
  `ID_MONEDA` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(60) NOT NULL,
  PRIMARY KEY (`ID_MONEDA`)
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MONEDAS`
--

LOCK TABLES `MONEDAS` WRITE;
/*!40000 ALTER TABLE `MONEDAS` DISABLE KEYS */;
INSERT INTO `MONEDAS` VALUES (1,'Afghan afghani'),(2,'Euro'),(3,'Albanian lek'),(4,'Algerian dinar'),(5,'United States Dollar'),(6,'Euro'),(7,'Angolan kwanza'),(8,'East Caribbean dollar'),(9,'East Caribbean dollar'),(10,'Argentine peso'),(11,'Armenian dram'),(12,'Aruban florin'),(13,'Australian dollar'),(14,'Euro'),(15,'Azerbaijani manat'),(16,'Bahamian dollar'),(17,'Bahraini dinar'),(18,'Bangladeshi taka'),(19,'Barbadian dollar'),(20,'New Belarusian ruble'),(21,'Old Belarusian ruble'),(22,'Euro'),(23,'Belize dollar'),(24,'West African CFA franc'),(25,'Bermudian dollar'),(26,'Bhutanese ngultrum'),(27,'Indian rupee'),(28,'Bolivian boliviano'),(29,'United States dollar'),(30,'Bosnia and Herzegovina convertible mark'),(31,'Botswana pula'),(32,'Norwegian krone'),(33,'Brazilian real'),(34,'United States dollar'),(35,'British pound'),(36,'United States dollar'),(37,'United States dollar'),(38,'Brunei dollar'),(39,'Singapore dollar'),(40,'Bulgarian lev'),(41,'West African CFA franc'),(42,'Burundian franc'),(43,'Cambodian riel'),(44,'United States dollar'),(45,'Central African CFA franc'),(46,'Canadian dollar'),(47,'Cape Verdean escudo'),(48,'Cayman Islands dollar'),(49,'Central African CFA franc'),(50,'Central African CFA franc'),(51,'Chilean peso'),(52,'Chinese yuan'),(53,'Australian dollar'),(54,'Australian dollar'),(55,'Colombian peso'),(56,'Comorian franc'),(57,'Central African CFA franc'),(58,'Congolese franc'),(59,'New Zealand dollar'),(60,'Cook Islands dollar'),(61,'Costa Rican colón'),(62,'Croatian kuna'),(63,'Cuban convertible peso'),(64,'Cuban peso'),(65,'Netherlands Antillean guilder'),(66,'Euro'),(67,'Czech koruna'),(68,'Danish krone'),(69,'Djiboutian franc'),(70,'East Caribbean dollar'),(71,'Dominican peso'),(72,'United States dollar'),(73,'Egyptian pound'),(74,'United States dollar'),(75,'Central African CFA franc'),(76,'Eritrean nakfa'),(77,'Euro'),(78,'Ethiopian birr'),(79,'Falkland Islands pound'),(80,'Danish krone'),(81,'Faroese króna'),(82,'Fijian dollar'),(83,'Euro'),(84,'Euro'),(85,'Euro'),(86,'CFP franc'),(87,'Euro'),(88,'Central African CFA franc'),(89,'Gambian dalasi'),(90,'Georgian Lari'),(91,'Euro'),(92,'Ghanaian cedi'),(93,'Gibraltar pound'),(94,'Euro'),(95,'Danish krone'),(96,'East Caribbean dollar'),(97,'Euro'),(98,'United States dollar'),(99,'Guatemalan quetzal'),(100,'British pound'),(101,'Guernsey pound'),(102,'Guinean franc'),(103,'West African CFA franc'),(104,'Guyanese dollar'),(105,'Haitian gourde'),(106,'Australian dollar'),(107,'Euro'),(108,'Honduran lempira'),(109,'Hungarian forint'),(110,'Hong Kong dollar'),(111,'Icelandic króna'),(112,'Indian rupee'),(113,'Indonesian rupiah'),(114,'West African CFA franc'),(115,'Iranian rial'),(116,'Iraqi dinar'),(117,'Euro'),(118,'British pound'),(119,'Manx pound'),(120,'Israeli new shekel'),(121,'Euro'),(122,'Jamaican dollar'),(123,'Japanese yen'),(124,'British pound'),(125,'Jersey pound'),(126,'Jordanian dinar'),(127,'Kazakhstani tenge'),(128,'Kenyan shilling'),(129,'Australian dollar'),(130,'Kiribati dollar'),(131,'Kuwaiti dinar'),(132,'Kyrgyzstani som'),(133,'Lao kip'),(134,'Euro'),(135,'Lebanese pound'),(136,'Lesotho loti'),(137,'South African rand'),(138,'Liberian dollar'),(139,'Libyan dinar'),(140,'Swiss franc'),(141,'Euro'),(142,'Euro'),(143,'Macanese pataca'),(144,'Macedonian denar'),(145,'Malagasy ariary'),(146,'Malawian kwacha'),(147,'Malaysian ringgit'),(148,'Maldivian rufiyaa'),(149,'West African CFA franc'),(150,'Euro'),(151,'United States dollar'),(152,'Euro'),(153,'Mauritanian ouguiya'),(154,'Mauritian rupee'),(155,'Euro'),(156,'Mexican peso'),(157,'United States dollar'),(158,'Moldovan leu'),(159,'Euro'),(160,'Mongolian tögrög'),(161,'Euro'),(162,'East Caribbean dollar'),(163,'Moroccan dirham'),(164,'Mozambican metical'),(165,'Burmese kyat'),(166,'Namibian dollar'),(167,'South African rand'),(168,'Australian dollar'),(169,'Nepalese rupee'),(170,'Euro'),(171,'CFP franc'),(172,'New Zealand dollar'),(173,'Nicaraguan córdoba'),(174,'West African CFA franc'),(175,'Nigerian naira'),(176,'New Zealand dollar'),(177,'Niue dollar'),(178,'Australian dollar'),(179,'North Korean won'),(180,'United States dollar'),(181,'Norwegian krone'),(182,'Omani rial'),(183,'Pakistani rupee'),(184,'United States dollar'),(185,'Egyptian pound'),(186,'Israeli new shekel'),(187,'Jordanian dinar'),(188,'Panamanian balboa'),(189,'United States dollar'),(190,'Papua New Guinean kina'),(191,'Paraguayan guaraní'),(192,'Peruvian sol'),(193,'Philippine peso'),(194,'New Zealand dollar'),(195,'Pitcairn Islands dollar'),(196,'Polish złoty'),(197,'Euro'),(198,'United States dollar'),(199,'Qatari riyal'),(200,'Euro'),(201,'Euro'),(202,'Romanian leu'),(203,'Russian ruble'),(204,'Rwandan franc'),(205,'Euro'),(206,'Saint Helena pound'),(207,'East Caribbean dollar'),(208,'East Caribbean dollar'),(209,'Euro'),(210,'Euro'),(211,'East Caribbean dollar'),(212,'Samoan tālā'),(213,'Euro'),(214,'São Tomé and Príncipe dobra'),(215,'Saudi riyal'),(216,'West African CFA franc'),(217,'Serbian dinar'),(218,'Seychellois rupee'),(219,'Sierra Leonean leone'),(220,'Singapore dollar'),(221,'Netherlands Antillean guilder'),(222,'Euro'),(223,'Euro'),(224,'Solomon Islands dollar'),(225,'Somali shilling'),(226,'South African rand'),(227,'Falkland Islands Pound'),(228,'South Korean won'),(229,'Euro'),(230,'Sri Lankan rupee'),(231,'Sudanese pound'),(232,'South Sudanese pound'),(233,'Surinamese dollar'),(234,'Norwegian krone'),(235,'Swazi lilangeni'),(236,'Swedish krona'),(237,'Swiss franc'),(238,'Syrian pound'),(239,'New Taiwan dollar'),(240,'Tajikistani somoni'),(241,'Tanzanian shilling'),(242,'Thai baht'),(243,'United States Dollar'),(244,'West African CFA franc'),(245,'New Zealand dollar'),(246,'Tongan paʻanga'),(247,'Trinidad and Tobago dollar'),(248,'Tunisian dinar'),(249,'Turkish lira'),(250,'Turkmenistan manat'),(251,'United States dollar'),(252,'Australian dollar'),(253,'Tuvaluan dollar'),(254,'Ugandan shilling'),(255,'Ukrainian hryvnia'),(256,'United Arab Emirates dirham'),(257,'British pound'),(258,'United States dollar'),(259,'Uruguayan peso'),(260,'Uzbekistani so\'m'),(261,'Vanuatu vatu'),(262,'Venezuelan bolívar'),(263,'Vietnamese đồng'),(264,'CFP franc'),(265,'Moroccan dirham'),(266,'Algerian dinar'),(267,'Yemeni rial'),(268,'Zambian kwacha'),(269,'Zambian kwacha');
/*!40000 ALTER TABLE `MONEDAS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MONEDAS_PAISES`
--

DROP TABLE IF EXISTS `MONEDAS_PAISES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MONEDAS_PAISES` (
  `MONEDA` int(10) unsigned NOT NULL,
  `PAIS` int(10) unsigned NOT NULL,
  PRIMARY KEY (`PAIS`,`MONEDA`),
  KEY `FK_MONEDA` (`MONEDA`),
  CONSTRAINT `FK_MONEDA` FOREIGN KEY (`MONEDA`) REFERENCES `MONEDAS` (`ID_MONEDA`) ON UPDATE CASCADE,
  CONSTRAINT `FK_PAIS` FOREIGN KEY (`PAIS`) REFERENCES `PAISES` (`ID_PAIS`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MONEDAS_PAISES`
--

LOCK TABLES `MONEDAS_PAISES` WRITE;
/*!40000 ALTER TABLE `MONEDAS_PAISES` DISABLE KEYS */;
INSERT INTO `MONEDAS_PAISES` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,10),(10,11),(11,12),(12,13),(13,14),(14,15),(15,16),(16,17),(17,18),(18,19),(19,20),(20,21),(21,21),(22,22),(23,23),(24,24),(25,25),(26,26),(27,26),(28,27),(29,28),(30,29),(31,30),(32,31),(33,32),(34,33),(35,34),(36,35),(37,36),(38,37),(39,37),(40,38),(41,39),(42,40),(43,41),(44,41),(45,42),(46,43),(47,44),(48,45),(49,46),(50,47),(51,48),(52,49),(53,50),(54,51),(55,52),(56,53),(57,54),(58,55),(59,56),(60,56),(61,57),(62,58),(63,59),(64,59),(65,60),(66,61),(67,62),(68,63),(69,64),(70,65),(71,66),(72,67),(73,68),(74,69),(75,70),(76,71),(77,72),(78,73),(79,74),(80,75),(81,75),(82,76),(83,77),(84,78),(85,79),(86,80),(87,81),(88,82),(89,83),(90,84),(91,85),(92,86),(93,87),(94,88),(95,89),(96,90),(97,91),(98,92),(99,93),(100,94),(101,94),(102,95),(103,96),(104,97),(105,98),(106,99),(107,100),(108,101),(109,102),(110,103),(111,104),(112,105),(113,106),(114,107),(115,108),(116,109),(117,110),(118,111),(119,111),(120,112),(121,113),(122,114),(123,115),(124,116),(125,116),(126,117),(127,118),(128,119),(129,120),(130,120),(131,121),(132,122),(133,123),(134,124),(135,125),(136,126),(137,126),(138,127),(139,128),(140,129),(141,130),(142,131),(143,132),(144,133),(145,134),(146,135),(147,136),(148,137),(149,138),(150,139),(151,140),(152,141),(153,142),(154,143),(155,144),(156,145),(157,146),(158,147),(159,148),(160,149),(161,150),(162,151),(163,152),(164,153),(165,154),(166,155),(167,155),(168,156),(169,157),(170,158),(171,159),(172,160),(173,161),(174,162),(175,163),(176,164),(177,164),(178,165),(179,166),(180,167),(181,168),(182,169),(183,170),(184,171),(185,172),(186,172),(187,172),(188,173),(189,173),(190,174),(191,175),(192,176),(193,177),(194,178),(195,178),(196,179),(197,180),(198,181),(199,182),(200,183),(201,184),(202,185),(203,186),(204,187),(205,188),(206,189),(207,190),(208,191),(209,192),(210,193),(211,194),(212,195),(213,196),(214,197),(215,198),(216,199),(217,200),(218,201),(219,202),(220,203),(221,204),(222,205),(223,206),(224,207),(225,208),(226,209),(227,210),(228,211),(229,212),(230,213),(231,214),(232,215),(233,216),(234,217),(235,218),(236,219),(237,220),(238,221),(239,222),(240,223),(241,224),(242,225),(243,226),(244,227),(245,228),(246,229),(247,230),(248,231),(249,232),(250,233),(251,234),(252,235),(253,235),(254,236),(255,237),(256,238),(257,239),(258,240),(259,241),(260,242),(261,243),(262,244),(263,245),(264,246),(265,247),(266,247),(267,248),(268,249),(269,250);
/*!40000 ALTER TABLE `MONEDAS_PAISES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PAISES`
--

DROP TABLE IF EXISTS `PAISES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAISES` (
  `ID_PAIS` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(80) NOT NULL,
  `NUM_HABITANTES` int(10) unsigned NOT NULL,
  `CAPITAL` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`ID_PAIS`),
  UNIQUE KEY `AK_NOMBRE` (`NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAISES`
--

LOCK TABLES `PAISES` WRITE;
/*!40000 ALTER TABLE `PAISES` DISABLE KEYS */;
INSERT INTO `PAISES` VALUES (1,'Afghanistan',40218234,'Kabul'),(2,'Åland Islands',28875,'Mariehamn'),(3,'Albania',2837743,'Tirana'),(4,'Algeria',44700000,'Algiers'),(5,'American Samoa',55197,'Pago Pago'),(6,'Andorra',77265,'Andorra la Vella'),(7,'Angola',32866268,'Luanda'),(8,'Anguilla',13452,'The Valley'),(9,'Antarctica',1000,NULL),(10,'Antigua and Barbuda',97928,'Saint John\'s'),(11,'Argentina',45376763,'Buenos Aires'),(12,'Armenia',2963234,'Yerevan'),(13,'Aruba',106766,'Oranjestad'),(14,'Australia',25687041,'Canberra'),(15,'Austria',8917205,'Vienna'),(16,'Azerbaijan',10110116,'Baku'),(17,'Bahamas',393248,'Nassau'),(18,'Bahrain',1701583,'Manama'),(19,'Bangladesh',164689383,'Dhaka'),(20,'Barbados',287371,'Bridgetown'),(21,'Belarus',9398861,'Minsk'),(22,'Belgium',11555997,'Brussels'),(23,'Belize',397621,'Belmopan'),(24,'Benin',12123198,'Porto-Novo'),(25,'Bermuda',63903,'Hamilton'),(26,'Bhutan',771612,'Thimphu'),(27,'Bolivia (Plurinational State of)',11673029,'Sucre'),(28,'Bonaire, Sint Eustatius and Saba',17408,'Kralendijk'),(29,'Bosnia and Herzegovina',3280815,'Sarajevo'),(30,'Botswana',2351625,'Gaborone'),(31,'Bouvet Island',0,NULL),(32,'Brazil',212559409,'Brasília'),(33,'British Indian Ocean Territory',3000,'Diego Garcia'),(34,'United States Minor Outlying Islands',300,NULL),(35,'Virgin Islands (British)',30237,'Road Town'),(36,'Virgin Islands (U.S.)',106290,'Charlotte Amalie'),(37,'Brunei Darussalam',437483,'Bandar Seri Begawan'),(38,'Bulgaria',6927288,'Sofia'),(39,'Burkina Faso',20903278,'Ouagadougou'),(40,'Burundi',11890781,'Gitega'),(41,'Cambodia',16718971,'Phnom Penh'),(42,'Cameroon',26545864,'Yaoundé'),(43,'Canada',38005238,'Ottawa'),(44,'Cabo Verde',555988,'Praia'),(45,'Cayman Islands',65720,'George Town'),(46,'Central African Republic',4829764,'Bangui'),(47,'Chad',16425859,'N\'Djamena'),(48,'Chile',19116209,'Santiago'),(49,'China',1402112000,'Beijing'),(50,'Christmas Island',2072,'Flying Fish Cove'),(51,'Cocos (Keeling) Islands',550,'West Island'),(52,'Colombia',50882884,'Bogotá'),(53,'Comoros',869595,'Moroni'),(54,'Congo',5518092,'Brazzaville'),(55,'Congo (Democratic Republic of the)',89561404,'Kinshasa'),(56,'Cook Islands',18100,'Avarua'),(57,'Costa Rica',5094114,'San José'),(58,'Croatia',4047200,'Zagreb'),(59,'Cuba',11326616,'Havana'),(60,'Curaçao',155014,'Willemstad'),(61,'Cyprus',1207361,'Nicosia'),(62,'Czech Republic',10698896,'Prague'),(63,'Denmark',5831404,'Copenhagen'),(64,'Djibouti',988002,'Djibouti'),(65,'Dominica',71991,'Roseau'),(66,'Dominican Republic',10847904,'Santo Domingo'),(67,'Ecuador',17643060,'Quito'),(68,'Egypt',102334403,'Cairo'),(69,'El Salvador',6486201,'San Salvador'),(70,'Equatorial Guinea',1402985,'Malabo'),(71,'Eritrea',5352000,'Asmara'),(72,'Estonia',1331057,'Tallinn'),(73,'Ethiopia',114963583,'Addis Ababa'),(74,'Falkland Islands (Malvinas)',2563,'Stanley'),(75,'Faroe Islands',48865,'Tórshavn'),(76,'Fiji',896444,'Suva'),(77,'Finland',5530719,'Helsinki'),(78,'France',67391582,'Paris'),(79,'French Guiana',254541,'Cayenne'),(80,'French Polynesia',280904,'Papeetē'),(81,'French Southern Territories',140,'Port-aux-Français'),(82,'Gabon',2225728,'Libreville'),(83,'Gambia',2416664,'Banjul'),(84,'Georgia',3714000,'Tbilisi'),(85,'Germany',83240525,'Berlin'),(86,'Ghana',31072945,'Accra'),(87,'Gibraltar',33691,'Gibraltar'),(88,'Greece',10715549,'Athens'),(89,'Greenland',56367,'Nuuk'),(90,'Grenada',112519,'St. George\'s'),(91,'Guadeloupe',400132,'Basse-Terre'),(92,'Guam',168783,'Hagåtña'),(93,'Guatemala',16858333,'Guatemala City'),(94,'Guernsey',62999,'St. Peter Port'),(95,'Guinea',13132792,'Conakry'),(96,'Guinea-Bissau',1967998,'Bissau'),(97,'Guyana',786559,'Georgetown'),(98,'Haiti',11402533,'Port-au-Prince'),(99,'Heard Island and McDonald Islands',0,NULL),(100,'Vatican City',451,'Vatican City'),(101,'Honduras',9904608,'Tegucigalpa'),(102,'Hungary',9749763,'Budapest'),(103,'Hong Kong',7481800,'City of Victoria'),(104,'Iceland',366425,'Reykjavík'),(105,'India',1380004385,'New Delhi'),(106,'Indonesia',273523621,'Jakarta'),(107,'Ivory Coast',26378275,'Yamoussoukro'),(108,'Iran (Islamic Republic of)',83992953,'Tehran'),(109,'Iraq',40222503,'Baghdad'),(110,'Ireland',4994724,'Dublin'),(111,'Isle of Man',85032,'Douglas'),(112,'Israel',9216900,'Jerusalem'),(113,'Italy',59554023,'Rome'),(114,'Jamaica',2961161,'Kingston'),(115,'Japan',125836021,'Tokyo'),(116,'Jersey',100800,'Saint Helier'),(117,'Jordan',10203140,'Amman'),(118,'Kazakhstan',18754440,'Nur-Sultan'),(119,'Kenya',53771300,'Nairobi'),(120,'Kiribati',119446,'South Tarawa'),(121,'Kuwait',4270563,'Kuwait City'),(122,'Kyrgyzstan',6591600,'Bishkek'),(123,'Lao People\'s Democratic Republic',7275556,'Vientiane'),(124,'Latvia',1901548,'Riga'),(125,'Lebanon',6825442,'Beirut'),(126,'Lesotho',2142252,'Maseru'),(127,'Liberia',5057677,'Monrovia'),(128,'Libya',6871287,'Tripoli'),(129,'Liechtenstein',38137,'Vaduz'),(130,'Lithuania',2794700,'Vilnius'),(131,'Luxembourg',632275,'Luxembourg'),(132,'Macao',649342,NULL),(133,'North Macedonia',2083380,'Skopje'),(134,'Madagascar',27691019,'Antananarivo'),(135,'Malawi',19129955,'Lilongwe'),(136,'Malaysia',32365998,'Kuala Lumpur'),(137,'Maldives',540542,'Malé'),(138,'Mali',20250834,'Bamako'),(139,'Malta',525285,'Valletta'),(140,'Marshall Islands',59194,'Majuro'),(141,'Martinique',378243,'Fort-de-France'),(142,'Mauritania',4649660,'Nouakchott'),(143,'Mauritius',1265740,'Port Louis'),(144,'Mayotte',226915,'Mamoudzou'),(145,'Mexico',128932753,'Mexico City'),(146,'Micronesia (Federated States of)',115021,'Palikir'),(147,'Moldova (Republic of)',2617820,'Chișinău'),(148,'Monaco',39244,'Monaco'),(149,'Mongolia',3278292,'Ulan Bator'),(150,'Montenegro',621718,'Podgorica'),(151,'Montserrat',4922,'Plymouth'),(152,'Morocco',36910558,'Rabat'),(153,'Mozambique',31255435,'Maputo'),(154,'Myanmar',54409794,'Naypyidaw'),(155,'Namibia',2540916,'Windhoek'),(156,'Nauru',10834,'Yaren'),(157,'Nepal',29136808,'Kathmandu'),(158,'Netherlands',17441139,'Amsterdam'),(159,'New Caledonia',271960,'Nouméa'),(160,'New Zealand',5084300,'Wellington'),(161,'Nicaragua',6624554,'Managua'),(162,'Niger',24206636,'Niamey'),(163,'Nigeria',206139587,'Abuja'),(164,'Niue',1470,'Alofi'),(165,'Norfolk Island',2302,'Kingston'),(166,'Korea (Democratic People\'s Republic of)',25778815,'Pyongyang'),(167,'Northern Mariana Islands',57557,'Saipan'),(168,'Norway',5379475,'Oslo'),(169,'Oman',5106622,'Muscat'),(170,'Pakistan',220892331,'Islamabad'),(171,'Palau',18092,'Ngerulmud'),(172,'Palestine, State of',4803269,'Ramallah'),(173,'Panama',4314768,'Panama City'),(174,'Papua New Guinea',8947027,'Port Moresby'),(175,'Paraguay',7132530,'Asunción'),(176,'Peru',32971846,'Lima'),(177,'Philippines',109581085,'Manila'),(178,'Pitcairn',56,'Adamstown'),(179,'Poland',37950802,'Warsaw'),(180,'Portugal',10305564,'Lisbon'),(181,'Puerto Rico',3194034,'San Juan'),(182,'Qatar',2881060,'Doha'),(183,'Republic of Kosovo',1775378,'Pristina'),(184,'Réunion',840974,'Saint-Denis'),(185,'Romania',19286123,'Bucharest'),(186,'Russian Federation',144104080,'Moscow'),(187,'Rwanda',12952209,'Kigali'),(188,'Saint Barthélemy',9417,'Gustavia'),(189,'Saint Helena, Ascension and Tristan da Cunha',4255,'Jamestown'),(190,'Saint Kitts and Nevis',53192,'Basseterre'),(191,'Saint Lucia',183629,'Castries'),(192,'Saint Martin (French part)',38659,'Marigot'),(193,'Saint Pierre and Miquelon',6069,'Saint-Pierre'),(194,'Saint Vincent and the Grenadines',110947,'Kingstown'),(195,'Samoa',198410,'Apia'),(196,'San Marino',33938,'City of San Marino'),(197,'Sao Tome and Principe',219161,'São Tomé'),(198,'Saudi Arabia',34813867,'Riyadh'),(199,'Senegal',16743930,'Dakar'),(200,'Serbia',6908224,'Belgrade'),(201,'Seychelles',98462,'Victoria'),(202,'Sierra Leone',7976985,'Freetown'),(203,'Singapore',5685807,'Singapore'),(204,'Sint Maarten (Dutch part)',40812,'Philipsburg'),(205,'Slovakia',5458827,'Bratislava'),(206,'Slovenia',2100126,'Ljubljana'),(207,'Solomon Islands',686878,'Honiara'),(208,'Somalia',15893219,'Mogadishu'),(209,'South Africa',59308690,'Pretoria'),(210,'South Georgia and the South Sandwich Islands',30,'King Edward Point'),(211,'Korea (Republic of)',51780579,'Seoul'),(212,'Spain',47351567,'Madrid'),(213,'Sri Lanka',21919000,'Sri Jayawardenepura Kotte'),(214,'Sudan',43849269,'Khartoum'),(215,'South Sudan',11193729,'Juba'),(216,'Suriname',586634,'Paramaribo'),(217,'Svalbard and Jan Mayen',2562,'Longyearbyen'),(218,'Swaziland',1160164,'Mbabane'),(219,'Sweden',10353442,'Stockholm'),(220,'Switzerland',8636896,'Bern'),(221,'Syrian Arab Republic',17500657,'Damascus'),(222,'Taiwan',23503349,'Taipei'),(223,'Tajikistan',9537642,'Dushanbe'),(224,'Tanzania, United Republic of',59734213,'Dodoma'),(225,'Thailand',69799978,'Bangkok'),(226,'Timor-Leste',1318442,'Dili'),(227,'Togo',8278737,'Lomé'),(228,'Tokelau',1411,'Fakaofo'),(229,'Tonga',105697,'Nuku\'alofa'),(230,'Trinidad and Tobago',1399491,'Port of Spain'),(231,'Tunisia',11818618,'Tunis'),(232,'Turkey',84339067,'Ankara'),(233,'Turkmenistan',6031187,'Ashgabat'),(234,'Turks and Caicos Islands',38718,'Cockburn Town'),(235,'Tuvalu',11792,'Funafuti'),(236,'Uganda',45741000,'Kampala'),(237,'Ukraine',44134693,'Kyiv'),(238,'United Arab Emirates',9890400,'Abu Dhabi'),(239,'United Kingdom of Great Britain and Northern Ireland',67215293,'London'),(240,'United States of America',329484123,'Washington, D.C.'),(241,'Uruguay',3473727,'Montevideo'),(242,'Uzbekistan',34232050,'Tashkent'),(243,'Vanuatu',307150,'Port Vila'),(244,'Venezuela (Bolivarian Republic of)',28435943,'Caracas'),(245,'Vietnam',97338583,'Hanoi'),(246,'Wallis and Futuna',11750,'Mata-Utu'),(247,'Western Sahara',510713,'El Aaiún'),(248,'Yemen',29825968,'Sana\'a'),(249,'Zambia',18383956,'Lusaka'),(250,'Zimbabwe',14862927,'Harare');
/*!40000 ALTER TABLE `PAISES` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-07 16:32:38
