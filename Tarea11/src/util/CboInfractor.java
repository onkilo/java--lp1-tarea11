package util;

import entidades.Infractor;

public class CboInfractor {
	
	private Infractor i;
	
	public CboInfractor(Infractor i){
		this.i = i;
	}
	
	@Override
	public String toString() {
		return this.i.getApe();
	}
	
}
