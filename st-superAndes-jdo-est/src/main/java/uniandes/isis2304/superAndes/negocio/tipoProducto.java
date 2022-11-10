package uniandes.isis2304.superAndes.negocio;

public class tipoProducto implements VOTipoProducto{
    private long id;

    private String tipo;

    private String categoria;

    public tipoProducto() {
        this.id = 0;
        this.tipo = "";
        this.categoria = "";
    }

    public tipoProducto(long id, String tipo, String categoria) {
        this.id = id;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "tipoProducto [id=" + id + ", tipo=" + tipo + ", categoria=" + categoria + "]";
    }


}
