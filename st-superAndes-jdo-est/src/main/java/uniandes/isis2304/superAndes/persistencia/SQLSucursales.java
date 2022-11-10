package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Sucursales;

public class SQLSucursales {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLSucursales(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarSucursal(PersistenceManager pm, String ciudad, String direccion, String nombre, long id_sucursal, int nivel_reorden, int recompra, long id_supermercado) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaSucursales() + "(ciudad, direccion, nombre, id_sucursal, nivel_reorden, recompra, id_supermercado) values(?,?,?,?,?,?,?)");
        q.setParameters(ciudad, direccion, nombre, id_sucursal, nivel_reorden, recompra, id_supermercado);
        return (long) q.executeUnique();
    }

    public long eliminarSucursal(PersistenceManager pm,long id_sucursal){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaSucursales() + " where id_sucursal = ?");
        q.setParameters(id_sucursal);
        return (long) q.executeUnique();
    }

    public Sucursales darSucursal(PersistenceManager pm,long id_sucursal){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaSucursales() + "where id_sucursal = ?");
        q.setResultClass(Sucursales.class);
        q.setParameters(id_sucursal);
        return (Sucursales) q.executeUnique();
    }

    public List <Sucursales> darSucursales(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaSucursales());
        q.setResultClass(Sucursales.class);
        return (List<Sucursales>) q.executeList();
}
}
