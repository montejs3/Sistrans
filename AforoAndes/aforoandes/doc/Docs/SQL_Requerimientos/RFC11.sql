CREATE INDEX A_FACTURA_cliente
    ON A_FACTURA (id_cliente);

CREATE INDEX A_FACTURA_c_fecha
    ON A_FACTURA (FECHA); 

CREATE INDEX A_FACTURA_cliente_fecha
    ON A_FACTURA (FECHA,id_cliente); 

CREATE INDEX A_PROVEEDORES_producto
    ON A_PROVEEDORES (Fproducto); 


select * 
from  A_COMPRA,A_FACTURA,A_PROVEEDORES
WHERE A_FACTURA.id_cliente = A_CLIENTES.id
    AND A_FACTURA.fehca BETWEEN TO_DATE('01/01/2022') AND TO_DATE('01/01/2023')
    AND A_FACTURA.id_cliente = NULL;
    AND IdProveedor max(producto)
    AND A_FACTURA.producto max(producto)



