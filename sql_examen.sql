-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: jesus-validozafra
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo` (
  `idcargo` varchar(3) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `sueldo_min` int NOT NULL,
  `sueldo_max` int NOT NULL,
  PRIMARY KEY (`idcargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES ('C01','Gerente General',15000,50000),('C02','Gerente',10000,15000),('C03','Empleado',7000,10000),('C04','Analista',5000,7000),('C05','Vendedor',3000,5000),('C06','Oficinista',1500,3000),('C07','Programador',2500,6000),('C08','Investigador',6000,8000),('C09','Digitador',1000,1500);
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `idempleado` varchar(5) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `fecingreso` date NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `idcargo` varchar(3) DEFAULT NULL,
  `iddepartamento` int DEFAULT NULL,
  `sueldo` int NOT NULL,
  `comision` int DEFAULT NULL,
  `jefe` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idempleado`),
  KEY `idcargo` (`idcargo`),
  CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`idcargo`) REFERENCES `cargo` (`idcargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('E0001','Coronel','Gustavo','2000-04-01','gcoronel@gmail.com','9666-4457','C01',100,25000,NULL,NULL),('E0002','Fernandez','Claudia','2000-05-01','cfernandez@gmail.com','9345-8365','C03',100,8000,NULL,'E0001'),('E0003','Matsukawa','Sergio','2000-04-01','smatsukawa@gmail.com','9772-8369','C02',102,15000,NULL,'E0001'),('E0004','Diaz','Mariela','2000-04-10','mdiaz@gmail.com','8654-6734','C06',102,1800,NULL,'E0003'),('E0005','Martinez','Roberto','2000-04-05','rmartinez@gmail.com',NULL,'C08',102,7000,500,'E0003'),('E0006','Espinoza','Miguel','2000-04-06','mespinoza@gmail.com',NULL,'C08',102,7500,500,'E0003'),('E0007','Ramos','Vanessa','2002-04-06','vramos@gmail.com','9456-3456','C08',102,7000,500,'E0003'),('E0008','Flores','Julio','2000-04-01','jflores@gmail.com',NULL,'C07',102,3500,1000,'E0003'),('E0009','Marcelo','Ricardo','2000-04-01','rmarcelo@gmail.com','9936-2966','C02',101,15000,NULL,'E0001'),('E0010','Barrios','Guisella','2001-01-15','gbarrios@gmail.com','9023-4512','C03',101,8000,NULL,'E0009'),('E0011','Cuellar','Lucy','2003-02-18','lcuellar@gmail.com',NULL,'C06',101,2000,NULL,'E0009'),('E0012','Valencia','Hugo','2000-05-01','hvalencia@gmail.com','9732-5601','C02',105,15000,NULL,'E0001'),('E0013','Veliz','Fortunato','2000-05-05','fveliz@gmail.com','9826-7603','C04',105,6000,NULL,'E0012'),('E0014','Aguero','Octavio','2000-05-15','oaguero@gmail.com',NULL,'C07',105,3000,300,'E0012'),('E0015','Rosales','Cesar','2003-03-15','crosales@gmail.com',NULL,'C07',105,2500,300,'E0012'),('E0016','Villarreal','Nora','2000-05-01','nvillarreal@gmail.com','9371-3641','C02',103,15000,NULL,'E0001'),('E0017','Romero','Alejandra','2000-05-03','aromero@gmail.com','8345-9526','C03',103,7500,NULL,'E0016'),('E0018','Valdiviezo','Jennifer','2000-06-12','jvaldiviezo@gmail.com','9263-5172','C06',103,2000,NULL,'E0016'),('E0019','Muchotrigo','Omar','2000-05-12','omuchotrigo@gmail.com','9963-5542','C05',103,3500,500,'E0016'),('E0020','Baltazar','Victor','2000-05-18','vbaltazar@gmail.com','9893-4433','C05',103,3000,800,'E0016'),('E0021','Castillo','Ronald','2001-05-18','rcastillo@gmail.com','9234-3487','C05',103,3000,800,'E0016'),('E0022','Espilco','Luis','2002-04-17','lespilco@gmail.com','9554-3456','C05',103,3000,300,'E0016');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idempleado` varchar(5) NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `clave` varchar(20) NOT NULL,
  `estado` int DEFAULT NULL,
  PRIMARY KEY (`idempleado`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`idempleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('E0001','gcoronelc','super',1),('E0008','jflores','cazador',1),('E0011','lcuellar','tigresa',0),('E0018','jvaldiviezo','gatita',1),('E0021','rcastillo','cerroeb',0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'jesus-validozafra'
--
/*!50003 DROP FUNCTION IF EXISTS `salario_maximo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `salario_maximo`() RETURNS varchar(99) CHARSET utf8mb4
BEGIN
	DECLARE	resultado varchar(99);
    DECLARE vempleado varchar(20);
    DECLARE vsueldo int;
    
    Select nombre, sueldo into vempleado, vsueldo from empleado where sueldo=(select max(sueldo) from empleado);
    
    set resultado = concat('Empleado: ',vempleado,', Sueldo: ',vsueldo);
    
RETURN resultado;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-25 14:25:55
