INSERT INTO doctor (nombre, especialidad, disponible) VALUES 
('Dr. Carlos Alvarado', 'Medicina General', TRUE),
('Dra. Sofia Segura', 'Pediatria', TRUE),
('Dr. Roberto Mora', 'Urgencias', FALSE);

INSERT INTO paciente (identificacion, nombre_completo, correo, telefono)
VALUES 
('118230495', 'Elena Madrigal Castro', 'elena@ucr.ac.cr', '88776655'),
('207410982', 'Mario Jimenez Solano', 'mario@ucr.ac.cr', '83332211');

INSERT INTO cita_medica 
(codigo_cita, paciente_id, doctor_id, nivel_prioridad, 
 motivo_consulta, estado, monto_consulta, fecha_cita)
VALUES 
('CIT-2026-001', 1, 1, 'ALTA', 
 'Fiebre alta y dificultad respiratoria', 'PENDIENTE', 
 25000.00, CURRENT_TIMESTAMP),
('CIT-2026-002', 2, 2, 'MEDIA', 
 'Dolor abdominal moderado', 'EN_ATENCION', 
 20000.00, CURRENT_TIMESTAMP);