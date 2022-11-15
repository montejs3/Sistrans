package uniandes.isis2304.superAndes.negocio;

public interface VOCarrito {
    public long getIdCarrito();
    public long getIdUsuario();
    public int getTotal() ;
    @Override
    public String toString();
    public Object getCliente();
}
