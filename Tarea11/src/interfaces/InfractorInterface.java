package interfaces;

import java.util.ArrayList;

import entidades.Infractor;

public interface InfractorInterface {
	
	public ArrayList<Infractor> listarInf();
	
	public boolean insertarInf(Infractor i);
	
	public boolean modificarInf(Infractor i);
	
	public boolean eliminarInf(String cod);
	
	public Infractor buscarInf(String cod);
	
}
