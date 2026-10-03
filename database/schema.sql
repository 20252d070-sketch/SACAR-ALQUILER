-- =========================================================
-- BASE DE DATOS C.C. TRIVANI
-- =========================================================

CREATE DATABASE IF NOT EXISTS trivani_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE trivani_db;


-- =========================================================
-- TABLA: INQUILINOS
-- =========================================================

CREATE TABLE IF NOT EXISTS inquilinos (
    id INT AUTO_INCREMENT PRIMARY KEY,

    nombre VARCHAR(150) NOT NULL,

    dni VARCHAR(15) NOT NULL UNIQUE,

    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- =========================================================
-- TABLA: PAGOS / CONSTANCIAS
-- =========================================================

CREATE TABLE IF NOT EXISTS pagos (

    id INT AUTO_INCREMENT PRIMARY KEY,

    comprobante_codigo VARCHAR(50) NOT NULL,

    inquilino_id INT NOT NULL,

    monto DECIMAL(10,2) NOT NULL,

    monto_letras VARCHAR(255) NOT NULL,

    periodo VARCHAR(50) NOT NULL,

    fecha_operacion DATE NOT NULL,

    concepto VARCHAR(150) NOT NULL,

    ubicacion VARCHAR(150) NOT NULL,

    cuenta_destino VARCHAR(150) NOT NULL,

    arrendador_nombre VARCHAR(150) NOT NULL,

    arrendador_dni VARCHAR(15) NOT NULL,

    -- Estado del pago
    estado_pago VARCHAR(30)
        NOT NULL DEFAULT 'PENDIENTE',

    -- Enlace generado para realizar el pago
    link_pago TEXT NULL,

    -- ID de la preferencia de Mercado Pago
    preferencia_id VARCHAR(150) NULL,

    -- URL del comprobante PDF
    pdf_url TEXT NULL,

    -- Fecha automática de registro
    fecha_registro TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_pago_inquilino
        FOREIGN KEY (inquilino_id)
        REFERENCES inquilinos(id)
        ON DELETE CASCADE
);


-- =========================================================
-- DATOS INICIALES
-- =========================================================

INSERT INTO inquilinos
    (id, nombre, dni)
VALUES
    (1, 'ESFRAIN CRUZ LLANOS', '47373742')
ON DUPLICATE KEY UPDATE
    nombre = VALUES(nombre),
    dni = VALUES(dni);
