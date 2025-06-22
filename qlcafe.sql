CREATE DATABASE  IF NOT EXISTS `qlcafe` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `qlcafe`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: qlcafe
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `ban`
--

DROP TABLE IF EXISTS `ban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ban` (
  `ma_ban` int NOT NULL AUTO_INCREMENT,
  `ten_ban` varchar(50) NOT NULL,
  `tinh_trang` varchar(50) NOT NULL,
  PRIMARY KEY (`ma_ban`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ban`
--

LOCK TABLES `ban` WRITE;
/*!40000 ALTER TABLE `ban` DISABLE KEYS */;
INSERT INTO `ban` VALUES (1,'Bàn 1','Đã đặt'),(2,'Bàn 2','Đang sử dụng'),(3,'Bàn 3','Đã đặt'),(4,'Bàn 4','Rảnh'),(5,'Bàn 5','Rảnh'),(6,'Bàn 6','Rảnh'),(7,'Bàn 7','Rảnh'),(8,'Bàn 8','Rảnh'),(9,'Bàn 9','Đang sử dụng'),(10,'Bàn 10','Rảnh'),(11,'Bàn 11','Đang sử dụng'),(12,'Bàn 12','Đã đặt'),(13,'Bàn 13','Đã đặt'),(14,'Bàn 14','Đang sử dụng'),(15,'Bàn 15','Rảnh'),(16,'Bàn 16','Rảnh'),(17,'Bàn 17','Rảnh'),(18,'Bàn 18','Đã đặt'),(19,'Bàn 19','Đang sử dụng'),(20,'Bàn 20','Rảnh');
/*!40000 ALTER TABLE `ban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chi_tiet_dat_ban`
--

DROP TABLE IF EXISTS `chi_tiet_dat_ban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chi_tiet_dat_ban` (
  `ma_chi_tiet_dat_ban` int NOT NULL AUTO_INCREMENT,
  `ngay_gio_dat` datetime(6) DEFAULT NULL,
  `sdt_khach_hang` varchar(15) DEFAULT NULL,
  `ten_khach_hang` varchar(100) NOT NULL,
  `ma_ban` int NOT NULL,
  `ma_hoa_don` int DEFAULT NULL,
  `ma_nhan_vien` int NOT NULL,
  PRIMARY KEY (`ma_chi_tiet_dat_ban`),
  KEY `FK5klmlfhlk3fetnqttm3qxn65j` (`ma_ban`),
  KEY `FKk1guwxj3os0mmdhuuj9un3ilh` (`ma_hoa_don`),
  KEY `FKtkqdwvqhcvq0i36qrnu0bug9b` (`ma_nhan_vien`),
  CONSTRAINT `FK5klmlfhlk3fetnqttm3qxn65j` FOREIGN KEY (`ma_ban`) REFERENCES `ban` (`ma_ban`),
  CONSTRAINT `FKk1guwxj3os0mmdhuuj9un3ilh` FOREIGN KEY (`ma_hoa_don`) REFERENCES `hoa_don` (`ma_hoa_don`),
  CONSTRAINT `FKtkqdwvqhcvq0i36qrnu0bug9b` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhan_vien` (`ma_nhan_vien`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chi_tiet_dat_ban`
--

LOCK TABLES `chi_tiet_dat_ban` WRITE;
/*!40000 ALTER TABLE `chi_tiet_dat_ban` DISABLE KEYS */;
INSERT INTO `chi_tiet_dat_ban` VALUES (1,'2025-06-10 08:00:00.000000','0911000001','Nguyễn A',1,1,2),(2,'2025-06-15 13:30:00.000000','0911000002','Trần B',2,2,3),(3,'2025-06-18 17:45:00.000000','0911000003','Lê C',3,3,4),(4,'2025-06-20 09:15:00.000000','0911000004','Phạm D',4,NULL,5);
/*!40000 ALTER TABLE `chi_tiet_dat_ban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chi_tiet_hoa_don`
--

DROP TABLE IF EXISTS `chi_tiet_hoa_don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chi_tiet_hoa_don` (
  `ma_chi_tiet_hoa_don` int NOT NULL AUTO_INCREMENT,
  `gia_tai_thoi_diem_ban` decimal(18,2) NOT NULL,
  `so_luong` int NOT NULL,
  `thanh_tien` decimal(18,2) NOT NULL,
  `ma_hoa_don` int NOT NULL,
  `ma_thuc_don` int NOT NULL,
  PRIMARY KEY (`ma_chi_tiet_hoa_don`),
  KEY `FKk49dolcd69qi88u6a25i9x2e` (`ma_hoa_don`),
  KEY `FK885iouix7lswc0ka9u0ix7ft9` (`ma_thuc_don`),
  CONSTRAINT `FK885iouix7lswc0ka9u0ix7ft9` FOREIGN KEY (`ma_thuc_don`) REFERENCES `thuc_don` (`ma_thuc_don`),
  CONSTRAINT `FKk49dolcd69qi88u6a25i9x2e` FOREIGN KEY (`ma_hoa_don`) REFERENCES `hoa_don` (`ma_hoa_don`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chi_tiet_hoa_don`
--

LOCK TABLES `chi_tiet_hoa_don` WRITE;
/*!40000 ALTER TABLE `chi_tiet_hoa_don` DISABLE KEYS */;
INSERT INTO `chi_tiet_hoa_don` VALUES (1,30000.00,2,60000.00,1,1),(2,10000.00,1,10000.00,1,5),(3,45000.00,2,90000.00,2,4),(4,11000.00,2,22000.00,2,5),(5,25000.00,2,50000.00,3,2),(6,35000.00,1,35000.00,3,3);
/*!40000 ALTER TABLE `chi_tiet_hoa_don` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chi_tiet_thuc_don`
--

DROP TABLE IF EXISTS `chi_tiet_thuc_don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chi_tiet_thuc_don` (
  `ma_chi_tiet_thuc_don` int NOT NULL AUTO_INCREMENT,
  `don_vi_tinh` varchar(50) DEFAULT NULL,
  `khoi_luong` decimal(18,2) NOT NULL,
  `ma_hang_hoa` int NOT NULL,
  `ma_thuc_don` int NOT NULL,
  PRIMARY KEY (`ma_chi_tiet_thuc_don`),
  KEY `FKb0xh7e15gjump4aoso7qlxwi5` (`ma_hang_hoa`),
  KEY `FKnol600mpi5wmt2qjsyqg9gstw` (`ma_thuc_don`),
  CONSTRAINT `FKb0xh7e15gjump4aoso7qlxwi5` FOREIGN KEY (`ma_hang_hoa`) REFERENCES `hang_hoa` (`ma_hang_hoa`),
  CONSTRAINT `FKnol600mpi5wmt2qjsyqg9gstw` FOREIGN KEY (`ma_thuc_don`) REFERENCES `thuc_don` (`ma_thuc_don`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chi_tiet_thuc_don`
--

LOCK TABLES `chi_tiet_thuc_don` WRITE;
/*!40000 ALTER TABLE `chi_tiet_thuc_don` DISABLE KEYS */;
INSERT INTO `chi_tiet_thuc_don` VALUES (1,'ml',50.00,1,1),(2,'gam',30.00,2,1),(3,'gam',20.00,3,1),(4,'gam',40.00,2,2),(5,'gam',15.00,3,2),(6,'ml',60.00,4,3),(7,'gam',15.00,3,3),(8,'ml',100.00,1,4),(9,'gam',10.00,3,4),(10,'gam',25.00,5,4),(11,'ml',80.00,4,5),(12,'gam',10.00,3,5);
/*!40000 ALTER TABLE `chi_tiet_thuc_don` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chi_tieu`
--

DROP TABLE IF EXISTS `chi_tieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chi_tieu` (
  `ma_chi_tieu` int NOT NULL AUTO_INCREMENT,
  `ngay_chi` date NOT NULL,
  `so_tien` decimal(18,2) NOT NULL,
  `ten_khoan_chi` varchar(100) DEFAULT NULL,
  `ma_tai_khoan` int NOT NULL,
  PRIMARY KEY (`ma_chi_tieu`),
  KEY `FKh1hw81euenjetnr95xm4l6ux9` (`ma_tai_khoan`),
  CONSTRAINT `FKh1hw81euenjetnr95xm4l6ux9` FOREIGN KEY (`ma_tai_khoan`) REFERENCES `tai_khoan` (`ma_tai_khoan`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chi_tieu`
--

LOCK TABLES `chi_tieu` WRITE;
/*!40000 ALTER TABLE `chi_tieu` DISABLE KEYS */;
INSERT INTO `chi_tieu` VALUES (1,'2025-06-05',1000000.00,'Chi điện nước',1),(2,'2025-06-06',500000.00,'Chi nguyên liệu thêm',2),(3,'2025-06-07',750000.00,'Chi bảo trì thiết bị',3);
/*!40000 ALTER TABLE `chi_tieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chuc_vu`
--

DROP TABLE IF EXISTS `chuc_vu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuc_vu` (
  `ma_chuc_vu` int NOT NULL AUTO_INCREMENT,
  `luong` decimal(18,2) NOT NULL,
  `ten_chuc_vu` varchar(100) NOT NULL,
  PRIMARY KEY (`ma_chuc_vu`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuc_vu`
--

LOCK TABLES `chuc_vu` WRITE;
/*!40000 ALTER TABLE `chuc_vu` DISABLE KEYS */;
INSERT INTO `chuc_vu` VALUES (1,15000000.00,'Admin'),(2,8000000.00,'Nhân viên');
/*!40000 ALTER TABLE `chuc_vu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `don_nhap`
--

DROP TABLE IF EXISTS `don_nhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `don_nhap` (
  `ma_don_nhap` int NOT NULL AUTO_INCREMENT,
  `ngay_nhap` date NOT NULL,
  `so_luong` int NOT NULL,
  `tong_tien` decimal(18,2) NOT NULL,
  `ma_hang_hoa` int NOT NULL,
  `ma_nhan_vien` int NOT NULL,
  `ma_thiet_bi` int NOT NULL,
  PRIMARY KEY (`ma_don_nhap`),
  KEY `FKhwnx5o639a54jbqc3ev97gsaq` (`ma_hang_hoa`),
  KEY `FK332yraxhj1ywgcqykves885t7` (`ma_nhan_vien`),
  KEY `FK618lhybf2wiyxeeuqoma8mpbu` (`ma_thiet_bi`),
  CONSTRAINT `FK332yraxhj1ywgcqykves885t7` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhan_vien` (`ma_nhan_vien`),
  CONSTRAINT `FK618lhybf2wiyxeeuqoma8mpbu` FOREIGN KEY (`ma_thiet_bi`) REFERENCES `thiet_bi` (`ma_thiet_bi`),
  CONSTRAINT `FKhwnx5o639a54jbqc3ev97gsaq` FOREIGN KEY (`ma_hang_hoa`) REFERENCES `hang_hoa` (`ma_hang_hoa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `don_nhap`
--

LOCK TABLES `don_nhap` WRITE;
/*!40000 ALTER TABLE `don_nhap` DISABLE KEYS */;
INSERT INTO `don_nhap` VALUES (1,'2025-06-05',50,250000.00,1,2,1),(2,'2025-06-06',40,200000.00,2,3,1),(3,'2025-06-07',30,150000.00,3,4,1);
/*!40000 ALTER TABLE `don_nhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `don_vi_tinh`
--

DROP TABLE IF EXISTS `don_vi_tinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `don_vi_tinh` (
  `ma_don_vi_tinh` int NOT NULL AUTO_INCREMENT,
  `ten_don_vi` varchar(50) NOT NULL,
  PRIMARY KEY (`ma_don_vi_tinh`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `don_vi_tinh`
--

LOCK TABLES `don_vi_tinh` WRITE;
/*!40000 ALTER TABLE `don_vi_tinh` DISABLE KEYS */;
INSERT INTO `don_vi_tinh` VALUES (1,'ml'),(2,'gam'),(3,'chai'),(4,'lon'),(5,'kg');
/*!40000 ALTER TABLE `don_vi_tinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `don_xuat`
--

DROP TABLE IF EXISTS `don_xuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `don_xuat` (
  `ma_don_xuat` int NOT NULL AUTO_INCREMENT,
  `ngay_xuat` date NOT NULL,
  `so_luong` int NOT NULL,
  `tong_tien_xuat` decimal(18,2) NOT NULL,
  `ma_hang_hoa` int NOT NULL,
  `ma_nhan_vien` int NOT NULL,
  PRIMARY KEY (`ma_don_xuat`),
  KEY `FKl3hu25g3fwku0mw5a4ijwr0h3` (`ma_hang_hoa`),
  KEY `FK1d1814c6lxlv6dywrh4mf0nkq` (`ma_nhan_vien`),
  CONSTRAINT `FK1d1814c6lxlv6dywrh4mf0nkq` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhan_vien` (`ma_nhan_vien`),
  CONSTRAINT `FKl3hu25g3fwku0mw5a4ijwr0h3` FOREIGN KEY (`ma_hang_hoa`) REFERENCES `hang_hoa` (`ma_hang_hoa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `don_xuat`
--

LOCK TABLES `don_xuat` WRITE;
/*!40000 ALTER TABLE `don_xuat` DISABLE KEYS */;
INSERT INTO `don_xuat` VALUES (1,'2025-06-10',10,50000.00,1,2),(2,'2025-06-10',5,25000.00,2,3),(3,'2025-06-11',3,15000.00,3,4);
/*!40000 ALTER TABLE `don_xuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hang_hoa`
--

DROP TABLE IF EXISTS `hang_hoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hang_hoa` (
  `ma_hang_hoa` int NOT NULL AUTO_INCREMENT,
  `don_gia` decimal(18,2) NOT NULL,
  `so_luong` int NOT NULL,
  `ten_hang_hoa` varchar(100) NOT NULL,
  `ma_don_vi_tinh` int NOT NULL,
  PRIMARY KEY (`ma_hang_hoa`),
  KEY `FK38nfcovnxvv29s4hyov8yay4r` (`ma_don_vi_tinh`),
  CONSTRAINT `FK38nfcovnxvv29s4hyov8yay4r` FOREIGN KEY (`ma_don_vi_tinh`) REFERENCES `don_vi_tinh` (`ma_don_vi_tinh`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hang_hoa`
--

LOCK TABLES `hang_hoa` WRITE;
/*!40000 ALTER TABLE `hang_hoa` DISABLE KEYS */;
INSERT INTO `hang_hoa` VALUES (1,25000.00,1000,'Sữa đặc',1),(2,50000.00,500,'Cà phê rang xay',2),(3,15000.00,300,'Đường trắng',2),(4,20000.00,200,'Trà đào',1),(5,10000.00,100,'Đá viên',1);
/*!40000 ALTER TABLE `hang_hoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoa_don`
--

DROP TABLE IF EXISTS `hoa_don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoa_don` (
  `ma_hoa_don` int NOT NULL AUTO_INCREMENT,
  `ngay_gio_tao` datetime(6) DEFAULT NULL,
  `tong_tien` decimal(18,2) NOT NULL,
  `trang_thai` bit(1) NOT NULL,
  `ma_khuyen_mai` int DEFAULT NULL,
  PRIMARY KEY (`ma_hoa_don`),
  KEY `FK6txq78m528cmusfwtcnpcjpt9` (`ma_khuyen_mai`),
  CONSTRAINT `FK6txq78m528cmusfwtcnpcjpt9` FOREIGN KEY (`ma_khuyen_mai`) REFERENCES `khuyen_mai` (`ma_khuyen_mai`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don`
--

LOCK TABLES `hoa_don` WRITE;
/*!40000 ALTER TABLE `hoa_don` DISABLE KEYS */;
INSERT INTO `hoa_don` VALUES (1,'2025-06-10 09:30:00.000000',70000.00,_binary '',1),(2,'2025-06-15 14:00:00.000000',112000.00,_binary '',NULL),(3,'2025-06-18 18:45:00.000000',85000.00,_binary '',2);
/*!40000 ALTER TABLE `hoa_don` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khuyen_mai`
--

DROP TABLE IF EXISTS `khuyen_mai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khuyen_mai` (
  `ma_khuyen_mai` int NOT NULL AUTO_INCREMENT,
  `gia_tri_giam` decimal(18,2) NOT NULL,
  `loai_khuyen_mai` varchar(50) NOT NULL,
  `mo_ta` varchar(255) DEFAULT NULL,
  `ngay_bat_dau` date NOT NULL,
  `ngay_ket_thuc` date NOT NULL,
  `ten_khuyen_mai` varchar(100) NOT NULL,
  `trang_thai` bit(1) NOT NULL,
  PRIMARY KEY (`ma_khuyen_mai`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khuyen_mai`
--

LOCK TABLES `khuyen_mai` WRITE;
/*!40000 ALTER TABLE `khuyen_mai` DISABLE KEYS */;
INSERT INTO `khuyen_mai` VALUES (1,10000.00,'VNĐ','Giảm giá món cà phê','2025-06-01','2025-06-30','Summer Sale',_binary ''),(2,20.00,'%','Giảm toàn bộ menu 20%','2025-07-01','2025-07-15','Happy Week',_binary ''),(3,15000.00,'VNĐ','Khuyến mãi món đá xay','2025-05-01','2025-05-15','Cool Drink',_binary '\0');
/*!40000 ALTER TABLE `khuyen_mai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhan_vien`
--

DROP TABLE IF EXISTS `nhan_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhan_vien` (
  `ma_nhan_vien` int NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(200) DEFAULT NULL,
  `ho_ten` varchar(100) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `so_dien_thoai` varchar(15) DEFAULT NULL,
  `ma_chuc_vu` int NOT NULL,
  `ma_tai_khoan` int NOT NULL,
  PRIMARY KEY (`ma_nhan_vien`),
  UNIQUE KEY `UKs931jur9i7px0jt36iev3osli` (`ma_tai_khoan`),
  KEY `FK3hv4jsqpgveoch7m0vkw09nsl` (`ma_chuc_vu`),
  CONSTRAINT `FK3hv4jsqpgveoch7m0vkw09nsl` FOREIGN KEY (`ma_chuc_vu`) REFERENCES `chuc_vu` (`ma_chuc_vu`),
  CONSTRAINT `FKdpk3u6xuawsiksnkklx1pfeyw` FOREIGN KEY (`ma_tai_khoan`) REFERENCES `tai_khoan` (`ma_tai_khoan`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhan_vien`
--

LOCK TABLES `nhan_vien` WRITE;
/*!40000 ALTER TABLE `nhan_vien` DISABLE KEYS */;
INSERT INTO `nhan_vien` VALUES (2,'Hà Nội','Trần Văn Admin',_binary '\0','0901000001',1,1),(3,'TP.HCM','Nguyễn Văn A',_binary '\0','0901000002',2,2),(4,'Đà Nẵng','Lê Thị B',_binary '\0','0901000003',2,3),(5,'Cần Thơ','Phạm Văn C',_binary '\0','0901000004',2,4),(6,'Hải Phòng','Đỗ Thị D',_binary '\0','0901000005',2,5);
/*!40000 ALTER TABLE `nhan_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tai_khoan`
--

DROP TABLE IF EXISTS `tai_khoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tai_khoan` (
  `ma_tai_khoan` int NOT NULL AUTO_INCREMENT,
  `anh` varchar(255) DEFAULT NULL,
  `mat_khau` varchar(255) NOT NULL,
  `quyen_han` varchar(255) DEFAULT NULL,
  `ten_dang_nhap` varchar(50) NOT NULL,
  PRIMARY KEY (`ma_tai_khoan`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tai_khoan`
--

LOCK TABLES `tai_khoan` WRITE;
/*!40000 ALTER TABLE `tai_khoan` DISABLE KEYS */;
INSERT INTO `tai_khoan` VALUES (1,NULL,'admin123','admin','admin01'),(2,NULL,'nv123','nhanvien','nv01'),(3,NULL,'nv123','nhanvien','nv02'),(4,NULL,'nv123','nhanvien','nv03'),(5,NULL,'nv123','nhanvien','nv04');
/*!40000 ALTER TABLE `tai_khoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thiet_bi`
--

DROP TABLE IF EXISTS `thiet_bi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thiet_bi` (
  `ma_thiet_bi` int NOT NULL AUTO_INCREMENT,
  `don_gia_mua` decimal(18,2) NOT NULL,
  `ghi_chu` varchar(255) DEFAULT NULL,
  `ngay_mua` date NOT NULL,
  `so_luong` int NOT NULL,
  `ten_thiet_bi` varchar(100) NOT NULL,
  PRIMARY KEY (`ma_thiet_bi`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thiet_bi`
--

LOCK TABLES `thiet_bi` WRITE;
/*!40000 ALTER TABLE `thiet_bi` DISABLE KEYS */;
INSERT INTO `thiet_bi` VALUES (1,5000000.00,'Máy pha cà phê nhập khẩu','2025-06-01',1,'Máy pha cà phê');
/*!40000 ALTER TABLE `thiet_bi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thuc_don`
--

DROP TABLE IF EXISTS `thuc_don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thuc_don` (
  `ma_thuc_don` int NOT NULL AUTO_INCREMENT,
  `gia_tien_hien_tai` decimal(18,2) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `loai_mon` varchar(50) NOT NULL,
  `ten_mon` varchar(100) NOT NULL,
  PRIMARY KEY (`ma_thuc_don`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuc_don`
--

LOCK TABLES `thuc_don` WRITE;
/*!40000 ALTER TABLE `thuc_don` DISABLE KEYS */;
INSERT INTO `thuc_don` VALUES (1,30000.00,_binary '\0','Cà phê','Cà phê sữa'),(2,25000.00,_binary '\0','Cà phê','Cà phê đen'),(3,40000.00,_binary '\0','Trà','Trà đào cam sả'),(4,45000.00,_binary '\0','Đá xay','Matcha đá xay'),(5,35000.00,_binary '\0','Trà','Trà chanh');
/*!40000 ALTER TABLE `thuc_don` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-21 23:33:13
