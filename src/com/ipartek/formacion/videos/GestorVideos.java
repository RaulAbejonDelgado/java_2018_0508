package com.ipartek.formacion.videos;

import java.util.Scanner;

import com.ipartek.formacion.model.VideoYoutubeArrayDAO;
import com.ipartek.formacion.pojo.VideoYoutube;

public class GestorVideos {

	static private VideoYoutubeArrayDAO dao;
	static private int opcionSeleccionada = 0;
	static Scanner sc = null;
	static private long IdCounter = 0;
	
	static private final int OPCION_SALIR = 0;
	static private final int OPCION_LISTAR = 1;
	static private final int OPCION_ANADIR = 2;
	static private final int OPCION_ELIMINAR = 3;
	
	
	public static void main(String[] args) {
				
		sc = new Scanner(System.in);
		
		dao = VideoYoutubeArrayDAO.getInstance();
		
		cargarVideos();
		
		do {
			pintarMenu();
		
			switch (opcionSeleccionada) {
			case OPCION_LISTAR:
				listar();
				break;

			case OPCION_SALIR:
				salir();
				break;	
			
			case OPCION_ANADIR:
				anadir();
				
			default:
				noOption();
				break;
			}

		} while (opcionSeleccionada!=OPCION_SALIR);
		
		sc.close();
	}
	
	private static void anadir() {
		
		Scanner sc = new Scanner(System.in);
		
		long id;
		String titulo;
		String codigo;
		
		try {
			System.out.println("Introduce la id del video");
			id = sc.nextLong();
		} catch (Exception e){
			sc.nextLine();
			System.out.println("OPCION NO VALIDA. Por favor introduce una ID correcta (solo numeros)");
		}
		
		System.out.println("Introduce el t�tulo del video");
		titulo = sc.next();
		
		System.out.println("Introduce el c�digo del video");
		codigo = sc.next();
		
		//videos[i] = new VideoYoutube(id, titulo, codigo);
		
		sc.close();
		
	}
	
	private static void salir() {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("AGUR BEN HUR, esperamos verte pronto");
		
	}


	private static void noOption() {
		System.out.println("Lo sentimos No existe esa opcion");
		pintarMenu();
		
	}


	private static void listar() {
		
		for ( VideoYoutube video : dao.getAll() ) {
			System.out.println("    " + video);
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		pintarMenu();
		
	}


	private static void cargarVideos() {
		VideoYoutube video = new VideoYoutube(1, "Nightmares On Wax Boiler Room London DJ Set", "Q692lHFaLVM");
		dao.insert(video);
		++IdCounter;
		
		video = new VideoYoutube(2, "The Skatalites - Rock Fort Rock", "6bLVdKbPHHY");
		dao.insert(video);
		++IdCounter;
		
	}


	private static void pintarMenu() {
		
		System.out.println("------------------------------------");
		System.out.println("--          youtube               --");
		System.out.println("------------------------------------");
		System.out.println("-    1. Listar                     -");
		System.out.println("-    2. A�adir Nuevo               -");
		System.out.println("-    3. Eliminar                   -");
		System.out.println("-                                  -");
		System.out.println("-    0 - salir                     -");
		System.out.println("------------------------------------");
		System.out.println("");
		System.out.println("Dime una opcion por favor");
		
		try {
			opcionSeleccionada = sc.nextInt();
		} catch (Exception e) {
			//e.printStackTrace(); pinta la pila de excepciones
			sc.nextLine();
			System.out.println("OPCION NO VALIDA. Por favor introduce un numero del menu");
			pintarMenu();
		}
		
	}
	
}