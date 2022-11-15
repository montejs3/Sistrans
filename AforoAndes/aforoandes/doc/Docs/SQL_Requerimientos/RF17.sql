DECLARE
    canprod int := 1;
    compraid number := 230942;
    productoid number := 12;
    promoid number := 12323;

INSERT INTO Producto_Compra 
VALUES(canprod, compraid, productoid, promoid)

UPDATE Productos
SET p.EXISTENCIAS_ESTANTE = p.EXISTENCIAS_ESTANTE - pc.CANTIDAD_PRODUCTO
FROM Producto_Compra pc, Productos p
WHERE p.CODIGO_BARRAS = productoid;

IF promoid IS NOT NULL
    UPDATE Promociones 
    SET ps.UNIDADES_VENDIDAS = ps.UNIDADES_VENDIDAS + pc.CANTIDAD_PRODUCTO, ps.UNIDADES_DISPONIBLES = ps.UNIDADES_DISPONIBLES - pc.CANTIDAD_PRODUCTO 
    FROM Producto_Compra pc, Promociones ps 
    WHERE ps.NUM_PROM = promoid;
