-- Crear esquema para Mysql
CREATE SCHEMA `clienteservice`;
   

-- Drop Tables if exist (MySQL style)
DROP TABLE IF EXISTS MOVIMIENTO;
DROP TABLE IF EXISTS CUENTA;
DROP TABLE IF EXISTS CLIENTE;

-- Create Tables (MySQL syntax)
CREATE TABLE CLIENTE (
    Persona_ID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único de la Persona',
    Cliente_ID INT NOT NULL UNIQUE COMMENT 'Identificador único del cliente',
    Nombre VARCHAR(100) NOT NULL COMMENT 'Nombre completo del cliente',
    Genero VARCHAR(10) COMMENT 'Género del cliente',
    Edad INT  COMMENT 'Edad del cliente',
    Identificacion VARCHAR(20) NOT NULL COMMENT 'Número de identificación',
    Direccion VARCHAR(200) COMMENT 'Dirección de residencia',
    Telefono VARCHAR(20) COMMENT  'Telefono de contacto',
    Contrasena VARCHAR(255) NOT NULL COMMENT 'Contrasena de acceso',
    Estado     VARCHAR(20) DEFAULT 'TRUE' NOT NULL COMMENT 'Estado del cliente TRUE - Activo  FALSE - Inactivo'
);

CREATE TABLE CUENTA (
    Cuenta_ID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único de la cuenta',
    Persona_ID INT NOT NULL COMMENT 'Cliente propietario de la cuenta',
    Numero_Cuenta VARCHAR(20) NOT NULL COMMENT 'Número de cuenta bancaria',
    Tipo_Cuenta VARCHAR(20)  NOT NULL COMMENT 'Tipo de cuenta (Ahorros, Corriente)',
    Saldo_Inicial DECIMAL(15, 2) DEFAULT 0 NOT NULL COMMENT 'Saldo actual de la cuenta',
    Estado VARCHAR(20)  DEFAULT 'TRUE' NOT NULL COMMENT 'Estado de la Cuenta TRUE - Activa  FALSE - Inactiva',
    FOREIGN KEY (Persona_ID) REFERENCES CLIENTE(Persona_ID)
);

CREATE TABLE MOVIMIENTO (
    Movimiento_ID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único del cliente',
    Cuenta_ID INT NOT NULL COMMENT 'Identificador externo o referencia un persona',
    Fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT 'Fecha y hora en la que realizo del movimiento',
    Tipo_Movimiento VARCHAR(20) NOT NULL COMMENT 'Tipo de movimiento (Depósito, Retiro)',
    Valor DECIMAL(15, 2) NOT NULL COMMENT'Monto del movimiento',
    FOREIGN KEY (Cuenta_ID) REFERENCES CUENTA(Cuenta_ID)
);

-- Comentarios para la tabla CLIENTE
ALTER TABLE CLIENTE COMMENT = 'Tabla que almacena los datos básicos del cliente';

-- Comentarios para la tabla CUENTA
ALTER TABLE CUENTA COMMENT = 'Tabla que almacena las cuentas asociadas a cada cliente';

-- Comentarios para la tabla MOVIMIENTO
ALTER TABLE MOVIMIENTO COMMENT = 'Tabla que almacena los movimientos de las cuentas';


-- Índices adicionales
CREATE INDEX idx_cliente_identificacion ON CLIENTE (Identificacion);
CREATE INDEX idx_cuenta_numero ON CUENTA (Numero_Cuenta);
CREATE INDEX idx_movimiento_fecha ON MOVIMIENTO (Fecha);

-- Restricciones de validación
ALTER TABLE CUENTA ADD CONSTRAINT chk_saldo CHECK (Saldo_Inicial >= 0);

ALTER TABLE CUENTA ADD CONSTRAINT unique_numero_cuenta UNIQUE (NUMERO_CUENTA);
   