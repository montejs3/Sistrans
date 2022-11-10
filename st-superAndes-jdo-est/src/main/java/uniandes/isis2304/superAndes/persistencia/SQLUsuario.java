package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Usuario;
import uniandes.isis2304.superAndes.negocio.VORolUsuario;
import uniandes.isis2304.superAndes.negocio.VOSucursales;

public class SQLUsuario {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLUsuario(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarUsuario(PersistenceManager pm, long id, String tipoIdentificacion, String nombre, String correo_electronico, String palabra_clave, VORolUsuario rolNuevoUsuario, VOSucursales sucuNuevoUsuario) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaUsuario() + "(id, tipoIdentificacion, nombre, correo_electronico, palabra_clave, rolUsuario,idSucursal) values(?,?,?,?,?,?,?)");
        q.setParameters(id, tipoIdentificacion, nombre, correo_electronico, palabra_clave, rolNuevoUsuario, sucuNuevoUsuario);
        return (long) q.executeUnique();
    }

    public long eliminarUsuario(PersistenceManager pm,long id){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaUsuario() + " where id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    public Usuario darUsuario(PersistenceManager pm,long id){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaUsuario() + "where id = ?");
        q.setResultClass(Usuario.class);
        q.setParameters(id);
        return (Usuario) q.executeUnique();
    }

    public List <Usuario> darUsuario(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaUsuario());
        q.setResultClass(Usuario.class);
        return (List<Usuario>) q.executeList();
    }
}
