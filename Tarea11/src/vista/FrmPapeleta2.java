package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import entidades.Infractor;
import entidades.Papeleta;
import entidades.Policia;
import modelo.SqlServerInfractor;
import modelo.SqlServerPapeleta;
import modelo.SqlServerPolicia;
import util.PapeletaTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.MouseEvent;

public class FrmPapeleta2 extends JInternalFrame implements ActionListener, MouseListener {
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JPanel panel_2;
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable tblPapeletas;
	private JLabel label;
	private JLabel lblPolica;
	private JLabel lblInfractor;
	private JLabel lblFecha;
	private JLabel lblMonto;
	private JTextField txtMonto;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JLabel lblDescripcin;
	private JDateChooser dcFecha;
	private DefaultTableModel modelo;
	
	private SqlServerPolicia mPol = new SqlServerPolicia();
	private SqlServerInfractor mInf = new SqlServerInfractor();
	private SqlServerPapeleta mPapa = new SqlServerPapeleta();
	public Policia pol;
	public Infractor infr;
	
	private SimpleDateFormat formatoDate = new SimpleDateFormat("dd-MM-yyyy");
	private DateTimeFormatter formatoLocal = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private JTextField txtPolicia;
	private JTextField txtInfractor;
	private JButton btnBuscarPol;
	private JButton btnBuscarInfr;
	private PapeletaTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPapeleta2 frame = new FrmPapeleta2();
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
	public FrmPapeleta2() {

		initComponents();
		
		modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Policía");
		modelo.addColumn("Infractor");
		modelo.addColumn("Fecha");
		modelo.addColumn("Monto");
		modelo.addColumn("Descripción");
		
		tblPapeletas.setModel(modelo);
		
		model = new PapeletaTableModel();
		tblPapeletas.setModel(model);
		
		dcFecha.setCalendar(new GregorianCalendar());

		Listado(mPapa.listarPap());
		Habilitar(false);
	}
	
	private void Limpiar(){
		txtCodigo.setText("");
		txtPolicia.setText("");
		txtInfractor.setText("");
		dcFecha.setCalendar(new GregorianCalendar());
		txtMonto.setText("");
		txtDescripcion.setText("");
	}
	
	private void Habilitar(boolean estado){
		txtCodigo.setEnabled(estado);
		txtPolicia.setEnabled(estado);
		txtInfractor.setEnabled(estado);
		dcFecha.setEnabled(estado);
		txtMonto.setEnabled(estado);
		txtDescripcion.setEnabled(estado);
		btnBuscarPol.setEnabled(estado);
		btnBuscarInfr.setEnabled(estado);
	}
	
	private void Listado(ArrayList<Papeleta> lista){
		model.clearData();
		for(Papeleta item : lista){
			/*modelo.addRow(new Object[]{
					item.getCodigo(),
					item.getApePol(),
					item.getApeInf(),
					item.getFecha().format(formatoLocal),
					item.getMonto(),
					item.getDesc()
			});*/
			
			model.addPapeleta(item);
		}
	}
	
	private void InsertarPap(){
		Papeleta p = new Papeleta();
		p.setCodigo(txtCodigo.getText());
		p.setCodPol(pol.getCodigo());
		p.setCodInf(infr.getCodigo());
		LocalDate fec = dcFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		p.setFecha(fec);
		p.setMonto(Double.parseDouble(txtMonto.getText()));
		p.setDesc(txtDescripcion.getText());
		
		if(mPapa.InsertarPap(p)){
			Limpiar();
			Listado(mPapa.listarPap());
		}
	}
	
	private void ModificarPap(){
		Papeleta p = new Papeleta();
		p.setCodigo(txtCodigo.getText());
		p.setCodPol(pol.getCodigo());
		p.setCodInf(infr.getCodigo());
		LocalDate fec = dcFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		p.setFecha(fec);
		p.setMonto(Double.parseDouble(txtMonto.getText()));
		p.setDesc(txtDescripcion.getText());
		
		if(mPapa.ModificarPap(p)){
			Limpiar();
			Listado(mPapa.listarPap());
		}
	}
	
	private void EliminarPap(){
		int confirmar = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este registro?");
		if(confirmar == JOptionPane.YES_OPTION){
			int fila = tblPapeletas.getSelectedRow();
			if(mPapa.EliminarPap(tblPapeletas.getValueAt(fila, 0).toString())){
				JOptionPane.showMessageDialog(this, "Registro eliminado");
				Listado(mPapa.listarPap());
				Limpiar();
			}
		}
	}
	
	public JTextField getTxtPol(){
		return txtPolicia;
	}
	
	public JTextField getTxtInfr(){
		return txtInfractor;
	}
	
	private void initComponents() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 675, 565);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Papeleta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 11, 362, 255);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		label = new JLabel("C\u00F3digo");
		label.setFont(new Font("Serif", Font.PLAIN, 12));
		label.setBounds(25, 33, 46, 14);
		panel.add(label);
		
		lblPolica = new JLabel("Polic\u00EDa");
		lblPolica.setFont(new Font("Serif", Font.PLAIN, 12));
		lblPolica.setBounds(25, 69, 46, 14);
		panel.add(lblPolica);
		
		lblInfractor = new JLabel("Infractor");
		lblInfractor.setFont(new Font("Serif", Font.PLAIN, 12));
		lblInfractor.setBounds(25, 108, 46, 14);
		panel.add(lblInfractor);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Serif", Font.PLAIN, 12));
		lblFecha.setBounds(25, 137, 46, 20);
		panel.add(lblFecha);
		
		lblMonto = new JLabel("Monto");
		lblMonto.setFont(new Font("Serif", Font.PLAIN, 12));
		lblMonto.setBounds(25, 175, 46, 14);
		panel.add(lblMonto);
		
		txtMonto = new JTextField();
		txtMonto.setEnabled(false);
		txtMonto.setColumns(10);
		txtMonto.setBounds(102, 173, 98, 20);
		panel.add(txtMonto);
		
		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(102, 31, 98, 20);
		panel.add(txtCodigo);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(102, 211, 160, 20);
		panel.add(txtDescripcion);
		
		lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setFont(new Font("Serif", Font.PLAIN, 12));
		lblDescripcin.setBounds(25, 213, 67, 14);
		panel.add(lblDescripcin);
		
		dcFecha = new JDateChooser();
		dcFecha.setEnabled(false);
		dcFecha.setBounds(102, 137, 160, 20);
		panel.add(dcFecha);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(415, 40, 154, 206);
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
		panel_2.setBorder(new TitledBorder(null, "Listado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 277, 639, 247);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		scrollPane.setBounds(10, 43, 619, 193);
		panel_2.add(scrollPane);
		
		tblPapeletas = new JTable();
		tblPapeletas.addMouseListener(this);
		tblPapeletas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblPapeletas);
		
		txtPolicia = new JTextField();
		txtPolicia.setBounds(102, 67, 160, 20);
		panel.add(txtPolicia);
		txtPolicia.setColumns(10);
		
		txtInfractor = new JTextField();
		txtInfractor.setColumns(10);
		txtInfractor.setBounds(102, 106, 160, 20);
		panel.add(txtInfractor);
		
		btnBuscarPol = new JButton("...");
		btnBuscarPol.addActionListener(this);
		btnBuscarPol.setBounds(266, 67, 46, 20);
		panel.add(btnBuscarPol);
		
		btnBuscarInfr = new JButton("...");
		btnBuscarInfr.addActionListener(this);
		btnBuscarInfr.setBounds(266, 105, 46, 20);
		panel.add(btnBuscarInfr);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscarInfr) {
			actionPerformedBtnBuscarInfr(arg0);
		}
		if (arg0.getSource() == btnBuscarPol) {
			actionPerformedBtnBuscarPol(arg0);
		}
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
		}
		else if(btnNuevo.getText().equals("Guardar")){
			ModificarPap();
		}
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		if(btnModificar.getText().equals("Modificar")){
			if(!tblPapeletas.getSelectionModel().isSelectionEmpty()){
				btnNuevo.setText("Guardar");
				btnModificar.setEnabled(false);
				btnEliminar.setText("Cancelar");
				Habilitar(true);
			}
			else{
				JOptionPane.showMessageDialog(this, "Debe seleccionar un registro para realizar esta acción");
			}
		}
		else if(btnModificar.getText().equals("Guardar")){
			InsertarPap();
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
			tblPapeletas.clearSelection();
		}
		else if(btnEliminar.getText().equals("Eliminar")){
			if(!tblPapeletas.getSelectionModel().isSelectionEmpty()){
				EliminarPap();
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
	public void mouseReleased(MouseEvent arg0) {
		if (arg0.getSource() == tblPapeletas) {
			mouseReleasedTblPapeletas(arg0);
		}
	}
	protected void mouseReleasedTblPapeletas(MouseEvent arg0) {
		if(!tblPapeletas.getSelectionModel().isSelectionEmpty()){
			int fila = tblPapeletas.getSelectedRow();
			/*txtCodigo.setText(tblPapeletas.getValueAt(fila, 0).toString());
			txtPolicia.setText(tblPapeletas.getValueAt(fila, 1).toString());
			txtInfractor.setText(tblPapeletas.getValueAt(fila, 2).toString());
			try {
				dcFecha.setDate(formatoDate.parse(tblPapeletas.getValueAt(fila, 3).toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtMonto.setText(tblPapeletas.getValueAt(fila, 4).toString());
			txtDescripcion.setText(tblPapeletas.getValueAt(fila, 5).toString());*/
			Papeleta p = model.getPapeleta(fila);
			txtCodigo.setText(p.getCodigo());
			txtPolicia.setText(p.getApePol());
			txtInfractor.setText(p.getApeInf());
			try {
				dcFecha.setDate(formatoDate.parse(tblPapeletas.getValueAt(fila, 3).toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtMonto.setText(p.getMonto() + "");
			txtDescripcion.setText(p.getDesc());
		}
	}
	protected void actionPerformedBtnBuscarPol(ActionEvent arg0) {
		FrmBuscarPol bPol = new FrmBuscarPol(this);
		bPol.setVisible(true);
	}
	protected void actionPerformedBtnBuscarInfr(ActionEvent arg0) {
		FrmBuscarInfr bInf = new FrmBuscarInfr(this);
		bInf.setVisible(true);
	}
}
