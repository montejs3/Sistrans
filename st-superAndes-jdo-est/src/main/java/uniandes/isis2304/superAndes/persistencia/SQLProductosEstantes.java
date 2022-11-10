package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductosEstantes;

public class SQLProductosEstantes {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLProductosEstantes(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarProductosEstantes(PersistenceManager pm, long idProducto, long idEstantes) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductosEstantes () + "(idProducto, idEstantes) values (?, ?)");
        q.setParameters(idProducto, idEstantes);
        return (long) q.executeUnique();
	}

	
	public long eliminarProductosEstantes (PersistenceManager pm, long idProducto, long idEstantes)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosEstantes () + " WHERE idProducto = ? AND idEstantes = ?");
        q.setParameters(idProducto, idEstantes);
        return (long) q.executeUnique();
	}

	
	public List<ProductosEstantes> darProductosEstantes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEstantes ()+ " WHERE idProducto = ?");
		q.setResultClass(ProductosEstantes.class);
		return (List<ProductosEstantes>) q.executeList();
	}
}
