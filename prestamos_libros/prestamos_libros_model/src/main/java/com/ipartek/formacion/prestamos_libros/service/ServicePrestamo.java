package com.ipartek.formacion.prestamos_libros.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.prestamos_libros.model.PrestamoDAO;
import com.ipartek.formacion.prestamos_libros.pojo.Alumno;
import com.ipartek.formacion.prestamos_libros.pojo.Libro;
import com.ipartek.formacion.prestamos_libros.pojo.Prestamo;


public class ServicePrestamo implements IServicePrestamo {

	private static ServicePrestamo INSTANCE = null;
	private static PrestamoDAO daoPrestamo = PrestamoDAO.getInstance();

	private ServicePrestamo() {
		super();
	}

	public static synchronized ServicePrestamo getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ServicePrestamo();
		}
		return INSTANCE;
	}

	@Override
	public List<Prestamo> listarHistorico() throws Exception {
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		prestamos = (ArrayList<Prestamo>) daoPrestamo.getHistorico();
		return prestamos;
	}

	@Override
	public List<Prestamo> listarPrestados() throws Exception {
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		prestamos = (ArrayList<Prestamo>) daoPrestamo.getAllPrestados();
		return prestamos;
	}

	@Override
	public boolean prestar(long idLibro, long idAlumno, Date fechaInicio) throws Exception {
		boolean resul = false;

		Prestamo p = new Prestamo();
		p.setFecha_prestado(fechaInicio);

		Alumno a = new Alumno();
		a.setId(idAlumno);
		p.setAlumno(a);

		Libro l = new Libro();
		l.setId(idLibro);
		p.setLibro(l);

		if (daoPrestamo.insert(p)) {
			resul = true;
		}

		return resul;
	}

	@Override
	public List<Libro> librosDisponibles() throws Exception {
		ArrayList<Libro> libros = new ArrayList<Libro>();
		libros = (ArrayList<Libro>) daoPrestamo.librosDisponibles();
		return libros;
	}

	@Override
	public List<Alumno> alumnosDisponibles() throws Exception {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		alumnos = (ArrayList<Alumno>) daoPrestamo.alumnosDisponibles();
		return alumnos;
	}

	@Override
	public Prestamo buscarPorId(long idLibro, long idAlumno, Date fecha_prestado) throws Exception {
		Prestamo p = null;
		p = daoPrestamo.buscarPorId( idLibro, idAlumno, fecha_prestado);
		return p;
	}

	@Override
	public boolean devolver(long idLibro, long idAlumno, Date fechaPrestado, Date fechaRetorno) throws Exception {
		boolean resul = false;
		Prestamo p = new Prestamo();

		Alumno a = new Alumno();
		a.setId(idAlumno);
		p.setAlumno(a);

		Libro l = new Libro();
		l.setId(idLibro);

		p.setLibro(l);
		
		p.setFecha_prestado(fechaPrestado);
		p.setFecha_retorno(fechaRetorno);		

		if (daoPrestamo.update(p)) {
			resul=true;
		}
		return resul;
	}

	@Override
	public boolean update(long idLibroOld, long idAlumnoOld, Date fechaPrestadoOld, long idLibroNew, long idAlumnoNew,
			Date fechaPrestadoNew, Date fechaFinal, Date fechaRetorno) throws Exception {
		boolean resul = false;

		if (daoPrestamo.modifyAll(idAlumnoOld, idLibroOld, fechaPrestadoOld, idAlumnoNew, idLibroNew, fechaPrestadoNew, fechaFinal, fechaRetorno)) {
			resul=true;
		}
		return resul;
	}

}