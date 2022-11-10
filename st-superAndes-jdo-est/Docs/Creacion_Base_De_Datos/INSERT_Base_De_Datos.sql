INSERT INTO Supermercado
VALUES(1,'SuperAndes');

INSERT INTO Tipo_Producto
VALUES(1,'Verdura', 'Perecedero');

INSERT INTO Tipo_Producto
VALUES(2,'Cocina', 'Abarrote');

INSERT INTO Tipo_Producto
VALUES(3,'Carne','Perecedero');

INSERT INTO Tipo_Producto
VALUES(4,'Alcohol', 'Perecedero');

INSERT INTO Tipo_Producto
VALUES(5,'Aseo', 'Abarrote');

INSERT INTO Tipo_Producto
VALUES(6,'Ropa','Abarrote');

INSERT INTO Productos
VALUES('Lechuga','Fruver',8000,'Paquetes de 4 unidades','Columnas de 2',25,'Plástico',1,785416,50,20,1,'17/10/2023');

INSERT INTO Productos
VALUES('Traperos','Rimax',15000,'Paquete de 1 unidad','Un solo trapero por marca',1.25,'Cartón de la marca',2,842693,100,40,2,NULL);

INSERT INTO Productos
VALUES('Carne molida','SurtiCarnes',25000,'Paquete de 1 unidad','Una tira por paquete',30,'Plástico',1,753159,20,10,3,'01/01/2023');

INSERT INTO Productos
VALUES('Aguardiente','Lider',15000,'Botella de 500mL','Una botella de vidrio',30,'Vidrio',1,104805,100,60,4,'01/01/2023');

INSERT INTO Productos
VALUES('Cerveza','Poker',13200,'Six Pack','Seis latas de cerveza',30,'Plástico',1,582436,100,60,4,'01/01/2023');

INSERT INTO Productos
VALUES('Jeans','American Eagle',20000,'1 unidad de ropa','Un jean único',40,'Prenda sola',2,125063,80,5,6,NULL);

INSERT INTO Productos
VALUES('Crema Dental','Colgate',12000,'Paquete de 2 unidades','Dos cremas por paquete',30,'Plástico',2,794586,60,60,5,'01/01/2026');

INSERT INTO Sucursales
VALUES ('Bogotá','Calle 10 #80-65','Sucursal10',10,25,50,1,785416);

INSERT INTO Sucursales
VALUES ('Cali','Calle 90 #100-12','Sucursal20',20,10,60,1,842693);

INSERT INTO Sucursales
VALUES ('Medellin','Calle 64 #1-74','Sucursal30',30,5,70,1,753159);

INSERT INTO Sucursales
VALUES ('Bogotá','Calle 3 #25B-88','Sucursal40',40,25,22,1,582436);

INSERT INTO Bodegas
VALUES(10,600,10,200,785416);

INSERT INTO Bodegas
VALUES(20,1100,20,1000,842693);

INSERT INTO Bodegas
VALUES(30,220,30,100,753159);

INSERT INTO Bodegas
VALUES(40,415,40,0,582436);

INSERT INTO Local_de_ventas
VALUES(1,10);

INSERT INTO Local_de_ventas
VALUES(2,20);

INSERT INTO Local_de_ventas
VALUES(3,30);

INSERT INTO Local_de_ventas
VALUES(4,40);

INSERT INTO Estantes
VALUES(10,15,1,15,14,10);

INSERT INTO Estantes
VALUES(20,18,3,18,7,10);

INSERT INTO Estantes
VALUES(30,22,2,22,20,20);

INSERT INTO Estantes
VALUES(40,23,4,23,10,20);

INSERT INTO Estantes
VALUES(50,14,1,14,10,30);

INSERT INTO Estantes
VALUES(60,35,6,35,5,30);

INSERT INTO Estantes
VALUES(70,19,2,19,10,40);

INSERT INTO Estantes
VALUES(80,46,5,46,30,40);

INSERT INTO Productos_Perecederos
VALUES(785416,'17/10/2023',1);

INSERT INTO Productos_Perecederos
VALUES(753159,'01/01/2023',1);

INSERT INTO Productos_Perecederos
VALUES(104805,'01/01/2023',1);

INSERT INTO Productos_Perecederos
VALUES(582436,'01/01/2023',1);

INSERT INTO Abarrotes
VALUES(842693,2);

INSERT INTO Abarrotes
VALUES(125063,2);

INSERT INTO Abarrotes
VALUES(794586,2);

INSERT INTO Proveedores
VALUES(785426,'DELCAMPO',4,'EXCLUSIVO');

INSERT INTO Proveedores
VALUES(753014,'HOMEHOME',4,'NOEXCLUSIVO');

INSERT INTO Proveedores
VALUES(962147,'CARNESYCARNES',5,'EXCLUSIVO');

INSERT INTO Proveedores
VALUES(921036,'LICORESJUAN',3,'NOEXCLUSIVO');

INSERT INTO Pedidos
VALUES(10,'Aguardiente Lider',50,250000,'23/10/2022',921036);

INSERT INTO Pedidos
VALUES(20,'Carne molida',1,25000,'23/10/2022',962147);

INSERT INTO Pedidos
VALUES(30,'Lechuga',10,12000,'23/10/2022',785426);

INSERT INTO Promociones
VALUES (10,'Compre 1 paquete de Carne molida, llevese 4 paquetes', '31-10-2022', '01/01/2023','No finalizado',20,962147,20,100);

INSERT INTO Promociones
VALUES (20,'Por la compra de 1 botella de 500mL de aguardiente, llevese un six-pack de cerveza', '31-10-2022','01/01/2023','No finalizado',50,921036,50,100);

INSERT INTO Promociones
VALUES (30,'Compre 2 traperos, llevese 1 jean', '8-11-2022','01/01/2023','No finalizado',40,753014,40,100);

INSERT INTO Promociones
VALUES (40,'Compre 1 jean, llevese 2 cremas dentales', '31/10/2022','01-01-2023','No finalizado',15,753014,15,100);

INSERT INTO Clientes
VALUES(1000458792,'Daniel Garcia','da.garciap1@uniandes.edu.co','Calle 160 #50B-15');

INSERT INTO Clientes
VALUES(1000654987,'Julian Camargo','j.camargo@uniandes.edu.co','Calle 1A #25-8');

INSERT INTO Clientes
VALUES(753159,'Millonarios','mfc.clubmillonarios@uniandes.edu.co','Calle 57 #19-10');

INSERT INTO Compra
VALUES(1,50000,'23/10/2022',1000458792);

INSERT INTO Compra
VALUES(2,15000,'23/10/2022',1000654987);

INSERT INTO Compra
VALUES(3,10200,'23/10/2022',753159);

INSERT INTO Producto_Compra 
VALUES (15,1,785416,20);

INSERT INTO Producto_Compra
VALUES (20,1,753159,10);

INSERT INTO Persona_Natural
VALUES(1000458792);

INSERT INTO Persona_Natural
VALUES(1000654987);

INSERT INTO Empresa
VALUES(753159);

INSERT INTO Usuario
VALUES(1000458792,'Cedula','Daniel Garcia','da.garciap1@uniandes.edu.co','paco','Cliente',10);

INSERT INTO Usuario
VALUES(1000654987,'Cedula','Julian Camargo','j.camargo@uniandes.edu.co','colina','Cliente',20);

INSERT INTO Usuario
VALUES(753159,'NIT','Millonarios','mfc.clubmillonarios@uniandes.edu.co','campin','Cliente',20);

INSERT INTO Proveedores_Productos
VALUES(785426,785416);

INSERT INTO Proveedores_Productos
VALUES(753014,842693);

INSERT INTO Proveedores_Productos
VALUES(962147,753159);

INSERT INTO Proveedores_Productos
VALUES(921036,104805);

INSERT INTO Proveedores_Productos
VALUES(921036,582436);

INSERT INTO Proveedores_Productos
VALUES(753014,125063);

INSERT INTO Proveedores_Productos
VALUES(753014,794586);

INSERT INTO Pedidos_Productos
VALUES(10,104805);

INSERT INTO Pedidos_Productos
VALUES(20,753159);

INSERT INTO Pedidos_Productos
VALUES(30,785416);

INSERT INTO Sucursales_Pedidos
VALUES(10,10);

INSERT INTO Sucursales_Pedidos
VALUES(10,20);

INSERT INTO Sucursales_Pedidos
VALUES(20,10);

INSERT INTO Sucursales_Pedidos
VALUES(20,30);

INSERT INTO Sucursales_Pedidos
VALUES(30,20);

INSERT INTO Sucursales_Pedidos
VALUES(40,10);

INSERT INTO Sucursales_Pedidos
VALUES(40,20);

INSERT INTO Sucursales_Pedidos
VALUES(40,30);

INSERT INTO Productos_Estantes
VALUES(785416,10);

INSERT INTO Productos_Estantes
VALUES(753159,20);

INSERT INTO Productos_Estantes
VALUES(842693,30);

INSERT INTO Productos_Estantes
VALUES(104805,40);

INSERT INTO Productos_Estantes
VALUES(125063,60);

INSERT INTO Productos_Estantes
VALUES(794586,80);

INSERT INTO Productos_Bodegas
VALUES(753159,10);

INSERT INTO Productos_Bodegas
VALUES(785416,20);

INSERT INTO Productos_Bodegas
VALUES(794586,30);

INSERT INTO Productos_Bodegas
VALUES(125063,40);

INSERT INTO Proveedores_Sucursales
VALUES(785426,10);

INSERT INTO Proveedores_Sucursales
VALUES(753014,20);

INSERT INTO Proveedores_Sucursales
VALUES(962147,30);

INSERT INTO Proveedores_Sucursales
VALUES(921036,40);

INSERT INTO Bodegas_TipoProducto
VALUES(10,1);

INSERT INTO Bodegas_TipoProducto
VALUES(20,1);

INSERT INTO Bodegas_TipoProducto
VALUES(30,3);

INSERT INTO Bodegas_TipoProducto
VALUES(40,2);












