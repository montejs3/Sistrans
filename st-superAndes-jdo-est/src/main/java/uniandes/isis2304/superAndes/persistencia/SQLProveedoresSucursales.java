package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProveedoresSucursales;

public class SQLProveedoresSucursales {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLProveedoresSucursales(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarProveedoresSucursales(PersistenceManager pm, long idProveedor, long idSucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProveedoresSucursales () + "(idProveedor, idSucursal) values (?, ?)");
        q.setParameters(idProveedor, idSucursal);
        return (long) q.executeUnique();
	}

	
	public long eliminarProveedoresSucursales (PersistenceManager pm, long idProveedor, long idSucursal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedoresSucursales () + " WHERE idProveedor = ? AND idSucursal = ?");
        q.setParameters(idProveedor, idSucursal);
        return (long) q.executeUnique();
	}

	
	public List<ProveedoresSucursales> darProveedoresSucursales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProveedoresSucursales ()+ " WHERE idProveedor = ?");
		q.setResultClass(ProveedoresSucursales.class);
		return (List<ProveedoresSucursales>) q.executeList();
	}
}
