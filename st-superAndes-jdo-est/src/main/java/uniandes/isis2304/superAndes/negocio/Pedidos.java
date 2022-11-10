package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public class Pedidos implements VOPedidos{
    private long id_pedido;
    private String productos;
    private double volumenes;
    private double precio;
    private Date fecha;
    
    public Pedidos() {
        this.id_pedido = 0;
        this.productos = "";
        this.volumenes = 0;
        this.precio = 0;
        this.fecha = new Date();
    }

    public Pedidos(long id_pedido, String productos, double volumenes, double precio, Date fecha) {
        this.id_pedido = id_pedido;
        this.productos = productos;
        this.volumenes = volumenes;
        this.precio = precio;
        this.fecha = fecha;
    }

    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public double getVolumenes() {
        return volumenes;
    }

    public void setVolumenes(double volumenes) {
        this.volumenes = volumenes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Pedidos [id_pedido=" + id_pedido + ", productos=" + productos + ", volumenes=" + volumenes + ", precio="
                + precio + ", fecha=" + fecha + "]";
    }


}
