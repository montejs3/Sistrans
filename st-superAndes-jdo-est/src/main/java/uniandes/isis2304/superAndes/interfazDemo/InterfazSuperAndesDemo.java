/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: SuperAndes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.superAndes.interfazDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VORolUsuario;
import uniandes.isis2304.superAndes.negocio.VOSucursales;
import uniandes.isis2304.superAndes.negocio.VOSupermercado;
import uniandes.isis2304.superAndes.negocio.VOUsuario;

/**
 * Clase principal de la interfaz
 * 
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazSuperAndesDemo extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesDemo.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigDemo.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
    /**
     * Asociación a la clase principal del negocio.
     */
    private SuperAndes superAndes;
    
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
    /**
     * Objeto JSON con la configuración de interfaz de la app.
     */
    private JsonObject guiConfig;
    
    /**
     * Panel de despliegue de interacción para los requerimientos
     */
    private PanelDatos panelDatos;
    
    /**
     * Menú de la aplicación
     */
    private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazSuperAndesDemo( )
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        superAndes = new SuperAndes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    
	/* ****************************************************************
	 * 			Métodos para la configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "SuperAndes App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "SuperAndes APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    /**
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
    
	/* ****************************************************************
	 * 			RF1
	 *****************************************************************/
	public void adicionarUsuario(){
		try {
			try {

				String [] output = {"numero de documento", "palabra clave"};
				String [] input = new String[output.length];
				for (int i = 0; i<output.length;i++) {
					input[i] = JOptionPane.showInputDialog (this, output[i], "Iniciar sesion", JOptionPane.QUESTION_MESSAGE);
					if (input[i]==null) {
						panelDatos.actualizarInterfaz("No ha ingrersado datos...");
						return;
					}
				}
				VOUsuario user = superAndes.darUsuarioPorId(Long.parseLong(input[0]));
				if (user==null) {
					panelDatos.actualizarInterfaz("Las creedenciales no coinciden con un usuario registrado.");
				
                }
				if (user.getPalabra_clave().equals(input[1])) {
					VORolUsuario rolUsuario = superAndes.darRolUsuarioPorId(user.getId());

					if(rolUsuario==null){
						throw new Exception("El usuario no tiene rol.");
					}

					if(rolUsuario.getNombre().equals("ADMINISTRADOR DE DATOS")){
						try {
							String nuevoRol = new JOptionPane().showInputDialog(this, "Nombre del nuevo rol", "Introduzca un nombre para el nuevo rol", JOptionPane.QUESTION_MESSAGE);
							if(nuevoRol=="")
							{
								panelDatos.actualizarInterfaz("No ha introducido un nombre");
							}
							else
							{
								VORolUsuario voru = superAndes.adicionarRolUsuario(nuevoRol);
								if(voru==null)
                                {
									throw new Exception("No se pudo crear el rol de usuario");
								}

								panelDatos.actualizarInterfaz("Se ha creado exitosamente el rol de usuario:" + nuevoRol);
							}
						} catch (Exception e) {
							e.printStackTrace();
							panelDatos.actualizarInterfaz(generarMensajeError(e));
						}
					}
					else
                    {
						panelDatos.actualizarInterfaz("No puede realizar esta acción");
					}
				}
				else
                {
					panelDatos.actualizarInterfaz("La palabra clave introducida es incorrecta");
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			panelDatos.actualizarInterfaz(generarMensajeError(e));
		}
	}
}
//RF2
public void adicionarSucursal(){
	try {
		try {
			String [] output = {"numero de documento", "palabra clave"};
			String [] input = new String[output.length];
			for (int i = 0; i<output.length;i++) {
				input[i] = JOptionPane.showInputDialog (this, output[i], "Iniciar sesion", JOptionPane.QUESTION_MESSAGE);
				if (input[i]==null) {
					panelDatos.actualizarInterfaz("No ha ingrersado datos...");
					return;
				}
			}
			VOUsuario user = superAndes.darUsuarioPorId(Long.parseLong(input[0]));
			if (user==null)
				panelDatos.actualizarInterfaz("Las creedenciales no coinciden con un usuario registrado.");

			if(user.getPalabra_clave() == input[1])
			{
				VORolUsuario rolUser = superAndes.darRolUsuarioPorId(user.getRolUsuario());
				if(rolUser== null)
				{
					throw new Exception("El usuario no tiene rol");
				}

				if(rolUser.getNombre().equals("ADMINISTRADOS_DATOS"))
				{
					try {
						String[] preguntas = {"ciudad", "direccion", "nombre", "nivel_reorden", "recompra", "id_supermercado"};
						String[] respuestas = new String[preguntas.length];
						for (int i = 0; i < preguntas.length; i++) {
							respuestas[i] = JOptionPane.showInputDialog(this, preguntas[i], "Registro de Sucursal", JOptionPane.QUESTION_MESSAGE);
							if (respuestas[i]==null) {
								panelDatos.actualizarInterfaz("La operación ha sido interrumpida por el usuario.");
								return;
							}
						}
						VOSupermercado superM = superAndes.darSupermercado(Long.parseLong(respuestas[4]));
						if(superM==null)
                        {
							throw new Exception("No existe el supermercado especificado.");
						}
						
						String ciudad = "";
						String direccion = "";
						String nombre = "";
						int nivel_reorden = 0;
						int recompra = 0;
						long superMId = 0;
						
						try {
							ciudad = respuestas[0];
							direccion = respuestas[1];
							nombre = respuestas[2];
							nivel_reorden = Integer.parseInt(respuestas[3]);
							recompra = Integer.parseInt(respuestas[4]);
							superMId = Long.parseLong(respuestas[5]);
						} catch (NumberFormatException e) {
							throw new Exception("Uno de los números es inválido");
						}

						VOSucursales ns = superAndes.adicionarSucursales(ciudad, direccion, nombre, nivel_reorden, recompra, superMId);

						if(ns==null) throw new Exception("No se pudo crear la sucursal.");
						
						panelDatos.actualizarInterfaz("Sucursal adicionada exitosamente");
					} catch (Exception e) {
						panelDatos.actualizarInterfaz(generarMensajeError(e));
					}
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("No cuenta con los permisos para realizar la operación.");
			}
		} catch (Exception e) {
			panelDatos.actualizarInterfaz("Clave incorrecta");
		}
	} catch (Exception e) {
		panelDatos.actualizarInterfaz(generarMensajeError(e));
	}
}

//RF3
public void registrarUsuario(){
	try {
		String[] output = {"numero de documento", "palabra clave"};
		String[] input = new String[output.length];
		for (int i = 0; i<output.length;i++) {
			input[i] = JOptionPane.showInputDialog (this, output[i], "Iniciar sesion", JOptionPane.QUESTION_MESSAGE);
			if (input[i]==null) {
				panelDatos.actualizarInterfaz("No ha ingrersado datos...");
				return;
			}
		}
		VOUsuario user = superAndes.darUsuarioPorId(Long.parseLong(input[0]));
		if (user==null)
			panelDatos.actualizarInterfaz("Las creedenciales no coinciden con un usuario registrado.");

		if(user.getPalabra_clave() == input[1])
		{
			VORolUsuario rolUser = superAndes.darRolUsuarioPorId(user.getRolUsuario());
			if(rolUser== null) throw new Exception("El usuario no tiene rol");

			if(rolUser.getNombre().equals("ADMINISTRADOR_DATOS"));
			{
				try {
					String[] preguntas = {"número de documento", "tipo de documento", "nombre", "correo", "palabra clave", "rol", "id de la sucursal"};
					String[] respuestas = new String[preguntas.length];

					for (int i = 0; i < preguntas.length; i++) {
						respuestas[i] = JOptionPane.showInputDialog(this, preguntas[i], "registrar usuario", JOptionPane.QUESTION_MESSAGE);
						if(respuestas[i]==null){
							panelDatos.actualizarInterfaz("La operación fue cancelada por el usuario");
							return;
						}
					}

					VORolUsuario rolNuevoUsuario = superAndes.darRolUsuarioPorId(Long.parseLong(respuestas[5]));
					if(rolNuevoUsuario == null) throw new Exception("El rol especificado no existe");

					VOSucursales sucuNuevoUsuario = superAndes.darSucursalesPorId(Long.parseLong(respuestas[6]));
					if(sucuNuevoUsuario == null) throw new Exception("La sucursal especificada no existe");

					VOUsuario nuevoUser = superAndes.adicionarUsuario(Long.parseLong(respuestas[0]), respuestas[1], respuestas[2], respuestas[3], respuestas[4], rolNuevoUsuario, sucuNuevoUsuario);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
	} catch (Exception e) {
		// TODO: handle exception
	}
}


    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
     */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y SuperAndes.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazSuperAndesDemo.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazSuperAndesDemo interfaz = new InterfazSuperAndesDemo( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
