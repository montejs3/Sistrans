package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

public class Compra implements VOCompra{
    private long id;
    private int precioCompra;
    private Timestamp fecha;
    private long cliente;
    public Compra() {
    }
    public Compra(long id, int precioCompra, Timestamp fecha, long cliente) {
        this.id = id;
        this.precioCompra = precioCompra;
        this.fecha = fecha;
        this.cliente = cliente;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getPrecioCompra() {
        return precioCompra;
    }
    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }
    public Timestamp getFecha() {
        return fecha;
    }
    public void setFecha (Timestamp fecha) {
        this.fecha = fecha;
    }
    public long getCliente() {
        return cliente;
    }
    public void setCliente(long cliente) {
        this.cliente = cliente;
    }
    @Override
    public String toString() {
        return "Compra [id=" + id + ", precioCompra=" + precioCompra + ", fecha=" + fecha + ", cliente=" + cliente
                + "]";
    }
}
