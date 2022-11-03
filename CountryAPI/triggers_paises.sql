DELIMITER $$

CREATE TRIGGER check_monedas_count ON monedas_paises 
AFTER DELETE AS
BEGIN

    IF (SELECT COUNT(
        SELECT * FROM monedas_paises
        WHERE id_moneda = OLD.moneda
    )) = 0;

        DELETE * FROM monedas WHERE id_moneda = OLD.moneda;

    end if;

END$$