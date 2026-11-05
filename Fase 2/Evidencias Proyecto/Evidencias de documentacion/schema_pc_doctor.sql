-- ============================================================
-- ESQUEMA SQL ACTUAL: pc_doctor_db
-- Sistema: PC Doctor – Autodiagnóstico, Cotización y Agendamiento
-- Compatibilidad: MySQL 8.0+ / MariaDB (XAMPP phpMyAdmin)
-- ============================================================

CREATE DATABASE IF NOT EXISTS `pc_doctor_db`
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE `pc_doctor_db`;

-- Desactivar temporalmente revisión de llaves foráneas para creación limpia
SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------------
-- 1. TABLA: sintomas
-- Mapea a com.pcdoctor.backend.model.Sintoma
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `sintomas`;
CREATE TABLE `sintomas` (
  `id_sintoma` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(255) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- 2. TABLA: diagnosticos
-- Mapea a com.pcdoctor.backend.model.Diagnostico
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `diagnosticos`;
CREATE TABLE `diagnosticos` (
  `id_diagnostico` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `titulo` VARCHAR(150) NOT NULL,
  `causa_probable` TEXT NOT NULL,
  `solucion_recomendada` TEXT NOT NULL,
  `presupuesto_estimado_clp` DECIMAL(10, 2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- 3. TABLA: preguntas
-- Mapea a com.pcdoctor.backend.model.Pregunta
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `preguntas`;
CREATE TABLE `preguntas` (
  `id_pregunta` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `id_sintoma` BIGINT NOT NULL,
  `texto_pregunta` TEXT NOT NULL,
  `id_siguiente_si` BIGINT NULL,
  `id_siguiente_no` BIGINT NULL,
  `id_diagnostico_si` BIGINT NULL,
  `id_diagnostico_no` BIGINT NULL,
  CONSTRAINT `fk_preguntas_sintoma` FOREIGN KEY (`id_sintoma`) REFERENCES `sintomas` (`id_sintoma`) ON DELETE CASCADE,
  CONSTRAINT `fk_preguntas_sig_si` FOREIGN KEY (`id_siguiente_si`) REFERENCES `preguntas` (`id_pregunta`) ON DELETE SET NULL,
  CONSTRAINT `fk_preguntas_sig_no` FOREIGN KEY (`id_siguiente_no`) REFERENCES `preguntas` (`id_pregunta`) ON DELETE SET NULL,
  CONSTRAINT `fk_preguntas_diag_si` FOREIGN KEY (`id_diagnostico_si`) REFERENCES `diagnosticos` (`id_diagnostico`) ON DELETE SET NULL,
  CONSTRAINT `fk_preguntas_diag_no` FOREIGN KEY (`id_diagnostico_no`) REFERENCES `diagnosticos` (`id_diagnostico`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- 4. TABLA: clientes
-- Mapea a com.pcdoctor.backend.model.Cliente
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id_cliente` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `rut` VARCHAR(12) NOT NULL UNIQUE,
  `nombre` VARCHAR(150) NOT NULL,
  `correo` VARCHAR(150) NOT NULL,
  `telefono` VARCHAR(20) NOT NULL,
  `fecha_registro` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- 5. TABLA: solicitudes_reparacion
-- Mapea a com.pcdoctor.backend.model.SolicitudReparacion
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `solicitudes_reparacion`;
CREATE TABLE `solicitudes_reparacion` (
  `id_solicitud` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `id_cliente` BIGINT NOT NULL,
  `id_diagnostico` BIGINT NULL,
  `resumen_respuestas` TEXT NULL,
  `presupuesto_total` DECIMAL(10, 2) NOT NULL,
  `fecha_visita` DATE NOT NULL,
  `estado` VARCHAR(50) DEFAULT 'PENDIENTE',
  `fecha_creacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `fk_solicitudes_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE CASCADE,
  CONSTRAINT `fk_solicitudes_diagnostico` FOREIGN KEY (`id_diagnostico`) REFERENCES `diagnosticos` (`id_diagnostico`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;