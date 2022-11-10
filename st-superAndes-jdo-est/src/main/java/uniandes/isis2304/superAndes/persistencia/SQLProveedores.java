package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveedores;

public class SQLProveedores {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLProveedores(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarProveedor(PersistenceManager pm, long nit, String nombre, int calidad, boolean esExclusivo) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaProveedores() + "(nit, nombre, calidad, esExclusivo) values(?,?,?,?)");
        q.setParameters(nit, nombre, calidad, esExclusivo);
        return (long) q.executeUnique();
    }

    public long eliminarProveedor(PersistenceManager pm,long nit){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaProveedores() + " where nit = ?");
        q.setParameters(nit);
        return (long) q.executeUnique();
    }

    public Proveedores darProveedor(PersistenceManager pm,long nit){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaProveedores() + "where nit = ?");
        q.setResultClass(Proveedores.class);
        q.setParameters(nit);
        return (Proveedores) q.executeUnique();
    }

    public List <Proveedores> darProveedores(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaProveedores());
        q.setResultClass(Proveedores.class);
        return (List<Proveedores>) q.executeList();
}
}