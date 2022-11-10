package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductosBodegas;

public class SQLProductosBodegas {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLProductosBodegas(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarProductosBodegas(PersistenceManager pm, long idProducto, long idBodega) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductosBodegas () + "(idProducto, idBodega) values (?, ?)");
        q.setParameters(idProducto, idBodega);
        return (long) q.executeUnique();
	}

	
	public long eliminarProductosBodegas (PersistenceManager pm, long idProducto, long idBodega)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosBodegas () + " WHERE idProducto = ? AND idBodega = ?");
        q.setParameters(idProducto, idBodega);
        return (long) q.executeUnique();
	}

	
	public List<ProductosBodegas> darProductosBodegas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosBodegas ()+ " WHERE idProducto = ?");
		q.setResultClass(ProductosBodegas.class);
		return (List<ProductosBodegas>) q.executeList();
	}
}
