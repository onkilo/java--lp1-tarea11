package interfaces;

import java.util.ArrayList;

import entidades.Policia;

public interface PoliciaInterface {
	
	public ArrayList<Policia> listarPol();
	
	public boolean insertarPol(Policia p);
	
	public boolean modificarPol(Policia p);
	
	public boolean eliminarPol(String cod);
	
	public Policia buscarPol(String cod);
	
}
