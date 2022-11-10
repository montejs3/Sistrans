package uniandes.isis2304.superAndes.negocio;

public class ProductoPerecedero implements VOProductoPerecedero {
    private long id_producto;

    private String fecha_vencimiento;
    
    private long tipoProducto;

    public ProductoPerecedero() {
        this.id_producto = 0;
        this.fecha_vencimiento = "";
        this.tipoProducto = 0;
    }

    public ProductoPerecedero(long id_producto, String fecha_vencimiento, long tipoProducto) {
        this.id_producto = id_producto;
        this.fecha_vencimiento = fecha_vencimiento;
        this.tipoProducto = tipoProducto;
    }

    public long getId_producto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public long getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(long tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Override
    public String toString() {
        return "ProductoPerecedero [id_producto=" + id_producto + ", fecha_vencimiento=" + fecha_vencimiento
                + ", tipoProducto=" + tipoProducto + "]";
    }
}
