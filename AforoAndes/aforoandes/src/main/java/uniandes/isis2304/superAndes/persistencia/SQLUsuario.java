/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
/**
 *   import uniandes.isis2304.superAndes.negocio.TipoBebida;

 */

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