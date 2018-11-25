package modelo;

import java.sql.*;
import java.util.ArrayList;

import entidades.Policia;
import interfaces.PoliciaInterface;
import util.Conexion;

public class SqlServerPolicia implements PoliciaInterface{
	
	private Connection con;
	private CallableStatement cstm;
	private ResultSet rs;
	private String sql = "";
	
	@Override
	public ArrayList<Policia> listarPol() {
		ArrayList<Policia> lista = new ArrayList<Policia>();
		sql = "{call USP_ListarPolicia()}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			rs = cstm.executeQuery();
			while(rs.next()){
				Policia p = new Policia();
				p.setCodigo(rs.getString("cod_pol"));
				p.setApe(rs.getString("ape_pol"));
				p.setDir(rs.getString("dir_pol"));
				p.setTele(rs.getString("tele_pol"));
				p.setMail(rs.getString("mail_pol"));
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally {
			try {
				if(rs != null) rs.close();
				if(cstm != null) cstm.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}

	@Override
	public boolean insertarPol(Policia p) {
		boolean exito = false;
		sql = "{call USP_InsertarPolicia(?,?,?,?,?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, p.getCodigo());
			cstm.setString(2, p.getApe());
			cstm.setString(3, p.getDir());
			cstm.setString(4, p.getTele());
			cstm.setString(5, p.getMail());
			cstm.executeUpdate();
			exito = true;
		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		}finally {
			try {
				if(cstm != null) cstm.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exito;
	}

	@Override
	public boolean modificarPol(Policia p) {
		boolean exito = false;
		sql = "{call USP_ModificarPolicia(?,?,?,?,?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, p.getCodigo());
			cstm.setString(2, p.getApe());
			cstm.setString(3, p.getDir());
			cstm.setString(4, p.getTele());
			cstm.setString(5, p.getMail());
			cstm.executeUpdate();
			exito = true;
		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		}finally {
			try {
				if(cstm != null) cstm.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exito;
	}

	@Override
	public boolean eliminarPol(String cod) {
		boolean exito = false;
		sql = "{call USP_EliminarPolicia(?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, cod);
			cstm.executeUpdate();
			exito = true;
		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		}finally {
			try {
				if(cstm != null) cstm.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exito;
	}

	@Override
	public Policia buscarPol(String cod) {
		Policia p = null;
		sql = "{call USP_BuscarPolicia(?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, cod);
			rs = cstm.executeQuery();
			if(rs.next()){
				p = new Policia();
				p.setCodigo(rs.getString("cod_pol"));
				p.setApe(rs.getString("ape_pol"));
				p.setDir(rs.getString("dir_pol"));
				p.setTele(rs.getString("tele_pol"));
				p.setMail(rs.getString("mail_pol"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			p = null;
		}finally {
			try {
				if(cstm != null) cstm.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return p;
	}

}
