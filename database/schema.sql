-- Base de Datos Comercial - C.C. Trivani
CREATE DATABASE IF NOT EXISTS trivani_db;
USE trivani_db;

-- 1. Tabla de Inquilinos
CREATE TABLE IF NOT EXISTS inquilinos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    dni VARCHAR(15) UNIQUE NOT NULL,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tabla de Usuarios y Seguridad (Roles Reales)
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    rol ENUM('ADMIN', 'INQUILINO') NOT NULL,
    inquilino_id INT NULL,
    FOREIGN KEY (inquilino_id) REFERENCES inquilinos(id) ON DELETE SET NULL
);

-- 3. Tabla de Pagos / Constancias
CREATE TABLE IF NOT EXISTS pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    comprobante_codigo VARCHAR(50) NOT NULL,
    inquilino_id INT NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    monto_letras VARCHAR(255) NOT NULL,
    periodo VARCHAR(50) NOT NULL,
    fecha_operacion DATE NOT NULL,
    concepto VARCHAR(150) NOT NULL,
    ubicacion VARCHAR(150) NOT NULL,
    cuenta_destino VARCHAR(150) NOT NULL,
    arrendador_nombre VARCHAR(150) NOT NULL,
    arrendador_dni VARCHAR(15) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (inquilino_id) REFERENCES inquilinos(id)
);

-- DATOS INICIALES REALES
INSERT INTO inquilinos (id, nombre, dni) VALUES (1, 'JUAN ELIAS QUISPE SURCO', '60241705');

-- Usuario Administrador (Víctor Almirón)
INSERT INTO usuarios (usuario, password_hash, rol, inquilino_id) 
VALUES ('victor_admin', 'admin123', 'ADMIN', NULL);

-- Usuario Inquilino (Juan Quispe)
INSERT INTO usuarios (usuario, password_hash, rol, inquilino_id) 
VALUES ('juan_inquilino', 'inquilino123', 'INQUILINO', 1);
