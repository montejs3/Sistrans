CREATE TABLE Productos (
NOMBRE VARCHAR(25) NOT NULL,
MARCA VARCHAR(25) NOT NULL,
PRECIO_UNITARIO FLOAT NOT NULL,
PRESENTACION VARCHAR(25) NOT NULL,
CANTIDAD_PRESENTACION VARCHAR(25) NOT NULL,
MEDIDA FLOAT NOT NULL,
EMPACADO VARCHAR(25) NOT NULL,
TIPO_PRODUCTO NUMBER NOT NULL,
CODIGO_BARRAS NUMBER PRIMARY KEY,
EXISTENCIAS_BODEGA INT NOT NULL,
EXISTENCIAS_ESTANTE INT NOT NULL,
CATEGORIA NUMBER NOT NULL,
FECHA_VENCIMIENTO DATE NOT NULL,
CONSTRAINT fk_Tipo_Producto_4 FOREIGN KEY (TIPO_PRODUCTO) REFERENCES Tipo_Producto(ID),
CONSTRAINT fk_Categoria FOREIGN KEY (CATEGORIA) REFERENCES Tipo_Producto(ID));
