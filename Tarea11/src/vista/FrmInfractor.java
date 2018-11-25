package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entidades.Infractor;
import modelo.SqlServerInfractor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class FrmInfractor extends JInternalFrame implements ActionListener, MouseListener {
	private JPanel panel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JPanel panel_1;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;
	
	private SqlServerInfractor mInf = new SqlServerInfractor();
	private JTable tblInfractor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmInfractor frame = new FrmInfractor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmInfractor() {

		initComponents();
		
		modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Dirección");
		modelo.addColumn("Teléfono");
		modelo.addColumn("Correo");
		
		tblInfractor.setModel(modelo);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodigo, txtNombre, txtDireccion, txtTelefono, txtCorreo, btnNuevo, btnModificar, btnEliminar}));
		Listado(mInf.listarInf());
		Habilitar(false);
	}
	
	private void Limpiar(){
		txtCodigo.setText("");
		txtCorreo.setText("");
		txtDireccion.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");
	}
	
	private void Habilitar(boolean estado){
		txtCodigo.setEnabled(estado);
		txtNombre.setEnabled(estado);
		txtDireccion.setEnabled(estado);
		txtTelefono.setEnabled(estado);
		txtCorreo.setEnabled(estado);
		
	}
	
	private void Listado(ArrayList<Infractor> lista){
		modelo.setRowCount(0);
		for(Infractor item : lista){
			modelo.addRow(new Object[]{
					item.getCodigo(),
					item.getApe(),
					item.getDir(),
					item.getTele(),
					item.getMail()
			});
		}
	}
	
	private void InsertarInf(){
		Infractor i = new Infractor();
		i.setCodigo(txtCodigo.getText());
		i.setApe(txtNombre.getText());
		i.setDir(txtDireccion.getText());
		i.setTele(txtTelefono.getText());
		i.setMail(txtCorreo.getText());
		
		if(mInf.insertarInf(i)){
			Listado(mInf.listarInf());
			Limpiar();
		}
	}
	
	private void ModificarInf(){
		Infractor i = new Infractor();
		i.setCodigo(txtCodigo.getText());
		i.setApe(txtNombre.getText());
		i.setDir(txtDireccion.getText());
		i.setTele(txtTelefono.getText());
		i.setMail(txtCorreo.getText());
		
		if(mInf.modificarInf(i)){
			Listado(mInf.listarInf());
			Limpiar();
		}
	}
	
	private void EliminarInf(){
		int confirmar = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este registro?");
		if(confirmar == JOptionPane.YES_OPTION){
			int fila = tblInfractor.getSelectedRow();
			if(mInf.eliminarInf(tblInfractor.getValueAt(fila, 0).toString())){
				JOptionPane.showMessageDialog(this, "Registro eliminado");
				Listado(mInf.listarInf());
				Limpiar();
			}
		}
	}
	
	private void initComponents() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 632, 554);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Infractor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(62, 11, 320, 231);
		getContentPane().add(panel);
		
		label = new JLabel("C\u00F3digo");
		label.setFont(new Font("Serif", Font.PLAIN, 12));
		label.setBounds(21, 43, 46, 14);
		panel.add(label);
		
		label_1 = new JLabel("Nombre");
		label_1.setFont(new Font("Serif", Font.PLAIN, 12));
		label_1.setBounds(21, 79, 46, 14);
		panel.add(label_1);
		
		label_2 = new JLabel("Direcci\u00F3n");
		label_2.setFont(new Font("Serif", Font.PLAIN, 12));
		label_2.setBounds(21, 118, 46, 14);
		panel.add(label_2);
		
		label_3 = new JLabel("Tel\u00E9fono");
		label_3.setFont(new Font("Serif", Font.PLAIN, 12));
		label_3.setBounds(21, 153, 46, 14);
		panel.add(label_3);
		
		label_4 = new JLabel("Correo");
		label_4.setFont(new Font("Serif", Font.PLAIN, 12));
		label_4.setBounds(21, 185, 46, 14);
		panel.add(label_4);
		
		txtCorreo = new JTextField();
		txtCorreo.setEnabled(false);
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(98, 183, 160, 20);
		panel.add(txtCorreo);
		
		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(98, 151, 160, 20);
		panel.add(txtTelefono);
		
		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(98, 116, 160, 20);
		panel.add(txtDireccion);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(98, 77, 160, 20);
		panel.add(txtNombre);
		
		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(98, 41, 98, 20);
		panel.add(txtCodigo);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(407, 21, 154, 206);
		getContentPane().add(panel_1);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setFont(new Font("Serif", Font.PLAIN, 12));
		btnNuevo.setBounds(37, 28, 89, 30);
		panel_1.add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setFont(new Font("Serif", Font.PLAIN, 12));
		btnModificar.setBounds(37, 90, 89, 30);
		panel_1.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Serif", Font.PLAIN, 12));
		btnEliminar.setBounds(37, 154, 89, 30);
		panel_1.add(btnEliminar);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Listado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 242, 596, 271);
		getContentPane().add(panel_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 576, 213);
		panel_2.add(scrollPane);
		
		tblInfractor = new JTable();
		tblInfractor.addMouseListener(this);
		tblInfractor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblInfractor);
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(arg0);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		if(btnNuevo.getText().equals("Nuevo")){
			btnNuevo.setEnabled(false);
			btnModificar.setText("Guardar");
			btnEliminar.setText("Cancelar");
			Habilitar(true);
			Limpiar();
			txtCodigo.grabFocus();
		}
		
		else if(btnNuevo.getText().equals("Guardar")){
			ModificarInf();
		}
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		if(btnModificar.getText().equals("Modificar")){
			if(!tblInfractor.getSelectionModel().isSelectionEmpty()){
				btnNuevo.setText("Guardar");
				btnModificar.setEnabled(false);
				btnEliminar.setText("Cancelar");
				Habilitar(true);
				txtCodigo.grabFocus();
			}else{
				JOptionPane.showMessageDialog(this, "Debe seleccionar un registro para realizar esta acción");
			}
		}
		
		else if (btnModificar.getText().equals("Guardar")){
			InsertarInf();
		}
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		if(btnEliminar.getText().equals("Cancelar")){
			btnNuevo.setText("Nuevo");
			btnModificar.setText("Modificar");
			btnNuevo.setEnabled(true);
			btnModificar.setEnabled(true);
			btnEliminar.setText("Eliminar");
			Habilitar(false);
			Limpiar();
			tblInfractor.clearSelection();
		}
		
		else if(btnEliminar.getText().equals("Eliminar")){
			if(!tblInfractor.getSelectionModel().isSelectionEmpty()){
				EliminarInf();
			}else{
				JOptionPane.showMessageDialog(this, "Debe seleccionar un registro para realizar esta acción");
			}
			
		}
	}
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == tblInfractor) {
			mouseReleasedTblInfractor(e);
		}
	}
	protected void mouseReleasedTblInfractor(MouseEvent e) {
		if(!tblInfractor.getSelectionModel().isSelectionEmpty()){
			int fila = tblInfractor.getSelectedRow();
			txtCodigo.setText(tblInfractor.getValueAt(fila, 0).toString());
			txtNombre.setText(tblInfractor.getValueAt(fila, 1).toString());
			txtDireccion.setText(tblInfractor.getValueAt(fila, 2).toString());
			txtTelefono.setText(tblInfractor.getValueAt(fila, 3).toString());
			txtCorreo.setText(tblInfractor.getValueAt(fila, 4).toString());
		}
	}
}
