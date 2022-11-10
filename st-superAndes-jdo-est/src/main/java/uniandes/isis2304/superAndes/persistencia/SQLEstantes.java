package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Estantes;

public class SQLEstantes {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLEstantes(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarEstantes(PersistenceManager pm, long id_estantes, int cant_productos, long tipoProducto, double capacidad, int ocupacion, long sucursal) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaEstantes() + "(id_estantes, cant_productos, tipoProducto, capacidad, ocupacion, sucursal) values(?,?,?,?)");
        q.setParameters(id_estantes, cant_productos, tipoProducto, capacidad, ocupacion, sucursal);
        return (long) q.executeUnique();
    }

    public long eliminarEstantes(PersistenceManager pm,long id_estantes){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaEstantes() + " where id_estantes = ?");
        q.setParameters(id_estantes);
        return (long) q.executeUnique();
    }

    public Estantes darEstante(PersistenceManager pm,long id_estantes){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaEstantes() + "where id_estantes = ?");
        q.setResultClass(Estantes.class);
        q.setParameters(id_estantes);
        return (Estantes) q.executeUnique();
    }

    public List<Estantes> darEstantes(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaEstantes());
        q.setResultClass(Estantes.class);
        return (List <Estantes>) q.executeList();
    }
}
