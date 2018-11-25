package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entidades.Policia;
import modelo.SqlServerPolicia;

import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class FrmBuscarPol extends JDialog implements ActionListener, MouseListener {

	private final JPanel contentPanel = new JPanel();
	private JTable tblPolicia;

	private DefaultTableModel modelo;
	private JTextField txtBuscar;
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JPanel panel;
	private JButton btnBuscar;
	private JLabel lblCdigo;
	private JButton btnResetear;
	
	private SqlServerPolicia mPol = new SqlServerPolicia();
	private FrmPapeleta2 parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			FrmBuscarPol dialog = new FrmBuscarPol();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmBuscarPol() {
		setModal(true);
		setBounds(100, 100, 649, 383);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar polic\u00EDa",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(21, 22, 585, 278);
		contentPanel.add(panel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 565, 199);
		panel.add(scrollPane);

		tblPolicia = new JTable();
		tblPolicia.addMouseListener(this);
		tblPolicia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblPolicia);

		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(20, 28, 65, 14);
		panel.add(lblCdigo);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(79, 25, 86, 20);
		panel.add(txtBuscar);
		txtBuscar.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(184, 24, 65, 23);
		panel.add(btnBuscar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(387, 310, 89, 23);
		contentPanel.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(517, 310, 89, 23);
		contentPanel.add(btnCancelar);

		modelo = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Dirección");
		modelo.addColumn("Teléfono");
		modelo.addColumn("Correo");

		tblPolicia.setModel(modelo);

		btnResetear = new JButton("Resetear");
		btnResetear.addActionListener(this);
		btnResetear.setBounds(259, 24, 77, 23);
		panel.add(btnResetear);
		
		
		Listado(mPol.listarPol());
	}
	
	public FrmBuscarPol(FrmPapeleta2 parent) {
		this();
		this.parent = parent;
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
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(arg0);
		}
		if (arg0.getSource() == btnResetear) {
			actionPerformedBtnResetear(arg0);
		}
		if (arg0.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
	}
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		this.dispose();
	}
	protected void actionPerformedBtnResetear(ActionEvent arg0) {
		txtBuscar.setText("");
		Listado(mPol.listarPol());
	}
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
		if(!tblPolicia.getSelectionModel().isSelectionEmpty()){
			int fila = tblPolicia.getSelectedRow();
			Policia p = new Policia();
			p.setCodigo(tblPolicia.getValueAt(fila, 0).toString());
			p.setApe(tblPolicia.getValueAt(fila, 1).toString());
			p.setDir(tblPolicia.getValueAt(fila, 2).toString());
			p.setTele(tblPolicia.getValueAt(fila, 3).toString());
			p.setMail(tblPolicia.getValueAt(fila, 4).toString());
			this.parent.pol = p;
			this.parent.getTxtPol().setText(p.getApe());
			this.dispose();	
		}
		else {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un registro para realizar esta acción");
		}
		
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		String cod = txtBuscar.getText();
		Policia p = mPol.buscarPol(cod);
		if(p != null){
			modelo.setRowCount(0);
			modelo.addRow(new Object[]{
					p.getCodigo(),
					p.getApe(),
					p.getDir(),
					p.getTele(),
					p.getMail()
			});
		}
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblPolicia) {
			mouseClickedTblPolicia(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTblPolicia(MouseEvent e) {
		if(e.getClickCount() == 2){
			if(!tblPolicia.getSelectionModel().isSelectionEmpty()){
				int fila = tblPolicia.getSelectedRow();
				Policia p = new Policia();
				p.setCodigo(tblPolicia.getValueAt(fila, 0).toString());
				p.setApe(tblPolicia.getValueAt(fila, 1).toString());
				p.setDir(tblPolicia.getValueAt(fila, 2).toString());
				p.setTele(tblPolicia.getValueAt(fila, 3).toString());
				p.setMail(tblPolicia.getValueAt(fila, 4).toString());
				this.parent.pol = p;
				this.parent.getTxtPol().setText(p.getApe());
				this.dispose();	
			}
			else {
				JOptionPane.showMessageDialog(this, "Debe seleccionar un registro para realizar esta acción");
			}
		}
	}
}
