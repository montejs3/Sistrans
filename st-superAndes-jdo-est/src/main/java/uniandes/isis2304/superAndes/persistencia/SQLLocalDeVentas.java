package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.LocalDeVentas;

public class SQLLocalDeVentas {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLLocalDeVentas(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarLocalDeVentas(PersistenceManager pm, long id_local, long idSucursal) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaLocalDeVentas() + "(id_local,idSucursal) values(?,?)");
        q.setParameters(id_local);
        return (long) q.executeUnique();
    }

    public long eliminarLocalDeVentas(PersistenceManager pm,long id_local){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaLocalDeVentas() + " where id_local = ?");
        q.setParameters(id_local);
        return (long) q.executeUnique();
    }

    public LocalDeVentas darLocalDeVentas(PersistenceManager pm,long id_local){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaLocalDeVentas() + "where id_local = ?");
        q.setResultClass(LocalDeVentas.class);
        q.setParameters(id_local);
        return (LocalDeVentas) q.executeUnique();
    }

    public List <LocalDeVentas> darLocalesDeVentas(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaLocalDeVentas());
        q.setResultClass(LocalDeVentas.class);
        return (List<LocalDeVentas>) q.executeList();
    }
}
