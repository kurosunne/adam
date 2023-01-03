/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.21-MariaDB : Database - adam_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`adam_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `adam_db`;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) NOT NULL,
  `access` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`password`,`full_name`,`status`,`access`) values 
(1,'alayna.kuhn','123','2022-12-04 13:32:47',0,0),
(2,'derick44','123','2022-12-04 13:32:47',0,0),
(3,'erobel','123','2022-12-04 13:32:47',0,0),
(4,'eliza.jaskolski','123','2022-12-04 13:32:47',0,0),
(5,'osinski.florida','123','2022-12-04 13:32:47',0,0),
(6,'henry.langworth','123','2022-12-04 13:32:47',0,0),
(7,'igutmann','123','2022-12-04 13:32:47',0,0),
(8,'erin.bergstrom','123','2022-12-04 13:32:47',0,0),
(9,'sporer.lesly','123','2022-12-04 13:32:47',0,0),
(10,'pamela.murazik','123','2022-12-04 13:32:47',0,0),
(11,'friesen.loy','123','2022-12-04 13:32:47',0,0),
(12,'gerald99','123','2022-12-04 13:32:47',0,0),
(13,'heathcote.alexa','123','2022-12-04 13:32:47',0,0),
(14,'fcrona','123','2022-12-04 13:32:47',0,0),
(15,'hortense10','123','2022-12-04 13:32:47',0,0),
(16,'tanner90','123','2022-12-04 13:32:47',0,0),
(17,'dlabadie','123','2022-12-04 13:32:47',0,0),
(18,'tlakin','123','2022-12-04 13:32:47',0,0),
(19,'rogelio00','123','2022-12-04 13:32:47',0,0),
(20,'anjali52','123','2022-12-04 13:32:47',0,0),
(21,'olabadie','123','2022-12-04 13:32:47',0,0),
(22,'dietrich.randi','123','2022-12-04 13:32:47',0,0),
(23,'johnston.eloy','123','2022-12-04 13:32:47',0,0),
(24,'lang.avery','123','2022-12-04 13:32:47',0,0),
(25,'wolff.pietro','123','2022-12-04 13:32:47',0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
