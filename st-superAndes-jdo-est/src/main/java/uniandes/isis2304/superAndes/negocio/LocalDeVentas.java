package uniandes.isis2304.superAndes.negocio;

public class LocalDeVentas implements VOLocalDeVentas {
    
    private long id_local;

    private long idSucursal;

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public LocalDeVentas() {
        this.id_local = 0;
        this.idSucursal = 0;
    }

    public LocalDeVentas(long id_local) {
        this.id_local = id_local;
    }

    public long getId_local() {
        return id_local;
    }

    public void setId_local(long id_local) {
        this.id_local = id_local;
    }
    
    @Override
        public String toString() {
            return "LocalDeVentas [id_local=" + id_local + "]";
        }
}
