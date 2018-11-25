package entidades;

import java.sql.Date;
import java.time.LocalDate;

public class Papeleta {
	private String codigo, codPol, codInf, desc, apePol, apeInf;
	private double monto;
	private LocalDate fecha;
	
	public Papeleta() {	}

	public Papeleta(String codigo, String codPol, String codInf, String desc, String apePol, String apeInf,
			double monto, LocalDate fecha) {
		this.codigo = codigo;
		this.codPol = codPol;
		this.codInf = codInf;
		this.desc = desc;
		this.apePol = apePol;
		this.apeInf = apeInf;
		this.monto = monto;
		this.fecha = fecha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodPol() {
		return codPol;
	}

	public void setCodPol(String codPol) {
		this.codPol = codPol;
	}

	public String getCodInf() {
		return codInf;
	}

	public void setCodInf(String codInf) {
		this.codInf = codInf;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getApePol() {
		return apePol;
	}

	public void setApePol(String apePol) {
		this.apePol = apePol;
	}

	public String getApeInf() {
		return apeInf;
	}

	public void setApeInf(String apeInf) {
		this.apeInf = apeInf;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	/*Metodos de conversión entre LocalDate y SqlDate*/
	
	public void setFecFromSql(Date fecha){
		this.fecha = fecha.toLocalDate();
	}
	
	public Date getFecFromLocal(){
		return Date.valueOf(fecha);
	}
}
