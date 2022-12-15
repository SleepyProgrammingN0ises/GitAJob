-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2022 at 12:50 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `escueladb`
--
CREATE DATABASE IF NOT EXISTS `escueladb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `escueladb`;

-- --------------------------------------------------------

--
-- Table structure for table `bancopreguntas`
--

DROP TABLE IF EXISTS `bancopreguntas`;
CREATE TABLE IF NOT EXISTS `bancopreguntas` (
  `IDPregunta` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) NOT NULL,
  `tipo` varchar(2) NOT NULL,
  `enunciado` varchar(255) NOT NULL,
  `respCorrecta` varchar(255) NOT NULL,
  `respPosibles` varchar(255) NOT NULL,
  PRIMARY KEY (`IDPregunta`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bancopreguntas`
--

INSERT INTO `bancopreguntas` (`IDPregunta`, `categoria`, `tipo`, `enunciado`, `respCorrecta`, `respPosibles`) VALUES
(1, '3EVAL', 'S', '¿A las cuantas vueltas se tumba el perro?', 'true', 'v/f'),
(3, 'MAT', 'RC', '¿Que pregunta es esta?', 'una, perfecto, aro', ''),
(4, 'MAT', 'RC', '¿Que pregunta es esta?', 'una, perfecto, aro', '');

-- --------------------------------------------------------

--
-- Table structure for table `calificacionesexam`
--

DROP TABLE IF EXISTS `calificacionesexam`;
CREATE TABLE IF NOT EXISTS `calificacionesexam` (
  `IDU` int(11) NOT NULL,
  `IDExam` int(11) NOT NULL,
  `calificacion` double NOT NULL,
  `fecha` varchar(255) NOT NULL,
  `curso` varchar(255) NOT NULL,
  KEY `FK_user_calif` (`IDU`),
  KEY `FK_exam_calif` (`IDExam`),
  KEY `FK_curso_calif` (`curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `categoria` varchar(255) NOT NULL,
  PRIMARY KEY (`categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categoria`
--

INSERT INTO `categoria` (`categoria`) VALUES
('1EVAL'),
('2EVAL'),
('3EVAL'),
('BACKEND'),
('FRONTEND'),
('LENGUA'),
('MAT'),
('PROG');

-- --------------------------------------------------------

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
CREATE TABLE IF NOT EXISTS `cursos` (
  `curso` varchar(255) NOT NULL,
  PRIMARY KEY (`curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cursos`
--

INSERT INTO `cursos` (`curso`) VALUES
('1ºASIR'),
('1ºBACH'),
('1ºDAW'),
('1ºESO'),
('2ºASIR'),
('2ºBACH'),
('2ºDAW'),
('2ºESO'),
('3ºESO'),
('4ºESO'),
('Ejemplo');

-- --------------------------------------------------------

--
-- Table structure for table `examenes`
--

DROP TABLE IF EXISTS `examenes`;
CREATE TABLE IF NOT EXISTS `examenes` (
  `IDExam` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `categoria` varchar(255) NOT NULL,
  `nPreguntas` int(3) NOT NULL,
  `inicio` varchar(255) NOT NULL,
  `duracion` varchar(255) NOT NULL,
  `curso` varchar(255) NOT NULL,
  PRIMARY KEY (`IDExam`),
  KEY `FK_curso_exam` (`curso`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `examenes`
--

INSERT INTO `examenes` (`IDExam`, `titulo`, `categoria`, `nPreguntas`, `inicio`, `duracion`, `curso`) VALUES
(1, 'PRUEBA1', 'BACKEND', 3, '12:00', '03:50', '2ºASIR'),
(2, 'PRUEBA2', 'FRONTEND', 3, '12:00', '03:50', '1ºASIR');

-- --------------------------------------------------------

--
-- Table structure for table `respuestasusuario`
--

DROP TABLE IF EXISTS `respuestasusuario`;
CREATE TABLE IF NOT EXISTS `respuestasusuario` (
  `IDU` int(11) NOT NULL,
  `IDExam` int(11) NOT NULL,
  `IDPregunta` int(11) NOT NULL,
  `respuesta` varchar(255) NOT NULL,
  KEY `FK_user_respusu` (`IDU`),
  KEY `FK_exam_respusu` (`IDExam`),
  KEY `FK_preg_respusu` (`IDPregunta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `IDU` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefono` int(9) NOT NULL,
  `cpostal` int(5) NOT NULL,
  `localidad` varchar(255) NOT NULL,
  `provincia` varchar(255) NOT NULL,
  `NIF` varchar(10) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `estado` varchar(2) NOT NULL,
  PRIMARY KEY (`IDU`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`IDU`, `nombre`, `apellidos`, `password`, `email`, `telefono`, `cpostal`, `localidad`, `provincia`, `NIF`, `direccion`, `estado`) VALUES
(1, 'Lo', 'Zano', '$2y$10$tOUuwag2dOCBnPWn/yZam.oG0H2ZNGu7dfQ.yNSHpKBujzteFlL5i', 'joselozy99@gmail.com', 665875789, 45789, 'asdf', 'Ávila', '20444785-V', 'HOl', '3'),
(2, 'admin', 'uno', '$2y$10$Ufr1aN0Ix8REQK43aHOgvevSwBQK7kgu0e2/f8elr5YAtS1tVQTX.', 'admin@root', 696785749, 19600, 'local', 'Tarragona', '25242788-D', 'por ahi', '1');

-- --------------------------------------------------------

--
-- Table structure for table `usuarioscursos`
--

DROP TABLE IF EXISTS `usuarioscursos`;
CREATE TABLE IF NOT EXISTS `usuarioscursos` (
  `IDU` int(11) NOT NULL,
  `curso` varchar(255) NOT NULL,
  KEY `FK_user_cursos` (`IDU`),
  KEY `FK_curso_cursos` (`curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `calificacionesexam`
--
ALTER TABLE `calificacionesexam`
  ADD CONSTRAINT `FK_curso_calif` FOREIGN KEY (`curso`) REFERENCES `cursos` (`curso`),
  ADD CONSTRAINT `FK_exam_calif` FOREIGN KEY (`IDExam`) REFERENCES `examenes` (`IDExam`),
  ADD CONSTRAINT `FK_user_calif` FOREIGN KEY (`IDU`) REFERENCES `usuarios` (`IDU`);

--
-- Constraints for table `examenes`
--
ALTER TABLE `examenes`
  ADD CONSTRAINT `FK_curso_exam` FOREIGN KEY (`curso`) REFERENCES `cursos` (`curso`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `respuestasusuario`
--
ALTER TABLE `respuestasusuario`
  ADD CONSTRAINT `FK_exam_respusu` FOREIGN KEY (`IDExam`) REFERENCES `examenes` (`IDExam`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_preg_respusu` FOREIGN KEY (`IDPregunta`) REFERENCES `bancopreguntas` (`IDPregunta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_user_respusu` FOREIGN KEY (`IDU`) REFERENCES `usuarios` (`IDU`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `usuarioscursos`
--
ALTER TABLE `usuarioscursos`
  ADD CONSTRAINT `FK_curso_cursos` FOREIGN KEY (`curso`) REFERENCES `cursos` (`curso`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_user_cursos` FOREIGN KEY (`IDU`) REFERENCES `usuarios` (`IDU`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
