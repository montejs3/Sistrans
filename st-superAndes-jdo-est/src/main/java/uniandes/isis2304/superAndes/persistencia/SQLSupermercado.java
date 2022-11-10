package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Supermercado;

public class SQLSupermercado {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLSupermercado(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarSupermercado(PersistenceManager pm, long id, String nombre) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaSupermercado() + "(id, nombre) values(?,?)");
        q.setParameters(id, nombre);
        return (long) q.executeUnique();
    }

    public long eliminarSupermercado(PersistenceManager pm,long id){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaSupermercado() + " where id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    public Supermercado darSupermercado(PersistenceManager pm,long id){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaSupermercado() + "where id = ?");
        q.setResultClass(Supermercado.class);
        q.setParameters(id);
        return (Supermercado) q.executeUnique();
    }

    public List <Supermercado> darSupermercados(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaSupermercado());
        q.setResultClass(Supermercado.class);
        return (List<Supermercado>) q.executeList();
}
}
