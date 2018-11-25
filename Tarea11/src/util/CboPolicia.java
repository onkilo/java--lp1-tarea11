package util;

import entidades.Policia;

public class CboPolicia {
	
	private Policia p;
	
	public CboPolicia( Policia p) {
		this.p = p;
	}
	
	
	
	public Policia getP() {
		return p;
	}



	public void setP(Policia p) {
		this.p = p;
	}



	@Override
	public String toString() {
		return this.p.getApe();
	}
}
