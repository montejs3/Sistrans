package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Pedidos;

public class SQLPedidos {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLPedidos(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarPedido(PersistenceManager pm, long id_pedido, String productos, double volumenes, double precio, Timestamp fecha) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaPedidos() + "(id_pedido, productos, volumenes, precio, fecha) values(?,?,?,?,?)");
        q.setParameters(id_pedido);
        return (long) q.executeUnique();
    }

    public long eliminarPedido(PersistenceManager pm,long id_pedido){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaPedidos() + " where id_pedido = ?");
        q.setParameters(id_pedido);
        return (long) q.executeUnique();
    }

    public Pedidos darPedido(PersistenceManager pm,long id_pedido){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaPedidos() + "where id_pedido = ?");
        q.setResultClass(Pedidos.class);
        q.setParameters(id_pedido);
        return (Pedidos) q.executeUnique();
    }

    public List <Pedidos> darPedidos(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaPedidos());
        q.setResultClass(Pedidos.class);
        return (List<Pedidos>) q.executeList();
    }

    public long llegadaPedido(PersistenceManager pm, long id, Timestamp fecha, long volumenes) {
                Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaPedidos  () + " SET  fecha = ?, volumenes = ? WHERE id = ?");
                q.setParameters(fecha, volumenes, id);
                return (long) q.executeUnique(); 
    }
}
