package com.ipartek.formacion.prestamos_libros.service;

import java.util.List;

import com.ipartek.formacion.prestamos_libros.model.UsuarioDAO;
import com.ipartek.formacion.prestamos_libros.pojo.Usuario;

public class ServiceUsuario implements IServiceUsuario{
	
	private UsuarioDAO daoUsuario = UsuarioDAO.getInstance();
	
	public ServiceUsuario ()  {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean crear(Usuario u) throws Exception {
		boolean resul = false;
		
		if(daoUsuario.insert(u)) {
			resul = true;
		}
		
		return resul;
	}

	@Override
	public boolean modificar(Usuario u) throws Exception {
		boolean resul = false;
		
		if(daoUsuario.update(u)) {
			resul = true;
		}
		
		return resul;
	}

	@Override
	public boolean eliminar(long id) throws Exception {
		boolean resul = false;
		
		if(daoUsuario.delete(Long.toString(id))) {
			resul = true;
		}
		
		return resul;
	}

	@Override
	public List<Usuario> listar() throws Exception {
		List <Usuario> usuarios = daoUsuario.getAll();
		return usuarios;
	}

	@Override
	public Usuario buscarId(long id) throws Exception {
		Usuario u = daoUsuario.getById(Long.toString(id));
		return u;
	}

	

}
