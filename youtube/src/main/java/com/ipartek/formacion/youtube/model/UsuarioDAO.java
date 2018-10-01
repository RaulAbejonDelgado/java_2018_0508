package com.ipartek.formacion.youtube.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.youtube.pojo.Usuario;

public class UsuarioDAO implements CrudAble<Usuario> {

	private static UsuarioDAO INSTANCE = null;
	private static List<Usuario> usuarios = null;

	private final String SQL_GET_ALL = "SELECT id, nombre, password, rol FROM usuario ORDER BY id DESC LIMIT 50;";
	private final String SQL_GET_BY_ID = "SELECT id, nombre, password, rol FROM usuario WHERE id = ?;";
	private final String SQL_UPDATE = "UPDATE usuario SET nombre = ?, password = ? WHERE id = ?;";
	private final String SQL_DELETE = "DELETE FROM usuario WHERE id = ?;";
	private final String SQL_INSERT = "INSERT INTO usuario (nombre, password) VALUES (?,?);";

	private UsuarioDAO() {
		usuarios = new ArrayList<Usuario>();
	}

	public static synchronized UsuarioDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}

		return INSTANCE;
	}

	@Override
	public boolean insert(Usuario pojo) {
		boolean resul = false;
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);) {

			ps.setString(1, pojo.getNombre());
			ps.setString(2, pojo.getContrasenya());

			int affectedRows = ps.executeUpdate();
			if (affectedRows == 1) {
				
				// conseguir id generado
				try (ResultSet rs = ps.getGeneratedKeys()){
					while(rs.next()) {
						resul = true;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public List<Usuario> getAll() {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				usuarios.add(rowMapper(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	@Override
	public Usuario getById(String id) {
		Usuario u = null;
		try (Connection con = ConnectionManager.getConnection();

		) {
			try (PreparedStatement ps = con.prepareStatement(SQL_GET_BY_ID);) {
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {

					u = rowMapper(rs);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public boolean update(Usuario pojo) {
		boolean resul = false;
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {

			ps.setString(1, pojo.getNombre());
			ps.setString(2, pojo.getContrasenya());
			ps.setInt(3, pojo.getRol());
			ps.setLong(4, pojo.getId());

			int affectedRows = ps.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public boolean delete(String id) {
		boolean resul = false;
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {

			ps.setString(1, id);

			int affectedRows = ps.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	private Usuario rowMapper(ResultSet rs) throws Exception {
		Usuario u = new Usuario();
		if (rs != null) {
			u = new Usuario();
			u.setId(rs.getLong("id"));
			u.setContrasenya(rs.getString("contrasenya"));
			u.setNombre(rs.getString("nombre"));
		}
		return u;
	}
}
