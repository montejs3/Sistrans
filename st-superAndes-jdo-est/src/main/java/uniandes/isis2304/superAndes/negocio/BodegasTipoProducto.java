package uniandes.isis2304.superAndes.negocio;

public class BodegasTipoProducto implements VOBodegasTipoProducto{
    private long idBodega;
    private long idTipoProducto;
    public BodegasTipoProducto() {
        this.idTipoProducto = 0;
        this.idBodega = 0;
    }
    public BodegasTipoProducto(long idBodega, long idTipoProducto) {
        this.idBodega = idBodega;
        this.idTipoProducto = idTipoProducto;
    }
    public long getIdBodega() {
        return idBodega;
    }
    public void setIdBodega(long idBodega) {
        this.idBodega = idBodega;
    }
    public long getIdTipoProducto() {
        return idTipoProducto;
    }
    public void setIdTipoProducto(long idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }
    @Override
    public String toString() {
        return "BodegasTipoProducto [idBodega=" + idBodega + ", idTipoProducto=" + idTipoProducto + "]";
    }

}
