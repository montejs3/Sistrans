package uniandes.isis2304.parranderos.negocio;

public class Empleado implements VOEmpleado {
	
	   private String nombre;
	   private int cedula;
	   private String tipo;
	   private String nombresupermercado;
		
		
		
		public Empleado(String nombre, int cedula, String tipo, String nombresupermercado) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.tipo = tipo;
		this.nombresupermercado = nombresupermercado;
	}



		
		
		public Empleado() 
		{
			
			this.nombre = "";
			this.cedula = 0;
			this.tipo = "";
			this.nombresupermercado = "";
		}
		
		 public String getNombre() {
				return nombre;
			}





			public void setNombre(String nombre) {
				this.nombre = nombre;
			}





			public int getCedula() {
				return cedula;
			}





			public void setCedula(int cedula) {
				this.cedula = cedula;
			}





			public String getTipo() {
				return tipo;
			}





			public void setTipo(String tipo) {
				this.tipo = tipo;
			}





			public String getNombresupermercado() {
				return nombresupermercado;
			}





			public void setNombresupermercado(String nombresupermercado) {
				this.nombresupermercado = nombresupermercado;
			}
			
			   @Override
				public String toString() {
					return "Empleado [nombre=" + nombre + ", cedula=" + cedula + ", tipo=" + tipo + ", nombresupermercado="
							+ nombresupermercado + "]";
				}




}
