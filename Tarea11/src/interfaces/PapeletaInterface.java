package interfaces;

import java.util.ArrayList;

import entidades.Papeleta;

public interface PapeletaInterface {
	
	public ArrayList<Papeleta> listarPap();
	
	public boolean InsertarPap(Papeleta p);
	
	public boolean ModificarPap(Papeleta p);
	
	public boolean EliminarPap(String cod);
	
	public Papeleta BuscarPap(String cod);
	
	public ArrayList<Papeleta> buscarPorPol(String cod);
	
	public ArrayList<Papeleta> buscarPorInfr(String cod);
}
