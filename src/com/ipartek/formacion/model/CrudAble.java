package com.ipartek.formacion.model;

import java.util.List;

import com.ipartek.formacion.pojo.VideoYoutube;

/**
 * Interfaz para especificar los metodos de <b>CRUD</b>:
 * 
 * <ul>
 * 		<li>Create</li>
 * 		<li>Read</li>
 * 		<li>Update</li>
 * 		<li>Delete</li>
 * </ul>
 * 
 * @author ur00
 *
 */
public interface CrudAble {

	
	boolean insert(VideoYoutube video);
	
	List<VideoYoutube> getAll();
		
	VideoYoutube getById(long id);
	
	boolean update(VideoYoutube video);
	
	boolean delete(long id);
	
}
