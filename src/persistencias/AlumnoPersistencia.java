package persistencias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Alumno;

public class AlumnoPersistencia {

	private AccesoBD acceso;

	public AlumnoPersistencia() {
		this.acceso = new AccesoBD();
	}
	
	
	public ArrayList<Alumno> cargarAlumnos() {
		
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		Alumno al = null;
		Connection con = null;
		Statement st = null;
        ResultSet rs = null;
		
		
		try {
			
			con = acceso.getConexion();
			System.out.println("Conectado");
			
            st = con.createStatement();
            rs = st.executeQuery("Select * from Alumno");
           
           while(rs.next()) {      	   
        	   al = new Alumno(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
        	   alumnos.add(al);      	   
           }
           
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error");
           ex.printStackTrace();
        }catch(ClassNotFoundException er) {
        	er.printStackTrace();
        }
		catch(Exception e) {
			e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Error 2", "Error", JOptionPane.CANCEL_OPTION);
        }finally {	
			try {
				if(rs != null) {
					rs.close();
				}
				if(st != null) {
					st.close();
				}
				if(con != null) {
					acceso.desconectar(con);
				}			
			}catch(SQLException e) {
				 System.out.println("No se pudo cerrar");
			}	
        }
		return alumnos;
	}
	
	
	public void eliminarAlumno(Alumno al) {
		
		 PreparedStatement st = null;
		 Connection con = null;
			
		try {
			
			con = acceso.getConexion();
			System.out.println("Conectado");
			
           st = con.prepareStatement("Delete from Alumno where dni =?");
           st.setString(1, al.getDni());
           st.execute();
           JOptionPane.showConfirmDialog(null, "Usuario eliminado correctamente", "Mensaje de confirmación", JOptionPane.CLOSED_OPTION);
	        } catch (SQLException ex) {
	           JOptionPane.showMessageDialog(null, "Error");
	        }catch(ClassNotFoundException er) {
	        	er.printStackTrace();
	        }catch(Exception e) {        
	        	JOptionPane.showMessageDialog(null, "Error 2", "Error", JOptionPane.CANCEL_OPTION);
	        }finally {
	        	
	        	try {
	        		if(st != null) {
	        			st.close();
	        		}
	        		if (con != null) {
	        			acceso.desconectar(con);
	        		}	    	
	    		}catch(SQLException e) {
	    			 System.out.println("No se pudo cerrar");
	    		}		        	
	        }				
	}	
	
	
}
