USE BBDD_PAISES;

DROP TABLE IF EXISTS USERS;
CREATE TABLE IF NOT EXISTS USERS(

    ID_USER INT UNSIGNED NOT NULL AUTO_INCREMENT,
    LOGIN VARCHAR(25) NOT NULL,
    PASSWD VARCHAR(128) NOT NULL,

    UNIQUE INDEX AK_LOGIN(LOGIN),

    PRIMARY KEY (ID_USER)
)ENGINE INNODB;

INSERT INTO USERS(LOGIN, PASSWD) VALUES ('user', '1046a2e4de40a55a09d7f6ae4b3214de8dbb586f4fb9b1547929ba132d17dc2e538ce87de57dfe639f1346bf6c2ddb8bfb46aa722256c03f750e3a71fd47afa4');
INSERT INTO USERS(LOGIN, PASSWD) VALUES ('administrador', 'b4d0afb88a5e459378b7fb4500e620866324e6990e9962724175b063ee6bd641200f9a2ee4de4658734bdbe42b3a75d5c1b03bb9256738415204dd0c43cb472b');

DROP TABLE IF EXISTS TOKENS_USERS;
CREATE TABLE IF NOT EXISTS TOKENS_USERS(
	ID_SESION INT UNSIGNED AUTO_INCREMENT NOT NULL,
    ID_USER INT UNSIGNED NOT NULL,
    USER_IP VARCHAR(12) NOT NULL,
	TOKEN CHAR(32) NOT NULL,
    
    FOREIGN KEY (ID_USER) REFERENCES USERS(ID_USER)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    
    PRIMARY KEY (ID_SESION),
    UNIQUE KEY AK_SESION_USER(ID_USER, USER_IP)
    
)ENGINE INNODB;