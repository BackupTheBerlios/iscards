-- --------------------------------------------------------
--                     crearBD.sql
-- --------------------------------------------------------
-- Crea la base de datos de genesis: 
--	 - Tabla de usuarios
--	 - Tabla de cartas
--	 - Tabla de noticias
--
-- Creación: 22/01/2005
--
-- Modificación: 10/02/2005
--   Añadidas tablas del foro
--
-- Modificación: 16/04/2005
--   Añadido campo ID a las noticias
--
-- Modificación: 23/04/2005
--   Ampliada la longitud de algunos campos
-- --------------------------------------------------------
-- ADVERTENCIA!!: La ejecución de este script borrará todo
-- el contenido de la base de datos.
-- --------------------------------------------------------

-- Borrar base de datos anterior si existía
drop database if exists genesis;

create database genesis;
use genesis;


-------------------------------------------- TABLA DE CARTAS
create table cartas (
   id_carta    varchar(5)     not null,	 
   nombre      varchar(50)    not null,	  
   raza	       varchar(10)    not null,
   tipo	       varchar(10)    not null,
   ataque      tinyint	      not null,
   defensa     tinyint	      not null,
   coste       tinyint	      not null,
   puntos      int            not null,
   nivel       tinyint	      not null,
   vida	       tinyint	      not null,
   habilidades text	      not null,
   desc_movil  text	      not null,
   primary key (id_carta),
   index (nombre)
);

insert into cartas (id_carta, nombre, raza, tipo, ataque, 
   defensa, coste, puntos, nivel, vida, habilidades, desc_movil)
   values ("H2", "Ardiz el Aprendiz", "Humanos", "Criatura", 14, 12, 8,
	  1260, 1, 3, "", "Descripción de Ardiz");


-------------------------------------------- TABLA DE USUARIOS 

create table usuarios(
      nick	  varchar(50),
      password	  varchar(50),
      nombre	  varchar(100), 
      email	  varchar(100),
      sexo	  enum('H','M'), 
      puntos	  float		 DEFAULT 0,
      npaquetes	  int		 DEFAULT 0,
      avatar	  varchar(5),
      primary key(nick)
) TYPE=InnoDB;

insert into usuarios (nick, password, nombre, email, sexo, puntos, npaquetes)
   values ('Manolo', 'mipass', 'nombre','acarox@wanadoo.es', 'H', 20.0, 0);

-------------------------------------------- TABLA DE NOTICIAS

Create table Noticias (
      id	  int not null auto_increment,
      titulo	  varchar(100), 
      tipo	  varchar(20), 
      contenido	  text, 
      fecha	  date,
      primary key (id)
);

INSERT INTO Noticias VALUES 
   (1, 'Nuevas cartas', 'Aviso', 
   'Ya esta disponible una nueva ampliacion de la baraja', '2005-01-12');
INSERT INTO Noticias VALUES 
   (2, 'Nuevo diseño', 'Novedad', 
   'Hemos cambiado el diseño de la pagina de inicio', '2005-02-16');
 

-------------------------------------------- TABLA DEL FORO 

CREATE TABLE temas (
      idTema	  int	      NOT NULL auto_increment,
      titulo	  varchar(100) NULL,
      fecha	  date,
      cont_visitas  int,
      estado	  int,
      PRIMARY KEY(idTema)
) TYPE=InnoDB;

CREATE TABLE mensajes (
      idMensaje	  int	      NOT NULL auto_increment,
      nick	  varchar(50) NOT NULL,
      texto	  text,
      fecha	  date,
      idTema	  int	      NOT NULL,
      PRIMARY KEY(idMensaje),
      INDEX(nick),
      FOREIGN KEY(nick) REFERENCES usuarios(nick)
	    ON DELETE CASCADE ON UPDATE CASCADE,
      FOREIGN KEY(idTema) REFERENCES temas(idTema)
	    ON DELETE CASCADE ON UPDATE CASCADE
) TYPE=InnoDB;

-- TABLA ELIMINADA:

--CREATE TABLE mensaje_tema (
--      idMensaje	  int	NOT NULL,
--      idTema	  int	NOT NULL,
--      PRIMARY KEY(idMensaje, idTema),
--      INDEX (idMensaje), 
--      INDEX(idTema),
--      FOREIGN KEY(idMensaje) REFERENCES mensajes(idMensaje)
--	    ON DELETE CASCADE ON UPDATE CASCADE,
--      FOREIGN KEY(idTema) REFERENCES temas(idTema)
--	    ON DELETE CASCADE ON UPDATE CASCADE
--) TYPE=InnoDB;


-------------------------------------------- PERMISOS

grant select, insert, update, delete on genesis.* to
   webuser identified by 'vamosahablar';

grant select on genesis.usuarios to
   chat identified by 'comprobacionusuario';
