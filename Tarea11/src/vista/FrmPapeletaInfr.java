package vista;

import java.awt.EventQueue;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entidades.Infractor;
import entidades.Papeleta;
import entidades.Policia;
import modelo.SqlServerInfractor;
import modelo.SqlServerPapeleta;
import modelo.SqlServerPolicia;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPapeletaInfr extends JInternalFrame implements MouseListener, ActionListener {
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txtBuscar;
	private JLabel lblCdigo;
	private JButton btnBuscar;
	private JButton btnResetear;
	private JScrollPane scrollPane;
	private JTable tblInfractor;
	private JScrollPane scrollPane_1;
	private JTable tblPapeleta;
	
	private DefaultTableModel modeloInfr;
	private DefaultTableModel modeloPap;
	
	private SqlServerInfractor mInfr = new SqlServerInfractor();
	private SqlServerPapeleta mPap = new SqlServerPapeleta();
	private DateTimeFormatter formatoLocal = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPapeletaInfr frame = new FrmPapeletaInfr();
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
	public FrmPapeletaInfr() {

		initComponents();
		
		modeloInfr = new DefaultTableModel();
		modeloInfr.addColumn("C�digo");
		modeloInfr.addColumn("Nombre");
		modeloInfr.addColumn("Direcci�n");
		modeloInfr.addColumn("Tel�fono");
		modeloInfr.addColumn("Correo");
		
		tblInfractor.setModel(modeloInfr);

		modeloPap = new DefaultTableModel();
		modeloPap.addColumn("C�digo");
		modeloPap.addColumn("Polic�a");
		modeloPap.addColumn("Infractor");
		modeloPap.addColumn("Fecha");
		modeloPap.addColumn("Monto");
		modeloPap.addColumn("Descripci�n");
		
		tblPapeleta.setModel(modeloPap);
		
		ListadoPol(mInfr.listarInf());
	}
	
	private void ListadoPol(ArrayList<Infractor> lista){
		modeloInfr.setRowCount(0);
		
		for(Infractor item : lista){
			modeloInfr.addRow(new Object[]{
					item.getCodigo(),
					item.getApe(),
					item.getDir(),
					item.getTele(),
					item.getMail()
			});
		}
	}
	
	
	private void ListadoPap(ArrayList<Papeleta> lista){
		modeloPap.setRowCount(0);
		for(Papeleta item : lista){
			modeloPap.addRow(new Object[]{
					item.getCodigo(),
					item.getApePol(),
					item.getApeInf(),
					item.getFecha().format(formatoLocal),
					item.getMonto(),
					item.getDesc()
			});
		}
	}
	
	private void initComponents() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 669, 546);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Policias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 633, 227);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(82, 22, 97, 20);
		panel.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(22, 25, 46, 14);
		panel.add(lblCdigo);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(189, 21, 65, 23);
		panel.add(btnBuscar);
		
		btnResetear = new JButton("Resetear");
		btnResetear.addActionListener(this);
		btnResetear.setBounds(264, 21, 77, 23);
		panel.add(btnResetear);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 613, 157);
		panel.add(scrollPane);
		
		tblInfractor = new JTable();
		tblInfractor.addMouseListener(this);
		tblInfractor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblInfractor);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Papeletas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 249, 633, 256);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 36, 613, 209);
		panel_1.add(scrollPane_1);
		
		tblPapeleta = new JTable();
		tblPapeleta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tblPapeleta);
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
			mouseReleasedTblPolicia(e);
		}
	}
	protected void mouseReleasedTblPolicia(MouseEvent e) {
		if(!tblInfractor.getSelectionModel().isSelectionEmpty()){
			int fila = tblInfractor.getSelectedRow();
			String cod = tblInfractor.getValueAt(fila, 0).toString();
			ListadoPap(mPap.buscarPorInfr(cod));
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == btnResetear) {
			actionPerformedBtnResetear(arg0);
		}
	}
	protected void actionPerformedBtnResetear(ActionEvent arg0) {
		ListadoPol(mInfr.listarInf());
		txtBuscar.setText("");
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		String cod = txtBuscar.getText();
		Infractor i = mInfr.buscarInf(cod);
		modeloInfr.setRowCount(0);
		modeloInfr.addRow(new Object[]{
				i.getCodigo(),
				i.getApe(),
				i.getDir(),
				i.getTele(),
				i.getMail()
		});
	}
}
