-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 20, 2018 at 09:08 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rjhck`
--

-- --------------------------------------------------------

--
-- Table structure for table `bhamashah_users`
--

CREATE TABLE `bhamashah_users` (
  `bhamashahid` varchar(20) NOT NULL,
  `adharid` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `phno` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bhamashah_users`
--

INSERT INTO `bhamashah_users` (`bhamashahid`, `adharid`, `name`, `phno`) VALUES
('bhamaid1', 123456, 'Rohit Patel', '9426210564'),
('bhamaid2', 654321, 'Abhinandan Shah', '8000374228');

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `city` varchar(30) NOT NULL,
  `cityid` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`city`, `cityid`) VALUES
('ABU_ROAD', 1),
('AJMER_FV', 2),
('AJMER_GRAIN', 3);

-- --------------------------------------------------------

--
-- Table structure for table `city_veg_price`
--

CREATE TABLE `city_veg_price` (
  `srno` int(11) NOT NULL,
  `city` int(11) DEFAULT NULL,
  `VegId` int(11) DEFAULT NULL,
  `Avg` int(11) DEFAULT NULL,
  `Min` int(11) DEFAULT NULL,
  `Max` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `city_veg_price`
--

INSERT INTO `city_veg_price` (`srno`, `city`, `VegId`, `Avg`, `Min`, `Max`) VALUES
(1, 1, 102, 4300, 4325, 4275),
(2, 1, 103, 1300, 1325, 1275),
(3, 1, 105, 4400, 4425, 4375),
(4, 1, 106, 5475, 5500, 5450),
(5, 1, 109, 8000, 8025, 7975),
(14, 2, 108, 3000, 3500, 1000),
(15, 2, 110, 2600, 3000, 2500),
(16, 2, 111, 1600, 1800, 1200),
(17, 2, 113, 1700, 2000, 800),
(18, 2, 115, 3800, 4000, 2500),
(22, 3, 101, 7103, 7500, 6594),
(23, 3, 103, 1100, 1100, 1100),
(24, 3, 104, 1450, 1450, 1450),
(25, 3, 105, 6320, 6350, 5030),
(26, 3, 107, 8331, 9001, 7300);

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `srno` int(11) NOT NULL,
  `catagory` varchar(30) NOT NULL,
  `msg` varchar(500) NOT NULL,
  `time` datetime NOT NULL,
  `done` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`srno`, `catagory`, `msg`, `time`, `done`) VALUES
(30, 'all', 'ROHHH1123456789', '2018-03-18 12:07:42', 1),
(31, 'all', 'ROHHH11234567890', '2018-03-18 12:08:33', 1),
(32, 'all', 'ROHHH112345678901', '2018-03-18 12:10:52', 1),
(33, 'all', 'ROHHH1123456789012', '2018-03-18 12:15:54', 1),
(34, 'all', 'ROHHH11234567890123', '2018-03-18 12:19:01', 1),
(35, 'all', 'ROHHH112345678901234', '2018-03-18 12:20:59', 1),
(36, 'all', 'Some new msg', '2018-03-20 10:37:38', 1),
(37, 'all', 'Some new msg.. YAY', '2018-03-20 10:47:54', 1),
(38, 'all', 'Some new msg.. YAY11', '2018-03-20 10:48:39', 1);

-- --------------------------------------------------------

--
-- Table structure for table `otps`
--

CREATE TABLE `otps` (
  `srno` int(11) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `otp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `srno` int(11) NOT NULL,
  `mob` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`srno`, `mob`) VALUES
(1, '9426210564');

-- --------------------------------------------------------

--
-- Table structure for table `users_soilreport`
--

CREATE TABLE `users_soilreport` (
  `srno` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `soilreport` int(11) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `veg`
--

CREATE TABLE `veg` (
  `Veg` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `VegId` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `veg`
--

INSERT INTO `veg` (`Veg`, `VegId`) VALUES
('Ajwain', 101),
('Arndi', 102),
('Bajra', 103),
('Barley', 104),
('Bengal_Grams', 105),
('Black_Grams', 106),
('Cummin_Seed', 107),
('Garlic', 108),
('Green_Grams', 109),
('Green_ginger', 110),
('Groundnut_pods', 111),
('Gur', 112),
('Gwar', 113),
('Jowar', 114),
('Lime', 115),
('Maize', 116),
('Mustard', 117),
('Onion', 118),
('Sugar', 119),
('Timber', 120),
('Tomato', 121),
('Wheat', 122);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bhamashah_users`
--
ALTER TABLE `bhamashah_users`
  ADD PRIMARY KEY (`bhamashahid`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`cityid`);

--
-- Indexes for table `city_veg_price`
--
ALTER TABLE `city_veg_price`
  ADD PRIMARY KEY (`srno`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`srno`);

--
-- Indexes for table `otps`
--
ALTER TABLE `otps`
  ADD PRIMARY KEY (`srno`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`srno`);

--
-- Indexes for table `users_soilreport`
--
ALTER TABLE `users_soilreport`
  ADD PRIMARY KEY (`srno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `city_veg_price`
--
ALTER TABLE `city_veg_price`
  MODIFY `srno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `srno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT for table `otps`
--
ALTER TABLE `otps`
  MODIFY `srno` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `srno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `users_soilreport`
--
ALTER TABLE `users_soilreport`
  MODIFY `srno` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
