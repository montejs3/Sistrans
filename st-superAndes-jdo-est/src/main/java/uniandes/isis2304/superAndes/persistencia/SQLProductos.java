package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Productos;

class SQLProductos {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLProductos(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }
    
    public long adicionarProducto(PersistenceManager pm, String nombre, String marca, long precio_unitario, String presentacion
    , int cantidad_presentacion, int medida, String empacado, long codigo_barras, int existenciasbodega, int existenciasestante,
    long tipoProducto, long categoria, Timestamp fechaVencimiento) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaProductos() + "(nombre, marca, precio_unitario, preesentacion, cantidad_presentacion, medida, empacado, codigo_barras, existenciasbodega, existenciasestante, tipoProducto, categoria, fechaVencimeinto) values (?,?,?,?,?,?,?,?,?,?,?,?)");
        q.setParameters(nombre,marca,precio_unitario,presentacion,cantidad_presentacion,medida,empacado,codigo_barras, existenciasbodega, existenciasbodega,existenciasestante,tipoProducto, categoria, fechaVencimiento);
        return (long) q.executeUnique();
    }

    public long eliminarProducto(PersistenceManager pm,long codigo_barras){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaProductos() + " where codigo_barras = ?");
        q.setParameters(codigo_barras);
        return (long) q.executeUnique();
    }

    public Productos darProducto(PersistenceManager pm,long codigo_barras){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaProductos() + "where codigo_barras = ?");
        q.setResultClass(Productos.class);
        q.setParameters(codigo_barras);
        return (Productos) q.executeUnique();
    }

    public List<Productos> darProductos(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaProductos());
        q.setResultClass(Productos.class);
        return (List<Productos>) q.executeList();
    }

    public long actualizarExistenciasBodega(PersistenceManager pm, long codigoDeBarras, long existenciasBodega)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductos  () + " SET existenciasBodega = ? WHERE codigoDeBarras = ?");
        q.setParameters(existenciasBodega, codigoDeBarras);
        return (long) q.executeUnique();  
	}
}