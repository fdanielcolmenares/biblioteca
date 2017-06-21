# MySQL-Front 5.1  (Build 4.13)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: biblioteca
# ------------------------------------------------------
# Server version 5.5.13

#
# Source for table actividad
#

DROP TABLE IF EXISTS `actividad`;
CREATE TABLE `actividad` (
  `id_actividad` int(10) NOT NULL DEFAULT '0',
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`id_actividad`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table actividad
#

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table actividad_usuario
#

DROP TABLE IF EXISTS `actividad_usuario`;
CREATE TABLE `actividad_usuario` (
  `id_actividad_usr` int(10) NOT NULL DEFAULT '0',
  `id_actividad` int(10) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id_actividad_usr`),
  KEY `id_actividad` (`id_actividad`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table actividad_usuario
#

LOCK TABLES `actividad_usuario` WRITE;
/*!40000 ALTER TABLE `actividad_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividad_usuario` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table autores
#

DROP TABLE IF EXISTS `autores`;
CREATE TABLE `autores` (
  `id_autor` int(10) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `nacionalidad` varchar(15) DEFAULT NULL,
  `id_libro` int(10) DEFAULT NULL,
  PRIMARY KEY (`id_autor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table autores
#

LOCK TABLES `autores` WRITE;
/*!40000 ALTER TABLE `autores` DISABLE KEYS */;
INSERT INTO `autores` VALUES (1,'alguien','algo','alguna',56);
/*!40000 ALTER TABLE `autores` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table autores_libro
#

DROP TABLE IF EXISTS `autores_libro`;
CREATE TABLE `autores_libro` (
  `id_libro` int(10) NOT NULL DEFAULT '0',
  `id_autor` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_libro`,`id_autor`),
  KEY `id_autor` (`id_autor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table autores_libro
#

LOCK TABLES `autores_libro` WRITE;
/*!40000 ALTER TABLE `autores_libro` DISABLE KEYS */;
/*!40000 ALTER TABLE `autores_libro` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ediciones
#

DROP TABLE IF EXISTS `ediciones`;
CREATE TABLE `ediciones` (
  `id_edicion` int(10) NOT NULL,
  `editorial` varchar(15) NOT NULL,
  `ano` varchar(15) NOT NULL,
  `lugar` varchar(50) NOT NULL,
  `numero_edicion` varchar(15) NOT NULL,
  `id_libro` int(10) NOT NULL,
  PRIMARY KEY (`id_edicion`),
  KEY `id_libro` (`id_libro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table ediciones
#

LOCK TABLES `ediciones` WRITE;
/*!40000 ALTER TABLE `ediciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `ediciones` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ejemplares
#

DROP TABLE IF EXISTS `ejemplares`;
CREATE TABLE `ejemplares` (
  `id_ejemplar` int(10) NOT NULL,
  `numero` int(10) NOT NULL,
  `id_libro` int(10) NOT NULL,
  `estado` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_ejemplar`),
  KEY `id_libro` (`id_libro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table ejemplares
#

LOCK TABLES `ejemplares` WRITE;
/*!40000 ALTER TABLE `ejemplares` DISABLE KEYS */;
INSERT INTO `ejemplares` VALUES (1,1,1,'existe');
INSERT INTO `ejemplares` VALUES (2,2,1,'existe');
INSERT INTO `ejemplares` VALUES (3,3,1,'prestado');
INSERT INTO `ejemplares` VALUES (4,4,1,'prestado');
INSERT INTO `ejemplares` VALUES (5,1,2,'existe');
INSERT INTO `ejemplares` VALUES (6,1,3,'prestado');
/*!40000 ALTER TABLE `ejemplares` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table libro_prestamo
#

DROP TABLE IF EXISTS `libro_prestamo`;
CREATE TABLE `libro_prestamo` (
  `id_prestamo` int(10) NOT NULL DEFAULT '0',
  `id_ejemplar` int(10) NOT NULL DEFAULT '0',
  `id_usuario` int(11) NOT NULL DEFAULT '0',
  `id_libro` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_prestamo`,`id_ejemplar`),
  KEY `id_ejemplar` (`id_ejemplar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table libro_prestamo
#

LOCK TABLES `libro_prestamo` WRITE;
/*!40000 ALTER TABLE `libro_prestamo` DISABLE KEYS */;
INSERT INTO `libro_prestamo` VALUES (1,1,1,1);
INSERT INTO `libro_prestamo` VALUES (2,2,1,1);
/*!40000 ALTER TABLE `libro_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table libros
#

DROP TABLE IF EXISTS `libros`;
CREATE TABLE `libros` (
  `id_libro` int(10) NOT NULL,
  `cota` varchar(15) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `estante` int(3) DEFAULT NULL,
  `id_sala` int(10) DEFAULT NULL,
  PRIMARY KEY (`id_libro`),
  KEY `id_sala` (`id_sala`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table libros
#

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,'000','iliada',1,0);
INSERT INTO `libros` VALUES (2,'001','el cerebro de broca',1,0);
INSERT INTO `libros` VALUES (3,'002','ich',1,0);
INSERT INTO `libros` VALUES (4,'004','cerebro de broca',2,0);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table misiones
#

DROP TABLE IF EXISTS `misiones`;
CREATE TABLE `misiones` (
  `id_mision` int(10) NOT NULL DEFAULT '0',
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`id_mision`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table misiones
#

LOCK TABLES `misiones` WRITE;
/*!40000 ALTER TABLE `misiones` DISABLE KEYS */;
/*!40000 ALTER TABLE `misiones` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table prestamos
#

DROP TABLE IF EXISTS `prestamos`;
CREATE TABLE `prestamos` (
  `id_prestamo` int(10) NOT NULL AUTO_INCREMENT,
  `fecha_prestamo` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `observacion` varchar(50) DEFAULT NULL,
  `fecha_entrega` date NOT NULL DEFAULT '0000-00-00',
  `id_ejemplar` int(11) NOT NULL DEFAULT '0',
  `id_libro` int(11) NOT NULL DEFAULT '0',
  `id_usuario` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_prestamo`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

#
# Dumping data for table prestamos
#

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (19,'2011-09-01','2011-09-02',NULL,'2011-09-27',1,1,1);
INSERT INTO `prestamos` VALUES (20,'2011-09-22','2011-09-22',NULL,'2011-09-27',1,1,1);
INSERT INTO `prestamos` VALUES (21,'2011-09-01','2011-09-01',NULL,'0000-00-00',2,1,1);
INSERT INTO `prestamos` VALUES (22,'2011-09-02','2011-09-09',NULL,'0000-00-00',3,1,6);
INSERT INTO `prestamos` VALUES (23,'2011-09-02','2011-09-03',NULL,'2011-12-01',4,1,1);
INSERT INTO `prestamos` VALUES (24,'2011-09-01','2011-09-01',NULL,'2011-09-27',1,1,1);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table referencias
#

DROP TABLE IF EXISTS `referencias`;
CREATE TABLE `referencias` (
  `id_referencia` int(10) NOT NULL,
  `cedula` varchar(15) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `id_usuario` int(10) NOT NULL,
  PRIMARY KEY (`id_referencia`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table referencias
#

LOCK TABLES `referencias` WRITE;
/*!40000 ALTER TABLE `referencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `referencias` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table registros
#

DROP TABLE IF EXISTS `registros`;
CREATE TABLE `registros` (
  `id_registro` int(10) NOT NULL DEFAULT '0',
  `sexo` varchar(1) DEFAULT NULL,
  `edad` int(3) DEFAULT NULL,
  `estudia` varchar(1) DEFAULT NULL,
  `trabaja` varchar(1) DEFAULT NULL,
  `id_mision` int(10) NOT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id_registro`),
  KEY `id_mision` (`id_mision`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table registros
#

LOCK TABLES `registros` WRITE;
/*!40000 ALTER TABLE `registros` DISABLE KEYS */;
/*!40000 ALTER TABLE `registros` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table salas
#

DROP TABLE IF EXISTS `salas`;
CREATE TABLE `salas` (
  `id_sala` int(10) NOT NULL,
  `descripcion` varchar(15) NOT NULL,
  `activo` int(11) NOT NULL,
  PRIMARY KEY (`id_sala`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table salas
#

LOCK TABLES `salas` WRITE;
/*!40000 ALTER TABLE `salas` DISABLE KEYS */;
INSERT INTO `salas` VALUES (0,'pasillo',1);
INSERT INTO `salas` VALUES (1,'Principal',1);
INSERT INTO `salas` VALUES (2,'secundaria',0);
/*!40000 ALTER TABLE `salas` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table sancion_prestamo
#

DROP TABLE IF EXISTS `sancion_prestamo`;
CREATE TABLE `sancion_prestamo` (
  `estado` varchar(1) NOT NULL,
  `fecha_final` date NOT NULL,
  `id_sancion` int(10) NOT NULL,
  `id_prestamo` int(10) NOT NULL,
  `id_usuario` int(10) NOT NULL,
  PRIMARY KEY (`id_sancion`,`id_prestamo`,`id_usuario`),
  KEY `id_prestamo` (`id_prestamo`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table sancion_prestamo
#

LOCK TABLES `sancion_prestamo` WRITE;
/*!40000 ALTER TABLE `sancion_prestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `sancion_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table sanciones
#

DROP TABLE IF EXISTS `sanciones`;
CREATE TABLE `sanciones` (
  `id_sancion` int(10) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `dias_sancion` int(3) NOT NULL,
  PRIMARY KEY (`id_sancion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table sanciones
#

LOCK TABLES `sanciones` WRITE;
/*!40000 ALTER TABLE `sanciones` DISABLE KEYS */;
INSERT INTO `sanciones` VALUES (0,'hols',1);
INSERT INTO `sanciones` VALUES (1,'reds',2);
/*!40000 ALTER TABLE `sanciones` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table usuarios
#

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id_usuario` int(10) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `cedula` varchar(15) NOT NULL,
  `edad` int(3) DEFAULT NULL,
  `sexo` varchar(1) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `profesion` varchar(15) DEFAULT NULL,
  `institucion` varchar(15) DEFAULT NULL,
  `telefono1` varchar(15) DEFAULT NULL,
  `telefono2` varchar(15) DEFAULT NULL,
  `usuario` varchar(15) DEFAULT NULL,
  `tipo_usuario` int(11) NOT NULL,
  `clave` varchar(15) DEFAULT NULL,
  `carnet` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table usuarios
#

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Admin','','123',100,'m','0000-00-00','Por ahi','Administrador','','0276-1234567','','admin',1,'123','0');
INSERT INTO `usuarios` VALUES (2,'a','a','a',NULL,'M',NULL,NULL,NULL,NULL,NULL,NULL,'a',0,'a',NULL);
INSERT INTO `usuarios` VALUES (3,'b','b','b',NULL,'F',NULL,NULL,NULL,NULL,NULL,NULL,'b',0,'b',NULL);
INSERT INTO `usuarios` VALUES (4,'v','v','v',NULL,'M',NULL,NULL,NULL,NULL,NULL,NULL,'v',0,'v',NULL);
INSERT INTO `usuarios` VALUES (5,'p','p','p',NULL,'F',NULL,NULL,NULL,NULL,NULL,NULL,'p',1,'p',NULL);
INSERT INTO `usuarios` VALUES (6,'lilian','patino','18968147',23,'F','1988-05-27','S.c','estudiante','UNET','000-00000',NULL,'lilian',0,NULL,NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

#
#  Foreign keys for table actividad_usuario
#

ALTER TABLE `actividad_usuario`
ADD CONSTRAINT `actividad_usuario_ibfk_1` FOREIGN KEY (`id_actividad`) REFERENCES `actividad` (`id_actividad`);

#
#  Foreign keys for table autores_libro
#

ALTER TABLE `autores_libro`
ADD CONSTRAINT `autores_libro_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id_libro`),
ADD CONSTRAINT `autores_libro_ibfk_2` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id_autor`);

#
#  Foreign keys for table ediciones
#

ALTER TABLE `ediciones`
ADD CONSTRAINT `ediciones_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id_libro`);

#
#  Foreign keys for table ejemplares
#

ALTER TABLE `ejemplares`
ADD CONSTRAINT `ejemplares_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id_libro`);

#
#  Foreign keys for table libro_prestamo
#

ALTER TABLE `libro_prestamo`
ADD CONSTRAINT `libro_prestamo_ibfk_2` FOREIGN KEY (`id_ejemplar`) REFERENCES `ejemplares` (`id_ejemplar`);

#
#  Foreign keys for table libros
#

ALTER TABLE `libros`
ADD CONSTRAINT `libros_ibfk_1` FOREIGN KEY (`id_sala`) REFERENCES `salas` (`id_sala`);

#
#  Foreign keys for table referencias
#

ALTER TABLE `referencias`
ADD CONSTRAINT `referencias_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

#
#  Foreign keys for table registros
#

ALTER TABLE `registros`
ADD CONSTRAINT `registros_ibfk_1` FOREIGN KEY (`id_mision`) REFERENCES `misiones` (`id_mision`);

#
#  Foreign keys for table sancion_prestamo
#

ALTER TABLE `sancion_prestamo`
ADD CONSTRAINT `sancion_prestamo_ibfk_1` FOREIGN KEY (`id_sancion`) REFERENCES `sanciones` (`id_sancion`),
ADD CONSTRAINT `sancion_prestamo_ibfk_2` FOREIGN KEY (`id_prestamo`) REFERENCES `prestamos` (`id_prestamo`),
ADD CONSTRAINT `sancion_prestamo_ibfk_3` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
