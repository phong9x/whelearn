-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.35-community - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for whelearn
CREATE DATABASE IF NOT EXISTS `whelearn` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `whelearn`;


-- Dumping structure for table whelearn.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(300) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `rank` int(11) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.category: ~10 rows (approximately)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT IGNORE INTO `category` (`id`, `category_name`, `count`, `rank`, `create_date`, `update_date`) VALUES
	(1, '언어 ', NULL, 0, '2016-08-22 11:01:28', '2016-08-22 11:01:29'),
	(2, '지식/비즈 ', NULL, 0, '2016-08-22 11:01:28', '2016-08-22 11:01:29'),
	(3, '운동 ', NULL, 0, '2016-08-22 11:01:28', '2016-08-22 11:01:29'),
	(4, '예술 ', NULL, 0, '2016-08-22 11:01:28', '2016-08-22 11:01:29'),
	(5, '라이프 ', NULL, 0, '2016-08-22 11:01:28', '2016-08-22 11:01:29'),
	(6, '건강 ', NULL, 0, '2016-08-22 11:01:28', '2016-08-22 11:01:29'),
	(7, '뷰티 ', NULL, 0, '2016-08-22 11:01:28', '2016-08-22 11:01:29'),
	(8, '문화 ', NULL, 0, '2016-08-22 11:01:28', '2016-08-22 11:01:29'),
	(9, '소셜 ', NULL, 0, '2016-08-22 11:01:28', '2016-08-22 11:01:29'),
	(10, 'Event', NULL, 0, '2016-08-22 11:01:28', '2016-08-22 11:01:29');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


-- Dumping structure for table whelearn.comment
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `program_id` int(11) DEFAULT NULL,
  `content` text,
  `point` int(2) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `content_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.comment: ~0 rows (approximately)
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;


-- Dumping structure for table whelearn.faq
CREATE TABLE IF NOT EXISTS `faq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL DEFAULT '0',
  `question` varchar(200) NOT NULL DEFAULT '0',
  `answer` varchar(500) NOT NULL DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.faq: ~0 rows (approximately)
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;


-- Dumping structure for table whelearn.keyword
CREATE TABLE IF NOT EXISTS `keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(300) DEFAULT NULL,
  `type_name` varchar(300) DEFAULT NULL,
  `type_id` int(1) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `rank` int(11) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.keyword: ~23 rows (approximately)
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
INSERT IGNORE INTO `keyword` (`id`, `keyword`, `type_name`, `type_id`, `description`, `count`, `rank`, `create_date`, `update_date`) VALUES
	(1, '남', 'gender', 1, '', 0, 0, '2016-08-22 16:26:21', '2016-08-22 16:26:22'),
	(2, '녀', 'gender', 1, NULL, 0, 0, '2016-08-22 16:26:24', '2016-08-22 16:26:23'),
	(3, '노', 'age', 2, NULL, 0, 0, '2016-08-22 16:26:25', '2016-08-22 16:26:31'),
	(4, '소', 'age', 2, NULL, 0, 0, '2016-08-22 16:26:26', '2016-08-22 16:26:30'),
	(5, '활동적', 'genitive', 3, NULL, 0, 0, '2016-08-22 16:26:26', '2016-08-22 16:26:29'),
	(6, '정적', 'genitive', 3, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(7, '베테랑', 'experience', 4, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(8, '신입', 'experience', 4, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(9, '실내', 'address', 5, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(10, '실외', 'address', 5, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(11, '강남', 'area', 6, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(12, '강북', 'area', 6, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(13, '주중', 'time', 7, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(14, '주말', 'time', 7, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(15, 'Day time(09-18)', 'time2', 8, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(16, 'Night time(18-)', 'time2', 8, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(17, '35,000 초과', 'fee', 9, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(18, '35,000 이하', 'fee', 9, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(19, '참여형', 'study mode', 10, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(20, '강연형', 'study mode', 10, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(21, '소규모(5인이하)', 'Class sizes', 11, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(22, '중규모(10인이하)', 'Class sizes', 11, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28'),
	(23, '대규모(10인초과)', 'Class sizes', 11, NULL, 0, 0, '2016-08-22 16:26:27', '2016-08-22 16:26:28');
/*!40000 ALTER TABLE `keyword` ENABLE KEYS */;


-- Dumping structure for table whelearn.notice
CREATE TABLE IF NOT EXISTS `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL DEFAULT '0',
  `content` varchar(500) NOT NULL DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.notice: ~0 rows (approximately)
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;


-- Dumping structure for table whelearn.program
CREATE TABLE IF NOT EXISTS `program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `category_name` varchar(300) NOT NULL,
  `title` varchar(300) NOT NULL,
  `summary` varchar(300) NOT NULL,
  `content` text NOT NULL,
  `introduce_teacher` text NOT NULL,
  `introduce_program` text NOT NULL,
  `introduce_study_program` text NOT NULL,
  `fee` int(11) NOT NULL,
  `time_learn_id` int(11) NOT NULL,
  `address` varchar(500) NOT NULL,
  `address_guide` varchar(500) NOT NULL,
  `total_people` int(11) NOT NULL,
  `hearing_faculty` int(1) NOT NULL DEFAULT '0',
  `total_time` varchar(50) NOT NULL,
  `dead_line` datetime NOT NULL,
  `image` varchar(500) NOT NULL,
  `include_fee` int(1) NOT NULL,
  `include_material_fee` int(1) NOT NULL,
  `include_learning_book` int(1) NOT NULL,
  `include_wifi` int(1) NOT NULL,
  `include_parking` int(1) NOT NULL,
  `include_drinking` int(1) NOT NULL,
  `latitude` int(1) NOT NULL,
  `longitude` int(1) NOT NULL,
  `point` int(2) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.program: ~0 rows (approximately)
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
/*!40000 ALTER TABLE `program` ENABLE KEYS */;


-- Dumping structure for table whelearn.program_includes
CREATE TABLE IF NOT EXISTS `program_includes` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.program_includes: ~0 rows (approximately)
/*!40000 ALTER TABLE `program_includes` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_includes` ENABLE KEYS */;


-- Dumping structure for table whelearn.program_view
CREATE TABLE IF NOT EXISTS `program_view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `program_id` int(11) NOT NULL,
  `time` date NOT NULL,
  `phone` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.program_view: ~0 rows (approximately)
/*!40000 ALTER TABLE `program_view` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_view` ENABLE KEYS */;


-- Dumping structure for table whelearn.purchase
CREATE TABLE IF NOT EXISTS `purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `program_id` int(11) NOT NULL DEFAULT '0',
  `fullname` varchar(200) NOT NULL DEFAULT '0',
  `phone` varchar(15) NOT NULL DEFAULT '0',
  `certification` varchar(200) NOT NULL DEFAULT '0',
  `money` int(11) NOT NULL DEFAULT '0',
  `payment_method_name` varchar(500) DEFAULT '0',
  `monkey` int(11) DEFAULT '0',
  `status` int(1) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_delete` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_purchase_user` (`user_id`),
  CONSTRAINT `FK_purchase_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=518 DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.purchase: ~0 rows (approximately)
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;


-- Dumping structure for table whelearn.push
CREATE TABLE IF NOT EXISTS `push` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `upload_content_notification` int(1) NOT NULL DEFAULT '0',
  `open_course_notification` int(1) NOT NULL DEFAULT '0',
  `comment_notification` int(1) NOT NULL DEFAULT '0',
  `coupon_notification` int(1) NOT NULL DEFAULT '0',
  `notice_notification` int(1) NOT NULL DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.push: ~0 rows (approximately)
/*!40000 ALTER TABLE `push` DISABLE KEYS */;
/*!40000 ALTER TABLE `push` ENABLE KEYS */;


-- Dumping structure for table whelearn.qa
CREATE TABLE IF NOT EXISTS `qa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ask_user_id` int(11) NOT NULL,
  `answer_user_id` int(11) NOT NULL,
  `questioin` varchar(500) NOT NULL,
  `answer` varchar(500) NOT NULL,
  `ask_date` datetime DEFAULT NULL,
  `answer_dare` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.qa: ~0 rows (approximately)
/*!40000 ALTER TABLE `qa` DISABLE KEYS */;
/*!40000 ALTER TABLE `qa` ENABLE KEYS */;


-- Dumping structure for table whelearn.time_learn
CREATE TABLE IF NOT EXISTS `time_learn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `program_id` int(11) NOT NULL DEFAULT '0',
  `time_learn` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.time_learn: ~0 rows (approximately)
/*!40000 ALTER TABLE `time_learn` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_learn` ENABLE KEYS */;


-- Dumping structure for table whelearn.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(100) NOT NULL,
  `nickname` varchar(200) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `gender` tinyint(2) DEFAULT NULL,
  `day_of_birth` date DEFAULT NULL,
  `phone` varchar(200) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `role` int(1) DEFAULT '0',
  `active` int(1) DEFAULT '1',
  `type` int(1) DEFAULT '0',
  `token` varchar(300) DEFAULT NULL,
  `gcm_token` varchar(500) DEFAULT NULL,
  `push_gcm_token` int(1) DEFAULT NULL,
  `is_delete` int(1) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `USERNAME` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8;

-- Dumping data for table whelearn.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT IGNORE INTO `user` (`id`, `username`, `password`, `nickname`, `fullname`, `gender`, `day_of_birth`, `phone`, `email`, `address`, `role`, `active`, `type`, `token`, `gcm_token`, `push_gcm_token`, `is_delete`, `create_date`, `update_date`) VALUES
	(1, 'kenny', '698d51a19d8a121ce581499d7b701668', '언어 ', 'KennyP', 1, '1945-02-03', '097425295', 'kennyphong2811@gmail.com', 'ii', 0, 1, 2, '0', 'cEm9IO-Dj14:APA91bGYx6h2gTKDlqNzkvls0X96mbCTbl05ymJsjRDKcnZC6qDSiE0g4LUCUebAqPwYG2nYh5I-NnOe9HZpofwElr-afCrexvhqA7Z0YCJoIjhKygblAadGI06spjlrwWz7VF8r5Ko6', 1, 0, '2016-01-08 18:30:18', '2016-07-27 15:44:16'),
	(2, 'admin', '698d51a19d8a121ce581499d7b701668', '인생은긴소풍', '전찬무', 0, '1986-10-26', '1234567', 'sinner187@nate.com', '0', 1, 0, 2, '0', NULL, 0, 0, '2016-04-26 10:07:02', '2016-04-26 10:07:02');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
