package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

public interface VOProductos {

    public String getNombre();

	public String getMarca();

	public long getPrecio_unitario();

	public String getPresentacion();

	public long getCantidad_presentacion();

	public long getMedida();

	public String getEmpacado();

	public long getCodigo_barras();

	public long getExistenciasbodega();

	public long getExistenciasestante();

	public long getTipoProducto();

	public long getCategoria();

	public Timestamp getFechaVencimiento();

    @Override
	public String toString();
}
