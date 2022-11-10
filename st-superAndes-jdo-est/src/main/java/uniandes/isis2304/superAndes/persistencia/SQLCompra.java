package uniandes.isis2304.superAndes.persistencia;


import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Compra;

class SQLCompra {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLCompra(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarCompra(PersistenceManager pm, long id, int precioCompra, Timestamp fecha, long cliente) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaCompra() + "(id, precioCompra, fecha, cliente) values(?,?,?,?)");
        q.setParameters(id, precioCompra, fecha, cliente);
        return (long) q.executeUnique();
    }

    public long eliminarCompra(PersistenceManager pm,long id){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaCompra() + " where id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    public Compra darCompra(PersistenceManager pm,long id){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaCompra() + "where id = ?");
        q.setResultClass(Compra.class);
        q.setParameters(id);
        return (Compra) q.executeUnique();
    }

    public List<Compra> darCompras(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaCompra());
        q.setResultClass(Compra.class);
        return (List<Compra>) q.executeList();
}
}
