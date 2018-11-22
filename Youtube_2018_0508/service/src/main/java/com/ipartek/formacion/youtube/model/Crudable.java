package com.ipartek.formacion.youtube.model;

import java.util.List;

/**
 * Interfaz para especificar los metodos de <b>CRUD</b>:
 * 
 * <ul>
 * <li>Create</li>
 * <li>Read</li>
 * <li>Update</li>
 * <li>Delete</li>
 * </ul>
 * 
 * @author ur00
 * 
 */
public interface Crudable<P> {

	boolean insert(P pojo) throws Exception;

	/**
	 * recupera todos los pojos
	 * 
	 * @return si no existen resultados retorna Lista vacia, no null
	 */
	List<P> getAll() throws Exception;

	/**
	 * Buscamos un Pojo por su identificador
	 * 
	 * @param id long identificador
	 * @return Pojo si lo encuentra, null si no encuentra
	 */
	P getById(String id) throws Exception;

	boolean update(P pojo) throws Exception;

	boolean delete(String id) throws Exception;

}