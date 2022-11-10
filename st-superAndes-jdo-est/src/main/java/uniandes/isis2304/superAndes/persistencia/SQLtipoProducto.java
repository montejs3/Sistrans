package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.tipoProducto;

public class SQLtipoProducto {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLtipoProducto(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionartipoProducto(PersistenceManager pm, long id, String tipo, String categoria) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablatipoProducto() + "(id, tipo, categoria) values(?,?,?)");
        q.setParameters(id, tipo, categoria);
        return (long) q.executeUnique();
    }

    public long eliminartipoProducto(PersistenceManager pm,long id){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablatipoProducto() + " where id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    public tipoProducto dartipoProducto(PersistenceManager pm,long id){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablatipoProducto() + "where id = ?");
        q.setResultClass(tipoProducto.class);
        q.setParameters(id);
        return (tipoProducto) q.executeUnique();
    }

    public List <tipoProducto> dartipoProductos(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablatipoProducto());
        q.setResultClass(tipoProducto.class);
        return (List<tipoProducto>) q.executeList();
}
}
