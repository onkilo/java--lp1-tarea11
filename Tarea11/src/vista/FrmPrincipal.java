package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mntmSalir;
	private JMenu mnMantenimiento;
	private JMenuItem mntmPolicias;
	private JMenuItem mntmInfractores;
	private JMenu mnPapeletas;
	private JDesktopPane pnEscritorio;
	
	private FrmPolicia formPolicia = null;
	private FrmInfractor formInfractor = null;
	private FrmPapeleta formPapeleta = null;
	private FrmPapeleta2 formPapeleta2 = null;
	private FrmPapeletaPol formPapPol = null;
	private FrmPapeletaInfr formPapInf = null;
	private JMenuItem mntmConCombobox;
	private JMenuItem mntmConBsqueda;
	private JMenu mnReportes;
	private JMenuItem mntmReporte;
	private JMenuItem mntmReporte_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 485);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		mntmPolicias = new JMenuItem("Polic\u00EDas");
		mntmPolicias.addActionListener(this);
		mnMantenimiento.add(mntmPolicias);
		
		mntmInfractores = new JMenuItem("Infractores");
		mntmInfractores.addActionListener(this);
		mnMantenimiento.add(mntmInfractores);
		
		mnPapeletas = new JMenu("Papeletas");
		menuBar.add(mnPapeletas);
		
		mntmConCombobox = new JMenuItem("con ComboBox...");
		mntmConCombobox.addActionListener(this);
		mnPapeletas.add(mntmConCombobox);
		
		mntmConBsqueda = new JMenuItem("con B\u00FAsqueda...");
		mntmConBsqueda.addActionListener(this);
		mnPapeletas.add(mntmConBsqueda);
		
		mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		mntmReporte = new JMenuItem("Reporte 1");
		mntmReporte.addActionListener(this);
		mnReportes.add(mntmReporte);
		
		mntmReporte_1 = new JMenuItem("Reporte 2");
		mntmReporte_1.addActionListener(this);
		mnReportes.add(mntmReporte_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pnEscritorio = new JDesktopPane();
		pnEscritorio.setBackground(new Color(0, 0, 128));
		contentPane.add(pnEscritorio, BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == mntmReporte_1) {
			actionPerformedMntmReporte_1(arg0);
		}
		if (arg0.getSource() == mntmReporte) {
			actionPerformedMntmReporte(arg0);
		}
		if (arg0.getSource() == mntmConBsqueda) {
			actionPerformedMntmConBsqueda(arg0);
		}
		if (arg0.getSource() == mntmConCombobox) {
			actionPerformedMntmConCombobox(arg0);
		}
		if (arg0.getSource() == mntmInfractores) {
			actionPerformedMntmInfractores(arg0);
		}
		if (arg0.getSource() == mntmPolicias) {
			actionPerformedMntmPolicias(arg0);
		}
		if (arg0.getSource() == mntmSalir) {
			actionPerformedMntmSalir(arg0);
		}
	}
	protected void actionPerformedMntmSalir(ActionEvent arg0) {
		System.exit(0);
	}
	protected void actionPerformedMntmPolicias(ActionEvent arg0) {
		if(formPolicia == null){
			formPolicia = new FrmPolicia();
			pnEscritorio.add(formPolicia);
			formPolicia.setVisible(true);
			formPolicia.addInternalFrameListener(new InternalFrameAdapter() {
				@Override
				public void internalFrameClosing(InternalFrameEvent e) {
					super.internalFrameClosing(e);
					formPolicia = null;
				}
			});
		}else{
			formPolicia.toFront();
		}
	}
	protected void actionPerformedMntmInfractores(ActionEvent arg0) {
		if(formInfractor == null){
			formInfractor = new FrmInfractor();
			pnEscritorio.add(formInfractor);
			formInfractor.setVisible(true);
			formInfractor.addInternalFrameListener(new InternalFrameAdapter() {
				@Override
				public void internalFrameClosing(InternalFrameEvent e) {
					super.internalFrameClosing(e);
					formInfractor = null;
				}
			});
		}else{
			formInfractor.toFront();
		}
	}
	protected void actionPerformedMntmConCombobox(ActionEvent arg0) {
		if(formPapeleta == null){
			formPapeleta = new FrmPapeleta();
			pnEscritorio.add(formPapeleta);
			formPapeleta.setVisible(true);
			formPapeleta.addInternalFrameListener(new InternalFrameAdapter() {
				@Override
				public void internalFrameClosing(InternalFrameEvent e) {
					super.internalFrameClosing(e);
					formPapeleta = null;
				}
			});
		}else{
			formPapeleta.toFront();
		}
	}
	protected void actionPerformedMntmConBsqueda(ActionEvent arg0) {
		if(formPapeleta2 == null){
			formPapeleta2 = new FrmPapeleta2();
			pnEscritorio.add(formPapeleta2);
			formPapeleta2.setVisible(true);
			formPapeleta2.addInternalFrameListener(new InternalFrameAdapter() {
				@Override
				public void internalFrameClosing(InternalFrameEvent e) {
					super.internalFrameClosing(e);
					formPapeleta2 = null;
				}
			});
		}else{
			formPapeleta2.toFront();
		}
	}
	protected void actionPerformedMntmReporte(ActionEvent arg0) {
		if(formPapPol == null){
			formPapPol = new FrmPapeletaPol();
			pnEscritorio.add(formPapPol);
			formPapPol.setVisible(true);
			formPapPol.addInternalFrameListener(new InternalFrameAdapter() {
				@Override
				public void internalFrameClosing(InternalFrameEvent e) {
					super.internalFrameClosing(e);
					formPapPol = null;
				}
			});
		}else{
			formPapPol.toFront();
		}
	}
	protected void actionPerformedMntmReporte_1(ActionEvent arg0) {
		if(formPapInf == null){
			formPapInf = new FrmPapeletaInfr();
			pnEscritorio.add(formPapInf);
			formPapInf.setVisible(true);
			formPapInf.addInternalFrameListener(new InternalFrameAdapter() {
				@Override
				public void internalFrameClosing(InternalFrameEvent e) {
					super.internalFrameClosing(e);
					formPapInf = null;
				}
			});
		}else{
			formPapInf.toFront();
		}
	}
}
