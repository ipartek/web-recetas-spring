-- --------------------------------------------------------
-- Host:                         127.0.0.1
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
DROP DATABASE IF EXISTS `recetas`;
CREATE DATABASE IF NOT EXISTS `recetas` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `recetas`;

-- Volcando estructura para tabla recetas.imagen
DROP TABLE IF EXISTS `imagen`;
CREATE TABLE IF NOT EXISTS `imagen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `receta_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `imagen_UNIQUE` (`nombre`),
  KEY `fk_receta_imagen` (`receta_id`),
  CONSTRAINT `fk_receta_imagen` FOREIGN KEY (`receta_id`) REFERENCES `receta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla recetas.imagen: ~4 rows (aproximadamente)
DELETE FROM `imagen`;
/*!40000 ALTER TABLE `imagen` DISABLE KEYS */;
INSERT INTO `imagen` (`id`, `nombre`, `receta_id`) VALUES
	(1, 'mojito.jpg', 23),
	(2, 'mojito_fresa.jpg', 23),
	(3, 'mojito_fresa.jpg', 2),
	(4, 'mojito.jpg', 2);
/*!40000 ALTER TABLE `imagen` ENABLE KEYS */;

-- Volcando estructura para tabla recetas.ingrediente
DROP TABLE IF EXISTS `ingrediente`;
CREATE TABLE IF NOT EXISTS `ingrediente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `gluten` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idtable1_UNIQUE` (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla recetas.ingrediente: ~32 rows (aproximadamente)
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
	(18, 'apio', 0),
	(19, 'vino tinto', 0),
	(20, 'sésamo blanco y negro', 1),
	(21, 'harina', 1),
	(22, 'pimienta', 0),
	(23, 'anchoas abiertas en libro', 0),
	(24, 'pimientos verdes', 0),
	(25, 'vinagre de sidra', 0),
	(28, 'patatas', 1),
	(29, 'verduras', 0),
	(34, 'vino blanco', 1),
	(56, 'bonito', 0),
	(57, 'leche', 0),
	(64, 'Mahonesa', 0),
	(65, 'carne picada', 0),
	(66, 'qwqwqwq', 1),
	(67, 'pan', 1),
	(68, 'dddddsdsf', 1),
	(69, '', 1),
	(70, 'sfdsf', 1),
	(71, 'sssdfas', 1),
	(72, 'dddaa', 1),
	(73, 'saa', 1),
	(74, 'd', 1),
	(75, 'dh', 1);
/*!40000 ALTER TABLE `ingrediente` ENABLE KEYS */;

-- Volcando estructura para tabla recetas.like_receta
DROP TABLE IF EXISTS `like_receta`;
CREATE TABLE IF NOT EXISTS `like_receta` (
  `receta_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  KEY `receta_has_like_receta` (`receta_id`),
  KEY `usuario_has_like_receta` (`usuario_id`),
  CONSTRAINT `receta_has_like_receta` FOREIGN KEY (`receta_id`) REFERENCES `receta` (`id`),
  CONSTRAINT `usuario_has_like_receta` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla recetas.like_receta: ~0 rows (aproximadamente)
DELETE FROM `like_receta`;
/*!40000 ALTER TABLE `like_receta` DISABLE KEYS */;
/*!40000 ALTER TABLE `like_receta` ENABLE KEYS */;

-- Volcando estructura para tabla recetas.receta
DROP TABLE IF EXISTS `receta`;
CREATE TABLE IF NOT EXISTS `receta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `imagen` varchar(255) NOT NULL,
  `descripcion` text NOT NULL,
  `latitud` varchar(50) NOT NULL DEFAULT '43.245550',
  `longitud` varchar(50) NOT NULL DEFAULT '-2.923334',
  `likes` int(11) NOT NULL DEFAULT '0',
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_receta_usuario_idx` (`usuario_id`),
  CONSTRAINT `fk_receta_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla recetas.receta: ~6 rows (aproximadamente)
DELETE FROM `receta`;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` (`id`, `nombre`, `imagen`, `descripcion`, `latitud`, `longitud`, `likes`, `usuario_id`) VALUES
	(2, 'Solomillo en hojaldre', 'http://recetariosecreto.com/wp-content/uploads/2013/12/solomillo-al-hojaldre-4_tn.jpg', 'Limpia el solomillo, corta 4 filetes gruesos y salpimiéntalos. Pon a calentar un chorrito de aceite en una sartén grande. Dóralos, retira y deja que se enfríen. Úntalos con el paté de finas hierbas y resérvalos.<br>Estira una lámina de hojaldre y córtala por la mitad de manera que te queden dos rectángulos. Corta 4 tiras finas de cada mitad y resérvalas. Coloca un solomillo en un extremo de cada trozo de hojaldre y tápalos de manera que queden perfectamente cubiertos. Sella los bordes con un tenedor y úntalos con huevo batido. Coloca las tiras en forma de aspa y úntalas con huevo batido. Decora la superficie con sésamo blanco y negro. Realiza la misma operación con la otra lámina de hojaldre de manera que queden los 4 solomillos envueltos. Colócalos en una bandeja de horno forrada con papel de horno y hornea a 200ºC durante 15 minutos.<br>Para la salsa, pela y pica los ajos, la cebolla, el puerro, la zanahoria y el apio en dados y ponlos a pochar en una cazuela con un chorrito de aceite. Sazona. Cuando la verdura esté dorada, agrega la harina y rehógala un poco. Vierte el vino tinto y dale un hervor. Vierte 300 ml de agua y mezcla bien. Deja reducir la salsa y tritúrala.<br>Sirve el solomillo y salsea. Decora con una ramita de perejil.\r\n\r\n\r\n', '43.3317920738598', '-2.9815433000412668', 9, 1),
	(3, 'Anchoas a la papillote', 'http://www.robinfoodtv.com/documentos/recetas/835.jpg', 'Pochar en aceite cebolleta + pimiento verde + sal. <br>Limpiar bien las anchoas, quitándoles la cabeza, los interiores y las espinas. <br>Limpiar bien las anchoas, quitándoles la cabeza, los interiores y las espinas. <br> Limpiar bien las anchoas, quitándoles la cabeza, los interiores y las espinas. <br>En una sartén hacer un refrito de aceite + ajos, verterlo sobre las anchoas, desglasar con el vinagre y rociar. <br>Tapar la cazuela y cocer durante 5 minutos a fuego suave. <br>Dejarlas reposar 2 minutos, tapadas y fuera del fuego. <br>Espolvorear con perejil. <br>Servir rápidamente.', '43.245550', '-2.923334', 41, 3),
	(4, 'Tortilla Patatas Huevona', 'http://www.recetasderechupete.com/wp-content/uploads/2015/07/TORTILLA-002-525x360.jpg', '- ¿ Sabeis la diferencia entre la torialla de patatas que hacais vosotors y yo ?\r\n\r\n- No, no sabemos\r\n\r\n- pues que yo le hecho mas HUEVOS :-)', '43.407685424213554', '-2.943783161529609', 5, 1),
	(21, 'huevos rellenos', 'https://unareceta.com/wp-content/uploads/2016/10/huevos-rellenos.jpg', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus accumsan elementum lectus, ut feugiat lorem venenatis vel. Sed pulvinar ut nibh eu fermentum. Phasellus placerat eget nunc iaculis porta. Duis dignissim augue tellus, fermentum eleifend dolor ullamcorper sed. Morbi vestibulum efficitur risus, non iaculis libero mattis nec. Nulla pretium purus dolor, nec luctus felis efficitur id. In vel venenatis neque, nec dapibus lorem. Nulla non consectetur lacus, eu fermentum lacus. Duis risus ante, eleifend sed ex in, pulvinar ultrices justo. Sed in libero vel ipsum aliquet maximus. Mauris dapibus iaculis elit, ac cursus arcu condimentum in. Maecenas facilisis in elit vel mollis. Phasellus mollis turpis quis mauris rhoncus, sed bibendum ante tincidunt.\r\n\r\nNulla ut malesuada turpis. Donec et neque pulvinar, porta quam eget, aliquet nibh. Mauris mattis auctor consequat. Pellentesque porttitor felis lacus, eget lacinia turpis consequat at. Proin ultrices urna et urna ultricies auctor. Curabitur scelerisque ultricies ante ac tempor. Cras sed rutrum purus. Ut eu nibh scelerisque, tempor sapien eget, consectetur diam. Fusce sed iaculis nunc. Mauris laoreet sollicitudin massa ac aliquam. Maecenas ut mattis mauris. Cras ac dapibus velit. Quisque vel sapien iaculis, volutpat justo quis, tincidunt tellus. Proin sit amet tempus eros, eu tristique lacus.', '43.245550', '-2.923334', 31, 4),
	(22, 'pastel de carne', 'http://pasteldecarne.com.es/img/pastel-de-carne-y-bacon-456.jpg', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus accumsan elementum lectus, ut feugiat lorem venenatis vel. Sed pulvinar ut nibh eu fermentum. Phasellus placerat eget nunc iaculis porta. Duis dignissim augue tellus, fermentum eleifend dolor ullamcorper sed. Morbi vestibulum efficitur risus, non iaculis libero mattis nec. Nulla pretium purus dolor, nec luctus felis efficitur id. In vel venenatis neque, nec dapibus lorem. Nulla non consectetur lacus, eu fermentum lacus. Duis risus ante, eleifend sed ex in, pulvinar ultrices justo. Sed in libero vel ipsum aliquet maximus. Mauris dapibus iaculis elit, ac cursus arcu condimentum in. Maecenas facilisis in elit vel mollis. Phasellus mollis turpis quis mauris rhoncus, sed bibendum ante tincidunt.\r\n\r\nNulla ut malesuada turpis. Donec et neque pulvinar, porta quam eget, aliquet nibh. Mauris mattis auctor consequat. Pellentesque porttitor felis lacus, eget lacinia turpis consequat at. Proin ultrices urna et urna ultricies auctor. Curabitur scelerisque ultricies ante ac tempor. Cras sed rutrum purus. Ut eu nibh scelerisque, tempor sapien eget, consectetur diam. Fusce sed iaculis nunc. Mauris laoreet sollicitudin massa ac aliquam. Maecenas ut mattis mauris. Cras ac dapibus velit. Quisque vel sapien iaculis, volutpat justo quis, tincidunt tellus. Proin sit amet tempus eros, eu tristique lacus.', '43.245550', '-2.923334', 12, 5),
	(23, 'spaguetis a la boloñesa', 'http://cdn.colombia.com/sdi/2011/08/25/espaguetis-a-la-bolonesa-533283.jpg', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus accumsan elementum lectus, ut feugiat lorem venenatis vel. Sed pulvinar ut nibh eu fermentum. Phasellus placerat eget nunc iaculis porta. Duis dignissim augue tellus, fermentum eleifend dolor ullamcorper sed. Morbi vestibulum efficitur risus, non iaculis libero mattis nec. Nulla pretium purus dolor, nec luctus felis efficitur id. In vel venenatis neque, nec dapibus lorem. Nulla non consectetur lacus, eu fermentum lacus. Duis risus ante, eleifend sed ex in, pulvinar ultrices justo. Sed in libero vel ipsum aliquet maximus. Mauris dapibus iaculis elit, ac cursus arcu condimentum in. Maecenas facilisis in elit vel mollis. Phasellus mollis turpis quis mauris rhoncus, sed bibendum ante tincidunt.\r\n\r\nNulla ut malesuada turpis. Donec et neque pulvinar, porta quam eget, aliquet nibh. Mauris mattis auctor consequat. Pellentesque porttitor felis lacus, eget lacinia turpis consequat at. Proin ultrices urna et urna ultricies auctor. Curabitur scelerisque ultricies ante ac tempor. Cras sed rutrum purus. Ut eu nibh scelerisque, tempor sapien eget, consectetur diam. Fusce sed iaculis nunc. Mauris laoreet sollicitudin massa ac aliquam. Maecenas ut mattis mauris. Cras ac dapibus velit. Quisque vel sapien iaculis, volutpat justo quis, tincidunt tellus. Proin sit amet tempus eros, eu tristique lacus.', '43.245550', '-2.923334', 10, 2),
	(24, 'kanelones', 'http://orsimages.unileversolutions.com/ORS_Images/Knorr_es-ES/Canelones_WEB_OK_33_2.1.132_1214X2164.Jpeg', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sit amet dolor et diam tempor vestibulum non nec ex. Nam id justo quis massa dignissim imperdiet. In hac habitasse platea dictumst. Morbi pretium ex vel maximus aliquam. Vestibulum nec laoreet est. Sed ultrices ex quis mi facilisis, a pellentesque massa consectetur. Duis ipsum nisi, accumsan non pulvinar tempus, vulputate id ex. Aliquam semper augue aliquam fringilla suscipit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras et convallis quam. Vivamus vulputate ac dui sit amet laoreet. Donec dignissim libero sit amet metus pharetra mollis. Nunc at leo sollicitudin, placerat libero nec, tincidunt turpis. Suspendisse a augue eget diam aliquet tristique. Nullam lacus ligula, facilisis in purus ut, porttitor porttitor sem.  Pellentesque pellentesque sagittis odio id vulputate. Nam porttitor tincidunt dolor ullamcorper bibendum. Curabitur velit magna, vulputate sit amet ipsum eget, malesuada semper libero. Nam lobortis accumsan arcu, quis pulvinar eros mollis sed. Duis volutpat dui non lectus varius rutrum. Etiam tincidunt magna ex. Sed et ipsum ligula. Pellentesque vehicula vel mi et tempus. Vivamus blandit, nisl varius ullamcorper eleifend, dui nisi tempor nisl, nec venenatis tellus enim eget urna. Sed faucibus felis a sapien pulvinar gravida. Curabitur auctor quis felis id viverra.  Cras libero leo, facilisis non turpis eu, luctus vestibulum augue. Morbi euismod ut urna non interdum. Integer faucibus magna in odio lobortis, eget pulvinar turpis tristique. Aliquam in risus sit amet nulla facilisis accumsan facilisis sed augue. Curabitur eget ipsum nec mauris fermentum mollis at sed turpis. Nullam magna arcu, sodales a pretium quis, congue vitae elit. Curabitur porttitor velit luctus convallis efficitur. Sed libero eros, bibendum nec mollis nec, fermentum pretium turpis. Sed iaculis dolor ac arcu vestibulum, ac imperdiet lacus gravida.', '43.245550', '-2.923334', 8, 1);
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;

-- Volcando estructura para tabla recetas.receta_ingrediente
DROP TABLE IF EXISTS `receta_ingrediente`;
CREATE TABLE IF NOT EXISTS `receta_ingrediente` (
  `receta_id` int(11) NOT NULL,
  `ingrediente_id` int(11) NOT NULL,
  `cantidad` varchar(45) NOT NULL DEFAULT 'a ojimetro...',
  PRIMARY KEY (`receta_id`,`ingrediente_id`),
  KEY `fk_receta_has_ingrediente_ingrediente1_idx` (`ingrediente_id`),
  KEY `fk_receta_has_ingrediente_receta1_idx` (`receta_id`),
  CONSTRAINT `fk_receta_ingrediente_has_ingrediente` FOREIGN KEY (`ingrediente_id`) REFERENCES `ingrediente` (`id`),
  CONSTRAINT `fk_receta_ingrediente_has_receta` FOREIGN KEY (`receta_id`) REFERENCES `receta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla recetas.receta_ingrediente: ~37 rows (aproximadamente)
DELETE FROM `receta_ingrediente`;
/*!40000 ALTER TABLE `receta_ingrediente` DISABLE KEYS */;
INSERT INTO `receta_ingrediente` (`receta_id`, `ingrediente_id`, `cantidad`) VALUES
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
	(4, 6, '2'),
	(4, 8, 'cucharada'),
	(4, 14, 'seis'),
	(4, 18, '2'),
	(4, 28, 'cinco gordas'),
	(4, 34, '1/2 litro'),
	(4, 56, '12 latas'),
	(4, 57, 'a ojimetro...'),
	(21, 10, 'a ojimetro...'),
	(21, 11, 'a ojimetro...'),
	(21, 14, '6'),
	(21, 64, 'a ojimetro...'),
	(23, 10, 'a ojimetro...'),
	(23, 22, 'una pizca'),
	(23, 75, 'a ojimetro...');
/*!40000 ALTER TABLE `receta_ingrediente` ENABLE KEYS */;

-- Volcando estructura para tabla recetas.usuario
DROP TABLE IF EXISTS `usuario`;
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
	(5, 'Pinche Junior', 'juniro@junior.com', '', 'http://1.bp.blogspot.com/-_cVubrH4oYk/TegcKGGKORI/AAAAAAAABm8/qk0OaJUicNw/s1600/cocinero.jpg');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Volcando estructura para vista recetas.checff_recetas_left_join
DROP VIEW IF EXISTS `checff_recetas_left_join`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `checff_recetas_left_join` (
	`cheff` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`receta` VARCHAR(255) NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.cheff_recetas_inner_join
DROP VIEW IF EXISTS `cheff_recetas_inner_join`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `cheff_recetas_inner_join` (
	`cheff` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`receta` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.cheff_recetas_sin_inner_join
DROP VIEW IF EXISTS `cheff_recetas_sin_inner_join`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `cheff_recetas_sin_inner_join` (
	`cheff` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`receta` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.contar_recetas
DROP VIEW IF EXISTS `contar_recetas`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `contar_recetas` (
	`COUNT(*)` BIGINT(21) NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.ingredientes_disponibles_sopa_cebolla
DROP VIEW IF EXISTS `ingredientes_disponibles_sopa_cebolla`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `ingredientes_disponibles_sopa_cebolla` (
	`id` INT(11) NOT NULL,
	`nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`gluten` TINYINT(4) NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.ingrediente_sopa_cebolla
DROP VIEW IF EXISTS `ingrediente_sopa_cebolla`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `ingrediente_sopa_cebolla` (
	`ingrediente` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`cantidad` VARCHAR(45) NOT NULL COLLATE 'utf8_general_ci',
	`gluten` TINYINT(4) NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.recetas_mas_de_5_ingredientes
DROP VIEW IF EXISTS `recetas_mas_de_5_ingredientes`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `recetas_mas_de_5_ingredientes` (
	`r_nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`numero_ingredientes` BIGINT(21) NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.recetas_sin_gluten
DROP VIEW IF EXISTS `recetas_sin_gluten`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `recetas_sin_gluten` (
	`r_nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`ingredientes_con_gluten` DECIMAL(25,0) NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.rectas_con_gluten
DROP VIEW IF EXISTS `rectas_con_gluten`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `rectas_con_gluten` (
	`r_nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`ingredientes_con_gluten` DECIMAL(25,0) NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.ultima_receta
DROP VIEW IF EXISTS `ultima_receta`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `ultima_receta` (
	`id` INT(11) NOT NULL,
	`nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.ultima_receta_subconsulta
DROP VIEW IF EXISTS `ultima_receta_subconsulta`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `ultima_receta_subconsulta` (
	`id` INT(11) NOT NULL,
	`nombre` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista recetas.checff_recetas_left_join
DROP VIEW IF EXISTS `checff_recetas_left_join`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `checff_recetas_left_join`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `checff_recetas_left_join` AS select `u`.`nombre` AS `cheff`,`r`.`nombre` AS `receta` from (`usuario` `u` left join `receta` `r` on((`u`.`id` = `r`.`usuario_id`)));

-- Volcando estructura para vista recetas.cheff_recetas_inner_join
DROP VIEW IF EXISTS `cheff_recetas_inner_join`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `cheff_recetas_inner_join`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cheff_recetas_inner_join` AS select `u`.`nombre` AS `cheff`,`r`.`nombre` AS `receta` from (`usuario` `u` join `receta` `r` on((`u`.`id` = `r`.`usuario_id`)));

-- Volcando estructura para vista recetas.cheff_recetas_sin_inner_join
DROP VIEW IF EXISTS `cheff_recetas_sin_inner_join`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `cheff_recetas_sin_inner_join`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cheff_recetas_sin_inner_join` AS select `u`.`nombre` AS `cheff`,`r`.`nombre` AS `receta` from (`usuario` `u` join `receta` `r`) where (`u`.`id` = `r`.`usuario_id`);

-- Volcando estructura para vista recetas.contar_recetas
DROP VIEW IF EXISTS `contar_recetas`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `contar_recetas`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `contar_recetas` AS select count(0) AS `COUNT(*)` from `receta`;

-- Volcando estructura para vista recetas.ingredientes_disponibles_sopa_cebolla
DROP VIEW IF EXISTS `ingredientes_disponibles_sopa_cebolla`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `ingredientes_disponibles_sopa_cebolla`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ingredientes_disponibles_sopa_cebolla` AS select `ingrediente`.`id` AS `id`,`ingrediente`.`nombre` AS `nombre`,`ingrediente`.`gluten` AS `gluten` from `ingrediente` where (not(`ingrediente`.`id` in (select `receta_ingrediente`.`ingrediente_id` from `receta_ingrediente` where (`receta_ingrediente`.`receta_id` = 1))));

-- Volcando estructura para vista recetas.ingrediente_sopa_cebolla
DROP VIEW IF EXISTS `ingrediente_sopa_cebolla`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `ingrediente_sopa_cebolla`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ingrediente_sopa_cebolla` AS select `i`.`nombre` AS `ingrediente`,`ri`.`cantidad` AS `cantidad`,`i`.`gluten` AS `gluten` from ((`receta` `r` join `ingrediente` `i`) join `receta_ingrediente` `ri`) where ((`r`.`id` = 1) and (`r`.`id` = `ri`.`receta_id`) and (`ri`.`ingrediente_id` = `i`.`id`));

-- Volcando estructura para vista recetas.recetas_mas_de_5_ingredientes
DROP VIEW IF EXISTS `recetas_mas_de_5_ingredientes`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `recetas_mas_de_5_ingredientes`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `recetas_mas_de_5_ingredientes` AS select `receta`.`nombre` AS `r_nombre`,count(`ingrediente`.`id`) AS `numero_ingredientes` from ((`receta` join `receta_ingrediente`) join `ingrediente`) where ((`receta`.`id` = `receta_ingrediente`.`receta_id`) and (`receta_ingrediente`.`ingrediente_id` = `ingrediente`.`id`)) group by `receta`.`nombre` having (`numero_ingredientes` > 5);

-- Volcando estructura para vista recetas.recetas_sin_gluten
DROP VIEW IF EXISTS `recetas_sin_gluten`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `recetas_sin_gluten`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `recetas_sin_gluten` AS select `receta`.`nombre` AS `r_nombre`,sum(`ingrediente`.`gluten`) AS `ingredientes_con_gluten` from ((`receta` join `receta_ingrediente`) join `ingrediente`) where ((`receta`.`id` = `receta_ingrediente`.`receta_id`) and (`receta_ingrediente`.`ingrediente_id` = `ingrediente`.`id`)) group by `receta`.`nombre` having (`ingredientes_con_gluten` <= 0);

-- Volcando estructura para vista recetas.rectas_con_gluten
DROP VIEW IF EXISTS `rectas_con_gluten`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `rectas_con_gluten`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rectas_con_gluten` AS select `receta`.`nombre` AS `r_nombre`,sum(`ingrediente`.`gluten`) AS `ingredientes_con_gluten` from ((`receta` join `receta_ingrediente`) join `ingrediente`) where ((`receta`.`id` = `receta_ingrediente`.`receta_id`) and (`receta_ingrediente`.`ingrediente_id` = `ingrediente`.`id`)) group by `receta`.`nombre` having (`ingredientes_con_gluten` > 0);

-- Volcando estructura para vista recetas.ultima_receta
DROP VIEW IF EXISTS `ultima_receta`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `ultima_receta`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ultima_receta` AS select `receta`.`id` AS `id`,`receta`.`nombre` AS `nombre` from `receta` order by `receta`.`id` desc limit 1;

-- Volcando estructura para vista recetas.ultima_receta_subconsulta
DROP VIEW IF EXISTS `ultima_receta_subconsulta`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `ultima_receta_subconsulta`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ultima_receta_subconsulta` AS select `receta`.`id` AS `id`,`receta`.`nombre` AS `nombre` from `receta` where (`receta`.`id` = (select max(`receta`.`id`) from `receta`));

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
