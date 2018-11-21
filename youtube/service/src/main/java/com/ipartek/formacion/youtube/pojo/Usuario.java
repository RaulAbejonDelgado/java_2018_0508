package com.ipartek.formacion.youtube.pojo;

public class Usuario {
	private long id;
	private String nombre;
	private String pass;
	private Rol rol;
	

	
	

	public Usuario() {
		super();
		this.nombre = "";
		this.pass = "";
		this.id=-1;
		this.rol=new Rol();
		
	}

		
	public Usuario(String nombre, String pass) {
		this();
		this.nombre = nombre;
		this.pass = pass;
	}

	


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", pass=" + pass + ", rol=" + rol + "]";
	}
	


	

	
}