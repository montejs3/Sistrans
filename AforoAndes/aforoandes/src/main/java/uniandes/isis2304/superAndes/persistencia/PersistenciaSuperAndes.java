
package uniandes.isis2304.superAndes.persistencia;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.negocio.Carrito;

import uniandes.isis2304.superAndes.negocio.Productos;
import uniandes.isis2304.superAndes.negocio.ProductosCarrito;
import uniandes.isis2304.superAndes.negocio.RolUsuario;
import uniandes.isis2304.superAndes.negocio.Sucursal;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.Usuario;
import uniandes.isis2304.superAndes.negocio.VOProductosCarrito;
import uniandes.isis2304.superAndes.negocio.VORolUsuario;
import uniandes.isis2304.superAndes.negocio.VOSucursal;


public class PersistenciaSuperAndes
{
	
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());
	

	public final static String SQL = "javax.jdo.query.SQL";


	private static PersistenciaSuperAndes instance;
	

	private PersistenceManagerFactory pmf;
	

	private List <String> tablas;
	
	
	private SQLUtil sqlUtil;
	
	/**
	 * Atributo para el acceso a la tabla TIPOBEBIDA de la base de datos
	 */
	private SQLUsuario sqlUsuario;

	private SQLCarrito sqlCarrito;

	private SQLProductos sqlProductos;

	private SQLSucursal sqlSucursal;

	private SQLRolUsuario sqlRolUsuario;

	private SQLCarritoCompra sqlCarritoCompra;


	
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaSuperAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Parranderos");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("AforoAndes_sequence");
		tablas.add ("SUPERMERCADO");
		tablas.add ("TIPO_PRODUCTO");
		tablas.add ("PRODUCTOS");
		tablas.add ("SUCURSALES");
		tablas.add ("BODEGAS");
		tablas.add ("LOCAL_DE_VENTAS");
		tablas.add ("ESTANTES");
		tablas.add ("PRODUCTOS_PERECEDEROS");
		tablas.add ("ABARROTES");
		tablas.add ("PROVEEDORES");
		tablas.add ("PEDIDOS");
		tablas.add ("PROMOCIONES");
		tablas.add ("CLIENTES");
		tablas.add ("COMPRA");
		tablas.add ("PRODUCTO_COMPRA");
		tablas.add ("PERSONA_NATURAL");
		tablas.add ("EMPRESA");
		tablas.add ("USUARIO");
		tablas.add ("PEDIDOS_PRODUCTOS");
		tablas.add ("PROVEEDORES_PRODUCTOS");
		tablas.add ("SUCURSALES_PEDIDOS");
		tablas.add ("SUCURSALES_PRODUCTOS");
		tablas.add ("PRODUCTOS_ESTANTES");
		tablas.add ("PRODUCTOS_BODEGAS");
		tablas.add ("PROVEEDORES_SUCURSALES");
		tablas.add ("BODEGAS_TIPO_PRODUCTO");
		tablas.add ("CARRITO");
		tablas.add ("CARRITO_COMPRA");

}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaSuperAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		
		sqlUtil = new SQLUtil(this);
		sqlUsuario = new SQLUsuario(this);
		sqlCarrito = new SQLCarrito(this);
		sqlProductos = new SQLProductos(this) ;
		sqlSucursal= new SQLSucursal(this);
	    sqlRolUsuario = new SQLRolUsuario(this);
		sqlCarritoCompra = new SQLCarritoCompra(this);
	}

	
	public String darSeqParranderos ()
	{
		return tablas.get (0);
	}

	public String darTablaUsuario ()
	{
		return tablas.get (1);
	}

	
	public String darTablaCarrito ()
	{
		return tablas.get (2);
	}

	
	public String darTablaProducto ()
	{
		return tablas.get (3);
	}

	public String darTablaSucursal ()
	{
		return tablas.get (4);
	}

	
	public String darTablaRolUsuario ()
	{
		return tablas.get (5);
	}

	
	

	
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE BEBIDA
	 *****************************************************************/


	
	public Usuario adicionarEmpleado(String nombre,int cedula,String tipo,String supermercado)
	{
	
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
    
            long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, cedula, nombre,tipo,supermercado);
            tx.commit();
            
            log.trace ("Inserción de empleado " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario ();
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el nombre del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarParranderos ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarParranderos (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}

    public Usuario adicionarUsuario(long id, String tipoIdentificacion, String nombre, String correo_electronico,
            String palabra_clave, VORolUsuario rolNuevoUsuario, VOSucursal sucuNuevoUsuario) {
        return null;
    }

    public Carrito darCarritoPorId(long id) {
        return null;
    }

    public Productos productoPorCodigo(long id) {
        return null;
    }

    public int actualizarExistenciasEstante(long codigoBarras, int nuevasExist) {
        return 0;
    }

    public ProductosCarrito adicionarProductoCarrito(long idCarrito, long codigoBarras, String cantidad) {
        return null;
    }

    public int carritoProductosPorIdCarrito(long idCarrito, long codigoBarras) {
        return 0;
    }

    public ProductosCarrito eliminarProductoCarrito(long idCarrito, long codigoBarras) {
        return null;
    }

    public ProductosCarrito actualizarProductoCarrito(long idCarrito, long codigoBarras, int productosCarritoAhora) {
        return null;
    }

    public VOProductosCarrito[] darProductosCarrito(long idCarrito) {
        return null;
    }

    public int darTotalCarrito(long idCarrito) {
        return 0;
    }

    public Carrito dejarCarrito(long idCarrito) {
        return null;
    }

    public Sucursal adicionarSucursal(String ciudad, String direccion, String nombre, int id_sucursal,
            int nivel_reorden, int recompra, int id_supermercado) {
        return null;
    }
	

 }
