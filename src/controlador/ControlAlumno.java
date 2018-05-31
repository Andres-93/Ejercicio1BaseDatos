package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import persistencias.AlumnoPersistencia;
import vista.VConsultaAlumno;

public class ControlAlumno implements ActionListener {

	private VConsultaAlumno ventana;
	
	
	public ControlAlumno(VConsultaAlumno ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ventana.getBtnSalit())) {
			System.exit(0);
		}else if(e.getSource().equals(ventana.getBtnBorrar())) {
			new AlumnoPersistencia().eliminarAlumno(ventana.obtenerSeleccionado());
			ventana.tablaPruebas(new AlumnoPersistencia().cargarAlumnos());
			ventana.getBtnBorrar().setEnabled(false);
		}

	}

}
