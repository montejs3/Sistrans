package uniandes.isis2304.superAndes.negocio;

public class ProductosEstantes implements VOProductosEstantes{
    private long idProducto;
    private long idEstante;
    public ProductosEstantes() {
        this.idProducto = 0;
        this.idEstante = 0;
    }
    public ProductosEstantes(long idProducto, long idEstante) {
        this.idProducto = idProducto;
        this.idEstante = idEstante;
    }
    public long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }
    public long getIdEstante() {
        return idEstante;
    }
    public void setIdEstante(long idEstante) {
        this.idEstante = idEstante;
    }
    @Override
    public String toString() {
        return "ProductosEstantes [idProducto=" + idProducto + ", idEstante=" + idEstante + "]";
    }
}
