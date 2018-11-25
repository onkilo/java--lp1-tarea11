package util;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entidades.Papeleta;

public class PapeletaTableModel extends AbstractTableModel {

	private ArrayList<Papeleta> lista;

	private String[] columnas = { "C�digo", "Polic�a", "Infractor", "Fecha", "Monto", "Descripci�n" };

	public PapeletaTableModel() {
		this.lista = new ArrayList<Papeleta>();
	}
	
	public PapeletaTableModel(ArrayList<Papeleta> lista){
		this.lista = lista;
	}
	
	public void clearData(){
		lista.clear();
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Papeleta p = lista.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return p.getCodigo();
		case 1:
			return p.getApePol();
		case 2:
			return p.getApeInf();
		case 3:
			return p.getFecha();
		case 4:
			return p.getMonto();
		case 5:
			return p.getDesc();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		switch (columnIndex) {
		case 3:
			return LocalDate.class;
		case 4:
			return double.class;
		default:
			return String.class;
		}

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		Papeleta p = lista.get(columnIndex);

		switch (columnIndex) {
		case 0:
			p.setCodigo((String) aValue);
			break;
		case 1:
			p.setApePol((String) aValue);
			break;
		case 2:
			p.setApeInf((String) aValue);
			break;
		case 3:
			p.setFecha((LocalDate) aValue);
			break;
		case 4:
			p.setMonto((double) aValue);
			break;
		case 5:
			p.setDesc((String) aValue);
			break;
		}

		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Papeleta getPapeleta(int row) {
		return lista.get(row);
	}

	public void modifyPapeleta(int row, Papeleta p) {
		lista.set(row, p);
		fireTableDataChanged();
	}

	public void insertPapeletaAt(int row, Papeleta p) {
		lista.add(row, p);
		fireTableRowsInserted(row, row);
	}

	public void addPapeleta(Papeleta p) {
		insertPapeletaAt(getRowCount(), p);
	}

	public void deletePapeleta(int row) {
		lista.remove(row);
		fireTableRowsDeleted(row, row);
	}
}
