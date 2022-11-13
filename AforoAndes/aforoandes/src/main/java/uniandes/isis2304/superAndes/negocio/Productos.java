package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

public class Productos implements VOProductos
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private String nombre;

	private String marca;

	private long precio_unitario;

	private String presentacion;

	private long cantidad_presentacion;
	
	private long medida; 

	private String empacado;

	private long codigo_barras;


	private long existenciasbodega;

	private long existenciasestante;

	private long tipoProducto;

	private long categoria;

	private Timestamp fechaVencimiento;



	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Productos(){
		this.nombre = "";
		this.marca = "";
		this.precio_unitario = 0;
		this.presentacion = "";
		this.cantidad_presentacion = 0;
		this.medida = 0;
		this.empacado = "";
		this.codigo_barras = 0;
		this.existenciasbodega = 0;
		this.existenciasestante = 0;
		this.tipoProducto = 0;
		this.categoria = 0;
		this.fechaVencimiento = new Timestamp(0);
	}

	public Productos(String nombre, String marca, long precio_unitario, String presentacion,
	long cantidad_presentacion, long medida, String empacado, long codigo_barras, long existenciasbodega,
	long existenciasestante, long tipoProducto, long categoria, Timestamp fechaVencimiento) {
		this.nombre = nombre;
		this.marca = marca;
		this.precio_unitario = precio_unitario;
		this.presentacion = presentacion;
		this.cantidad_presentacion = cantidad_presentacion;
		this.medida = medida;
		this.empacado = empacado;
		this.codigo_barras = codigo_barras;
		this.existenciasbodega = existenciasbodega;
		this.existenciasestante = existenciasestante;
		this.tipoProducto = tipoProducto;
		this.categoria = categoria;
		this.fechaVencimiento = fechaVencimiento;
	}

	public Timestamp getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Timestamp fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public long getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(long precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public long getCantidad_presentacion() {
		return cantidad_presentacion;
	}

	public void setCantidad_presentacion(long cantidad_presentacion) {
		this.cantidad_presentacion = cantidad_presentacion;
	}

	public long getMedida() {
		return medida;
	}

	public void setMedida(long medida) {
		this.medida = medida;
	}

	public String getEmpacado() {
		return empacado;
	}

	public void setEmpacado(String empacado) {
		this.empacado = empacado;
	}

	public long getCodigo_barras() {
		return codigo_barras;
	}

	public void setCodigo_barras(long codigo_barras) {
		this.codigo_barras = codigo_barras;
	}

	public long getExistenciasbodega() {
		return existenciasbodega;
	}

	public void setExistenciasbodega(long existenciasbodega) {
		this.existenciasbodega = existenciasbodega;
	}

	public long getExistenciasestante() {
		return existenciasestante;
	}

	public void setExistenciasestante(long existenciasestante) {
		this.existenciasestante = existenciasestante;
	}

	public long getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(long tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public long getCategoria() {
		return categoria;
	}

	public void setCategoria(long categoria) {
		this.categoria = categoria;
	}


	
	@Override

	public String toString() 
	{
		return "Productos [codigobarras=" + codigo_barras + ", nombre=" + nombre + ", marca=" + marca + ", precio_unitario=" + precio_unitario
				+ ", cantidad_presentacion=" + cantidad_presentacion + ", medida=" + medida + ", empacado=" + empacado 
				+ ", existenciasbodega=" + existenciasbodega + ", existenciasestante=" + existenciasestante 
				+ ", tipoProducto=" + tipoProducto + ", categoria=" + categoria + "]";
	}

}
