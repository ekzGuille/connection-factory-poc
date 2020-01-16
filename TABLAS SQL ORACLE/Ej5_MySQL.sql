-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-11-2018 a las 20:59:31
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ej5`
--
DROP DATABASE IF EXISTS `ej5`;
CREATE DATABASE IF NOT EXISTS `ej5` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `ej5`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conducir`
--

CREATE TABLE `conducir` (
  `d_dni` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `d_mat` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `d_fecha` date NOT NULL,
  `d_hora_inicio` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `d_hora_fin` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `revisar`
--

CREATE TABLE `revisar` (
  `d_cod` int(4) NOT NULL,
  `d_mat` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `d_fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `taller`
--

CREATE TABLE `taller` (
  `d_cod` int(4) NOT NULL,
  `d_nom1` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `d_tfno` int(9) NOT NULL,
  `d_nom` varchar(40) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `taxi`
--

CREATE TABLE `taxi` (
  `d_mat` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `d_tfno` int(6) NOT NULL,
  `d_km` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `taxista`
--

CREATE TABLE `taxista` (
  `d_dni` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `d_nom` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `d_tfno` int(9) NOT NULL,
  `d_dir` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `d_edad` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `conducir`
--
ALTER TABLE `conducir`
  ADD PRIMARY KEY (`d_dni`,`d_fecha`),
  ADD KEY `FK_d_mat` (`d_mat`);

--
-- Indices de la tabla `revisar`
--
ALTER TABLE `revisar`
  ADD PRIMARY KEY (`d_cod`,`d_fecha`);

--
-- Indices de la tabla `taller`
--
ALTER TABLE `taller`
  ADD PRIMARY KEY (`d_cod`);

--
-- Indices de la tabla `taxi`
--
ALTER TABLE `taxi`
  ADD PRIMARY KEY (`d_mat`),
  ADD UNIQUE KEY `d_tfno` (`d_tfno`);

--
-- Indices de la tabla `taxista`
--
ALTER TABLE `taxista`
  ADD PRIMARY KEY (`d_dni`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `conducir`
--
ALTER TABLE `conducir`
  ADD CONSTRAINT `FK_d_dni` FOREIGN KEY (`d_dni`) REFERENCES `taxista` (`d_dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_d_mat` FOREIGN KEY (`d_mat`) REFERENCES `taxi` (`d_mat`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `revisar`
--
ALTER TABLE `revisar`
  ADD CONSTRAINT `FK_d_cod` FOREIGN KEY (`d_cod`) REFERENCES `taller` (`d_cod`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
