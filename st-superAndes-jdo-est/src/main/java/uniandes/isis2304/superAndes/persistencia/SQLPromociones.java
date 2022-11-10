package uniandes.isis2304.superAndes.persistencia;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Promociones;

public class SQLPromociones {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLPromociones(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }


    public long adicionarPromociones(PersistenceManager pm, long num_promocion, String tipo_promocion, Timestamp fecha_inicio, Timestamp fecha_final, int porcentaje, String estado, long proveedor, int unidadesVendidas, int unidadesDisponibles) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaPromociones() + "(num_promocion, tipo_promocion, fecha_inicio, fecha_final, porcentaje, estado, proveedor, unidadesVendidas, unidadesDisponibles) values(?,?,?,?,?,?,?)");
        q.setParameters(num_promocion, tipo_promocion, fecha_inicio, fecha_final, porcentaje, estado, proveedor, unidadesVendidas, unidadesDisponibles);
        return (long) q.executeUnique();
    }

    public long eliminarPromociones(PersistenceManager pm,long num_promocion){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaPromociones() + " where num_promocion = ?");
        q.setParameters(num_promocion);
        return (long) q.executeUnique();
    }

    public Promociones darPromociones(PersistenceManager pm,long num_promocion){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaPromociones() + "where num_promocion = ?");
        q.setResultClass(Promociones.class);
        q.setParameters(num_promocion);
        return (Promociones) q.executeUnique();
    }

    public List <Promociones> darPromociones(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaPromociones());
        q.setResultClass(Promociones.class);
        return (List<Promociones>) q.executeList();
}


    public long finalizarPromocion(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaPromociones  () + " SET estado = 'FINALIZADA' WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique(); 
    }

    public long actualizarUnidadesVendidas (PersistenceManager pm, long unidadesVendidas, long id)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaPromociones  () + " SET unidadesVendidas = ? WHERE id = ?");
        q.setParameters(unidadesVendidas, id);
        return (long) q.executeUnique();            
	}

	public long actualizarUnidadesDisponibles (PersistenceManager pm, long unidadesDisponibles, long id)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaPromociones  () + " SET unidadesDisponibles = ? WHERE id = ?");
        q.setParameters(unidadesDisponibles, id);
        return (long) q.executeUnique();            
	}
}
