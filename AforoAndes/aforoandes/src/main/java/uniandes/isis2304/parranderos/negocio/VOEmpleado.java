package uniandes.isis2304.parranderos.negocio;

public interface VOEmpleado {
	
	

	/**
	 * @return El nombre de la bebida
	 */
	public String getNombre();

	/**
	 * @return El id del Tipo de Bebida
	 */
	public int getCedula();

	/**
	 * @return El gradoAlcohol de la bebida
	 */
	public String getTipo();
	public String getNombresupermercado();

	/**
	 * @return Una cadena con la información básica de la bebida
	 */
	@Override
	public String toString();
	
	
	
	


}
