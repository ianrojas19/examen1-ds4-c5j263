DROP TABLE IF EXISTS cita_medica;
DROP TABLE IF EXISTS paciente;
DROP TABLE IF EXISTS doctor;

CREATE TABLE doctor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(80) NOT NULL,
    disponible BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE paciente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    identificacion VARCHAR(20) NOT NULL UNIQUE,
    nombre_completo VARCHAR(120) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

CREATE TABLE cita_medica (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_cita VARCHAR(30) NOT NULL UNIQUE,
    paciente_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    nivel_prioridad VARCHAR(20) NOT NULL,
    motivo_consulta VARCHAR(255) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    monto_consulta DECIMAL(10,2) NOT NULL,
    fecha_cita TIMESTAMP NOT NULL,
    CONSTRAINT fk_cita_paciente 
        FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    CONSTRAINT fk_cita_doctor 
        FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);