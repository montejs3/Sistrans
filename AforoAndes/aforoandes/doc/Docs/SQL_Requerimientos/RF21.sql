


UPDATE PEDIDOS 
SET PEDIDOS.VOLUMENES = BODEGAS.CAPACIDAD
FROM PRODUCTOS, PEDIDOS , BODEGAS
WHERE EXISTENCIAS_BODEGA = 0
