-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         5.7.14 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para recetas
CREATE DATABASE IF NOT EXISTS `recetas` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `recetas`;

-- Volcando estructura para tabla recetas.ingrediente
CREATE TABLE IF NOT EXISTS `ingrediente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `gluten` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idtable1_UNIQUE` (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla recetas.ingrediente: ~27 rows (aproximadamente)
DELETE FROM `ingrediente`;
/*!40000 ALTER TABLE `ingrediente` DISABLE KEYS */;
INSERT INTO `ingrediente` (`id`, `nombre`, `gluten`) VALUES
	(1, 'garbanzos', 0),
	(2, 'muslo de pollo', 0),
	(3, 'carcasa de pollo', 0),
	(4, 'cebollas', 0),
	(5, 'cebolletas', 0),
	(6, 'dientes de ajo', 0),
	(7, 'queso gruyer', 0),
	(8, 'aceite de oliva virgen extra', 0),
	(9, 'tomillo', 0),
	(10, 'sal', 0),
	(11, 'perejil', 0),
	(12, 'solomillo de ternera', 0),
	(13, 'hojaldre', 0),
	(14, 'huevo', 0),
	(15, 'paté a las finas hierbas', 0),
	(16, 'zanahoria', 0),
	(17, 'puerro', 0),
	(18, 'apio', 1),
	(19, 'vino tinto', 0),
	(20, 'sésamo blanco y negro', 1),
	(21, 'harina', 1),
	(22, 'pimienta', 0),
	(23, 'anchoas abiertas en libro', 0),
	(24, 'pimientos verdes', 0),
	(25, 'vinagre de sidra', 0),
	(28, 'patatas', 1),
	(29, 'verduras', 0);
/*!40000 ALTER TABLE `ingrediente` ENABLE KEYS */;

-- Volcando estructura para tabla recetas.receta
CREATE TABLE IF NOT EXISTS `receta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `imagen` varchar(255) NOT NULL,
  `descripcion` text NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_receta_usuario_idx` (`usuario_id`),
  CONSTRAINT `fk_receta_has_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla recetas.receta: ~4 rows (aproximadamente)
DELETE FROM `receta`;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` (`id`, `nombre`, `imagen`, `descripcion`, `usuario_id`) VALUES
	(1, 'Sopa de cebolla', 'http://tucocinafacil.net/wp-content/uploads/2011/04/sopa-cebolla.jpg', 'Pon los garbanzos a remojo de víspera.<br>Para hacer el caldo, pon abundante agua a calentar en la olla rápida. Cuando empiece a hervir, agrega los garbanzos escurridos y la rama de tomillo. Pela y trocea las cebollas y los dientes de ajo y añádelos. Incorpora el muslo de pollo, la carcasa y unas ramas de perejil. Pon apunto de sal. Coloca la tapa y cuece todo durante 20 minutos. Cuela el caldo y resérvalo. (Reserva el pollo y los garbanzos para otra ocasión).<br>Pela y pica las cebolletas en juliana fina y ponlas a pochar en una cazuela grande con un chorrito de aceite. Cocínalas hasta que queden bien pochadas. Sazona, vierte el caldo y cocina todo durante 20 minutos más.<br>Reparte la sopa en 4 cuencos aptos para el horno. Ralla encima el queso y gratínalos. Sirve y adorna unas hojas de perejil.', 1),
	(2, 'Solomillo en hojaldre', 'http://recetariosecreto.com/wp-content/uploads/2013/12/solomillo-al-hojaldre-4_tn.jpg', 'Limpia el solomillo, corta 4 filetes gruesos y salpimiéntalos. Pon a calentar un chorrito de aceite en una sartén grande. Dóralos, retira y deja que se enfríen. Úntalos con el paté de finas hierbas y resérvalos.<br>Estira una lámina de hojaldre y córtala por la mitad de manera que te queden dos rectángulos. Corta 4 tiras finas de cada mitad y resérvalas. Coloca un solomillo en un extremo de cada trozo de hojaldre y tápalos de manera que queden perfectamente cubiertos. Sella los bordes con un tenedor y úntalos con huevo batido. Coloca las tiras en forma de aspa y úntalas con huevo batido. Decora la superficie con sésamo blanco y negro. Realiza la misma operación con la otra lámina de hojaldre de manera que queden los 4 solomillos envueltos. Colócalos en una bandeja de horno forrada con papel de horno y hornea a 200ºC durante 15 minutos.<br>Para la salsa, pela y pica los ajos, la cebolla, el puerro, la zanahoria y el apio en dados y ponlos a pochar en una cazuela con un chorrito de aceite. Sazona. Cuando la verdura esté dorada, agrega la harina y rehógala un poco. Vierte el vino tinto y dale un hervor. Vierte 300 ml de agua y mezcla bien. Deja reducir la salsa y tritúrala.<br>Sirve el solomillo y salsea. Decora con una ramita de perejil.\n\n\n', 1),
	(3, 'Anchoas a la papillote', 'http://www.robinfoodtv.com/documentos/recetas/835.jpg', 'Pochar en aceite cebolleta + pimiento verde + sal. <br>Limpiar bien las anchoas, quitándoles la cabeza, los interiores y las espinas. <br>Limpiar bien las anchoas, quitándoles la cabeza, los interiores y las espinas. <br> Limpiar bien las anchoas, quitándoles la cabeza, los interiores y las espinas. <br>En una sartén hacer un refrito de aceite + ajos, verterlo sobre las anchoas, desglasar con el vinagre y rociar. <br>Tapar la cazuela y cocer durante 5 minutos a fuego suave. <br>Dejarlas reposar 2 minutos, tapadas y fuera del fuego. <br>Espolvorear con perejil. <br>Servir rápidamente.', 3),
	(4, 'Tortilla Patatas Huevona', 'http://www.recetasderechupete.com/wp-content/uploads/2015/07/TORTILLA-002-525x360.jpg', '- ¿ Sabeis la diferencia entre la tortilla de patatas que hacéis vosotros y yo ?\r\n\r\n- No, no sabemos\r\n\r\n- pues que yo le hecho mas HUEVOS :-)', 1);
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;

-- Volcando estructura para tabla recetas.receta_ingrediente
CREATE TABLE IF NOT EXISTS `receta_ingrediente` (
  `receta_id` int(11) NOT NULL,
  `ingrediente_id` int(11) NOT NULL,
  `cantidad` varchar(45) NOT NULL DEFAULT 'a ojimetro...',
  PRIMARY KEY (`receta_id`,`ingrediente_id`),
  KEY `fk_receta_has_ingrediente_ingrediente1_idx` (`ingrediente_id`),
  KEY `fk_receta_has_ingrediente_receta1_idx` (`receta_id`),
  CONSTRAINT `fk_receta_has_ingrediente_ingrediente1` FOREIGN KEY (`ingrediente_id`) REFERENCES `ingrediente` (`id`),
  CONSTRAINT `fk_receta_has_ingrediente_receta1` FOREIGN KEY (`receta_id`) REFERENCES `receta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla recetas.receta_ingrediente: ~35 rows (aproximadamente)
DELETE FROM `receta_ingrediente`;
/*!40000 ALTER TABLE `receta_ingrediente` DISABLE KEYS */;
INSERT INTO `receta_ingrediente` (`receta_id`, `ingrediente_id`, `cantidad`) VALUES
	(1, 1, '153 gr'),
	(1, 2, '4'),
	(1, 3, '1'),
	(1, 4, '2'),
	(1, 5, '7'),
	(1, 6, '4'),
	(1, 7, '250 gr'),
	(1, 8, 'a ojimetro...'),
	(1, 9, 'a ojimetro...'),
	(1, 10, 'a ojimetro...'),
	(1, 11, 'a ojimetro...'),
	(2, 4, '1'),
	(2, 6, '3'),
	(2, 8, 'a ojimetro...'),
	(2, 10, 'a ojimetro...'),
	(2, 11, 'a ojimetro...'),
	(2, 12, '600 gr'),
	(2, 13, '2 láminas'),
	(2, 14, '1'),
	(2, 15, '100 gr'),
	(2, 16, '1'),
	(2, 17, '1'),
	(2, 18, '1'),
	(2, 19, '150 ml'),
	(2, 20, '30 gr'),
	(2, 21, '15 gr'),
	(2, 22, 'a ojimetro...'),
	(3, 8, 'dos cucharada'),
	(3, 11, 'dos hojas'),
	(3, 23, '1 kg'),
	(3, 24, '2'),
	(3, 25, '3 soperas'),
	(4, 8, 'cucharada'),
	(4, 14, 'seis'),
	(4, 28, 'cinco gordas');
/*!40000 ALTER TABLE `receta_ingrediente` ENABLE KEYS */;

-- Volcando estructura para tabla recetas.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `imagen` varchar(255) NOT NULL DEFAULT 'https://pbs.twimg.com/profile_images/617295207202209792/P8kGVpP-_reasonably_small.png',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla recetas.usuario: ~5 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `nombre`, `email`, `password`, `imagen`) VALUES
	(1, 'Karlos Arguiñano', 'karlos.arguiñano@email.com', '123456', 'http://www.estrelladigital.es/media/estrelladigital/images/2010/06/24/2014022421075998200.jpg'),
	(2, 'Ferran Adria', 'ferran.adria@email.com', '123456', 'http://www.vivelohoy.com/wp-content/uploads/2011/07/bulli.jpg.jpg'),
	(3, 'Robin Food', 'robin.food@email.com', '123456', 'http://www.oletusfogones.es/wp-content/uploads/2012/11/robinfood.jpg'),
	(4, 'pinche', 'pinche@pinche.com', '123456', 'http://estaticos.sport.es/resources/jpg/4/4/los-jugadores-roja-transforman-1398678225044.jpg'),
	(5, 'Pinche Junior', '', 'null', 'http://1.bp.blogspot.com/-_cVubrH4oYk/TegcKGGKORI/AAAAAAAABm8/qk0OaJUicNw/s1600/cocinero.jpg');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Volcando estructura para vista recetas.checff_recetas_left_join
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `checff_recetas_left_join` (
	`cheff` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`receta` VARCHAR(255) NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.cheff_recetas_inner_join
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `cheff_recetas_inner_join` (
	`cheff` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`receta` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.cheff_recetas_sin_inner_join
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `cheff_recetas_sin_inner_join` (
	`cheff` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`receta` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.contar_recetas
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `contar_recetas` (
	`COUNT(*)` BIGINT(21) NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.ingredientes_disponibles_sopa_cebolla
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `ingredientes_disponibles_sopa_cebolla` (
	`id` INT(11) NOT NULL,
	`nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`gluten` TINYINT(4) NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.ingrediente_sopa_cebolla
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `ingrediente_sopa_cebolla` (
	`ingrediente` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`cantidad` VARCHAR(45) NOT NULL COLLATE 'utf8_general_ci',
	`gluten` TINYINT(4) NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.recetas_mas_de_5_ingredientes
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `recetas_mas_de_5_ingredientes` (
	`r_nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`numero_ingredientes` BIGINT(21) NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.recetas_sin_gluten
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `recetas_sin_gluten` (
	`r_nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`ingredientes_con_gluten` DECIMAL(25,0) NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.rectas_con_gluten
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `rectas_con_gluten` (
	`r_nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`ingredientes_con_gluten` DECIMAL(25,0) NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.ultima_receta
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `ultima_receta` (
	`id` INT(11) NOT NULL,
	`nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.ultima_receta_subconsulta
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `ultima_receta_subconsulta` (
	`id` INT(11) NOT NULL,
	`nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.checff_recetas_left_join
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `checff_recetas_left_join`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `checff_recetas_left_join` AS select `u`.`nombre` AS `cheff`,`r`.`nombre` AS `receta` from (`usuario` `u` left join `receta` `r` on((`u`.`id` = `r`.`usuario_id`)));

-- Volcando estructura para vista recetas.cheff_recetas_inner_join
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `cheff_recetas_inner_join`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cheff_recetas_inner_join` AS select `u`.`nombre` AS `cheff`,`r`.`nombre` AS `receta` from (`usuario` `u` join `receta` `r` on((`u`.`id` = `r`.`usuario_id`)));

-- Volcando estructura para vista recetas.cheff_recetas_sin_inner_join
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `cheff_recetas_sin_inner_join`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cheff_recetas_sin_inner_join` AS select `u`.`nombre` AS `cheff`,`r`.`nombre` AS `receta` from (`usuario` `u` join `receta` `r`) where (`u`.`id` = `r`.`usuario_id`);

-- Volcando estructura para vista recetas.contar_recetas
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `contar_recetas`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `contar_recetas` AS select count(0) AS `COUNT(*)` from `receta`;

-- Volcando estructura para vista recetas.ingredientes_disponibles_sopa_cebolla
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `ingredientes_disponibles_sopa_cebolla`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ingredientes_disponibles_sopa_cebolla` AS select `ingrediente`.`id` AS `id`,`ingrediente`.`nombre` AS `nombre`,`ingrediente`.`gluten` AS `gluten` from `ingrediente` where (not(`ingrediente`.`id` in (select `receta_ingrediente`.`ingrediente_id` from `receta_ingrediente` where (`receta_ingrediente`.`receta_id` = 1))));

-- Volcando estructura para vista recetas.ingrediente_sopa_cebolla
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `ingrediente_sopa_cebolla`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ingrediente_sopa_cebolla` AS select `i`.`nombre` AS `ingrediente`,`ri`.`cantidad` AS `cantidad`,`i`.`gluten` AS `gluten` from ((`receta` `r` join `ingrediente` `i`) join `receta_ingrediente` `ri`) where ((`r`.`id` = 1) and (`r`.`id` = `ri`.`receta_id`) and (`ri`.`ingrediente_id` = `i`.`id`));

-- Volcando estructura para vista recetas.recetas_mas_de_5_ingredientes
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `recetas_mas_de_5_ingredientes`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `recetas_mas_de_5_ingredientes` AS select `receta`.`nombre` AS `r_nombre`,count(`ingrediente`.`id`) AS `numero_ingredientes` from ((`receta` join `receta_ingrediente`) join `ingrediente`) where ((`receta`.`id` = `receta_ingrediente`.`receta_id`) and (`receta_ingrediente`.`ingrediente_id` = `ingrediente`.`id`)) group by `receta`.`nombre` having (`numero_ingredientes` > 5);

-- Volcando estructura para vista recetas.recetas_sin_gluten
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `recetas_sin_gluten`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `recetas_sin_gluten` AS select `receta`.`nombre` AS `r_nombre`,sum(`ingrediente`.`gluten`) AS `ingredientes_con_gluten` from ((`receta` join `receta_ingrediente`) join `ingrediente`) where ((`receta`.`id` = `receta_ingrediente`.`receta_id`) and (`receta_ingrediente`.`ingrediente_id` = `ingrediente`.`id`)) group by `receta`.`nombre` having (`ingredientes_con_gluten` <= 0);

-- Volcando estructura para vista recetas.rectas_con_gluten
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `rectas_con_gluten`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rectas_con_gluten` AS select `receta`.`nombre` AS `r_nombre`,sum(`ingrediente`.`gluten`) AS `ingredientes_con_gluten` from ((`receta` join `receta_ingrediente`) join `ingrediente`) where ((`receta`.`id` = `receta_ingrediente`.`receta_id`) and (`receta_ingrediente`.`ingrediente_id` = `ingrediente`.`id`)) group by `receta`.`nombre` having (`ingredientes_con_gluten` > 0);

-- Volcando estructura para vista recetas.ultima_receta
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `ultima_receta`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ultima_receta` AS select `receta`.`id` AS `id`,`receta`.`nombre` AS `nombre` from `receta` order by `receta`.`id` desc limit 1;

-- Volcando estructura para vista recetas.ultima_receta_subconsulta
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `ultima_receta_subconsulta`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ultima_receta_subconsulta` AS select `receta`.`id` AS `id`,`receta`.`nombre` AS `nombre` from `receta` where (`receta`.`id` = (select max(`receta`.`id`) from `receta`));

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
