CREATE INDEX A_FACTURA_cliente
    ON A_FACTURA (id_cliente);

CREATE INDEX A_FACTURA_c_fecha
    ON A_FACTURA (FECHA); 

CREATE INDEX A_FACTURA_cliente_fecha
    ON A_FACTURA (FECHA,id_cliente); 




select * , SUM(TOTAL) totalvalor
from A_FACTURA, A_CLIENTES
WHERE A_FACTURA.id_cliente = A_CLIENTES.id
    AND A_FACTURA.fehca BETWEEN TO_DATE('22/05/202200') AND TO_DATE('24/12/2022')
ORDER BY totalvalor DESC;


