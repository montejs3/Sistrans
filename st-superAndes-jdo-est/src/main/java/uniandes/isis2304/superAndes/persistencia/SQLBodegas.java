package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Bodegas;

class SQLBodegas {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLBodegas(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarBodega(PersistenceManager pm, long id_bodega, double capacidad, int ocupacion, long tipoProducto, long sucursal) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaBodegas() + "(id_bodega, capacidad, ocupacion, tipoProducto, sucursal) values(?,?,?,?,?)");
        q.setParameters(id_bodega, capacidad, ocupacion, tipoProducto, sucursal);
        return (long) q.executeUnique();
    }

    public long eliminarBodega(PersistenceManager pm,long id_bodega){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaBodegas() + " where id_bodega = ?");
        q.setParameters(id_bodega);
        return (long) q.executeUnique();
    }

    public Bodegas darBodega(PersistenceManager pm,long id_bodega){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaBodegas() + "where id_bodega = ?");
        q.setResultClass(Bodegas.class);
        q.setParameters(id_bodega);
        return (Bodegas) q.executeUnique();
    }

    public List<Bodegas> darBodegas(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaBodegas());
        q.setResultClass(Bodegas.class);
        return (List <Bodegas>) q.executeUnique();
    }

}
