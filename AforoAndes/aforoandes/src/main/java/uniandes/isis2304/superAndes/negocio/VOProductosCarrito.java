package uniandes.isis2304.superAndes.negocio;

public interface VOProductosCarrito {
    public long getIdCarrito();
    public long getCodigoBarras();
    public int getCantidad();
    @Override
    public String toString();
}
