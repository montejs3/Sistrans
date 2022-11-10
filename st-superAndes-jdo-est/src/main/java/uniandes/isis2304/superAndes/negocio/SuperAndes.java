/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: SuperAPersistenciaSuperAndes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;
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
	 * 			Métodos para manejar BODEGA
	 *****************************************************************/
	 /**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Bodegas asociandola a una sucursal
	 * Adiciona entradas al log de la aplicación
	 * @param capacidad - La capacidad de la bodega 
	 * @param ocupacion - La ocupación de la bodega 
	 * @param tipoProducto - El tipo producto de la bodega 
	 * @param sucursal - La sucursal asociada a la bodega 
	 * @return El objeto Bodegas adicionado. null si ocurre alguna Excepción
	 */

	public Bodegas adicionarBodegas (long id_bodega, double capacidad, int ocupacion, long tipoProducto, long sucursal)
	{
		log.info ("Adicionando Bodega a la sucursal: " + id_bodega);
		Bodegas tipoBodegas = ps.adicionarBodega(capacidad, ocupacion, tipoProducto, sucursal);
		log.info ("Adicionando Bodega; " + tipoBodegas);
		return tipoBodegas;
	}

	/**
	 * Elimina una bodega asociada a una sucursal
	 * Adiciona entradas al log de la aplicación
	 * @param id_bodega - Identificador único de una bodega
	 * @param capacidad - La capacidad de la bodega 
	 * @param ocupacion - La ocupación de la bodega 
	 * @param tipoProducto - El tipo producto de la bodega 
	 * @param sucursal - La sucursal asociada a la bodega 
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarBodegasPorId (long id_bodega)
	{
		log.info ("Eliminando Bodega por ID: " + id_bodega);
        long resp = ps.eliminarBodegasPorId(id_bodega);		
        log.info ("Eliminando Bodegas por ID: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todas las bodegas en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos con todas las Bodegas que conoce la aplicación, llenos con su información básica
	 */
	public List<Bodegas> darBodegas ()
	{
		log.info ("Consultando Bodegas");
        List<Bodegas> tipoBodegas = ps.darBodegas();	
        log.info ("Consultando Bodegas: " + tipoBodegas.size() + " existentes");
        return tipoBodegas;
	}

	/**
	 * Encuentra todas las Bodegas en SuperAPersistenciaSuperAndes y los devuelve como una lista de VOBodegas
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOTipoBebida con todas las bodegas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOBodegas> darVOBodegas ()
	{
		log.info ("Generando los VO de Bodegas");        
        List<VOBodegas> voBodegas = new LinkedList<VOBodegas> ();
        for (Bodegas bdg : ps.darBodegas())
        {
        	voBodegas.add (bdg);
        }
        log.info ("Generando los VO de Bodegas: " + voBodegas.size() + " existentes");
        return voBodegas;
	}


	/**
	 * Encuentra la Bodega en SuperAPersistenciaSuperAndes con el ID solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param id_bodega - El identificador de la bodega
	 * @return Un objeto Bodega con el id de esa bodega que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public Bodegas darBodegasPorId (long id_bodega)
	{
		log.info ("Buscando Bodega por ID: " + id_bodega);
		List<Bodegas> tb = ps.darBodegaPorId(id_bodega);
		return !tb.isEmpty () ? tb.get(0) : null;
	}

	/**
	 * Encuentra la Bodega en SuperAPersistenciaSuperAndes con el Tipo Producto solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param tipo_producto - El identificador de la bodega
	 * @return Un objeto Bodega con el tipo producto de esa bodega que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public Bodegas darBodegasPorTipoProducto (long tipo_producto)
	{
		log.info ("Buscando Bodega por Tipo Producto: " + tipo_producto);
		List<Bodegas> bd = ps.darBodegaPorTipoProducto();
		return !bd.isEmpty () ? bd.get(3) : null;
	}

	/* ****************************************************************
	 * 			Métodos para manejar CLIENTES
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un cliente 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del cliente
	 * @param correo - El correo del cliente
	 * @param direccion - La dirección del cliente
	 * @param id_supermercado - El id del supermercado al cual esta afiliado
	 * @param compra - La compra del cliente
	 * @param tipo - El tipo de cliente
	 * @return El objeto Cliente adicionado. null si ocurre alguna Excepción
	 */
	public Clientes adicionarClientes (long id_cliente, String nombre, String correo, String direccion, int id_supermercado, int compra, String tipo)
	{
		log.info ("Adicionando cliente " + id_cliente);
		Clientes clientes = ps.adicionarCliente(nombre, correo, direccion, id_supermercado, compra, tipo);
        log.info ("Adicionando bebida: " + clientes);
        return clientes;
	}
	
	/**
	 * Elimina un cliente por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idCliente - El identificador del cliente a eliminar
	 * @return El número de tuplas eliminadas (1 o 0)
	 */
	public long eliminarClientePorID (long idCliente)
	{
        log.info ("Eliminando cliente por id: " + idCliente);
        long resp = ps.eliminarClientePorID(idCliente);
        log.info ("Eliminando cliente por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los clientes en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Cliente con todos los clientes que conoce la aplicación, llenos con su información básica
	 */
	public List<Clientes> darClientes ()
	{
        log.info ("Consultando Clientes");
        List<Clientes> clientes = ps.darClientes ();	
        log.info ("Consultando Clientes " + clientes.size() + " clientes existentes");
        return clientes;
	}

	/**
	 * Encuentra el cliente en SuperAPersistenciaSuperAndes con el ID solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param id_cliente - El identificador del cliente
	 * @return Un objeto cliente con el id de ese cliente que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public Clientes darClientePorId (long id_cliente)
	{
		log.info ("Buscando Cliente por ID: " + id_cliente);
		List<Clientes> cl = ps.darClientePorID(id_cliente);
		return !cl.isEmpty () ? cl.get(0) : null;
	}

	/**
	 * Encuentra todos los tipos de bebida en SuperAPersistenciaSuperAndes y los devuelve como una lista de VOTipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOBebida con todos las bebidas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOClientes> darVOClientes ()
	{
		log.info ("Generando los VO de las bebidas");       
        List<VOClientes> voClientes = new LinkedList<VOClientes> ();
        for (Clientes clt : ps.darClientes ())
        {
        	voClientes.add (clt);
        }
        log.info ("Generando los VO de los clientes: " + voClientes.size() + " existentes");
        return voClientes;
	}

	/* ****************************************************************
	 * 			Métodos para manejar COMPRA
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una compra 
	 * Adiciona entradas al log de la aplicación
	 * @param precioCompra - El precio de la compra
	 * @param fecha - La fecha de la compra
	 * @param cliente - id del cliente que realiza la compra
	 * @return El objeto BEBEDOR adicionado. null si ocurre alguna Excepción
	 */
	public Compra adicionarCompra (long id_compra, int precioCompra, Timestamp fecha, long cliente)
		{
        log.info ("Adicionando compra: " + id_compra);
        Compra compra = ps.adicionarCompra(precioCompra, fecha, cliente);
        log.info ("Adicionando compra: " + compra);
        return compra;
	}

	/**
	 * Elimina una compra por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id_compra - El id de la compra a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarCompraPorID (long id_compra)
	{
        log.info ("Eliminando compra por id: " + id_compra);
        long resp = ps.eliminarCompraPorID(id_compra);
        log.info ("Eliminando compra por id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra una compra y su información básica, según su identificador
	 * @param id_compra - El identificador de la compra buscada
	 * @return Un objeto compra que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si una compra con dicho identificador no existe
	 */
	public Compra darComprasPorId (long id_compra)
	{
		log.info ("Buscando Compra por ID: " + id_compra);
		List<Compra> cmp = ps.darCompraPorID(id_compra);
		return !cmp.isEmpty () ? cmp.get(0) : null;
	}

	/**
	 * Encuentra todas las compras en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Compras con todos las compras que conoce la aplicación, llenos con su información básica
	 */
	public List<Compra> darCompras ()
	{
        log.info ("Listando Compras");
        List<Compra> compra = ps.darCompras();	
        log.info ("Listando Compras: " + compra.size() + " compras existentes");
        return compra;
	}
	
	/**
	 * Encuentra todos los Compras en SuperAPersistenciaSuperAndes y los devuelve como VOCompra
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOCompra con todos las compras que conoce la aplicación, llenos con su información básica
	 */
	public List<VOCompra> darVOCompras ()
	{
        log.info ("Generando los VO de Compras");
         List<VOCompra> voCompras = new LinkedList<VOCompra> ();
        for (Compra cmp : ps.darCompras() )
        {
        	voCompras.add (cmp);
        }
        log.info ("Generando los VO de Compras: " + voCompras.size() + " compras existentes");
       return voCompras;
	}


	/* ****************************************************************
	 * 			Métodos para manejar los ESTANTES
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un estante
	 * Adiciona entradas al log de la aplicación
	 * @param cant_productos - Cantidad productos en el estante
	 * @param tipoProducto - Identificador que hace referencia al tipo de producto 
	 * @param capacidad - La capacidad del estante
	 * @param ocupacion - Cantidad ocupada del estante
	 * @param sucursal - Sucursal asociada al estante
	 * @return El objeto Estante adicionado. null si ocurre alguna Excepción
	 */
	public Estantes adicionarEstantes (long id_estante, int cant_productos, long tipoProducto, double capacidad, int ocupacion, long sucursal)
	{
        log.info ("Adicionando estante: " + id_estante);
        Estantes estantes = ps.adicionarEstantes(cant_productos, tipoProducto, capacidad, ocupacion, sucursal);
        log.info ("Adicionando estante: " + estantes);
        return estantes;
	}
	
	/**
	 * Elimina un estante por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id_estante - El identificador del estante a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarEstantePorID (long id_estante)
	{
        log.info ("Eliminando estante por id: " + id_estante);
        long resp = ps.eliminarEstantePorID(id_estante);
        log.info ("Eliminando estante: " + resp);
        return resp;
	}

	/**
	 * Encuentra un estante y su información básica, según su identificador
	 * @param id_estante - El identificador del estante buscado
	 * @return Un objeto Estante que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un estante con dicho identificador no existe
	 */
	public Estantes darEstantesPorId (long id_estante)
	{
		log.info ("Buscando Estante por ID: " + id_estante);
		List<Estantes> est = ps.darEstantePorID(id_estante);
		return !est.isEmpty () ? est.get(0) : null;
	}
	
	/**
	 * Encuentra todos los estantes en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Estante con todos los estantes que conoce la aplicación, llenos con su información básica
	 */
	public List<Estantes> darEstantes ()
	{
        log.info ("Listando Estantes");
        List<Estantes> estantes = ps.darEstantes();	
        log.info ("Listando Bares: " + estantes.size() + " bares existentes");
        return estantes;
	}

	/**
	 * Encuentra todos los estantes en SuperAPersistenciaSuperAndes y los devuelce como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Estante con todos los estantes que conoce la aplicación, llenos con su información básica
	 */
	public List<VOEstantes> darVOEstantes ()
	{
		log.info ("Generando los VO de Estantes");
		List<VOEstantes> voEstantes = new LinkedList<VOEstantes> ();
		for (Estantes estantes: ps.darEstantes() )
		{
			voEstantes.add (estantes);
		}
		log.info ("Generando los VO de Estantes: " + voEstantes.size () + " estantes existentes");
		return voEstantes;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar LOCAL DE VENTAS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un local de ventas
	 * Adiciona entradas al log de la aplicación
	 * @param id_sucursal - El identificador de la sucursal
	 * @return Un objeto local de venta con los valores dados
	 */
	public LocalDeVentas adicionarLocalDeVentas (long id_local, long id_sucursal)
	{
        log.info ("Adicionando local de ventas " + id_local);
        LocalDeVentas localDeVentas = ps.adicionarLocalDeVentas(id_sucursal);
        log.info ("Adicionando local de ventas: " + localDeVentas + " tuplas insertadas");
        return localDeVentas;
	}

	/**
	 * Elimina un local de ventas por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id_estante - El identificador del local de ventas a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarLocalDeVentasPorID (long id_local)
	{
        log.info ("Eliminando local por id: " + id_local);
        long resp = ps.eliminarLocalDeVentasPorID(id_local);
        log.info ("Eliminando local de ventas: " + resp);
        return resp;
	}

	/**
	 * Encuentra un local de ventas y su información básica, según su identificador
	 * @param id_estante - El identificador del local de ventas buscado
	 * @return Un objeto local de ventas que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un local de ventas con dicho identificador no existe
	 */
	public LocalDeVentas darLocalDeVentasPorId (long id_local)
	{
		log.info ("Buscando local de ventas por ID: " + id_local);
		List<LocalDeVentas> ldv = ps.darLocalDeVentasPorID(id_local);
		return !ldv.isEmpty () ? ldv.get(0) : null;
	}
	
	
	/**
	 * Encuentra todos los locales de venta en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos locales de venta con todos los locales de venta que conoce la aplicación, llenos con su información básica
	 */
	public List<LocalDeVentas> darLocalDeVentas ()
	{
        log.info ("Listando Local de Ventas");
        List<LocalDeVentas> localDeVentas = ps.darLocalDeVentas();	
        log.info ("Listando locales de venta: " + localDeVentas.size() + " locales de venta existentes");
        return localDeVentas;
	}

	/**
	 * Encuentra todos los locales de venta en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos local de ventas con todos los locales de venta que conoce la aplicación, llenos con su información básica
	 */
	public List<VOLocalDeVentas> darVOLocalDeVentas ()
	{
		log.info ("Generando los VO de Local de Ventas");
		List<VOLocalDeVentas> voLocalDeVentas = new LinkedList<VOLocalDeVentas> ();
		for (VOLocalDeVentas localDeVentas: ps.darLocalDeVentas())
		{
			voLocalDeVentas.add (localDeVentas);
		}
		log.info ("Generando los VO de local de venta: " + voLocalDeVentas.size () + " local de venta existentes");
		return voLocalDeVentas;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PEDIDOS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un pedido
	 * Adiciona entradas al log de la aplicación
	 * @param productos - productos del pedido
	 * @param volumenes - volumen de productos en el pedido
	 * @param precio - precio total del pedido
	 * @param fecha - fecha donde se realiza el pedido
	 * @return Un objeto Pedidos con los valores dados
	 */
	public Pedidos adicionarPedidos (long id_pedido, String productos, double volumenes, double precio, Timestamp fecha)
	{
        log.info ("Adicionando pedido " + id_pedido);
        Pedidos pedidos = ps.adicionarPedidos(productos, volumenes, precio, fecha);
        log.info ("Adicionando pedidos: " + pedidos + " tuplas insertadas");
        return pedidos;
	}

	/**
	 * Elimina un pedido por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id_pedido - El identificador del pedido a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPedidoPorID (long id_pedido)
	{
        log.info ("Eliminando pedido por id: " + id_pedido);
        long resp = ps.eliminarPedidoPorID(id_pedido);
        log.info ("Eliminando pedido: " + resp);
        return resp;
	}

		/**
	 * Encuentra un pedido y su información básica, según su identificador
	 * @param id_pedido - El identificador del local de ventas buscado
	 * @return Un objeto pedido que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un pedido con dicho identificador no existe
	 */
	public Pedidos darPedidosPorId (long id_pedido)
	{
		log.info ("Buscando pedido por ID: " + id_pedido);
		List<Pedidos> pdd = ps.darPedidoPorID(id_pedido);
		return !pdd.isEmpty () ? pdd.get(0) : null;
	}

	/**
	 * Encuentra todos los pedidos en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos pedidos con todos los pedidos que conoce la aplicación, llenos con su información básica
	 */
	public List<Pedidos> darPedidos ()
	{
        log.info ("Listando Pedidos");
        List<Pedidos> pedidos = ps.darPedidos ();	
        log.info ("Listando Pedidos: " + pedidos.size() + " pedidos existentes");
        return pedidos;
	}

	/**
	 * Encuentra todos los pedidos en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos pedidos con todos los pedidos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPedidos> darVOPedidos ()
	{
		log.info ("Generando los VO de Pedidos");
		List<VOPedidos> voPedidos = new LinkedList<VOPedidos> ();
		for (VOPedidos pedidos: ps.darPedidos() )
		{
			voPedidos.add (pedidos);
		}
		log.info ("Generando los VO de Pedidos: " + voPedidos.size () + " Pedidos existentes");
		return voPedidos;
	}

	/**
	 * Actualizar llegada de un pedido por sus identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - Identificador del pedido
	 * @param fecha - Fecha de llegada del pedido
	 * @param volumenes - Volumen entregado del pedido
	 * @return El número de tuplas actualizadas
	 */
	public long llegadaPedido (long id, Timestamp fecha, long volumenes)
	{
        log.info ("actualizando fecha llegada: " + fecha + "del pedido: " + id + "con los volumenes" + volumenes);
        long resp = ps.llegadaPedido(id, fecha, volumenes);
        log.info ("Actualizando llegada pedido: " + resp);
        return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PRODUCTO COMPRA
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente el producto compra
	 * Adiciona entradas al log de la aplicación
	 * @param cantidadProducto - Cantidad de producto comprado
	 * @param compra - El identificador de la compra
	 * @param producto - Producto comprado
	 * @param promoción - Que promoción fue utilizada 
	 * @return Un objeto Visitan con los valores dados
	 */
	public productoCompra adicionarProductoCompra (int cantidadProducto, long compra, long producto, long promocion)
	{
        log.info ("Adicionando Producto_Compra " + compra);
        productoCompra productoCompra = ps.adicionarProductoCompra(cantidadProducto, compra, producto, promocion);
        log.info ("Adicionando producto_compra: " + productoCompra+ " tuplas insertadas");
        return productoCompra;
	}
	

	/**
	 * Elimina un producto compra por su producto
	 * Adiciona entradas al log de la aplicación
	 * @param producto - El identificador del pedido a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarproductoCompraPorProducto (long producto)
	{
        log.info ("Eliminando producto_compra por producto: " + producto);
        long resp = ps.eliminarproductoCompraPorProducto(producto);
        log.info ("Eliminando producto_compra: " + resp);
        return resp;
	}

	/**
	 * Encuentra un producto_compra y su información básica, según su producto
	 * @param producto - El identificador del local de ventas buscado
	 * @return Un objeto pedido que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un pedido con dicho identificador no existe
	 */
	public productoCompra darProductoCompraPorProducto (long producto)
	{
		log.info ("Buscando producto_compra por producto: " + producto);
		List<productoCompra> pdc = ps.darProductoCompraPorProducto(producto);
		return !pdc.isEmpty () ? pdc.get(2) : null;
	}
	
	/**
	 * Encuentra todos los productos Compras en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos productos Compras con todos los productosCompras que conoce la aplicación, llenos con su información básica
	 */
	public List<productoCompra> darProductoCompras ()
	{
        log.info ("Listando Productos_Compras");
        List<productoCompra> productosCompra = ps.darProductoCompras();	
        log.info ("Listando Productos_Comoras: " + productosCompra.size() + " productos compra existentes");
        return productosCompra;
	}

	/**
	 * Encuentra todos los productosCompra en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos productos compra con todos los productos compra que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductoCompra> darVOProductoCompras ()
	{
		log.info ("Generando los VO de Producto Compra");
		List<VOProductoCompra> voProductoCompras = new LinkedList<VOProductoCompra> ();
		for (VOProductoCompra productosCompra: ps.darProductoCompras() )
		{
			voProductoCompras.add (productosCompra);
		}
		log.info ("Generando los VO de Producto Compra: " + voProductoCompras.size () + " Producto Compra existentes");
		return voProductoCompras;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PRODUCTOS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un producto
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - Nombre del producto
	 * @param marca - Marca del producto
	 * @param precio_unitario - precio del producto
	 * @param presentacion - presentación del producto
	 * @param cantidad_presentacion - cantidad presentación producto
	 * @param medida - medida del producto
	 * @param empacado - empacado del producto
	 * @param existenciasBodega - existencias del producto en bodega
	 * @param existenciasEstante - existencias del producto en estante}
	 * @param tipoProducto - identificador del tipo producto (1 - Perecedero , 2 - Abarrotes)
	 * @param categoria - identificador de la categoria del producto (1 - verdura , 2 - cocina , 3 - carne , 4 - alcohol , 5 - cocina , 6 - ropa )
	 * @param fechaVencimiento - fecha de vencimiento del producto
	 * @return Un objeto local de venta con los valores dados
	 */
	public Productos adicionarProductos (long id_producto, String nombre, String marca, long precio_unitario, String presentacion, int cantidad_presentacion, int medida, String empacado, int existenciasbodega, int existenciasestante, long tipoProducto, long categoria, Timestamp fechaVencimiento)
	{
        log.info ("Adicionando producto " + id_producto);
        Productos productos = ps.adicionarProducto(nombre, marca, precio_unitario, presentacion, cantidad_presentacion, medida, empacado, existenciasbodega, existenciasestante, tipoProducto, categoria, fechaVencimiento);
        log.info ("Adicionando producto: " + productos + " tuplas insertadas");
        return productos;
	}

    /**
	 * Elimina un producto por su id
	 * Adiciona entradas al log de la aplicación
	 * @param codigo_barras - El identificador del producto a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProducto (long codigo_barras)
	{
        log.info ("Eliminando producto por codigo de barras: " + codigo_barras);
        long resp = ps.eliminarProducto(codigo_barras);
        log.info ("Eliminando producto: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los productos en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos productos con todos los productos que conoce la aplicación, llenos con su información básica
	 */
	public List<Productos> darProductos ()
	{
        log.info ("Listando Productos");
        List<Productos> productos = ps.darProductos();	
        log.info ("Listando Productos: " + productos.size() + " productos existentes");
        return productos;
	}

	/**
	 * Encuentra el Producto en SuperAPersistenciaSuperAndes con el ID solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param codigo_barras - El identificador de la bodega
	 * @return Un objeto Producto con el id de ese producto que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public Productos darProductosPorCodigoDeBarras (long codigo_barras)
	{
		log.info ("Buscando Producto por codigo de barras: " + codigo_barras);
		List<Productos> pdt = ps.darProductoPorCodigoDeBarras(codigo_barras);
		return !pdt.isEmpty () ? pdt.get(0) : null;
	}

	/**
	 * Actualizar existencias en una bodega por sus identificador
	 * Adiciona entradas al log de la aplicación
	 * @param existenciasBodega - Unidades vendidas en la promoción
	 * @param codigo_barras - El identificador de la promoción
	 * @return El número de tuplas actualizadas
	 */
	public long actualizarExistenciasBodega (long codigo_barras, long existenciasBodega)
	{
        log.info ("actualizando existencias Bodega: " + existenciasBodega + "asociadas al producto: " + codigo_barras;
        long resp = ps.actualizarExistenciasBodega(codigo_barras, existenciasBodega);
        log.info ("Actualizando existenciasBodega: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los productos en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos productos con todos los productos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductos> darVOProductos ()
	{
		log.info ("Generando los VO de Productos");
		List<VOProductos> voProductos = new LinkedList<VOProductos> ();
		for (VOProductos productos: ps.darProductos() )
		{
			voProductos.add (productos);
		}
		log.info ("Generando los VO de Productos: " + voProductos.size () + " Producto existentes");
		return voProductos;
	}

    /* ****************************************************************
	 * 			Métodos para manejar PROMOCIONES
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una promocion
	 * Adiciona entradas al log de la aplicación
	 * @param tipo_promocion - Tipo de la promoción
	 * @param fecha_inicio - Fecha de inicio de la promoción
	 * @param fecha_final - Fecha final de la promoción
	 * @param porcentaje - Porcentaje de productos vendidos en la promoción
	 * @param estado - Si la promoción sigue vigente o ya finalizó 
	 * @param proveedor - Proveedor asociado a la promoción
	 * @param unidadesVendidas - Unidades vendidas en la promoción
	 * @param unidadesDisponibles - Unidades disponibles en la promoción
	 * @return Un objeto Promocion con los valores dados
	 */
	public Promociones adicionaPromociones (long id_promocion, String tipo_promocion, Timestamp fecha_inicio, Timestamp fecha_final, int porcentaje, String estado, long proveedor, int unidadesVendidas, int unidadesDisponibles)
	{
        log.info ("Adicionando Promocion " + id_promocion);
        Promociones promociones = ps.adicionarPromocion(tipo_promocion, fecha_inicio, fecha_final, porcentaje, estado, proveedor,unidadesVendidas,unidadesDisponibles);
        log.info ("Adicionando promociones: " + promociones + " tuplas insertadas");
        return promociones;
	}
	
	/**
	 * Elimina una promocion por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id_promocion - El identificador de la promoción a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long finalizarPromocion (long id_promocion)
	{
        log.info ("Finalizando promocion con identificador: " + id_promocion);
        long resp = ps.finalizarPromocion(id_promocion);
        log.info ("Eliminando promoción: " + resp);
        return resp;
	}

	/**
	 * Actualizar unidades vendidas de una promocion por sus identificador
	 * Adiciona entradas al log de la aplicación
	 * @param unidadesVendidas - Unidades vendidas en la promoción
	 * @param id - El identificador de la promoción
	 * @return El número de tuplas actualizadas
	 */
	public long actualizarUnidadesVendidas (long unidadesVendidas, long id)
	{
        log.info ("actualizando unidadesVendidas: " + unidadesVendidas + "asociadas a la promoción: " + id);
        long resp = ps.actualizarUnidadesVendidas(unidadesVendidas, id);
        log.info ("Actualizando UnidadesVendidas: " + resp);
        return resp;
	}

	/**
	 * Actualizar unidades disponibles de una promocion por sus identificador
	 * Adiciona entradas al log de la aplicación
	 * @param unidadesDisponibles - Unidades disponibles en la promoción
	 * @param id - El identificador de la promoción
	 * @return El número de tuplas actualizadas
	 */
	public long actualizarUnidadesDisponibles (long unidadesDisponibles, long id)
	{
        log.info ("actualizando unidadesDisponibles: " + unidadesDisponibles + "asociadas a la promoción: " + id);
        long resp = ps.actualizarUnidadesDisponibles(unidadesDisponibles, id);
        log.info ("Actualizando UnidadesDisponibles: " + resp);
        return resp;
	}


	/* ****************************************************************
	 * 			Métodos para manejar PROVEEDORES
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un proveedor
	 * Adiciona entradas al log de la aplicación
	 * @param nit - identificador del proveedor
	 * @param nombre - nombre del proveedor
	 * @param calidad - calidad del proveedor
	 * @param esExclusivo - exclusividad del proveedor
	 * @return Un objeto Promocion con los valores dados
	 */
	public Proveedores adicionarProveedores (long nit, String nombre, int calidad, boolean esExclusivo)
	{
        log.info ("Adicionando Proveedor " + nit);
        Proveedores proveedores = ps.adicionarProveedor(nit, nombre, calidad, esExclusivo);
        log.info ("Adicionando proveedor: " + proveedores + " tuplas insertadas");
        return proveedores;
	}

	/**
	 * Elimina un proveedor por su NIT
	 * Adiciona entradas al log de la aplicación
	 * @param NIT - El identificador del proveedor a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProveedorPorNit (long nit)
	{
        log.info ("Eliminando proveedor con NIT: " + nit);
        long resp = ps.eliminarProveedorPorNit(nit);
        log.info ("Eliminando proveedor: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los proveedores en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos productos con todos los productos que conoce la aplicación, llenos con su información básica
	 */
	public List<Proveedores> darProveedores ()
	{
        log.info ("Listando Proveedores");
        List<Proveedores> proveedores = ps.darProveedores();	
        log.info ("Listando Productos: " + proveedores.size() + " productos existentes");
        return proveedores;
	}

	/**
	 * Encuentra el Proveedor en SuperAPersistenciaSuperAndes con el NIT solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param nit - El identificador del proveedor
	 * @return Un objeto Proveedor con el nit de ese proveedor que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public Proveedores darProveedoresPorNit (long nit)
	{
		log.info ("Buscando Proveedor por nit: " + nit);
		List<Proveedores> pvs = ps.darProveedorPorNit(nit);
		return !pvs.isEmpty () ? pvs.get(0) : null;
	}

	/**
	 * Encuentra todos los proveedores en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos proveedores con todos los proveedores que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProveedores> darVOProveedores ()
	{
		log.info ("Generando los VO de Proveedores");
		List<VOProveedores> voProveedores = new LinkedList<VOProveedores> ();
		for (VOProveedores proveedores: ps.darProveedores() )
		{
			voProveedores.add (proveedores);
		}
		log.info ("Generando los VO de Proveedores: " + voProveedores.size () + " Proveedores existentes");
		return voProveedores;
	}

	/* ****************************************************************
	 * 			Métodos para manejar ROL USUARIO
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un rol de usuario
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - nombre del rol del usuario
	 * @return Un objeto Rol de Usuario con los valores dados
	 */
	public RolUsuario adicionarRolUsuario (String nombre)
	{
        log.info ("Adicionando Rol de Usuario " + nombre);
        RolUsuario rolUsuario = ps.adicionarRolUsuario(nombre);
        log.info ("Adicionando rol de usuario: " + rolUsuario + " tuplas insertadas");
        return rolUsuario;
	}

	
	/**
	 * Elimina un rol usuario por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del rol de usuario a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarRolUsuarioPorId (long id)
	{
        log.info ("Eliminando rol usuario con id: " + id);
        long resp = ps.eliminarRolUsuarioPorId(id);
        log.info ("Eliminando rol usuario: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los roles usuario en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos roles de usuario con todos los roles usuario que conoce la aplicación, llenos con su información básica
	 */
	public List<RolUsuario> darRolUsuarios ()
	{
        log.info ("Listando Roles usuario");
        List<RolUsuario> rolUsuarios = ps.darRolesUsuario();
        log.info ("Listando Roles de Usuario: " + rolUsuarios.size() + " roles usuario existentes");
        return rolUsuarios;
	}

	/**
	 * Encuentra el rol usuario en SuperAPersistenciaSuperAndes con el id solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del rol usuario
	 * @return Un objeto rol usuario con el id de ese rol usuario que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public RolUsuario darRolUsuarioPorId (long id)
	{
		log.info ("Buscando rol usuario por id: " + id);
		List<RolUsuario> rus = ps.darRolUsuarioPorId(id);
		return !rus.isEmpty () ? rus.get(0) : null;
	}

	/**
	 * Encuentra todos los proveedores en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos proveedores con todos los proveedores que conoce la aplicación, llenos con su información básica
	 */
	public List<VORolUsuario> darVORolUsuarios ()
	{
		log.info ("Generando los VO de Roles usuario");
		List<VORolUsuario> voRolUsuarios = new LinkedList<VORolUsuario> ();
		for (VORolUsuario rolUsuario: ps.darRolesUsuario() )
		{
			voRolUsuarios.add (rolUsuario);
		}
		log.info ("Generando los VO de roles usuario: " + voRolUsuarios.size () + " roles usuario existentes");
		return voRolUsuarios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar SUCURSALES
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una sucursal
	 * Adiciona entradas al log de la aplicación
	 * @param ciudad - ciudad donde se encuentra la sucursal
	 * @param direccion - dirección donde se encuentra la sucursal
	 * @param nombre - nombre de la sucursal
	 * @param nivel_reorden - nivel de reorden de la sucursal
	 * @param recompra - nivel de recompra de la sucursal
	 * @param supermercadoId - id del supermercado al cual una sucursal esta afilada
	 * @return Un objeto Sucursal con los valores dados
	 */
	public Sucursales adicionarSucursales (String ciudad, String direccion, String nombre, int nivel_reorden, int recompra, long supermercadoId)
	{
        log.info ("Adicionando Sucursal " + nombre);
        Sucursales sucursales = ps.adicionarSucursal(ciudad, direccion, nombre, nivel_reorden, recompra, supermercadoId);
        log.info ("Adicionando sucursal: " + sucursales + " tuplas insertadas");
        return sucursales;
	}

	/**
	 * Elimina una sucursal por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador de la sucursal a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarSucursalPorId (long id)
	{
        log.info ("Eliminando sucursal con id: " + id);
        long resp = ps.eliminarSucursalPorId(id);
        log.info ("Eliminando sucursal: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos las sucursales en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos sucursales con todos las sucursales que conoce la aplicación, llenos con su información básica
	 */
	public List<Sucursales> darSucursales ()
	{
        log.info ("Listando Sucursales");
        List<Sucursales> sucursales = ps.darSucursales();
        log.info ("Listando Sucursales: " + sucursales.size() + " sucursales existentes");
        return sucursales;
	}

	/**
	 * Encuentra la sucursal en SuperAPersistenciaSuperAndes con el id solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador de la sucursal
	 * @return Un objeto sucursal con el id de ese sucursal que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public Sucursales darSucursalesPorId (long id)
	{
		log.info ("Buscando sucursal por id: " + id);
		List<Sucursales> suc = ps.darSucursalPorId(id);
		return !suc.isEmpty () ? suc.get(0) : null;
	}

	/**
	 * Encuentra todas las sucursales en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos sucursales con todas las sucursales que conoce la aplicación, llenos con su información básica
	 */
	public List<VOSucursales> darVOSucursales ()
	{
		log.info ("Generando los VO de sucursales");
		List<VOSucursales> voSucursales = new LinkedList<VOSucursales> ();
		for (VOSucursales sucursales: ps.darSucursales()) 
		{
			voSucursales.add (sucursales);
		}
		log.info ("Generando los VO de sucursales: " + voSucursales.size () + " sucursales existentes");
		return voSucursales;
	}

	/* ****************************************************************
	 * 			Métodos para manejar SUPERMERCADO
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un usuario
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - nombre del supermercado
	 * @return Un objeto Usuario con los valores dados
	 */
	public Supermercado adicionarSupermercado (String nombre)
	{
        log.info ("Adicionando Supermercado " + nombre);
        Supermercado supermercado = ps.adicionarSupermercado(nombre);
        log.info ("Adicionando supermercado: " + supermercado + " tuplas insertadas");
        return supermercado;
	}

	/**
	 * Elimina una supermercado por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del supermercado a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarSupermercado (long id)
	{
        log.info ("Eliminando supermercado con id: " + id);
        long resp = ps.eliminarSupermercado(id);
        log.info ("Eliminando supermercado: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los supermercados en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos supermercados con todos los supermercados que conoce la aplicación, llenos con su información básica
	 */
	public List<Supermercado> darSuperSupermercados ()
	{
        log.info ("Listando Supermercados");
        List<Supermercado> supermercados = ps.darSupermercados();
        log.info ("Listando Supermercados: " + supermercados.size() + " supermercados existentes");
        return supermercados;
	}

	/**
	 * Encuentra el supermercado en SuperAPersistenciaSuperAndes con el id solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del supermercado
	 * @return Un objeto supermercado con el id de ese supermercado que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public Supermercado darSupermercado (long id)
	{
		log.info ("Buscando supermecado por id: " + id);
		List<Supermercado> sup = ps.darSupermercado(id);
		return !sup.isEmpty () ? sup.get(0) : null;
	}

	/**
	 * Encuentra todos los supermercados en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos supermercado con todos los supermercados que conoce la aplicación, llenos con su información básica
	 */
	public List<VOSupermercado> darVOSupermercados ()
	{
		log.info ("Generando los VO de supermercados");
		List<VOSupermercado> voSupermercados = new LinkedList<VOSupermercado> ();
		for (VOSupermercado supermercado: ps.darSupermercados()) 
		{
			voSupermercados.add (supermercado);
		}
		log.info ("Generando los VO de supermercados: " + voSupermercados.size () + " supermercados existentes");
		return voSupermercados;
	}

		/* ****************************************************************
	 * 			Métodos para manejar TIPOPRODUCTO
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un tipoproducto
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - tipo del producto
	 * @param categoria - categoria del producto
	 * @return Un objeto TipoProducto con los valores dados
	 */
	public tipoProducto adicionarTipoProducto (long id, String tipo, String categoria)
	{
        log.info ("Adicionando Tipo_Producto " + id);
        tipoProducto tipoProductos = ps.adicionarTipoProducto(tipo, categoria);
        log.info ("Adicionando tipo producto: " + tipoProductos + " tuplas insertadas");
        return tipoProductos;
	}

	/**
	 * Elimina un tipoproducto por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del tipoproducto a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarTipoProductoPorId (long id)
	{
        log.info ("Eliminando tipoproducto con id: " + id);
        long resp = ps.eliminarTipoProductoPorId(id);
        log.info ("Eliminando tipoproducto: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los tiposproductos en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos tiposproductos con todos los tiposproductos que conoce la aplicación, llenos con su información básica
	 */
	public List<tipoProducto> darTipoProductos ()
	{
        log.info ("Listando Tiposproductos");
        List<tipoProducto> tipoProductos = ps.darTipoProductos();
        log.info ("Listando tiposproductos: " + tipoProductos.size() + " tiposproductos existentes");
        return tipoProductos;
	}

	/**
	 * Encuentra el tipoproducto en SuperAPersistenciaSuperAndes con el id solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del tipoproducto
	 * @return Un objeto tipoproducto con el id de ese tipoproducto que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public tipoProducto darTipoProducto (long id)
	{
		log.info ("Buscando tipoproducto por id: " + id);
		List<tipoProducto> tpo = ps.darTipoProducto(id);
		return !tpo.isEmpty () ? tpo.get(0) : null;
	}

	/**
	 * Encuentra todos los tiposproductos en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos tiposproducto con todos los tiposproductos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOTipoProducto> darVOTipoProductos ()
	{
		log.info ("Generando los VO de tiposproductos");
		List<VOTipoProducto> voTipoProductos = new LinkedList<VOTipoProducto> ();
		for (VOTipoProducto tipoProducto: ps.darTipoProductos()) 
		{
			voTipoProductos.add (tipoProducto);
		}
		log.info ("Generando los VO de tiposproductos: " + voTipoProductos.size () + " tiposproductos existentes");
		return voTipoProductos;
	}

	/* ****************************************************************
	 * 			Métodos para manejar USUARIOS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un usuario
	 * Adiciona entradas al log de la aplicación
	 * @param id - identificación del usuario
	 * @param tipoIdentificacion - tipo de identifiación del usuario, puede ser varias opciones
	 * @param nombre - nombre del usuario
	 * @param correo_electronico - correo electrónico del usuario
	 * @param palabra_clave - palabra clave del usuario
	 * @param rolNuevoUsuario - rol del usuario en el supermercado
	 * @param sucuNuevoUsuario - identificación de la sucursal a la cual esta asociado un usuario
	 * @return Un objeto Usuario con los valores dados
	 */
	public Usuario adicionarUsuario (long id, String tipoIdentificacion, String nombre, String correo_electronico, String palabra_clave, VORolUsuario rolNuevoUsuario, VOSucursales sucuNuevoUsuario)
	{
        log.info ("Adicionando Usuario " + id);
        Usuario usuario = ps.adicionarUsuario(id, tipoIdentificacion, nombre, correo_electronico, palabra_clave, rolNuevoUsuario,sucuNuevoUsuario);
        log.info ("Adicionando usuario: " + usuario + " tuplas insertadas");
        return usuario;
	}

	/**
	 * Elimina un usuario por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id_usuario - El identificador del usuario a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarUsuarioPorId (long id_usuario)
	{
        log.info ("Eliminando usuario por id: " + id_usuario);
        long resp = ps.eliminarUsuarioPorId(id_usuario);
        log.info ("Eliminando usuario: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los Usuarios en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos usuarios con todos los usuarios que conoce la aplicación, llenos con su información básica
	 */
	public List<Usuario> darUsuarios ()
	{
        log.info ("Listando Usuarios");
        List<Usuario> usuario = ps.darUsuarios();	
        log.info ("Listando usuarios: " + usuario.size() + " usuarios existentes");
        return usuario;
	}

		/**
	 * Encuentra el usuario en SuperAPersistenciaSuperAndes con el id solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del usuario
	 * @return Un objeto usuarion con el id de ese tipoproducto que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public Usuario darUsuarioPorId (long id)
	{
		log.info ("Buscando usuario por id: " + id);
		List<Usuario> usu = ps.darUsuarioPorId(id);
		return !usu.isEmpty () ? usu.get(0) : null;
	}


	
	/* ****************************************************************
	 * 			Métodos para manejar PedidosProductos
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un usuario
	 * Adiciona entradas al log de la aplicación
	 * @param idPedido - identificación del pedido
	 * @param idProducto - identificación del producto
	 * @return Un objeto PedidosProductos con los valores dados
	 */
	public PedidosProductos adicionarPedidosProductos (long idPedido, long idProducto)
	{
        log.info ("Adicionando Pedidos: " + idPedido + "asociado a: " + idProducto + "producto");
        PedidosProductos pedidosProductos = ps.adicionarPedidosProductos(idPedido, idProducto);
        log.info ("Adicionando pedidoProducto: " + pedidosProductos + " tuplas insertadas");
        return pedidosProductos;
	}

	
	/**
	 * Elimina un pedidoProducto por sus identificadores
	 * Adiciona entradas al log de la aplicación
	 * @param id_pedido - El identificador del pedido a eliminar
	 * @param id_producto - El identificador del producto a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPedidosProductos (long id_pedido, long id_producto)
	{
        log.info ("Eliminando pedido: " + id_pedido + "asociado al producto: " + id_producto);
        long resp = ps.eliminarPedidosProductos(id_pedido, id_producto);
        log.info ("Eliminando pedidoProducto: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los pedidosProductos en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos pedidosProductos con todos los pedidosProductos que conoce la aplicación, llenos con su información básica
	 */
	public List<PedidosProductos> darPedidosProductos ()
	{
        log.info ("Listando Pedidos_Productos");
        List<PedidosProductos> pedidosProductos = ps.darPedidosProductos();	
        log.info ("Listando Pedidos_Productos: " + pedidosProductos.size() + " pedidos_productos existentes");
        return pedidosProductos;
	}

	/**
	 * Encuentra todos los pedidosProductos en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos pedidosProductos con todos los pedidosProductos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPedidosProductos> darVOPedidosProductos ()
	{
		log.info ("Generando los VO de pedidosproductos");
		List<VOPedidosProductos> voPedidosProductos = new LinkedList<VOPedidosProductos> ();
		for (VOPedidosProductos pedidosProductos: ps.darPedidosProductos()) 
		{
			voPedidosProductos.add (pedidosProductos);
		}
		log.info ("Generando los VO de pedidosProductos: " + voPedidosProductos.size () + " pedidosProductos existentes");
		return voPedidosProductos;
	}

		
	/* ****************************************************************
	 * 			Métodos para manejar SUCURSALESPRODUCTOS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente sucursales asociadas a productos
	 * Adiciona entradas al log de la aplicación
	 * @param idSucursal- identificación del pedido
	 * @param idProducto - identificación del producto
	 * @param existenciasEnEstante- identificación del producto
	 * @param existenciasEnBodega- identificación del producto
	 * @return Un objeto PedidosProductos con los valores dados
	 */
	public SucursalesProductos adicionarSucursalesProductos (long idSucursal, long idProducto, int existenciasEnEstante, int existenciasEnBodega)
	{
        log.info ("Adicionando Sucursal: " + idSucursal + "asociado a: " + idProducto + "producto");
        SucursalesProductos sucursalesProductos = ps.adicionarSucursalesProductos(idSucursal, idProducto, existenciasEnEstante, existenciasEnBodega);
        log.info ("Adicionando sucursalesProductos: " + sucursalesProductos + " tuplas insertadas");
        return sucursalesProductos;
	}

	/**
	 * Elimina un SucursalProducto por sus identificadores
	 * Adiciona entradas al log de la aplicación
	 * @param id_sucursal - El identificador de la sucursal a eliminar
	 * @param id_producto - El identificador del producto a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarSucursalesProductos (long id_sucursal, long id_producto)
	{
        log.info ("Eliminando pedido: " + id_sucursal+ "asociado al producto: " + id_producto);
        long resp = ps.eliminarSucursalesProductos(id_sucursal, id_producto);
        log.info ("Eliminando SucursalProducto: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los sucursalesProductos en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos sucursalesProductos con todos los sucursalesProductos que conoce la aplicación, llenos con su información básica
	 */
	public List<SucursalesProductos> darSucursalesProductos ()
	{
        log.info ("Listando Sucursales_Productos");
        List<SucursalesProductos> sucursalesProductos = ps.darSucursalesProductos();
        log.info ("Listando Pedidos_Productos: " + sucursalesProductos.size() + " pedidos_productos existentes");
        return sucursalesProductos;
	}

	/**
	 * Encuentra todos los sucursalesProductos en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos sucursalesProductos con todos los sucursalesProductos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOSucursalesProductos> darVOSucursalesProductos ()
	{
		log.info ("Generando los VO de sucursalesProductos");
		List<VOSucursalesProductos> voSucursalesProductos = new LinkedList<VOSucursalesProductos> ();
		for (VOSucursalesProductos sucursalesProductos: ps.darSucursalesProductos()) 
		{
			voSucursalesProductos.add (sucursalesProductos);
		}
		log.info ("Generando los VO de sucursalesProductos: " + voSucursalesProductos.size () + " sucursalesProductos existentes");
		return voSucursalesProductos;
	}

	/**
	 * Actualizar una bodega de una SucursalProducto por sus identificadores
	 * Adiciona entradas al log de la aplicación
	 * @param id_sucursal - El identificador de la sucursal a actualizar
	 * @param codigoDeBarras - El identificador del producto a actualizar
	 * @param existenciasEnBodegas - existencias en bodegas a actualizar
	 * @return El número de tuplas actualizadas
	 */
	public long actualizarExistenciasBodega (long codigoDeBarras, long existenciasEnBodegas, long idSucursal)
	{
        log.info ("actualizando existencias en bodega: " + existenciasEnBodegas + "asociado al producto: " + codigoDeBarras + "en la sucursal: " + idSucursal);
        long resp = ps.actualizarExistenciasBodega(codigoDeBarras, existenciasEnBodegas, idSucursal);
        log.info ("Actualizando SucursalProducto: " + resp);
        return resp;
	}

	/**
	 * Actualizar un estante de una SucursalProducto por sus identificadores
	 * Adiciona entradas al log de la aplicación
	 * @param id_sucursal - El identificador de la sucursal a actualizar
	 * @param codigoDeBarras - El identificador del producto a actualizar
	 * @param existenciasEnEstante - existencias en estantes a actualizar
	 * @return El número de tuplas actualizadas
	 */
	public long actualizarExistenciasEstante (long codigoDeBarras, long existenciasEnEstantes, long idSucursal)
	{
        log.info ("actualizando existencias en estante: " + existenciasEnEstantes + "asociado al producto: " + codigoDeBarras + "en la sucursal: " + idSucursal);
        long resp = ps.actualizarExistenciasEstante(codigoDeBarras, existenciasEnEstantes, idSucursal);
        log.info ("Actualizando SucursalProducto: " + resp);
        return resp;
	}

		/* ****************************************************************
	 * 			Métodos para manejar PROVEEDORESPRODUCTOS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente proveedores asociadas a productos
	 * Adiciona entradas al log de la aplicación
	 * @param nitProveedor- identificación del proveedor
	 * @param idProducto - identificación del producto
	 * @return Un objeto PedidosProductos con los valores dados
	 */
	public ProveedoresProductos adicionarProveedoresProductos (long nitProveedor, long idProducto)
	{
        log.info ("Adicionando Proveedor: " + nitProveedor + "asociado a: " + idProducto + "producto");
        ProveedoresProductos proveedoresProductos = ps.adicionarProveedoresProductos(nitProveedor, idProducto);
        log.info ("Adicionando ProveedoreProductos: " + proveedoresProductos + " tuplas insertadas");
        return proveedoresProductos;
	}

	/**
	 * Elimina un ProveedorProducto por sus identificadores
	 * Adiciona entradas al log de la aplicación
	 * @param nitProveedor - El identificador del proveedor a eliminar
	 * @param id_producto - El identificador del producto a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProveedoresProductos (long nitProveedor, long id_producto)
	{
        log.info ("Eliminando proveedor: " + nitProveedor + "asociado al producto: " + id_producto);
        long resp = ps.eliminarProveedoresProductos(nitProveedor, id_producto);
        log.info ("Eliminando ProveedorProducto: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los proveedoresProductos en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos proveedoresProductos con todos los proveedoresProductos que conoce la aplicación, llenos con su información básica
	 */
	public List<ProveedoresProductos> darProveedoresProductos ()
	{
        log.info ("Listando Proveedores_Productos");
        List<ProveedoresProductos> proveedoresProductos = ps.darProveedoresProductos();
        log.info ("Listando Proveedores_Productos: " + proveedoresProductos.size() + " proveedores_productos existentes");
        return proveedoresProductos;
	}

	/**
	 * Encuentra todos los ProveedoresProductos en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos proveedoresProductos con todos los proveedoresProductos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProveedoresProductos> darVOProveedoresProductos ()
	{
		log.info ("Generando los VO de proveedoresProductos");
		List<VOProveedoresProductos> voProveedoresProductos = new LinkedList<VOProveedoresProductos> ();
		for (VOProveedoresProductos proveedoresProductos: ps.darProveedoresProductos()) 
		{
			voProveedoresProductos.add (proveedoresProductos);
		}
		log.info ("Generando los VO de proveedoresProductos: " + voProveedoresProductos.size () + " proveedoresProductos existentes");
		return voProveedoresProductos;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PRODUCTOSESTANTES
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente productos asociadas a estantes
	 * Adiciona entradas al log de la aplicación
	 * @param idProducto - identificación del producto
	 * @param idEstante - identificación del estante
	 * @return Un objeto ProductosEstantes con los valores dados
	 */
	public ProductosEstantes adicionarProductosEstantes (long idProducto , long idEstante)
	{
        log.info ("Adicionando Productos: " + idProducto + "asociado a: " + idEstante + "estante");
        ProductosEstantes productosEstantes = ps.adicionarProductosEstantes(idProducto, idEstante);
        log.info ("Adicionando ProductosEstantes: " + productosEstantes + " tuplas insertadas");
        return productosEstantes;
	}

	/**
	 * Elimina un ProductoEstante por sus identificadores
	 * Adiciona entradas al log de la aplicación
	 * @param id_producto - El identificador del producto a eliminar
	 * @param idEstante - identificación del estante
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductosEstantes (long id_producto , long idEstante)
	{
        log.info ("Eliminando producto " + id_producto + "asociado al estante: " + idEstante);
        long resp = ps.eliminarProductosEstantes(id_producto, idEstante);
        log.info ("Eliminando ProductosEstantes: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los productosEstantes en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos productosEstantes con todos los productosEstantes que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductosEstantes> darProductosEstantes ()
	{
        log.info ("Listando Productos_Estantes");
        List<ProductosEstantes> productosEstantes = ps.darProductosEstantes();
        log.info ("Listando Productos_Estantes: " + productosEstantes.size() + " productos_estantes existentes");
        return productosEstantes;
	}

	/**
	 * Encuentra todos los ProductosEstantes en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos productosEstantes con todos los productosEstantes que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductosEstantes> darVOProductosEstantes ()
	{
		log.info ("Generando los VO de productosEstantes");
		List<VOProductosEstantes> voProductosEstantes = new LinkedList<VOProductosEstantes> ();
		for (VOProductosEstantes productosEstantes: ps.darProductosEstantes()) 
		{
			voProductosEstantes.add (productosEstantes);
		}
		log.info ("Generando los VO de productosEstantes: " + voProductosEstantes.size () + " ProductosEstantes existentes");
		return voProductosEstantes;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PRODUCTOSBODEGAS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente productos asociadas a bodegas
	 * Adiciona entradas al log de la aplicación
	 * @param idProducto - identificación del producto
	 * @param idBodega - identificación de la bodega
	 * @return Un objeto ProductosBodegas con los valores dados
	 */
	public ProductosBodegas adicionarProductosBodegas (long idProducto , long idBodega)
	{
        log.info ("Adicionando Productos: " + idProducto + "asociado a: " + idBodega + "bodega");
        ProductosBodegas productosBodegas = ps.adicionarProductosBodegas(idProducto, idBodega);
        log.info ("Adicionando ProductosBodegas: " + productosBodegas + " tuplas insertadas");
        return productosBodegas;
	}

	/**
	 * Elimina un ProductoBodega por sus identificadores
	 * Adiciona entradas al log de la aplicación
	 * @param id_producto - El identificador del producto a eliminar
	 * @param idBodega - identificación del estante
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductosBodegas (long id_producto , long idBodega)
	{
        log.info ("Eliminando producto " + id_producto + "asociado a la bodega: " + idBodega);
        long resp = ps.eliminarProductosBodegas(id_producto, idBodega);
        log.info ("Eliminando ProductosEstantes: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los productosBodega en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos productosBodegas con todos los productosBodegas que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductosBodegas> darProductosBodegas ()
	{
        log.info ("Listando Productos_Bodegas");
        List<ProductosBodegas> productosBodegas = ps.darProductosBodegas();
        log.info ("Listando Productos_Bodegas: " + productosBodegas.size() + " productos_bodegas existentes");
        return productosBodegas;
	}

	/**
	 * Encuentra todos los ProductosBodegas en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos productosBodegas con todos los productosBodegas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductosBodegas> darVOProductosBodegas ()
	{
		log.info ("Generando los VO de productosBodegas");
		List<VOProductosBodegas> voProductosBodegas = new LinkedList<VOProductosBodegas> ();
		for (VOProductosBodegas productosBodegas: ps.darProductosBodegas()) 
		{
			voProductosBodegas.add (productosBodegas);
		}
		log.info ("Generando los VO de productosBodegas: " + voProductosBodegas.size () + " ProductosBodegas existentes");
		return voProductosBodegas;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar PROVEEDORESSUCURSALES
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente proveedores asociadas a sucursales
	 * Adiciona entradas al log de la aplicación
	 * @param nitProveedor - identificación del proveedor
	 * @param idSucursal - identificación de la sucursal
	 * @return Un objeto ProveedoresSucursales con los valores dados
	 */
	public ProveedoresSucursales adicionarProveedoresSucursales (long nitProveedor , long idSucursal)
	{
        log.info ("Adicionando Proveedor: " + nitProveedor + "asociado a: " + idSucursal + "sucursal");
        ProveedoresSucursales proveedoresSucursales = ps.adicionarProveedoresSucursales(nitProveedor, idSucursal);
        log.info ("Adicionando ProveedoresSucursales: " + proveedoresSucursales + " tuplas insertadas");
        return proveedoresSucursales;
	}

	/**
	 * Elimina un ProveedoresSucursales por sus identificadores
	 * Adiciona entradas al log de la aplicación
	 * @param nitProveedor - identificación del proveedor
	 * @param idSucursal - identificación de la sucursal
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProveedoresSucursales (long nitProveedor , long idSucursal)
	{
        log.info ("Eliminando proveedor " + nitProveedor + "asociado a la sucursal: " + idSucursal);
        long resp = ps.eliminarProveedoresSucursales(nitProveedor, idSucursal);
        log.info ("Eliminando ProveedoresSucursales: " + resp);
        return resp;
	}

	/**
	 * Encuentra todos los proveedoresSucursales en SuperAPersistenciaSuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos proveedoresSucursales con todos los proveedoresSucursales que conoce la aplicación, llenos con su información básica
	 */
	public List<ProveedoresSucursales> darProveedoresSucursales ()
	{
        log.info ("Listando Proveedores_Sucursales");
        List<ProveedoresSucursales> proveedoresSucursales = ps.darProveedoresSucursales();
        log.info ("Listando Proveedores_Sucursales: " + proveedoresSucursales.size() + " proveedores_sucursales existentes");
        return proveedoresSucursales;
	}

	/**
	 * Encuentra todos los ProveedoresSucursales en SuperAPersistenciaSuperAndes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos proveedoresSucursales con todos los proveedoresSucursales que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProveedoresSucursales> darVOProveedoresSucursales ()
	{
		log.info ("Generando los VO de proveedoresSucursales");
		List<VOProveedoresSucursales> voProveedoresSucursales = new LinkedList<VOProveedoresSucursales> ();
		for (VOProveedoresSucursales proveedoresSucursales: ps.darProveedoresSucursales()) 
		{
			voProveedoresSucursales.add (proveedoresSucursales);
		}
		log.info ("Generando los VO de proveedoresSucursales: " + voProveedoresSucursales.size () + " ProveedoresSucursales existentes");
		return voProveedoresSucursales;
	}


	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de SuperAPersistenciaSuperAndes
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarSuperAPersistenciaSuperAndes ()
	{
        log.info ("Limpiando la BD de SuperAPersistenciaSuperAndes");
        long [] borrrados = ps.limpiarSuperAPersistenciaSuperAndes();	
        log.info ("Limpiando la BD de SuperAPersistenciaSuperAndes: Listo!");
        return borrrados;
	}
}
