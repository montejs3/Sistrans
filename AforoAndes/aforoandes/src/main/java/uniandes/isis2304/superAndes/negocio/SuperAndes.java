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

package uniandes.isis2304.superAndes.negocio;


import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;


import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Germán Bravo
 */
public class SuperAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperAndes ps;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public SuperAndes ()
	{
		ps = PersistenciaSuperAndes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public SuperAndes (JsonObject tableConfig)
	{
		ps = PersistenciaSuperAndes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		ps.cerrarUnidadPersistencia ();
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE BEBIDA
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	
	public Usuario adicionarUsuario (long id, String tipoIdentificacion, String nombre, String correo_electronico, String palabra_clave, VORolUsuario rolNuevoUsuario, VOSucursal sucuNuevoUsuario)
	{
		
        log.info ("Adicionando Usuario " + id);
        Usuario usuario = ps.adicionarUsuario(id, tipoIdentificacion, nombre, correo_electronico, palabra_clave, rolNuevoUsuario,sucuNuevoUsuario);
        log.info ("Adicionando usuario: " + usuario + " tuplas insertadas");
        return usuario;
	}
	
	public Sucursal adicionarSucursal(String ciudad, String direccion, String nombre, int id_sucursal, int nivel_reorden, int recompra, int id_supermercado) {
		log.info ("Adicionando Sucursal: " + nombre);
        Sucursal sucursal = ps.adicionarSucursal(ciudad, direccion, nombre, id_sucursal, nivel_reorden, recompra, id_supermercado);		
        log.info ("Adicionando Sucursal: " + sucursal + " tuplas insertadas");
        return sucursal;
	}

	public Carrito carritoPorId(long id) {
		log.info ("Dar información de un carrito por id: " + id);
		Carrito carrito = ps.darCarritoPorId (id);
		log.info ("Buscando carrito por id: " + carrito != null ? carrito : "NO EXISTE");
		return carrito;
	}

    public Productos productoPorCodigo(long id) {
        log.info ("Dar producto por id: " + id);
		Productos producto = ps.productoPorCodigo (id);
		log.info ("Buscando carrito por id: " + producto != null ? producto : "NO EXISTE");
		return producto;
    }

	public int actualizarExistenciasEstante(long codigoBarras, int nuevasExist) {
		log.info ("Actualizar existencias estante: " + codigoBarras);
		int existenciasActualizadas = ps.actualizarExistenciasEstante (codigoBarras, nuevasExist);
		log.info ("Actualizando existencias estante: " + codigoBarras != null ? codigoBarras : "NO EXISTE");
		return existenciasActualizadas;
	}

    public ProductosCarrito adicionarProductoCarrito(long idCarrito, long codigoBarras, String cantidad) {
        log.info ("Adicionar producto a carrito: " + idCarrito);
		ProductosCarrito productosCarrito = ps.adicionarProductoCarrito (idCarrito, codigoBarras, cantidad);
		log.info ("Adicionando productos al carrito: " + idCarrito != null ? idCarrito : "NO EXISTE");
		return productosCarrito;
    }

    public int carritoProductosPorIdCarrito(long idCarrito, long codigoBarras) {
        log.info ("Producto " + codigoBarras + " en carrito: " + idCarrito);
		int productosEnCarrito = ps.carritoProductosPorIdCarrito(idCarrito, codigoBarras);
		log.info ("Mostrando cantidad del producto en el carrito: " + idCarrito != null ? idCarrito : "NO EXISTE");
		return productosEnCarrito;
    }

    public ProductosCarrito eliminarProductoCarrito(long idCarrito, long codigoBarras) {
        log.info ("Eliminar producto a carrito: " + idCarrito);
		ProductosCarrito productosCarrito = ps.eliminarProductoCarrito(idCarrito, codigoBarras);
		log.info ("Eliminando productos al carrito: " + idCarrito != null ? idCarrito : "NO EXISTE");
		return productosCarrito;
    }

    public ProductosCarrito actualizarProductoCarrito(long idCarrito, long codigoBarras, int productosCarritoAhora) {
        log.info ("Actualizar producto a carrito: " + idCarrito);
		ProductosCarrito productosCarrito = ps.actualizarProductoCarrito(idCarrito, codigoBarras, productosCarritoAhora);
		log.info ("Actualizando productos al carrito: " + idCarrito != null ? idCarrito : "NO EXISTE");
		return productosCarrito;
    }

    public List<VOProductosCarrito> darVOProductosCarrito(long idCarrito) {
        log.info ("Generando los VO de productosCarrito");        
		List<VOProductosCarrito> voTipos = new LinkedList<VOProductosCarrito> ();
		for (VOProductosCarrito tb : ps.darProductosCarrito(idCarrito))
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de productoCarrito: " + voTipos.size() + " existentes");
		return voTipos;
    }

    public int darTotalCarrito(long idCarrito) {
        log.info ("Dar total en carrito: " + idCarrito);
		int total = ps.darTotalCarrito(idCarrito);
		log.info ("Dar total en carrito: " + idCarrito != null ? idCarrito : "NO EXISTE");
		return total;
    }

    public Carrito dejarCarrito(long idCarrito) {
        log.info ("Dejar carrito: " + idCarrito);
		Carrito carrito = ps.dejarCarrito(idCarrito);
		log.info ("Dejando carrito: " + idCarrito != null ? idCarrito : "NO EXISTE");
		return carrito;
    }

	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarParranderos ()
	{
        log.info ("Limpiando la BD de Parranderos");
        long [] borrrados = ps.limpiarParranderos();	
        log.info ("Limpiando la BD de Parranderos: Listo!");
        return borrrados;
	}

    public long[] limpiarsuperAndes() {
        return null;
    }

    public List<VOCarrito> darCarritos() {
        return null;
    }

    public VOUsuario adicionarUsuario(String nombre, int pata, String tipo, String supermercado) {
        return null;
    }

    public String darCarritosCompraDisponibles() {
        return null;
    }

    public VOCarrito adicionarCarritoCliente(long cliente, String carritoDisponible) {
        return null;
    }

    public int darCantidadProductoCarrito(long parseLong, long codigoBarras) {
        return 0;
    }

    public Object darEstantePorProducto(long codigoBarras) {
        return null;
    }

    public int darExistenciasBodega(long codigoBarras) {
        return 0;
    }
}
