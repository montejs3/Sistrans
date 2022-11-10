package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PedidosProductos;

public class SQLPedidosProductos {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLPedidosProductos(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

	public long adicionarPedidosProductos(PersistenceManager pm, long idPedido, long idProducto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPedidosProductos () + "(idPedido, idProducto) values (?, ?)");
        q.setParameters(idPedido, idProducto);
        return (long) q.executeUnique();
	}

	public long eliminarPedidosProductos (PersistenceManager pm, long idPedido, long productoCodigoDeBarras)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPedidosProductos () + " WHERE idPedido = ? AND productocodigodebarras = ?");
        q.setParameters(idPedido, productoCodigoDeBarras);
        return (long) q.executeUnique();
	}

	public List<PedidosProductos> darPedidosProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPedidosProductos  ());
		q.setResultClass(PedidosProductos.class);
		return (List<PedidosProductos>) q.executeList();
	}

	public PedidosProductos darPedidosProductosPorId (PersistenceManager pm, long idPedido) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPedidosProductos  () + " WHERE idPedido = ?");
		q.setResultClass(PedidosProductos.class);
		q.setParameters(idPedido);
		return (PedidosProductos) q.executeUnique();
	}

}
