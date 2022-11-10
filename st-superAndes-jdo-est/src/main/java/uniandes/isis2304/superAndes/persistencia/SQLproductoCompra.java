package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.productoCompra;

public class SQLproductoCompra {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLproductoCompra(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarproductoCompra(PersistenceManager pm, int cantidadProducto, long compra, long producto, long promocion) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaproductoCompra() + "(cantidadProducto, compra, producto, promocion) values(?,?,?,?)");
        q.setParameters(producto);
        return (long) q.executeUnique();
    }

    public long eliminarproductoCompra(PersistenceManager pm,long producto){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaproductoCompra() + " where producto = ?");
        q.setParameters(producto);
        return (long) q.executeUnique();
    }

    public List <productoCompra> darproductoCompraPorProducto(PersistenceManager pm,long producto){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaproductoCompra() + "where producto = ?");
        q.setResultClass(productoCompra.class);
        q.setParameters(producto);
        return (List <productoCompra>) q.executeList();
    }

    public List <productoCompra> darproductoCompras(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaproductoCompra());
        q.setResultClass(productoCompra.class);
        return (List<productoCompra>) q.executeList();
    }
}
