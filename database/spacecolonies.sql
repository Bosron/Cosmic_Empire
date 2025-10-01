-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2024 at 04:59 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spacecolonies`
--

-- --------------------------------------------------------

--
-- Table structure for table `celestialbody`
--

CREATE TABLE `celestialbody` (
  `ID` int(10) NOT NULL,
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `celestialbody`
--

INSERT INTO `celestialbody` (`ID`, `Description`) VALUES
(1, 'Moon'),
(2, 'Mars'),
(3, 'Jupiter'),
(4, 'Titan'),
(5, 'Saturn'),
(6, 'Open space');

-- --------------------------------------------------------

--
-- Table structure for table `colony`
--

CREATE TABLE `colony` (
  `id` int(11) NOT NULL,
  `Colony_Name` varchar(255) DEFAULT NULL,
  `Population` int(10) DEFAULT NULL,
  `Government_ID` int(10) DEFAULT NULL,
  `Purpose_ID` int(10) DEFAULT NULL,
  `Type_ID` int(10) DEFAULT NULL,
  `CB_ID` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `colony`
--

INSERT INTO `colony` (`id`, `Colony_Name`, `Population`, `Government_ID`, `Purpose_ID`, `Type_ID`, `CB_ID`) VALUES
(1, 'Aldebaran', 16, 5, 3, 6, 6),
(2, 'Limke', 500, 1, 1, 5, 3),
(3, 'Karekan', 3000, 2, 2, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `government`
--

CREATE TABLE `government` (
  `ID` int(10) NOT NULL,
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `government`
--

INSERT INTO `government` (`ID`, `Description`) VALUES
(1, 'Diarchy'),
(2, 'Democracy'),
(3, 'Anarchy'),
(4, 'Dependant'),
(5, 'Non-governing');

-- --------------------------------------------------------

--
-- Table structure for table `purpose`
--

CREATE TABLE `purpose` (
  `ID` int(10) NOT NULL,
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purpose`
--

INSERT INTO `purpose` (`ID`, `Description`) VALUES
(1, 'Resource'),
(2, 'Exploration'),
(3, 'Experimental'),
(4, 'Comms'),
(5, 'Diplomatic'),
(6, 'Military');

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE `type` (
  `ID` int(10) NOT NULL,
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `type`
--

INSERT INTO `type` (`ID`, `Description`) VALUES
(1, 'Orbiting'),
(2, 'Underground'),
(3, 'Onground'),
(4, 'Underwater'),
(5, 'Floating'),
(6, 'Freemoving');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `celestialbody`
--
ALTER TABLE `celestialbody`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `colony`
--
ALTER TABLE `colony`
  ADD PRIMARY KEY (`id`),
  ADD KEY `GovernmentColony` (`Government_ID`),
  ADD KEY `PurposeColony` (`Purpose_ID`),
  ADD KEY `TypeColony` (`Type_ID`),
  ADD KEY `CelestialBodyColony` (`CB_ID`);

--
-- Indexes for table `government`
--
ALTER TABLE `government`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `purpose`
--
ALTER TABLE `purpose`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `colony`
--
ALTER TABLE `colony`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `colony`
--
ALTER TABLE `colony`
  ADD CONSTRAINT `CelestialBodyColony` FOREIGN KEY (`CB_ID`) REFERENCES `celestialbody` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `GovernmentColony` FOREIGN KEY (`Government_ID`) REFERENCES `government` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PurposeColony` FOREIGN KEY (`Purpose_ID`) REFERENCES `purpose` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `TypeColony` FOREIGN KEY (`Type_ID`) REFERENCES `type` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
