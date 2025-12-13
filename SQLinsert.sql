-- 1. Cédula de Ciudadanía (CC)
INSERT INTO document_type (initials, document_name, state_document_type)
VALUES ('CC', 'Cédula de Ciudadanía', 'Activo');

-- 2. Tarjeta de Identidad (TI)
INSERT INTO document_type (initials, document_name, state_document_type)
VALUES ('TI', 'Tarjeta de Identidad', 'Activo');

-- 3. Cédula de Extranjería (CE)
INSERT INTO document_type (initials, document_name, state_document_type)
VALUES ('CE', 'Cédula de Extranjería', 'Activo');

-- 4. Registro Civil (RC) - Usado para menores de 7 años
INSERT INTO document_type (initials, document_name, state_document_type)
VALUES ('RC', 'Registro Civil', 'Activo');

-- 5. Pasaporte (PA)
INSERT INTO document_type (initials, document_name, state_document_type)
VALUES ('PA', 'Pasaporte', 'Activo');

-- 6. Documento de Identificación Extranjero (DIE) - Usado para extranjeros con trámites específicos
INSERT INTO document_type (initials, document_name, state_document_type)
VALUES ('DIE', 'Documento de Identificación Extranjero', 'Activo');

-- 7. Permiso Especial de Permanencia (PEP) - Usado para migrantes venezolanos con estatus regular
INSERT INTO document_type (initials, document_name, state_document_type)
VALUES ('PEP', 'Permiso Especial de Permanencia', 'Activo');

-- 8. Permiso por Protección Temporal (PPT) - Nuevo documento para migrantes venezolanos
INSERT INTO document_type (initials, document_name, state_document_type)
VALUES ('PPT', 'Permiso por Protección Temporal', 'Activo');