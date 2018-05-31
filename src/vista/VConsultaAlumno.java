package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controlador.ControlAlumno;
import modelo.Alumno;
import persistencias.AlumnoPersistencia;

import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

public class VConsultaAlumno extends JFrame {
	private JButton btnSalit;
	private JButton btnBorrar;
	private JTable table;
	private DefaultTableModel ta;
	
	public VConsultaAlumno() throws HeadlessException {
		super("Aplicacion Alumnos");
		inicializar();
	}
	private void inicializar() {
		
		try {
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		getContentPane().setLayout(null);
		
		JLabel lblListaAlumnos = new JLabel("LISTA ALUMNOS");
		lblListaAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaAlumnos.setBounds(10, 21, 144, 59);
		getContentPane().add(lblListaAlumnos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 78, 499, 227);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int[] filaSel = table.getSelectedRows();
				
				if(filaSel.length == 0) {
					btnBorrar.setEnabled(false);
				}else{
					btnBorrar.setEnabled(true);
				}
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		btnBorrar = new JButton("borrar");
		btnBorrar.setBounds(316, 326, 177, 42);
		getContentPane().add(btnBorrar);
		
		btnSalit = new JButton("SALIT");
		btnSalit.setBounds(64, 326, 177, 42);
		getContentPane().add(btnSalit);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		btnBorrar.setEnabled(false);
		
		
		
	}
	
	public void setControlador(ControlAlumno c) {
		btnBorrar.addActionListener(c);
		btnSalit.addActionListener(c);
	}
	public JButton getBtnSalit() {
		return btnSalit;
	}
	public JButton getBtnBorrar() {
		return btnBorrar;
	}
	/*
	public void cargarTabla(ArrayList<Alumno> lista) {
		

		DefaultListModel<Alumno> model = new DefaultListModel<Alumno>();
		
		for(int i = 0 ; i <lista.size(); i++) {
			
			model.addElement(lista.get(i));
		}
		 //listaAlumnos.setModel(model);
		
	}
	*/
	public Alumno obtenerSeleccionado() {
		Alumno al = new Alumno(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString(), table.getModel().getValueAt(table.getSelectedRow(), 3).toString()); //obtenemos el valor
		return al;
	}
	
	public void hacerVisible() {
		setVisible(true);
		setSize(575, 500);
	}
	
	public void tablaPruebas(ArrayList<Alumno> lista) {
		
		 ta = new DefaultTableModel() {
				
				public boolean isCellEditable(int row, int colum) {
					 return false;
				}	
			};
		table.setModel(ta);
		
		
		ta.addColumn("DNI");
		ta.addColumn("NOMBRE");
		ta.addColumn("DIRECCION");
		ta.addColumn("TELEFONO");
		
		table.getColumn("DNI").setPreferredWidth(75);
		table.getColumn("NOMBRE").setPreferredWidth(125);
		table.getColumn("DIRECCION").setPreferredWidth(75);
		table.getColumn("TELEFONO").setPreferredWidth(75);
		
		Object[] fila = new Object[4];
		
		
		for(Alumno al : lista) {
			
			fila[0] = al.getDni();
			fila[1] = al.getNombre();
			fila[2] = al.getDireccion();
			fila[3] = al.getTelefono();
			ta.addRow(fila);
		}
	}
}
