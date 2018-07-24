package com.ipartek.formacion.gestor.libros.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ejemplo-response")
public class ResponseController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// private static final Logger LOG =
	// Logger.getLogger(PrestamosController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		
		PrintWriter out = null;
		
		try {
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			out = response.getWriter();
			
			//TODO Maquetar la estructura basica del html
			out.print("<!doctype html>");
			//head,body
			out.print("<p>Soy una response<p>");
			
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!= null)
				out.flush();
		}
	}
}
