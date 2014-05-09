-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 09, 2014 at 03:33 PM
-- Server version: 5.5.36
-- PHP Version: 5.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ienergy_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `ienergy`
--

CREATE TABLE IF NOT EXISTS `ienergy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Year` int(11) NOT NULL DEFAULT '2013',
  `Month` int(11) DEFAULT NULL,
  `Day` int(11) DEFAULT NULL,
  `Week_Day` int(11) DEFAULT NULL,
  `Hour` int(11) DEFAULT NULL,
  `Min` int(11) DEFAULT NULL,
  `Sec` int(11) DEFAULT NULL,
  `Temperature` float NOT NULL,
  `Humidity` int(11) NOT NULL,
  `Proximity` int(11) DEFAULT NULL,
  `Luminosity` int(11) DEFAULT NULL,
  `Current` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=69 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
