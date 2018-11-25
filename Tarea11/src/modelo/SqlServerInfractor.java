package modelo;

import java.sql.*;
import java.util.ArrayList;

import entidades.Infractor;
import interfaces.InfractorInterface;
import util.Conexion;

public class SqlServerInfractor implements InfractorInterface {
	private Connection con;
	private CallableStatement cstm;
	private ResultSet rs;
	private String sql = "";
	
	@Override
	public ArrayList<Infractor> listarInf() {
		ArrayList<Infractor> lista = new ArrayList<Infractor>();
		sql = "{call USP_ListarInfractor()}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			rs = cstm.executeQuery();
			while (rs.next()){
				Infractor i = new Infractor();
				i.setCodigo(rs.getString("cod_infr"));
				i.setApe(rs.getString("ape_infr"));
				i.setDir(rs.getString("dir_infr"));
				i.setTele(rs.getString("tele_infr"));
				i.setMail(rs.getString("mail_infr"));
				
				lista.add(i);
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
	public boolean insertarInf(Infractor i) {
		boolean exito = false;
		sql = "{call USP_InsertarInfractor(?,?,?,?,?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, i.getCodigo());
			cstm.setString(2, i.getApe());
			cstm.setString(3, i.getDir());
			cstm.setString(4, i.getTele());
			cstm.setString(5, i.getMail());
			cstm.executeUpdate();
			exito = true;
		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		} finally {
			try {
				if (cstm != null) cstm.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exito;
	}

	@Override
	public boolean modificarInf(Infractor i) {
		boolean exito = false;
		sql = "{call USP_ModificarInfractor(?,?,?,?,?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, i.getCodigo());
			cstm.setString(2, i.getApe());
			cstm.setString(3, i.getDir());
			cstm.setString(4, i.getTele());
			cstm.setString(5, i.getMail());
			cstm.executeUpdate();
			exito = true;
		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		} finally {
			try {
				if (cstm != null) cstm.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exito;
	}

	@Override
	public boolean eliminarInf(String cod) {
		boolean exito = false;
		sql = "{call USP_EliminarInfractor(?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, cod);
			cstm.executeUpdate();
			exito = true;
		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		} finally {
			try {
				if (cstm != null) cstm.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exito;
	}

	@Override
	public Infractor buscarInf(String cod) {
		Infractor i = null;
		sql = "{call USP_BuscarInfractor(?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, cod);
			rs = cstm.executeQuery();
			if (rs.next()){
				i = new Infractor();
				i.setCodigo(rs.getString("cod_infr"));
				i.setApe(rs.getString("ape_infr"));
				i.setDir(rs.getString("dir_infr"));
				i.setTele(rs.getString("tele_infr"));
				i.setMail(rs.getString("mail_infr"));
			}
		} catch (Exception e) {
			i = null;
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(cstm != null) cstm.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return i;
	}

}
