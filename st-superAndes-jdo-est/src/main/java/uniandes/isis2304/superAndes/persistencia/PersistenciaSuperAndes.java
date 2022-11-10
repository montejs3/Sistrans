/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: 
 *SuperAndes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

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


import uniandes.isis2304.superAndes.negocio.Bodegas;
import uniandes.isis2304.superAndes.negocio.Clientes;
import uniandes.isis2304.superAndes.negocio.Compra;
import uniandes.isis2304.superAndes.negocio.Estantes;
import uniandes.isis2304.superAndes.negocio.LocalDeVentas;
import uniandes.isis2304.superAndes.negocio.Pedidos;
import uniandes.isis2304.superAndes.negocio.PedidosProductos;
import uniandes.isis2304.superAndes.negocio.Productos;
import uniandes.isis2304.superAndes.negocio.ProductosBodegas;
import uniandes.isis2304.superAndes.negocio.ProductosEstantes;
import uniandes.isis2304.superAndes.negocio.Promociones;
import uniandes.isis2304.superAndes.negocio.Proveedores;
import uniandes.isis2304.superAndes.negocio.ProveedoresProductos;
import uniandes.isis2304.superAndes.negocio.ProveedoresSucursales;
import uniandes.isis2304.superAndes.negocio.RolUsuario;
import uniandes.isis2304.superAndes.negocio.Sucursales;
import uniandes.isis2304.superAndes.negocio.SucursalesProductos;
import uniandes.isis2304.superAndes.negocio.Supermercado;
import uniandes.isis2304.superAndes.negocio.Usuario;
import uniandes.isis2304.superAndes.negocio.VORolUsuario;
import uniandes.isis2304.superAndes.negocio.VOSucursales;
import uniandes.isis2304.superAndes.negocio.productoCompra;
import uniandes.isis2304.superAndes.negocio.tipoProducto;


/**
 * Clase para el manejador de persistencia del proyecto 
 *SuperAndes
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLBodegas y SQLVisitan, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author Germán Bravo
 */
public class PersistenciaSuperAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaSuperAndes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, Bodegas, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a Persistencia
	 *SuperAndes
	 */
	private SQLUtil sqlUtil;

	private SQLBodegas sqlBodegas;
	private SQLClientes sqlClientes;
	private SQLCompra sqlCompra;
	private SQLEstantes sqlEstantes;
	private SQLLocalDeVentas sqlLocalDeVentas;
	private SQLPedidos sqlPedidos;
	private SQLproductoCompra sqlproductoCompra;
	private SQLProductos sqlProductos;
	private SQLPromociones sqlPromociones;
	private SQLProveedores sqlProveedores;
	private SQLRolUsuario sqlRolUsuario;
	private SQLSucursales sqlSucursales;
	private SQLSupermercado sqlSupermercado;
	private SQLtipoProducto sqltipoProducto;
	private SQLUsuario sqlUsuario;
	private SQLPedidosProductos sqlPedidosProductos;
	private SQLSucursalesProductos sqlSucursalesProductos;
	private SQLProveedoresProductos sqlProveedoresProductos;
	private SQLProductosEstantes sqlProductosEstantes;
	private SQLProductosBodegas sqlProductosBodegas;
	private SQLProveedoresSucursales sqlProveedoresSucursales;
	
	

	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaSuperAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de 

		tablas = new LinkedList<String> ();
		tablas.add ("SuperAndes_sequence");
		tablas.add ("BODEGAS");
		tablas.add ("CLIENTES");
		tablas.add ("ESTANTES");
		tablas.add ("LOCALDEVENTAS");
		tablas.add ("PEDIDOS");
		tablas.add ("PRODUCTOCOMPRA");
		tablas.add ("PRODUCTOS");
		tablas.add ("PROMOCIONES");
		tablas.add ("PROVEEDORES");
		tablas.add ("ROLUSUARIO");
		tablas.add ("SUCURSALES");
		tablas.add ("SUPERMERCADO");
		tablas.add ("TIPOPRODUCTO");
		tablas.add ("USUARIO");
		tablas.add ("PEDIDOSPRODUCTOS");
		tablas.add ("SUCURSALESPRODUCTOS");
		tablas.add ("PROOVEDORESPRODUCTOS");
		tablas.add ("PRODUCTOSESTANTES");
		tablas.add ("PRODUCTOSBODEGAS");
		tablas.add ("PROVEEDORESSUCURSALES");
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
	 * @return Retorna el único objeto Persistencia
	 *SuperAndes existente - Patrón SINGLETON
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
	 * @return Retorna el único objeto Persistencia
	 *SuperAndes existente - Patrón SINGLETON
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
		sqlBodegas = new SQLBodegas(this);
		sqlClientes = new SQLClientes(this);
		sqlCompra = new SQLCompra(this);
		sqlEstantes = new SQLEstantes(this);
		sqlLocalDeVentas = new SQLLocalDeVentas(this);
		sqlPedidos = new SQLPedidos(this);
		sqlproductoCompra = new SQLproductoCompra(this);
		sqlProductos = new SQLProductos(this);
		sqlPromociones = new SQLPromociones(this);
		sqlProveedores = new SQLProveedores(this);
		sqlRolUsuario = new SQLRolUsuario(this);
		sqlSucursales = new SQLSucursales(this);
		sqlSupermercado = new SQLSupermercado(this);
		sqltipoProducto = new SQLtipoProducto(this);
		sqlUsuario = new SQLUsuario(this);
		sqlUtil = new SQLUtil(this);
		sqlPedidosProductos = new SQLPedidosProductos(this);
		sqlSucursalesProductos = new SQLSucursalesProductos(this);
		sqlProveedoresProductos = new SQLProveedoresProductos (this);
		sqlProductosEstantes = new SQLProductosEstantes (this);
		sqlProductosBodegas = new SQLProductosBodegas (this);
		sqlProveedoresSucursales = new SQLProveedoresSucursales (this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de 
	 *SuperAndes
	 */
	public String darSeqSuperAndes ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bodegas de 
	 *SuperAndes
	 */
	public String darTablaBodegas ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebida de 
	 *SuperAndes
	 */
	public String darTablaClientes ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bar de 
	 *SuperAndes
	 */
	public String darTablaCompra ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de 
	 *SuperAndes
	 */
	public String darTablaEstantes ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de 
	 *SuperAndes
	 */
	public String darTablaLocalDeVentas ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de 
	 *SuperAndes
	 */
	public String darTablaPedidos ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de 
	 *SuperAndes
	 */
	public String darTablaproductoCompra ()
	{
		return tablas.get (7);
	}

	public String darTablaProductos ()
	{
		return tablas.get (8);
	}

	public String darTablaPromociones ()
	{
		return tablas.get (9);
	}

	public String darTablaProveedores ()
	{
		return tablas.get (10);
	}

	public String darTablaRolUsuario ()
	{
		return tablas.get (11);
	}
	
	public String darTablaSucursales ()
	{
		return tablas.get (12);
	}

	public String darTablaSupermercado ()
	{
		return tablas.get (13);
	}

	public String darTablatipoProducto ()
	{
		return tablas.get (14);
	}

	public String darTablaUsuario ()
	{
		return tablas.get (15);
	}

	public String darTablaPedidosProductos() {
		return tablas.get(16);
	}

	public String darTablaSucursalesProductos() {
		return tablas.get(17);
	}

	public String darTablaProveedoresProductos() {
		return tablas.get(18);
	}

	public String darTablaProductosEstantes() {
		return tablas.get(19);
	}
	
	public String darTablaProductosBodegas() {
		return tablas.get(20);
	}

	public String darTablaProveedoresSucursales() {
		return tablas.get(21);
	}

	/**
	 * Transacción para el generador de secuencia de 
	 *SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de 
	 *SuperAndes
	 */
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
	 * 			Métodos para manejar BODEGA
	 *****************************************************************/

	 //rf4
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Bodegas
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto Bodegas adicionado. null si ocurre alguna Excepción
	 */
	public Bodegas adicionarBodega(double capacidad, int ocupacion, long tipoProducto, long sucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id_bodega = nextval ();
            long tuplasInsertadas = sqlBodegas.adicionarBodega(pm, id_bodega, capacidad, ocupacion, tipoProducto, sucursal);
            tx.commit();
            
            log.trace ("Inserción de bodega: " + tuplasInsertadas + " tuplas insertadas");
            
            return new Bodegas (id_bodega, capacidad, ocupacion, tipoProducto, sucursal);
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
	 * Método que elimina, de manera transaccional, una tupla en la tabla Bodegas, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idBodegas - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarBodegasPorId (long id_bodega) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlBodegas.eliminarBodega(pm, id_bodega);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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
	 * Método que consulta todas las tuplas en la tabla Bodegas
	 * @return La lista de objetos Bodegas, construidos con base en las tuplas de la tabla Bodegas
	 */
	public List<Bodegas> darBodegaPorTipoProducto (long tipo_producto)
	{
		return sqlBodegas.darBodegas(pmf.getPersistenceManager());
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla Bodegas con un identificador dado
	 * @param idBodegas - El identificador del tipo de bebida
	 * @return El objeto Bodegas, construido con base en las tuplas de la tabla Bodegas con el identificador dado
	 */
	public Bodegas darBodegaPorId (long id_bodega)
	{
		return sqlBodegas.darBodega(pmf.getPersistenceManager(), id_bodega);
	}

	public List<Bodegas> darBodegas()
	{
		return sqlBodegas.darBodegas(pmf.getPersistenceManager());
	}


 
	/* ****************************************************************
	 * 			Métodos para manejar CLIENTES
	 *****************************************************************/
	//rf8
	public Clientes adicionarCliente(String nombre, String correo, String direccion, long id_supermercado, long compra, String tipo) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
			long id = nextval ();
			long tuplasInsertadas = sqlClientes.adicionarCliente(pm, id, nombre, correo, direccion, id_supermercado, compra, tipo);
			tx.commit();
			log.trace ("Inserción de cliente: " + tuplasInsertadas + " tuplas insertadas");
            
            return new Clientes(id, nombre, correo, direccion, id_supermercado, compra, tipo);
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

    public long eliminarClientePorID(long id){
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlClientes.eliminarCliente(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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

    public Clientes darClientePorID(long id){
		return sqlClientes.darCliente(pmf.getPersistenceManager(), id);
    }

    public List <Clientes> darClientes(){
		return sqlClientes.darClientes(pmf.getPersistenceManager());
	}


	/* ****************************************************************
	 * 			Métodos para manejar COMPRA
	 *****************************************************************/

	public Compra adicionarCompra(int precioCompra, Timestamp fecha, long cliente) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
			long id = nextval ();
			long tuplasInsertadas = sqlCompra.adicionarCompra(pm, id, precioCompra, fecha, cliente);
			tx.commit();
			log.trace ("Inserción de compra: " + tuplasInsertadas + " tuplas insertadas");
            
            return new Compra(id, precioCompra, fecha, cliente);
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

    public long eliminarCompraPorID(long id){
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCompra.eliminarCompra(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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

    public Compra darCompraPorID(long id){
		return sqlCompra.darCompra(pmf.getPersistenceManager(), id);
    }

    public List <Compra> darCompras(){
		return sqlCompra.darCompras(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar ESTANTES
	 *****************************************************************/

	 //rf5
	public Estantes adicionarEstantes(int cant_productos, long tipoProducto, double capacidad, int ocupacion, long sucursal) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
			long id = nextval ();
			long tuplasInsertadas = sqlEstantes.adicionarEstantes(pm, id, cant_productos, tipoProducto, capacidad, ocupacion, sucursal);
			tx.commit();
			log.trace ("Inserción de estante: " + tuplasInsertadas + " tuplas insertadas");
            
            return new Estantes(id, cant_productos, tipoProducto, capacidad, ocupacion, sucursal);
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

    public long eliminarEstantePorID(long id){
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlEstantes.eliminarEstantes(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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

    public Estantes darEstantePorID(long id){
		return sqlEstantes.darEstante(pmf.getPersistenceManager(), id);
    }

    public List <Estantes> darEstantes(){
		return sqlEstantes.darEstantes(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar LOCAL DE VENTAS
	 *****************************************************************/

	public LocalDeVentas adicionarLocalDeVentas(long idSucursal) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
			long id = nextval ();
			long tuplasInsertadas = sqlLocalDeVentas.adicionarLocalDeVentas(pm, id, idSucursal);
			tx.commit();
			log.trace ("Inserción de Local De Ventas: " + tuplasInsertadas + " tuplas insertadas");
            
            return new LocalDeVentas(idSucursal);
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

    public long eliminarLocalDeVentasPorID(long id){
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlLocalDeVentas.eliminarLocalDeVentas(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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

    public LocalDeVentas darLocalDeVentasPorID(long id){
		return sqlLocalDeVentas.darLocalDeVentas(pmf.getPersistenceManager(), id);
    }

    public List <LocalDeVentas> darLocalDeVentas(){
		return sqlLocalDeVentas.darLocalesDeVentas(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar PEDIDOS
	 *****************************************************************/

	public Pedidos adicionarPedidos(String productos, double volumenes, double precio, Timestamp fecha) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
			long id = nextval ();
			long tuplasInsertadas = sqlPedidos.adicionarPedido(pm, id, productos, volumenes, precio, fecha);
			tx.commit();
			log.trace ("Inserción de Pedido: " + tuplasInsertadas + " tuplas insertadas");
            
            return new Pedidos(id, productos, volumenes, precio, fecha);
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

    public long eliminarPedidoPorID(long id){
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPedidos.eliminarPedido(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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

    public Pedidos darPedidoPorID(long id){
		return sqlPedidos.darPedido(pmf.getPersistenceManager(), id);
    }

    public List <Pedidos> darPedidos(){
		return sqlPedidos.darPedidos(pmf.getPersistenceManager());
	}

	//RF12
	public long llegadaPedido(long id, Timestamp fecha, long volumenes)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        { 
            tx.begin();
            long resp = sqlPedidos.llegadaPedido(pm, id, fecha, volumenes);           
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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

	/* ****************************************************************
	 * 			Métodos para manejar PRODUCTO COMPRA
	 *****************************************************************/

	public productoCompra adicionarProductoCompra(int cantidadProducto, long compra, long producto, long promocion) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
			long tuplasInsertadas = sqlproductoCompra.adicionarproductoCompra(pm, cantidadProducto, compra, producto, promocion);
			tx.commit();
			log.trace ("Inserción de Compra de Producto: " + producto + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new productoCompra(cantidadProducto, compra, producto, promocion);
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

    public long eliminarproductoCompraPorProducto(long id){
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlproductoCompra.eliminarproductoCompra(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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

	//TODO: public long eliminarproductoCompraPorCompra(long id)

    public List<productoCompra> darProductoCompras()
	{
		return sqlproductoCompra.darproductoCompras(pmf.getPersistenceManager());
	}
	
	public List<productoCompra> darProductoCompraPorProducto (long producto)
	{
		return sqlproductoCompra.darproductoCompraPorProducto(pmf.getPersistenceManager(), producto);
	}
	 /* ****************************************************************
	 * 			Métodos para manejar PRODUCTOS
	 *****************************************************************/
	 
	//rf7
	public Productos adicionarProducto(String nombre, String marca, long precio_unitario, String presentacion, int cantidad_presentacion, int medida, String empacado, int existenciasbodega, int existenciasestante, long tipoProducto, long categoria, Timestamp fechaVencimiento) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
			long codigo_barras = nextval();
			long tuplasInsertadas = sqlProductos.adicionarProducto(pm, nombre, marca, precio_unitario, presentacion, cantidad_presentacion, medida, empacado, codigo_barras, existenciasbodega, existenciasestante, tipoProducto, categoria, fechaVencimiento);
			tx.commit();
			log.trace ("Inserción de Compra de Producto: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Productos(nombre, marca, precio_unitario, presentacion, cantidad_presentacion, medida, empacado, codigo_barras, existenciasbodega, existenciasestante, tipoProducto, categoria, fechaVencimiento);
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

	public long eliminarProducto(long codigo_barras) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductos.eliminarProducto(pm, codigo_barras);           
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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
	
	
	public List<Productos> darProductos()
	{
		return sqlProductos.darProductos(pmf.getPersistenceManager());
	}

	public Productos darProductoPorCodigoDeBarras (long codigo_barras)
	{
		return sqlProductos.darProducto (pmf.getPersistenceManager(), codigo_barras);
	}

	public long actualizarExistenciasBodega(long codigo_barras, long existenciasBodega)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductos.actualizarExistenciasBodega(pm, codigo_barras, existenciasBodega);           
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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


	 /* ****************************************************************
	 * 			Métodos para manejar PROMOCIONES
	 *****************************************************************/
	//rf9
	public Promociones adicionarPromocion(String tipo_promocion, Timestamp fecha_inicio, Timestamp fecha_final, int porcentaje, String estado, long proveedor, int unidadesVendidas, int unidadesDisponibles)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long num_promocion = nextval ();
            long tuplasInsertadas = sqlPromociones.adicionarPromociones(pm, num_promocion, tipo_promocion, fecha_inicio, fecha_final, porcentaje, estado, proveedor, unidadesVendidas, unidadesDisponibles);
            tx.commit();
            
            log.trace ("Inserción de Promoción: " + tuplasInsertadas + " tuplas insertadas");
            
            
            return new Promociones (num_promocion, tipo_promocion, fecha_inicio, fecha_final, porcentaje, estado, proveedor, unidadesVendidas, unidadesDisponibles);
        }
        catch (Exception e)
        {

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

	//rf10
	public long finalizarPromocion(long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromociones.finalizarPromocion(pm, id);           
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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

	public long actualizarUnidadesVendidas(long unidadesVendidas, long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromociones.actualizarUnidadesVendidas(pm, unidadesVendidas, id);           
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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

	public long actualizarUnidadesDisponibles(long unidadesDisponibles, long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromociones.actualizarUnidadesDisponibles(pm, unidadesDisponibles, id);           
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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

	 /* ****************************************************************
	 * 			Métodos para manejar PROVEEDORES
	 *****************************************************************/
	//rf6
	public Proveedores adicionarProveedor(long nit, String nombre, int calidad, boolean esExclusivo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlProveedores.adicionarProveedor(pm, nit, nombre, calidad, esExclusivo);
            tx.commit();
            
            log.trace ("Inserción de usuario: " + nit + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Proveedores (nit, nombre, calidad, esExclusivo);
        }
        catch (Exception e)
        {

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

	public long eliminarProveedorPorNit (long nit) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProveedores.eliminarProveedor(pm, nit);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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
	
	public List<Proveedores> darProveedores()
	{
		return sqlProveedores.darProveedores (pmf.getPersistenceManager());
	}
	
	public Proveedores darProveedorPorNit (long nit)
	{
		return sqlProveedores.darProveedor (pmf.getPersistenceManager(), nit);
	}

	 /* ****************************************************************
	 * 			Métodos para manejar ROLUSUARIO
	 *****************************************************************/

	 //rf1
	public RolUsuario adicionarRolUsuario(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlRolUsuario.adicionarRolUsuario(pm, id, nombre);
            tx.commit();
            
            log.trace ("Inserción de rol de usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new RolUsuario (id, nombre);
        }
        catch (Exception e)
        {

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

	public long eliminarRolUsuarioPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlRolUsuario.eliminarRolUsuario(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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
	
	
	public List<RolUsuario> darRolesUsuario ()
	{
		return sqlRolUsuario.darRolesUsuario (pmf.getPersistenceManager());
	}

	public RolUsuario darRolUsuarioPorId (long id)
	{
		return sqlRolUsuario.darRolUsuario (pmf.getPersistenceManager(), id);
	}

	 /* ****************************************************************
	 * 			Métodos para manejar SUCURSALES
	 *****************************************************************/
	//rf2
	public Sucursales adicionarSucursal( String ciudad, String direccion, String nombre, int nivel_reorden, int recompra, long supermercadoId)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSucursales.adicionarSucursal(pm, ciudad, direccion, nombre, id, nivel_reorden, recompra, supermercadoId);
            tx.commit();
            
            log.trace ("Inserción de sede: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Sucursales(ciudad, direccion, nombre, id, nivel_reorden, recompra, supermercadoId);
        }
        catch (Exception e)
        {

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

    public long eliminarSucursalPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSucursales.eliminarSucursal(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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

	public Sucursales darSucursalPorId (long id)
	{
		return sqlSucursales.darSucursal (pmf.getPersistenceManager(), id);
	}

    public List<Sucursales> darSucursales ()
	{
		return sqlSucursales.darSucursales (pmf.getPersistenceManager());
	}

	 /* ****************************************************************
	 * 			Métodos para manejar SUPERMERCADO
	 *****************************************************************/
    public Supermercado adicionarSupermercado(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSupermercado.adicionarSupermercado(pm, id, nombre);
            tx.commit();
            
            log.trace ("Inserción de supermercado: " + tuplasInsertadas + " tuplas insertadas");
            
            return new Supermercado (id, nombre);
        }
        catch (Exception e)
        {

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
	
	public long eliminarSupermercado(long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSupermercado.eliminarSupermercado(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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
	
	public Supermercado darSupermercado (long id)
	{
		return sqlSupermercado.darSupermercado (pmf.getPersistenceManager(), id);
	}

    public List<Supermercado> darSupermercados ()
	{
		return sqlSupermercado.darSupermercados (pmf.getPersistenceManager());
	}

	 /* ****************************************************************
	 * 			Métodos para manejar TIPOPRODUCTO
	 *****************************************************************/
    public tipoProducto adicionarTipoProducto(String tipo, String categoria)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqltipoProducto.adicionartipoProducto(pm, id, tipo, categoria);
            tx.commit();
            
            log.trace ("Inserción de tipoProducto: " + tipo + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new tipoProducto (id, tipo, categoria);
        }
        catch (Exception e)
        {

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
	
	public long eliminarTipoProductoPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqltipoProducto.eliminartipoProducto(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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
	
	public List<tipoProducto> darTipoProductos()
	{
		return sqltipoProducto.dartipoProductos(pmf.getPersistenceManager());
	}

	public tipoProducto darTipoProducto (long id)
	{
		return sqltipoProducto.dartipoProducto(pmf.getPersistenceManager(), id);
	}


	 /* ****************************************************************
	 * 			Métodos para manejar USUARIO
	 *****************************************************************/
	//rf3
	public Usuario adicionarUsuario(long id, String tipoIdentificacion, String nombre, String correo_electronico, String palabra_clave, VORolUsuario rolNuevoUsuario, VOSucursales sucuNuevoUsuario)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, id, tipoIdentificacion, nombre, correo_electronico, palabra_clave, rolNuevoUsuario, sucuNuevoUsuario);
            tx.commit();
            
            log.trace ("Inserción de usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario (id, tipoIdentificacion, nombre, correo_electronico, palabra_clave, rolNuevoUsuario, sucuNuevoUsuario);
        }
        catch (Exception e)
        {

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
	
	public long eliminarUsuarioPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlUsuario.eliminarUsuario(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
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
	 * Método que consulta todas las tuplas en la tabla Usuarios
	 * @return La lista de objetos Usuarios, construidos con base en las tuplas de la tabla Usuarios
	 */
	public List<Usuario> darUsuarios ()
	{
		return sqlUsuario.darUsuario(pmf.getPersistenceManager());
	}

    public Usuario darUsuarioPorId (long id)
	{
		return sqlUsuario.darUsuario(pmf.getPersistenceManager(), id);
	}

	/* ****************************************************************
	 * 			Métodos para manejar PedidosProductos
	 *****************************************************************/

	public PedidosProductos adicionarPedidosProductos(long idPedido, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlPedidosProductos.adicionarPedidosProductos (pm, idPedido, idProducto);
            tx.commit();
            log.trace ("Inserción de PedidosProductos: [" + idPedido + ", " + idProducto + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new PedidosProductos (idPedido, idProducto);
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
	 * Método que elimina, de manera transaccional, una tupla en la tabla PedidosProductos, dados los identificadores de pedido y producto
	 * @param idPedido - El identificador del pedido
	 * @param idProducto - El identificador del producto
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarPedidosProductos(long idPedido, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPedidosProductos.eliminarPedidosProductos(pm, idPedido, idProducto);           
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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
	 * Método que consulta todas las tuplas en la tabla PedidosProductos
	 * @return La lista de objetos PedidosProductos, construidos con base en las tuplas de la tabla PedidosProductos
	 */
	public List<PedidosProductos> darPedidosProductos ()
	{
		return sqlPedidosProductos.darPedidosProductos (pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar SUCURSALESPRODUCTOS
	 *****************************************************************/
	public SucursalesProductos adicionarSucursalesProductos(long idSucursal, long idProducto, int existenciasEnEstante, int existenciasEnBodega) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlSucursalesProductos.adicionarSucursalesProductos (pm, idSucursal, idProducto, existenciasEnEstante, existenciasEnBodega);
            tx.commit();
            log.trace ("Inserción de sucursalesProductos: [" + idSucursal + ", " + idProducto + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new SucursalesProductos (idSucursal, idProducto, existenciasEnEstante, existenciasEnBodega);
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
	 * Método que elimina, de manera transaccional, una tupla en la tabla SUCURSALesPRODUCTOs, dados los identificadores de sucursal y producto
	 * @param idSucursal - El identificador de la sucursal
	 * @param idProducto - El identificador del producto
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarSucursalesProductos(long idSucursal, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSucursalesProductos.eliminarSucursalesProductos(pm, idSucursal, idProducto);           
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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
	 * Método que consulta todas las tuplas en la tabla SUCURSALesPRODUCTOs
	 * @return La lista de objetos SUCURSALesPRODUCTOs, construidos con base en las tuplas de la tabla SUCURSALesPRODUCTOs
	 */
	public List<SucursalesProductos> darSucursalesProductos ()
	{
		return sqlSucursalesProductos.darSucursalesProductos (pmf.getPersistenceManager());
	}
	public long actualizarExistenciasBodega(long codigoDeBarras, long existenciasEnBodegas, long idSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSucursalesProductos.actualizarExistenciasBodega(pm, codigoDeBarras, existenciasEnBodegas, idSucursal);           
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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

	public long actualizarExistenciasEstante(long codigoDeBarras, long existenciasEnEstantes, long idSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSucursalesProductos.actualizarExistenciasEstante(pm, codigoDeBarras, existenciasEnEstantes, idSucursal);           
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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

    /* ****************************************************************
	 * 			Métodos para manejar PROVEEDORESPRODUCTOS
	 *****************************************************************/
	public ProveedoresProductos adicionarProveedoresProductos(long nitProveedor, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlProveedoresProductos.adicionarProveedoresProductos (pm, nitProveedor, idProducto);
            tx.commit();
            log.trace ("Inserción de ProveedoresProductos: [" + nitProveedor + ", " + idProducto + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new ProveedoresProductos (nitProveedor, idProducto);
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
	 * Método que elimina, de manera transaccional, una tupla en la tabla ProveedoresProductos, dados los identificadores de proveedor y producto
	 * @param nitProveedor - El identificador del proveedor
	 * @param idProducto - El identificador del producto
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarProveedoresProductos(long nitProveedor, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProveedoresProductos.eliminarProveedoresProductos(pm, nitProveedor, idProducto);           
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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
	 * Método que consulta todas las tuplas en la tabla PROVEEDOResPRODUCTOs
	 * @return La lista de objetos PROVEEDOResPRODUCTOs, construidos con base en las tuplas de la tabla PROVEEDOResPRODUCTOs
	 */
	public List<ProveedoresProductos> darProveedoresProductos ()
	{ 
		return sqlProveedoresProductos.darProveedoresProductos (pmf.getPersistenceManager());
	}

    /* ****************************************************************
	 * 			Métodos para manejar PRODUCTOSESTANTES
	 *****************************************************************/
	public ProductosEstantes adicionarProductosEstantes(long idProducto , long idEstante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlProductosEstantes.adicionarProductosEstantes(pm, idProducto, idEstante);
            tx.commit();
            log.trace ("Inserción de ProductosEstantes: [" + idProducto + ", " + idEstante + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new ProductosEstantes(idProducto , idEstante);
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
	 * Método que elimina, de manera transaccional, una tupla en la tabla ProductosEstantes, dados los identificadores de producto y estante
	 * @param idProducto - El identificador del producto
     * @param idEstante - El identificador del estante
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarProductosEstantes(long idProducto , long idEstante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductosEstantes.eliminarProductosEstantes(pm, idProducto, idEstante);          
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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
	 * Método que consulta todas las tuplas en la tabla PRODUCtosESTANTes
	 * @return La lista de objetos PRODUCTosESTANTes, construidos con base en las tuplas de la tabla PRODUCTosESTANTes
	 */
	public List<ProductosEstantes> darProductosEstantes ()
	{ 
		return sqlProductosEstantes.darProductosEstantes(pmf.getPersistenceManager());
	}

       /* ****************************************************************
	 * 			Métodos para manejar PRODUCTOSBODEGAS
	 *****************************************************************/
	public ProductosBodegas adicionarProductosBodegas(long idProducto , long idBodega) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlProductosBodegas.adicionarProductosBodegas(pm, idProducto, idBodega);
            tx.commit();
            log.trace ("Inserción de ProductosBodegas: [" + idProducto + ", " + idBodega + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new ProductosBodegas(idProducto , idBodega);
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
	 * Método que elimina, de manera transaccional, una tupla en la tabla ProductosBodegas, dados los identificadores de producto y bodega
	 * @param idProducto - El identificador del producto
     * @param idBodega - El identificador de la bodega
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarProductosBodegas(long idProducto , long idBodega) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductosBodegas.eliminarProductosBodegas(pm, idProducto, idBodega);          
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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
	 * Método que consulta todas las tuplas en la tabla PRODUCtosBODEGas
	 * @return La lista de objetos PRODUCTosESTANTes, construidos con base en las tuplas de la tabla PRODUCTosBODEGas
	 */
	public List<ProductosBodegas> darProductosBodegas ()
	{ 
		return sqlProductosBodegas.darProductosBodegas(pmf.getPersistenceManager());
	}

    
       /* ****************************************************************
	 * 			Métodos para manejar PROOVEDORESSUCURSALES
	 *****************************************************************/
	public ProveedoresSucursales adicionarProveedoresSucursales(long nitProveedor , long idSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlProveedoresSucursales.adicionarProveedoresSucursales(pm, nitProveedor , idSucursal);
            tx.commit();
            log.trace ("Inserción de ProveedoresSucursales: [" + nitProveedor + ", " + idSucursal + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new ProveedoresSucursales(nitProveedor , idSucursal);
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
	 * Método que elimina, de manera transaccional, una tupla en la tabla ProveedoresSucursales, dados los identificadores de proveedor y sucursal
	 * @param nitProveedor - El identificador del proveedor
     * @param idSucursal - El identificador de la sucursal
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarProveedoresSucursales(long nitProveedor , long idSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProveedoresSucursales.eliminarProveedoresSucursales(pm, nitProveedor, idSucursal);         
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
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
	 * Método que consulta todas las tuplas en la tabla PROVEEDOResSUCURSALes
	 * @return La lista de objetos PROVEEDOResSUCURSALes, construidos con base en las tuplas de la tabla PROVEEDOResSUCURSALes
	 */
	public List<ProveedoresSucursales> darProveedoresSucursales()
	{ 
		return sqlProveedoresSucursales.darProveedoresSucursales(pmf.getPersistenceManager());
	}

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de 
	 *SuperAndes
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * Bodegas, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarSuperAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarSuperAndes (pm);
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

	
	

 }
