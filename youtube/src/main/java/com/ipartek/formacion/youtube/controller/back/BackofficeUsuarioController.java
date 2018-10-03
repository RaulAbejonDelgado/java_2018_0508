package com.ipartek.formacion.youtube.controller.back;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.youtube.model.UsuarioDAO;
import com.ipartek.formacion.youtube.pojo.Usuario;

/**
 * Servlet implementation class BackofficeUsuarioController
 */
@WebServlet("/backoffice/usuarios")
public class BackofficeUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static UsuarioDAO dao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = UsuarioDAO.getInstance();
		
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) dao.getAll();
		
		String id = request.getParameter("id");
		
		if(id == null) { //Si el ID es nulo, lista todos los usuarios
			request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher("usuarios/index.jsp").forward(request, response);
		}else {
			Usuario usuario = new Usuario();
			if(Integer.parseInt(id) > 0) { //Si el ID es mayor que cero, muestra los datos de ese usuario, si no crea uno nuevo
				usuario = dao.getById(id);
			}
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("usuarios/formulario.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		String rol = request.getParameter("rol");
		
		//TODO Comprobar si es CREAR o MODIFICAR y llamar al DAO
		
		Usuario usuario = new Usuario();
		usuario.setId(Long.parseLong(id));
		usuario.setNombre(nombre);
		usuario.setPassword(password);
		usuario.setRol(Integer.parseInt(rol));
		
		request.setAttribute("usuario", usuario);
		request.getRequestDispatcher("usuarios/formulario.jsp").forward(request, response);
		
	}

}
