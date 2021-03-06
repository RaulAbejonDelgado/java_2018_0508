package com.ipartek.formacion.youtube.controller.back;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.youtube.controller.pojo.Alert;
import com.ipartek.formacion.youtube.model.UsuarioDAO;
import com.ipartek.formacion.youtube.model.VideoDAO;
import com.ipartek.formacion.youtube.pojo.Usuario;
import com.ipartek.formacion.youtube.pojo.Video;

/**
 * Servlet implementation class BackofficeUsuarioController
 */
@WebServlet("/backoffice/videos")
public class BackofficeVideosController extends HttpServlet implements CrudControllable {
	
	
	private static final long serialVersionUID = 1L;
	private static VideoDAO daoVideo = null;
	private static UsuarioDAO daoUsuario = null;
	
	private static final String VIEW_LISTADO = "videos/index.jsp";
	private static final String VIEW_FORMULARIO = "videos/form.jsp";
	private String view;
	private Alert alert;
	
	private String op; //operacion a realizar
	private String id; // id Video
	private String id_usuario;
	private String nombre;
	private String codigo;	
			
	
	@Override
	public void init(ServletConfig config) throws ServletException {	
		super.init(config);
		daoVideo  = VideoDAO.getInstance();
		daoUsuario = UsuarioDAO.getInstance();
	}
	
	@Override
	public void destroy() {	
		super.destroy();
		daoVideo = null;
		daoUsuario = null;
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doProcess(request, response);		
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			alert = new Alert();
			
			getParameters(request);
			
			switch (op) {
			case OP_ELIMINAR:
				eliminar(request);
				break;
			case OP_IR_FORMULARIO:
				irFormulario(request);
				break;	
			case OP_GUARDAR:
				guardar(request);
				break;	

			default:  //LISTAR
				listar(request);
				break;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			view = VIEW_LISTADO;
			alert = new Alert();
		}finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);			
		}
		
	}

	public void listar(HttpServletRequest request) throws Exception {
		
			
		alert = null;		
		view = VIEW_LISTADO;
		request.setAttribute("videos", daoVideo.getAll());		
		
	}

	public void guardar(HttpServletRequest request) {
		
		Video v = new Video();
			
		
		try {
			
			v.setId(Long.parseLong(id));
			v.setCodigo(codigo);
			v.setNombre(nombre);
			
			Usuario usuarioVideo = new Usuario();
			usuarioVideo.setId( Long.parseLong(id_usuario));		
			v.setUsuario(usuarioVideo);	
			
			
			
			if( v.getId() > 0 ) {			
				daoVideo.update(v);				
			}else {                 
				daoVideo.insert(v);				
			}			
			alert = new Alert(Alert.SUCCESS, "Registro guardado con exito");	
	
		// nombre repetido
		} catch ( SQLIntegrityConstraintViolationException e ) {
			e.printStackTrace();
			alert = new Alert(Alert.WARNING, "<b>" + v.getCodigo() +  "</b> ya existe !!!" );
		
		//longitud campos nombre y password
		}catch (SQLException e) {
			e.printStackTrace();			
			alert = new Alert(Alert.WARNING, "El <b>nombre</b> debe ser inferior a 150 caracteres");					
		}catch (Exception e) {
			e.printStackTrace();
			alert = new Alert();
		}	
				
		view = VIEW_FORMULARIO;
		request.setAttribute("usuarios", daoUsuario.getAll());
		request.setAttribute("video", v);
		
		
	}

	public void irFormulario(HttpServletRequest request) throws Exception {
		
		alert = null;
		view = VIEW_FORMULARIO;
		if ( id.equalsIgnoreCase("-1")) {
			request.setAttribute("video", new Video() );
		}else {			
			request.setAttribute("video", daoVideo.getById( Long.parseLong(id)));
		}
		
		request.setAttribute("usuarios", daoUsuario.getAll() );
	}

	
	public void eliminar(HttpServletRequest request) throws Exception {
		
		try {
			if ( daoVideo.delete(Long.parseLong(id))) {
				alert = new Alert(Alert.SUCCESS, "Registro Eliminado");
			}else {
				alert = new Alert(Alert.WARNING, "NO se puedo Eliminar");
			}	
		}catch (Exception e) {
			alert = new Alert();
		}	
		view = VIEW_LISTADO;
		request.setAttribute("videos", daoVideo.getAll());	
		
	}

	public void getParameters(HttpServletRequest request) {		
		op = ( request.getParameter("op") != null ) ? request.getParameter("op") : OP_LISTAR;
		id = request.getParameter("id");
		id_usuario = request.getParameter("id_usuario");
		nombre = request.getParameter("nombre");	
		codigo = request.getParameter("codigo");
		
		
	}

}
