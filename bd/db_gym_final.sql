-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-11-2017 a las 13:27:17
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_gym_final`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

CREATE TABLE `client` (
  `identificationCard` int(11) NOT NULL,
  `name` varchar(110) NOT NULL,
  `address` varchar(50) NOT NULL,
  `number_cel` varchar(25) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`identificationCard`, `name`, `address`, `number_cel`, `password`) VALUES
(1, '59', '0', '59', '59'),
(2, '3', '0', '2', '2'),
(3, '76', '0', '3', '6'),
(10, '10', '0', '10', '10'),
(12, 'hola', '0', '35', '35'),
(59, '59', '1', '59', '59');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exercises`
--

CREATE TABLE `exercises` (
  `id_exercise` int(11) NOT NULL,
  `name_Exercise` varchar(50) NOT NULL,
  `repetitions` varchar(50) NOT NULL,
  `quantity` varchar(50) NOT NULL,
  `id_Muscle` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `food`
--

CREATE TABLE `food` (
  `id_Food` int(11) NOT NULL,
  `breakfast` varchar(50) NOT NULL,
  `lunch` varchar(50) NOT NULL,
  `dinner` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `food_routine`
--

CREATE TABLE `food_routine` (
  `id_Food_Rout` int(11) NOT NULL,
  `id_Instructor` int(11) NOT NULL,
  `id_Client` int(11) NOT NULL,
  `id_Food` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gym`
--

CREATE TABLE `gym` (
  `id_Gym` int(11) NOT NULL,
  `name` varchar(110) NOT NULL,
  `longitud` float NOT NULL,
  `latitud` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `gym`
--

INSERT INTO `gym` (`id_Gym`, `name`, `longitud`, `latitud`) VALUES
(12, 'Torox Gym', 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `instructor`
--

CREATE TABLE `instructor` (
  `identificationCardIns` int(11) NOT NULL,
  `name_Ins` varchar(50) NOT NULL,
  `address_Ins` varchar(50) NOT NULL,
  `number_Cel_Ins` varchar(50) NOT NULL,
  `id_Gym` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `muscles`
--

CREATE TABLE `muscles` (
  `id_Muscle` int(11) NOT NULL,
  `name_Muscld` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `register`
--

CREATE TABLE `register` (
  `id_Client` int(11) NOT NULL,
  `id_Gym` int(11) NOT NULL,
  `date_Register` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `register`
--

INSERT INTO `register` (`id_Client`, `id_Gym`, `date_Register`) VALUES
(1, 12, '30-12-2015');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `routines`
--

CREATE TABLE `routines` (
  `id_Routines` int(11) NOT NULL,
  `id_Client` int(11) NOT NULL,
  `id_Instruc` int(11) NOT NULL,
  `day` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`identificationCard`);

--
-- Indices de la tabla `exercises`
--
ALTER TABLE `exercises`
  ADD PRIMARY KEY (`id_exercise`);

--
-- Indices de la tabla `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id_Food`);

--
-- Indices de la tabla `food_routine`
--
ALTER TABLE `food_routine`
  ADD PRIMARY KEY (`id_Food_Rout`);

--
-- Indices de la tabla `gym`
--
ALTER TABLE `gym`
  ADD PRIMARY KEY (`id_Gym`);

--
-- Indices de la tabla `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`identificationCardIns`),
  ADD KEY `id_Gym` (`id_Gym`);

--
-- Indices de la tabla `muscles`
--
ALTER TABLE `muscles`
  ADD PRIMARY KEY (`id_Muscle`);

--
-- Indices de la tabla `register`
--
ALTER TABLE `register`
  ADD KEY `id_Client` (`id_Client`),
  ADD KEY `id_Gym` (`id_Gym`);

--
-- Indices de la tabla `routines`
--
ALTER TABLE `routines`
  ADD PRIMARY KEY (`id_Routines`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `client`
--
ALTER TABLE `client`
  MODIFY `identificationCard` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=357;
--
-- AUTO_INCREMENT de la tabla `exercises`
--
ALTER TABLE `exercises`
  MODIFY `id_exercise` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `food`
--
ALTER TABLE `food`
  MODIFY `id_Food` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `food_routine`
--
ALTER TABLE `food_routine`
  MODIFY `id_Food_Rout` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `gym`
--
ALTER TABLE `gym`
  MODIFY `id_Gym` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `instructor`
--
ALTER TABLE `instructor`
  MODIFY `identificationCardIns` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `muscles`
--
ALTER TABLE `muscles`
  MODIFY `id_Muscle` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `routines`
--
ALTER TABLE `routines`
  MODIFY `id_Routines` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `instructor`
--
ALTER TABLE `instructor`
  ADD CONSTRAINT `instructor_ibfk_1` FOREIGN KEY (`id_Gym`) REFERENCES `gym` (`id_Gym`);

--
-- Filtros para la tabla `register`
--
ALTER TABLE `register`
  ADD CONSTRAINT `register_ibfk_1` FOREIGN KEY (`id_Client`) REFERENCES `client` (`identificationCard`),
  ADD CONSTRAINT `register_ibfk_2` FOREIGN KEY (`id_Gym`) REFERENCES `gym` (`id_Gym`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
