package uniandes.isis2304.superAndes.negocio;

public interface VOProductoPerecedero {
    public long getId_producto();

    public String getFecha_vencimiento();

    public long getTipoProducto();

    @Override
    public String toString();
}
