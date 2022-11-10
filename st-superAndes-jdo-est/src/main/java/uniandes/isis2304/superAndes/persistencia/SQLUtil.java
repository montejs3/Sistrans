package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLUtil {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLUtil(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }    

    public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ psa.darSeqSuperAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
    }

    public long[] limpiarSuperAndes(PersistenceManager pm) {
        Query qBodegas = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaBodegas ());
        Query qClientes = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaClientes());
        Query qCompra = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCompra ());
        Query qEstantes = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaEstantes ());
        Query qLocalDeVentas = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaLocalDeVentas ());
        Query qPedidos = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPedidos ());
        Query qPedidosProductos = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPedidosProductos ());
        Query qProductoCompra = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaproductoCompra ());          
        Query qProductos = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductos());
        Query qPromociones = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromociones ());
        Query qProveedores = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedores());
        Query qRolUsuario = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaRolUsuario());
        Query qSucursales = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursales());
        Query qProveedoresProductos = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedoresProductos ());
        Query qSucursalesProductos = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursalesProductos ());
        Query qSupermercado = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSupermercado());
        Query qTipoProducto = pm.newQuery(SQL, "DELETE FROM " + psa.darTablatipoProducto ());
        Query qUsuario = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaUsuario());
        Query qProductosEstantes = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosEstantes ());
        Query qProductosBodegas = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosBodegas ());
		Query qProveedoresSucursales = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedoresSucursales());

        long productoCompraDelete = (long) qProductoCompra.executeUnique ();
        long pedidoProductosDelete = (long) qPedidosProductos.executeUnique ();
        long proveedoresProductosDelete = (long) qProveedoresProductos.executeUnique ();
        long sucursalesProductosDelete = (long) qSucursalesProductos.executeUnique ();
        long productosEstantesDelete = (long) qProductosEstantes.executeUnique ();
        long productosBodegasDelete = (long) qProductosBodegas.executeUnique ();
		long productosDelete = (long) qProductos.executeUnique ();
		long estantesDelete = (long) qEstantes.executeUnique ();
		long bodegasDelete = (long) qBodegas.executeUnique ();
		long compraDelete = (long) qCompra.executeUnique ();
		long promocionDelete = (long) qPromociones.executeUnique ();
		long pedidoDelete = (long) qPedidos.executeUnique ();
		long tipoProductoDelete = (long) qTipoProducto.executeUnique ();
		long proveedoresSucursalesDelete = (long) qProveedoresSucursales.executeUnique ();
		long rolUsuarioDelete = (long) qRolUsuario.executeUnique ();
		long clientesDelete = (long) qClientes.executeUnique ();
		long proveedoresDelete = (long) qProveedores.executeUnique ();
		long sucursalesDelete = (long) qSucursales.executeUnique ();
		long supermercadoDelete = (long) qSupermercado.executeUnique ();
        long localDeVentasDelete = (long) qLocalDeVentas.executeUnique ();
        long usuarioDelete = (long) qUsuario.executeUnique ();
		

        return new long[] {productoCompraDelete,  pedidoProductosDelete, proveedoresProductosDelete, 
        		sucursalesProductosDelete, productosEstantesDelete, productosBodegasDelete, productosDelete,
				bodegasDelete, compraDelete, promocionDelete, pedidoDelete, tipoProductoDelete, proveedoresSucursalesDelete,
				usuarioDelete, rolUsuarioDelete, clientesDelete, proveedoresDelete,
				sucursalesDelete, supermercadoDelete, localDeVentasDelete};
    }


}
