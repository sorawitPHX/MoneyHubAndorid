-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 29, 2024 at 12:22 AM
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
-- Database: `moneyhubandorid`
--

-- --------------------------------------------------------

--
-- Table structure for table `account_books`
--

CREATE TABLE `account_books` (
  `idaccount_book` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `account_book` varchar(255) NOT NULL,
  `balance` decimal(10,2) NOT NULL,
  `account_photo_path` varchar(2048) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `careers`
--

CREATE TABLE `careers` (
  `idcareer` int(11) NOT NULL,
  `career` varchar(150) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `careers`
--

INSERT INTO `careers` (`idcareer`, `career`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'นักเรียน/นักศึกษา', '2024-02-27 20:12:27', '2024-02-28 23:01:24', '2024-02-28 23:01:24'),
(2, 'ธุรกิจส่วนตัว', '2024-02-27 20:12:27', '2024-02-28 23:01:36', '2024-02-28 23:01:36'),
(3, 'ข้าราชการ/พนักงานราชการ', '2024-02-28 23:02:09', '2024-02-28 23:02:09', NULL),
(4, 'รับจ้าง', '2024-02-28 23:02:09', '2024-02-28 23:02:09', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `idcategory` int(11) NOT NULL,
  `idtransaction_types` int(11) DEFAULT NULL,
  `category` varchar(255) NOT NULL,
  `cate_photo_path` varchar(2048) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `genders`
--

CREATE TABLE `genders` (
  `idgender` int(11) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `genders`
--

INSERT INTO `genders` (`idgender`, `gender`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'ชาย', '2024-02-27 20:10:32', '2024-02-28 23:21:51', '2024-02-28 23:21:51'),
(2, 'หญิง', '2024-02-27 20:10:32', '2024-02-28 23:21:55', '2024-02-28 23:21:55'),
(3, 'อื่นๆ', '2024-02-28 22:46:29', '2024-02-28 23:22:00', '2024-02-28 23:22:00');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `idtransaction` int(11) NOT NULL,
  `idaccount_book` int(11) NOT NULL,
  `idcategory` int(11) DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transactions_types`
--

CREATE TABLE `transactions_types` (
  `idtransaction_types` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `iduser` int(11) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profile_photo_path` varchar(2048) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  `idcareer` int(11) NOT NULL,
  `idgender` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`iduser`, `firstname`, `lastname`, `birthday`, `email`, `password`, `profile_photo_path`, `created_at`, `updated_at`, `deleted_at`, `idcareer`, `idgender`) VALUES
(1, 'Somchai', 'Boonrueng', '2003-11-27', 'somchai77@gmail.com', '$2a$10$qmibVJ//f/ovn91HyqMw/uQCg5nt7NOU1poTnK1H0nuzv8k82XuHi', NULL, '2024-02-27 20:13:39', '2024-02-27 20:13:39', NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users_has_categories`
--

CREATE TABLE `users_has_categories` (
  `iduser` int(11) NOT NULL,
  `idcategory` int(11) NOT NULL,
  `custom_name` varchar(255) NOT NULL,
  `custom_photo_path` varchar(2048) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account_books`
--
ALTER TABLE `account_books`
  ADD PRIMARY KEY (`idaccount_book`),
  ADD KEY `iduser` (`iduser`);

--
-- Indexes for table `careers`
--
ALTER TABLE `careers`
  ADD PRIMARY KEY (`idcareer`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`idcategory`),
  ADD KEY `idtransaction_types` (`idtransaction_types`);

--
-- Indexes for table `genders`
--
ALTER TABLE `genders`
  ADD PRIMARY KEY (`idgender`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`idtransaction`),
  ADD KEY `idaccount_book` (`idaccount_book`),
  ADD KEY `idcategory` (`idcategory`);

--
-- Indexes for table `transactions_types`
--
ALTER TABLE `transactions_types`
  ADD PRIMARY KEY (`idtransaction_types`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`iduser`),
  ADD KEY `idcareer` (`idcareer`),
  ADD KEY `idgender` (`idgender`);

--
-- Indexes for table `users_has_categories`
--
ALTER TABLE `users_has_categories`
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idcategory` (`idcategory`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `careers`
--
ALTER TABLE `careers`
  MODIFY `idcareer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `genders`
--
ALTER TABLE `genders`
  MODIFY `idgender` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account_books`
--
ALTER TABLE `account_books`
  ADD CONSTRAINT `iduser` FOREIGN KEY (`iduser`) REFERENCES `users` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `categories`
--
ALTER TABLE `categories`
  ADD CONSTRAINT `idtransaction_types` FOREIGN KEY (`idtransaction_types`) REFERENCES `transactions_types` (`idtransaction_types`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `idaccount_book` FOREIGN KEY (`idaccount_book`) REFERENCES `account_books` (`idaccount_book`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idcategory` FOREIGN KEY (`idcategory`) REFERENCES `categories` (`idcategory`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `idcareer` FOREIGN KEY (`idcareer`) REFERENCES `careers` (`idcareer`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `idgender` FOREIGN KEY (`idgender`) REFERENCES `genders` (`idgender`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `users_has_categories`
--
ALTER TABLE `users_has_categories`
  ADD CONSTRAINT `users_has_categories_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `users` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `users_has_categories_ibfk_2` FOREIGN KEY (`idcategory`) REFERENCES `categories` (`idcategory`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
