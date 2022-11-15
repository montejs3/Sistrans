package uniandes.isis2304.superAndes.negocio;

public class Carrito implements VOCarrito{
    private long idCarrito;
    private long idUsuario;
    private int total;
    
    public Carrito() {
        this.idCarrito = 0;
        this.idUsuario = 0;
        this.total = 0;
    }
    public Carrito(long idCarrito, long idUsuario, int total) {
        this.idCarrito = idCarrito;
        this.idUsuario = idUsuario;
        this.total = total;
    }
    public long getIdCarrito() {
        return idCarrito;
    }
    public void setIdCarrito(long idCarrito) {
        this.idCarrito = idCarrito;
    }
    public long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return "Carrito [idCarrito=" + idCarrito + ", idUsuario=" + idUsuario + ", total=" + total + "]";
    }
    @Override
    public Object getCliente() {
        // TODO Auto-generated method stub
        return null;
    }
}
