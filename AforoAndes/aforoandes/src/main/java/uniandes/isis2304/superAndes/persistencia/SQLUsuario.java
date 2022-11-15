
package uniandes.isis2304.superAndes.persistencia;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;


class SQLUsuario
{

	private final static String SQL = PersistenciaSuperAndes.SQL;

	
	private PersistenciaSuperAndes pp;


	public  SQLUsuario (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarUsuario (PersistenceManager pm,int  cedula , String nombre , String tipo, String NOMBRESUPERMERCADO) 
	{
		
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario  () + "(nombre, cedula,tipo,NOMBRESUPERMERCADO ) values (?, ?, ?, ?)");
        q.setParameters(nombre,cedula,tipo,NOMBRESUPERMERCADO);
        System.out.println("Hpola");
        return (long) q.executeUnique();            
	}
	
	


	

}