package com.ipartek.formacion.youtube.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.youtube.model.UsuarioDAO;
import com.ipartek.formacion.youtube.pojo.Alert;
import com.ipartek.formacion.youtube.pojo.Rol;
import com.ipartek.formacion.youtube.pojo.Usuario;

/**
 * Implementación de un Controlador Servlet que detecta si un usuario ha sido
 * correctamente logueado, contrastando con la base de datos.
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String VIEW_INICIO_ADMIN = "/backoffice/inicio";
	private static final String VIEW_INICIO_USER = "/inicio";

	private String view;
	private Alert alert;
	private UsuarioDAO daoUsuario;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// Se ejecuta solo con la 1º petición, el resto de peticiones iran a "service"
		daoUsuario = UsuarioDAO.getInstance();
	}

	@Override
	public void destroy() {
		super.destroy();
		// Se ejecuta al parar el servidor
		daoUsuario = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		alert = new Alert();
		view = VIEW_INICIO_USER;

		try {

			// Recoger parametros
			String usuarioNombre = request.getParameter("usuario");
			String pass = request.getParameter("pass");

			Usuario user = new Usuario(usuarioNombre, pass);

			if (  daoUsuario.login(user) != null ) {

					Locale locale = request.getLocale();
					ResourceBundle idiomas = ResourceBundle.getBundle("idiomas", locale);

					// Mensaje de idiomas con parámetro (usuarioNombre)
					alert.setTexto(MessageFormat.format(idiomas.getString("msj.bienvenida"), usuarioNombre));
					alert.setTipo(Alert.PRIMARY);

					
					gestionarSesionDeUsuario(request, user); // Guardar Usuario en session
					gestionarCookiesDeUsuario(request, response, user); // Guardar Cookies de Usuario
					
					if ( user.getRol().getId() == Rol.ID_ROL_ADMIN ) {
						
						view = VIEW_INICIO_ADMIN;
					}
				
			}

			else { // Usuario no encontrado

				alert.setTexto("Credenciales incorrectas");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			request.getSession().setAttribute("alert", alert);
			response.sendRedirect(request.getContextPath() + view);
		}

	}

	/**
	 * Procedimiento privado que se encarga de almacenar al usuario en la sesión.
	 * @param request, HttpServletRequest
	 * @param u, Usuario
	 */
	private void gestionarSesionDeUsuario(HttpServletRequest request, Usuario u) {
		HttpSession session = request.getSession();

		session.setAttribute("usuario", u);
		session.setMaxInactiveInterval(60 * 5); // 5 min

	}

	/**
	 * Procedimiento privado que se encarga de almacenar las cookies para cada usuario.
	 * @param request, HttpServletRequest
	 * @param response, HttpServletResponse
	 * @param u, Usuario
	 */
	private void gestionarCookiesDeUsuario(HttpServletRequest request, HttpServletResponse response, Usuario u) {

		String recordar = (String) request.getParameter("recuerdame");
		Cookie cNombre = new Cookie("cNombre", u.getNombre());

		if (recordar != null) {
			cNombre.setMaxAge(60 * 60 * 24 * 30 * 3); // 3 meses
		} else {
			cNombre.setMaxAge(0); // No guardar
		}

		setCookieIdioma(request, response);
		response.addCookie(cNombre);
	}

	/**
	 * Procedimiento que gestiona la cookie del idioma.
	 * 
	 * @param request
	 * @param response
	 */
	private void setCookieIdioma(HttpServletRequest request, HttpServletResponse response) {

		Cookie cIdioma = new Cookie("cIdioma", request.getLocale().toString());
		cIdioma.setMaxAge(60 * 60 * 24 * 30); // 1 mes

		response.addCookie(cIdioma);
	}

}
