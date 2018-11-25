package modelo;

import java.sql.*;
import java.util.ArrayList;

import entidades.Papeleta;
import interfaces.PapeletaInterface;
import util.Conexion;

public class SqlServerPapeleta implements PapeletaInterface{
	
	private Connection con;
	private CallableStatement cstm;
	private ResultSet rs;
	private String sql = "";

	@Override
	public ArrayList<Papeleta> listarPap() {
		ArrayList<Papeleta> lista = new ArrayList<Papeleta>();
		sql = "{call USP_ListarPapeleta()}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			rs = cstm.executeQuery();
			while(rs.next()){
				Papeleta p = new Papeleta();
				p.setCodigo(rs.getString("pap"));
				p.setApePol(rs.getString("pol"));
				p.setApeInf(rs.getString("infr"));
				p.setFecFromSql(rs.getDate("fecha"));
				p.setMonto(rs.getDouble("monto"));
				p.setDesc(rs.getString("descripcion"));
				
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally{
			try{
				if(rs != null) rs.close();
				if(cstm != null) cstm.close();
				if(con != null) con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public boolean InsertarPap(Papeleta p) {
		boolean exito = false;
		sql = "{call USP_InsertarPapeleta(?,?,?,?,?,?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, p.getCodigo());
			cstm.setString(2, p.getCodPol());
			cstm.setString(3, p.getCodInf());
			cstm.setDate(4, p.getFecFromLocal());
			cstm.setDouble(5, p.getMonto());
			cstm.setString(6, p.getDesc());
			
			cstm.executeUpdate();
			exito = true;
		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		} finally {
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
	public boolean ModificarPap(Papeleta p) {
		boolean exito = false;
		sql = "{call USP_ModificarPapeleta(?,?,?,?,?,?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, p.getCodigo());
			cstm.setString(2, p.getCodPol());
			cstm.setString(3, p.getCodInf());
			cstm.setDate(4, p.getFecFromLocal());
			cstm.setDouble(5, p.getMonto());
			cstm.setString(6, p.getDesc());
			
			cstm.executeUpdate();
			exito = true;
		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		} finally {
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
	public boolean EliminarPap(String cod) {
		boolean exito = false;
		sql = "{call USP_EliminarPapeleta(?)}";
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
				if(cstm != null) cstm.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return exito;
	}

	@Override
	public Papeleta BuscarPap(String cod) {
		Papeleta p = null;
		sql = "{call USP_BuscarPapeleta(?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, cod);
			rs = cstm.executeQuery();
			if(rs.next()){
				p = new Papeleta();
				p.setCodigo(rs.getString("pap"));
				p.setApePol(rs.getString("pol"));
				p.setApeInf(rs.getString("infr"));
				p.setFecFromSql(rs.getDate("fecha"));
				p.setMonto(rs.getDouble("monto"));
				p.setDesc(rs.getString("descripcion"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			p = null;
		} finally{
			try{
				if(rs != null) rs.close();
				if(cstm != null) cstm.close();
				if(con != null) con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return p;
	}

	@Override
	public ArrayList<Papeleta> buscarPorPol(String cod) {
		ArrayList<Papeleta> lista = new ArrayList<Papeleta>();
		sql = "{call USP_PapeletaPol(?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, cod);
			rs = cstm.executeQuery();
			while(rs.next()){
				Papeleta p = new Papeleta();
				p.setCodigo(rs.getString("pap"));
				p.setApePol(rs.getString("pol"));
				p.setApeInf(rs.getString("infr"));
				p.setFecFromSql(rs.getDate("fecha"));
				p.setMonto(rs.getDouble("monto"));
				p.setDesc(rs.getString("descripcion"));
				
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally{
			try{
				if(rs != null) rs.close();
				if(cstm != null) cstm.close();
				if(con != null) con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public ArrayList<Papeleta> buscarPorInfr(String cod) {
		ArrayList<Papeleta> lista = new ArrayList<Papeleta>();
		sql = "{call USP_PapeletaInfr(?)}";
		try {
			con = Conexion.conectar();
			cstm = con.prepareCall(sql);
			cstm.setString(1, cod);
			rs = cstm.executeQuery();
			while(rs.next()){
				Papeleta p = new Papeleta();
				p.setCodigo(rs.getString("pap"));
				p.setApePol(rs.getString("pol"));
				p.setApeInf(rs.getString("infr"));
				p.setFecFromSql(rs.getDate("fecha"));
				p.setMonto(rs.getDouble("monto"));
				p.setDesc(rs.getString("descripcion"));
				
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally{
			try{
				if(rs != null) rs.close();
				if(cstm != null) cstm.close();
				if(con != null) con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return lista;
	}
	
}
