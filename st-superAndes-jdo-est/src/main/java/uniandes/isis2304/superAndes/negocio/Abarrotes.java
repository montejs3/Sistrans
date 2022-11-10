package uniandes.isis2304.superAndes.negocio;

public class Abarrotes implements VOAbarrotes{
    private long id_producto;

    private long tipoProducto;

    public Abarrotes() {
        this.id_producto = 0;
        this.tipoProducto = 0;
    }

    public Abarrotes(long id_producto, long tipoProducto) {
        this.id_producto = id_producto;
        this.tipoProducto = tipoProducto;
    }

    public long getId_producto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public long getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(long tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Override
    public String toString() {
        return "Abarrotes [id_producto=" + id_producto + ", tipoProducto=" + tipoProducto + "]";
    }
}
