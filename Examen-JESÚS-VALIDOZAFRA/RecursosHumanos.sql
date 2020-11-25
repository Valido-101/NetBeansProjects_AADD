-- ==========================================================
-- Cargar datos a la tabla: cargo
-- ==========================================================
INSERT INTO cargo ( idcargo, nombre, sueldo_min, sueldo_max ) VALUES ( 'C01', 'Gerente General', 15000, 50000 );
INSERT INTO cargo ( idcargo, nombre, sueldo_min, sueldo_max ) VALUES ( 'C02', 'Gerente', 10000, 15000 );
INSERT INTO cargo ( idcargo, nombre, sueldo_min, sueldo_max ) VALUES ( 'C03', 'Empleado', 7000, 10000 );
INSERT INTO cargo ( idcargo, nombre, sueldo_min, sueldo_max ) VALUES ( 'C04', 'Analista', 5000, 7000 );
INSERT INTO cargo ( idcargo, nombre, sueldo_min, sueldo_max ) VALUES ( 'C05', 'Vendedor', 3000, 5000 );
INSERT INTO cargo ( idcargo, nombre, sueldo_min, sueldo_max ) VALUES ( 'C06', 'Oficinista', 1500, 3000 );
INSERT INTO cargo ( idcargo, nombre, sueldo_min, sueldo_max ) VALUES ( 'C07', 'Programador', 2500, 6000 );
INSERT INTO cargo ( idcargo, nombre, sueldo_min, sueldo_max ) VALUES ( 'C08', 'Investigador', 6000, 8000 );
INSERT INTO cargo ( idcargo, nombre, sueldo_min, sueldo_max ) VALUES ( 'C09', 'Digitador', 1000, 1500 );
-- ==========================================================
-- Cargar datos a la tabla: empleado
-- ==========================================================
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0001', 'Coronel', 'Gustavo', '20000401', 'gcoronel@gmail.com', '9666-4457', 'C01', 100, 25000, NULL, NULL );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0002', 'Fernandez', 'Claudia', '20000501', 'cfernandez@gmail.com', '9345-8365', 'C03', 100, 8000, NULL, 'E0001' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0003', 'Matsukawa', 'Sergio', '20000401', 'smatsukawa@gmail.com', '9772-8369', 'C02', 102, 15000, NULL, 'E0001' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0004', 'Diaz', 'Mariela', '20000410', 'mdiaz@gmail.com', '8654-6734', 'C06', 102, 1800, NULL, 'E0003' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0005', 'Martinez', 'Roberto', '20000405', 'rmartinez@gmail.com', NULL, 'C08', 102, 7000, 500, 'E0003' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0006', 'Espinoza', 'Miguel', '20000406', 'mespinoza@gmail.com', NULL, 'C08', 102, 7500, 500, 'E0003' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0007', 'Ramos', 'Vanessa', '20020406', 'vramos@gmail.com', '9456-3456', 'C08', 102, 7000, 500, 'E0003' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0008', 'Flores', 'Julio', '20000401', 'jflores@gmail.com', NULL, 'C07', 102, 3500, 1000, 'E0003' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0009', 'Marcelo', 'Ricardo', '20000401', 'rmarcelo@gmail.com', '9936-2966', 'C02', 101, 15000, NULL, 'E0001' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0010', 'Barrios', 'Guisella', '20010115', 'gbarrios@gmail.com', '9023-4512', 'C03', 101, 8000, NULL, 'E0009' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0011', 'Cuellar', 'Lucy', '20030218', 'lcuellar@gmail.com', NULL, 'C06', 101, 2000, NULL, 'E0009' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0012', 'Valencia', 'Hugo', '20000501', 'hvalencia@gmail.com', '9732-5601', 'C02', 105, 15000, NULL, 'E0001' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0013', 'Veliz', 'Fortunato', '20000505', 'fveliz@gmail.com', '9826-7603', 'C04', 105, 6000, NULL, 'E0012' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0014', 'Aguero', 'Octavio', '20000515', 'oaguero@gmail.com', NULL, 'C07', 105, 3000, 300, 'E0012' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0015', 'Rosales', 'Cesar', '20030315', 'crosales@gmail.com', NULL, 'C07', 105, 2500, 300, 'E0012' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0016', 'Villarreal', 'Nora', '20000501', 'nvillarreal@gmail.com', '9371-3641', 'C02', 103, 15000, NULL, 'E0001' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0017', 'Romero', 'Alejandra', '20000503', 'aromero@gmail.com', '8345-9526', 'C03', 103, 7500, NULL, 'E0016' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0018', 'Valdiviezo', 'Jennifer', '20000612', 'jvaldiviezo@gmail.com', '9263-5172', 'C06', 103, 2000, NULL, 'E0016' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0019', 'Muchotrigo', 'Omar', '20000512', 'omuchotrigo@gmail.com', '9963-5542', 'C05', 103, 3500, 500, 'E0016' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0020', 'Baltazar', 'Victor', '20000518', 'vbaltazar@gmail.com', '9893-4433', 'C05', 103, 3000, 800, 'E0016' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0021', 'Castillo', 'Ronald', '20010518', 'rcastillo@gmail.com', '9234-3487', 'C05', 103, 3000, 800, 'E0016' );
INSERT INTO empleado ( idempleado, apellido, nombre, fecingreso, email, telefono, idcargo, iddepartamento, sueldo, comision, jefe )
VALUES ( 'E0022', 'Espilco', 'Luis', '20020417', 'lespilco@gmail.com', '9554-3456', 'C05', 103, 3000, 300, 'E0016' );
-- ==========================================================
-- Cargar datos a la tabla: usuario
-- ==========================================================
INSERT INTO usuario ( idempleado, usuario, clave, estado ) VALUES ( 'E0001', 'gcoronelc', 'super', 1 );
INSERT INTO usuario ( idempleado, usuario, clave, estado ) VALUES ( 'E0018', 'jvaldiviezo', 'gatita', 1 );
INSERT INTO usuario ( idempleado, usuario, clave, estado ) VALUES ( 'E0011', 'lcuellar', 'tigresa', 0 );
INSERT INTO usuario ( idempleado, usuario, clave, estado ) VALUES ( 'E0008', 'jflores', 'cazador', 1 );
INSERT INTO usuario ( idempleado, usuario, clave, estado ) VALUES ( 'E0021', 'rcastillo', 'cerroeb', 0 );