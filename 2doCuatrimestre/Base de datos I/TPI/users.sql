USE tp1;

DROP USER IF EXISTS 'usuario_publico'@'localhost';
DROP USER IF EXISTS 'usuario_privado'@'localhost';

CREATE USER 'usuario_privado'@'localhost' IDENTIFIED BY 'Privado123!';
GRANT SELECT ON tp1.vista_stock_general TO 'usuario_privado'@'localhost';
GRANT SELECT ON tp1.vista_stock_publica TO 'usuario_privado'@'localhost';
GRANT SELECT ON tp1.vista_resumen_publica TO 'usuario_privado'@'localhost';

CREATE USER 'usuario_publico'@'localhost' IDENTIFIED BY 'Publico123!';
GRANT SELECT ON tp1.vista_stock_publica TO 'usuario_publico'@'localhost';
GRANT SELECT ON tp1.vista_resumen_publica TO 'usuario_publico'@'localhost';

FLUSH PRIVILEGES;