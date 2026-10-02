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

-- 2. Tabla de Pagos / Constancias
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
    FOREIGN KEY (inquilino_id) REFERENCES inquilinos(id) ON DELETE CASCADE
);

-- DATOS INICIALES DE PRUEBA
INSERT INTO inquilinos (id, nombre, dni) VALUES (1, 'ESFRAIN CRUZ LLANOS', '47373742');
