package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.RolUsuario;

public class SQLRolUsuario {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLRolUsuario(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarRolUsuario(PersistenceManager pm, long idProducto, String nombre) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaRolUsuario() + "(idProducto, nombre) values(?,?)");
        q.setParameters(idProducto, nombre);
        return (long) q.executeUnique();
    }

    public long eliminarRolUsuario(PersistenceManager pm,long idProducto){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaRolUsuario() + " where idProducto = ?");
        q.setParameters(idProducto);
        return (long) q.executeUnique();
    }

    public RolUsuario darRolUsuario(PersistenceManager pm,long idProducto){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaRolUsuario() + "where idProducto = ?");
        q.setResultClass(RolUsuario.class);
        q.setParameters(idProducto);
        return (RolUsuario) q.executeUnique();
    }

    public List <RolUsuario> darRolesUsuario(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaRolUsuario());
        q.setResultClass(RolUsuario.class);
        return (List<RolUsuario>) q.executeList();
}
}
