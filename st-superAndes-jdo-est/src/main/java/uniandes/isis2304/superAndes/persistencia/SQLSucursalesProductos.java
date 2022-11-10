package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.SucursalesProductos;

public class SQLSucursalesProductos {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLSucursalesProductos(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarSucursalesProductos(PersistenceManager pm, long idSucursal, long idProducto, long existenciasEnEstante, long existenciasenBodega) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaSucursalesProductos () + "(idSucursal, idProducto, existenciasEnEstante, existenciasenBodega) values (?, ?, ?, ?)");
        q.setParameters(idSucursal, idProducto, existenciasEnEstante, existenciasenBodega);
        return (long) q.executeUnique();
	}

	
	public long eliminarSucursalesProductos (PersistenceManager pm, long idSucursal, long idProducto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursalesProductos () + " WHERE idSucursal = ? AND idProducto = ?");
        q.setParameters(idSucursal, idProducto);
        return (long) q.executeUnique();
	}

	
	public List<SucursalesProductos> darSucursalesProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursalesProductos ()+ " WHERE idSucursal = ?");
		q.setResultClass(SucursalesProductos.class);
		return (List<SucursalesProductos>) q.executeList();
	}

	public long actualizarExistenciasBodega(PersistenceManager pm, long codigoDeBarras, long existenciasenBodegas, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaSucursalesProductos  () + " SET existenciasenBodega = ? WHERE idProducto = ? AND idSucursal = ?");
        q.setParameters(existenciasenBodegas, codigoDeBarras, idSucursal);
        return (long) q.executeUnique();  
	}

	public long actualizarExistenciasEstante(PersistenceManager pm, long codigoDeBarras, long existenciasEnEstantes, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaSucursalesProductos  () + " SET existenciasEnEstante = ? WHERE idProducto = ? AND idSucursal = ?");
        q.setParameters(existenciasEnEstantes, codigoDeBarras, idSucursal);
        return (long) q.executeUnique();  
	}

	public SucursalesProductos darSucursalesProductosPorSucursalYProducto (PersistenceManager pm, long codigoDeBarras, long idSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursalesProductos  () + " WHERE idProducto = ? AND idSucursal = ?");
		q.setResultClass(SucursalesProductos.class);
		q.setParameters(codigoDeBarras, idSucursal);
		return (SucursalesProductos) q.executeUnique();
	}

	public List<SucursalesProductos> darSucursalesProductosPorSucursal (PersistenceManager pm, long idSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursalesProductos  () + " WHERE idSucursal = ?");
		q.setResultClass(SucursalesProductos.class);
		q.setParameters(idSucursal);
		return (List<SucursalesProductos>) q.executeList();
	}
}
