package persistencias;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AccesoBD {

	
	private String driver;
	private String url;
	
	public AccesoBD() {
		
		Properties propiedades = new Properties();
		InputStream entrada = null; 
		
		try {
			entrada = new FileInputStream("configuracion.properties");
			propiedades.load(entrada); 
			this.driver = propiedades.getProperty("driver");
			this.url = propiedades.getProperty("url"); 
		}catch(FileNotFoundException er) {
			er.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(entrada != null) entrada.close();
			}catch(IOException err) {
				err.printStackTrace();
			}			
		}		
	}
	
	public Connection getConexion() 
			throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url);
		System.out.println("Conexión establecida");
		return con;
	}
	
	public void desconectar(Connection con) throws SQLException {
        con.close();
   
	}
		
}
