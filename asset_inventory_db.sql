-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2026 at 12:35 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `asset_inventory_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `asset`
--

CREATE TABLE `asset` (
  `id` bigint(20) NOT NULL,
  `asset_condition` varchar(255) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `device_name` varchar(255) NOT NULL,
  `device_type` varchar(255) NOT NULL,
  `model` varchar(255) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `serial_number` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `asset`
--

INSERT INTO `asset` (`id`, `asset_condition`, `brand`, `device_name`, `device_type`, `model`, `purchase_date`, `serial_number`, `status`) VALUES
(1, 'GOOD', 'well', 'Lenovo', 'LAPTOP', 'mac', '2026-04-17', '123456mav', 'ASSIGNED'),
(4, 'NEW', 'well', 'Lenovo12', 'LAPTOP', 'mac', '2026-04-01', '133456mav', 'ASSIGNED'),
(5, 'GOOD', 'Notepad', 'HP', 'LAPTOP', '12model', '2026-04-16', '12M', 'AVAILABLE'),
(9, 'GOOD', 'Iphone promax', 'Iphone', 'MOBILE', 'V2', '2026-04-10', 'Iph1234', 'AVAILABLE'),
(17, 'POOR', 'php mac', 'Devdesktop', 'DESKTOP', 'php123', '2026-04-10', 'V193b', 'AVAILABLE'),
(18, 'FAIR', 'Tec12', 'TECNO', 'MOBILE', '13Version', '2026-04-17', '123344Tec12345', 'AVAILABLE'),
(20, 'GOOD', 'MAC1234', 'PHP', 'LAPTOP', 'm1213', '2026-04-17', 'P123', 'AVAILABLE');

-- --------------------------------------------------------

--
-- Table structure for table `assignment`
--

CREATE TABLE `assignment` (
  `id` bigint(20) NOT NULL,
  `issue_date` date NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `asset_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `assignment`
--

INSERT INTO `assignment` (`id`, `issue_date`, `notes`, `return_date`, `status`, `asset_id`, `employee_id`) VALUES
(1, '2026-04-20', 'youhave ', NULL, 'ACTIVE', 1, 1),
(3, '2026-04-20', 'Good ', NULL, 'ACTIVE', 4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `audit_log`
--

CREATE TABLE `audit_log` (
  `id` bigint(20) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `details` varchar(1000) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `assignment_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `audit_log`
--

INSERT INTO `audit_log` (`id`, `action`, `details`, `timestamp`, `assignment_id`) VALUES
(1, 'ASSIGN', 'Device \'Lenovo\' assigned to MURAGIJIMANA Janvier on 2026-04-20', '2026-04-20 18:11:04', 1),
(3, 'ASSIGN', 'Device \'Lenovo12\' assigned to Ibyimanakora ubalde on 2026-04-20', '2026-04-20 20:14:19', 3);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `department`, `email`, `name`, `phone`) VALUES
(1, 'IT', 'muragijimanajanvier0@gmail.com', 'MURAGIJIMANA Janvier', '0789428439'),
(3, 'MECH', 'mutuyimana@gmail.com', 'MUTUYIMANA Ange', '0795175573'),
(4, 'IT', 'ubalide@gmail.com', 'IBYIMANAIKORA Ubalde', '+250795175573'),
(6, 'RET', 'evariste@gmail.com', 'NSABIMANA', '+25089765242'),
(8, 'MECH', 'nkusi@gmail.com', 'Nkusi Elisa', '078935342');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `enabled`, `password`, `role`, `username`) VALUES
(1, b'1', '$2a$10$LOMLqYHvYJlas1FoJS2jDOonbOrAIdTq8h9cDFL6D.C78GcmbDAf2', 'ADMIN', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `asset`
--
ALTER TABLE `asset`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_pl8v78k0lsxi66mo36uu0cdyu` (`serial_number`);

--
-- Indexes for table `assignment`
--
ALTER TABLE `assignment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1nwyrfx52nof06vnvfmacbja5` (`employee_id`),
  ADD KEY `FKp4xufrrwsc72xaffg2yv1hiu1` (`asset_id`);

--
-- Indexes for table `audit_log`
--
ALTER TABLE `audit_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2lnolvb975mwftwl19q6rqic6` (`assignment_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `asset`
--
ALTER TABLE `asset`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `assignment`
--
ALTER TABLE `assignment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `audit_log`
--
ALTER TABLE `audit_log`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignment`
--
ALTER TABLE `assignment`
  ADD CONSTRAINT `FK1nwyrfx52nof06vnvfmacbja5` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `FKp4xufrrwsc72xaffg2yv1hiu1` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`);

--
-- Constraints for table `audit_log`
--
ALTER TABLE `audit_log`
  ADD CONSTRAINT `FK2lnolvb975mwftwl19q6rqic6` FOREIGN KEY (`assignment_id`) REFERENCES `assignment` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
