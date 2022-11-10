package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Clientes;

class SQLClientes {
    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes psa;

    public SQLClientes(PersistenciaSuperAndes psa) 
    {
        this.psa = psa;
    }

    public long adicionarCliente(PersistenceManager pm, long id, String nombre, String correo, String direccion, long id_supermercado, long compra, String tipo) {
        Query q = pm.newQuery(SQL, "insert into" + psa.darTablaClientes() + "(id, nombre, correo, direccion, id_supermercado, compra, tipo) values(?,?,?,?,?,?,?)");
        q.setParameters(id, nombre, correo, direccion, id_supermercado, compra, tipo);
        return (long) q.executeUnique();
    }

    public long eliminarCliente(PersistenceManager pm,long id){
        Query q = pm.newQuery(SQL, "delete from" + psa.darTablaClientes() + " where id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    public Clientes darCliente(PersistenceManager pm,long id){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaClientes() + "where id = ?");
        q.setResultClass(Clientes.class);
        q.setParameters(id);
        return (Clientes) q.executeUnique();
    }

    public List <Clientes> darClientes(PersistenceManager pm){
        Query q = pm.newQuery(SQL, "select * from" + psa.darTablaClientes());
        q.setResultClass(Clientes.class);
        return (List <Clientes>) q.executeList();
    }
}
