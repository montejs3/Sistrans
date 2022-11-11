CREATE TABLE Usuario (
NUMERO_IDENTIFICACION NUMBER PRIMARY KEY,
TIPO_IDENTIFICACION VARCHAR(25) NOT NULL,
NOMBRE VARCHAR(25) NOT NULL,
CORREO_ELECTRONICO VARCHAR(35) NOT NULL,
PALABRA_CLAVE VARCHAR(15) NOT NULL,
ROL VARCHAR(15) NOT NULL,
SUCURSAL NUMBER NOT NULL,
CONSTRAINT fk_SUC FOREIGN KEY (SUCURSAL) REFERENCES Sucursales(ID));

