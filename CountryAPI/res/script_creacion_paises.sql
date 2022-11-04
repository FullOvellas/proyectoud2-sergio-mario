DROP DATABASE BBDD_PAISES IF EXISTS;
CREATE DATABASE IF NOT EXISTS BBDD_PAISES;

USE BBDD_PAISES;

DROP TABLE IF EXISTS MONEDAS;
CREATE TABLE IF NOT EXISTS MONEDAS(

     ID_MONEDA INT UNSIGNED AUTO_INCREMENT NOT NULL,
                        NOMBRE VARCHAR(20) NOT NULL,

                        PRIMARY KEY (ID_MONEDA)

) ENGINE INNODB;

DROP TABLE IF EXISTS PAISES;
CREATE TABLE IF NOT EXISTS PAISES(

                                     ID_PAIS INT UNSIGNED AUTO_INCREMENT NOT NULL,
                                     NOMBRE VARCHAR(40) NOT NULL,
                                     NUM_HABITANTES INT UNSIGNED NOT NULL,
                                     CAPITAL VARCHAR(40) NOT NULL,

                                     PRIMARY KEY (ID_PAIS),
                                     UNIQUE INDEX AK_NOMBRE(NOMBRE)

)ENGINE INNODB;

DROP TABLE IF EXISTS MONEDAS_PAISES;
CREATE TABLE IF NOT EXISTS MONEDAS_PAISES(

                                             MONEDA INT UNSIGNED NOT NULL,
                                             PAIS INT UNSIGNED NOT NULL,

                                             FOREIGN KEY FK_MONEDA(MONEDA) REFERENCES MONEDAS(ID_MONEDA)
                                                 ON DELETE RESTRICT
                                                 ON UPDATE CASCADE,

                                             FOREIGN KEY FK_PAIS(PAIS) REFERENCES PAISES(ID_PAIS)
                                                 ON DELETE CASCADE
                                                 ON UPDATE CASCADE,

                                             PRIMARY KEY (PAIS, MONEDA)

) ENGINE INNODB;

DROP TABLE IF EXISTS IDIOMAS;
CREATE TABLE IF NOT EXISTS IDIOMAS(

    ID_IDIOMA INT UNSIGNED AUTO_INCREMENT NOT NULL,
    NOMBRE VARCHAR(20) NOT NULL,

    PRIMARY KEY (ID_IDIOMA),
    UNIQUE INDEX AK_NOMBRE(NOMBRE)

) ENGINE INNODB;

DROP TABLE IF EXISTS IDIOMAS_PAISES;
CREATE TABLE IF NOT EXISTS IDIOMAS_PAISES (

    IDIOMA INT UNSIGNED NOT NULL,
    PAIS INT UNSIGNED NOT NULL,

    FOREIGN KEY FK_IDIOMA(IDIOMA) REFERENCES IDIOMAS(ID_IDIOMA)
                                          ON DELETE RESTRICT
                                          ON UPDATE CASCADE,

    FOREIGN KEY FK_PAIS_IDIOMA(PAIS) REFERENCES PAISES(ID_PAIS)
                                          ON DELETE RESTRICT
                                          ON UPDATE CASCADE,

    PRIMARY KEY (IDIOMA, PAIS)


) ENGINE INNODB;
