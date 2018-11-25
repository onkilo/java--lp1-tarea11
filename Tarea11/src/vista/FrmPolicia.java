package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import entidades.Policia;
import modelo.SqlServerPolicia;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class FrmPolicia extends JInternalFrame implements ActionListener, MouseListener {
	private JLabel lblCdigo;
	private JLabel lblNombre;
	private JLabel lblDireccin;
	private JLabel lblTelfono;
	private JLabel lblCorreo;
	private JPanel panel;
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
	private JTable tblPolicia;
	
	
	private SqlServerPolicia mPol = new SqlServerPolicia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPolicia frame = new FrmPolicia();
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
	public FrmPolicia() {

		initComponents();
		
		modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Dirección");
		modelo.addColumn("Teléfono");
		modelo.addColumn("Correo");
		
		tblPolicia.setModel(modelo);
		
		Listado(mPol.listarPol());
		Habilitar(false);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodigo, txtNombre, txtDireccion, txtTelefono, txtCorreo, btnNuevo, btnModificar, btnEliminar}));
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
	
	private void Listado(ArrayList<Policia> lista){
		modelo.setRowCount(0);
		
		for(Policia item : lista){
			modelo.addRow(new Object[]{
					item.getCodigo(),
					item.getApe(),
					item.getDir(),
					item.getTele(),
					item.getMail()
			});
		}
	}
	
	private void InsertarPol(){
		Policia p = new Policia();
		p.setCodigo(txtCodigo.getText());
		p.setApe(txtNombre.getText());
		p.setDir(txtDireccion.getText());
		p.setTele(txtTelefono.getText());
		p.setMail(txtCorreo.getText());
		
		if(mPol.insertarPol(p)){
			Listado(mPol.listarPol());
			Limpiar();
		}
	}
	
	private void ModificarPol(){
		Policia p = new Policia();
		p.setCodigo(txtCodigo.getText());
		p.setApe(txtNombre.getText());
		p.setDir(txtDireccion.getText());
		p.setTele(txtTelefono.getText());
		p.setMail(txtCorreo.getText());
		
		if(mPol.modificarPol(p)){
			Listado(mPol.listarPol());
		}
	}
	
	public void EliminarPol(){
		int confirmar = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este registro?");
		if(confirmar == JOptionPane.YES_OPTION){
			int fila = tblPolicia.getSelectedRow();
			if(mPol.eliminarPol(tblPolicia.getValueAt(fila, 0).toString())){
				JOptionPane.showMessageDialog(this, "Registro eliminado");
				Listado(mPol.listarPol());
				Limpiar();
			}
		}
		
	}
	
	private void initComponents() {
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 675, 565);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Polic\u00EDa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(57, 11, 320, 231);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Serif", Font.PLAIN, 12));
		lblCdigo.setBounds(21, 43, 46, 14);
		panel.add(lblCdigo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Serif", Font.PLAIN, 12));
		lblNombre.setBounds(21, 79, 46, 14);
		panel.add(lblNombre);
		
		lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setFont(new Font("Serif", Font.PLAIN, 12));
		lblDireccin.setBounds(21, 118, 46, 14);
		panel.add(lblDireccin);
		
		lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Serif", Font.PLAIN, 12));
		lblTelfono.setBounds(21, 153, 46, 14);
		panel.add(lblTelfono);
		
		lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Serif", Font.PLAIN, 12));
		lblCorreo.setBounds(21, 185, 46, 14);
		panel.add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(98, 183, 160, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(98, 151, 160, 20);
		panel.add(txtTelefono);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(98, 116, 160, 20);
		panel.add(txtDireccion);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(98, 77, 160, 20);
		panel.add(txtNombre);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(98, 41, 98, 20);
		panel.add(txtCodigo);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodigo, txtNombre, txtDireccion, txtTelefono, txtCorreo}));
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(409, 25, 154, 206);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
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
		panel_2.setBorder(new TitledBorder(null, "Listado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(33, 253, 585, 271);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 565, 213);
		panel_2.add(scrollPane);
		
		tblPolicia = new JTable();
		tblPolicia.addMouseListener(this);
		tblPolicia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblPolicia);
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
			ModificarPol();
		}
		
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		if(btnModificar.getText().equals("Modificar")){
			if(!tblPolicia.getSelectionModel().isSelectionEmpty()){
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
			InsertarPol();
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
			tblPolicia.clearSelection();
		}
		
		else if(btnEliminar.getText().equals("Eliminar")){
			if(!tblPolicia.getSelectionModel().isSelectionEmpty()){
				EliminarPol();
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
		if (e.getSource() == tblPolicia) {
			mouseReleasedTblPolicia(e);
		}
	}
	protected void mouseReleasedTblPolicia(MouseEvent e) {
		if(!tblPolicia.getSelectionModel().isSelectionEmpty()){
			int fila = tblPolicia.getSelectedRow();
			txtCodigo.setText(tblPolicia.getValueAt(fila, 0).toString());
			txtNombre.setText(tblPolicia.getValueAt(fila, 1).toString());
			txtDireccion.setText(tblPolicia.getValueAt(fila, 2).toString());
			txtTelefono.setText(tblPolicia.getValueAt(fila, 3).toString());
			txtCorreo.setText(tblPolicia.getValueAt(fila, 4).toString());
		}
	}
}
