package ejecutable;

import controlador.ControlAlumno;
import persistencias.AlumnoPersistencia;
import vista.VConsultaAlumno;

public class AppAlumno {

	public static void main(String[] args) {
		
		VConsultaAlumno ventana = new VConsultaAlumno();
		ControlAlumno control = new ControlAlumno(ventana);
		
		ventana.setControlador(control);
		ventana.hacerVisible();
		ventana.tablaPruebas(new AlumnoPersistencia().cargarAlumnos());
	}

}
