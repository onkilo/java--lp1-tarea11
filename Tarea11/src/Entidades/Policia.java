package entidades;

public class Policia {
	private String codigo, ape, dir, tele, mail;
	
	public Policia(){}

	public Policia(String codigo, String ape, String dir, String tele, String mail) {
		this.codigo = codigo;
		this.ape = ape;
		this.dir = dir;
		this.tele = tele;
		this.mail = mail;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getApe() {
		return ape;
	}

	public void setApe(String ape) {
		this.ape = ape;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
