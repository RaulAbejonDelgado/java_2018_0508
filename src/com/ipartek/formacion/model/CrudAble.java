package com.ipartek.formacion.model;

import java.util.List;

import com.ipartek.formacion.pojo.VideoYoutube;

/**
 * Interfaz paraespecificar los metodos de <b>CRUD</b>:
 * <ul>
 * <li>Create</li>
 * <li>Read</li>
 * <li>Update</li>
 * <li>Delete</li>
 * </ul>
 * 
 * @author Guillermo
 *
 */
public interface CrudAble {

	boolean insert(VideoYoutube video);

	/**
	 * Recupera todos los videoYoutube
	 * 
	 * @return si no existen resultados retorna Lista vacia, no null
	 */
	List<VideoYoutube> getAll();

	/**
	 * Buscamos un VideoYoutube por su identidicador
	 * 
	 * @param id long identificador
	 * @return VideoYoutube si lo encuentra, null si no encuentra
	 */
	VideoYoutube getById(long id);
	
	boolean update(VideoYoutube video);

	boolean delete(long id);

}
