package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProveedoresProductos;

public class SQLProveedoresProductos {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLProveedoresProductos(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarProveedoresProductos(PersistenceManager pm, long idProveedor, long idProducto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProveedoresProductos () + "(idProveedor, idProducto) values (?, ?)");
        q.setParameters(idProveedor, idProducto);
        return (long) q.executeUnique();
	}

	
	public long eliminarProveedoresProductos (PersistenceManager pm, long idProveedor, long idProducto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedoresProductos () + " WHERE idProveedor = ? AND idProducto = ?");
        q.setParameters(idProveedor, idProducto);
        return (long) q.executeUnique();
	}

	
	public List<ProveedoresProductos> darProveedoresProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProveedoresProductos ()+ " WHERE idProveedor = ?");
		q.setResultClass(ProveedoresProductos.class);
		return (List<ProveedoresProductos>) q.executeList();
	}
}
