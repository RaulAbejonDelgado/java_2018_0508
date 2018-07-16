package com.ipartek.formacion.videos;

import java.util.Scanner;

import com.ipartek.formacion.model.VideoYoutubeArrayDAO;
import com.ipartek.formacion.pojo.VideoYoutube;
import com.sun.corba.se.impl.io.TypeMismatchException;

/**
 * Clase que presenta un menu en el cual se puede realizar las acciones basicas
 * en una lista de videos de youtube
 * 
 * @author Asier Cornejo
 *
 */

public class VideoClub {
	static private VideoYoutubeArrayDAO dao;
	static private int opcionSeleccionada = 0;
	static Scanner sc = null;

	static private final int OPCION_LISTAR = 1;
	static private final int OPCION_ANADIR = 2;
	static private final int OPCION_ELIMINAR = 3;
	static private final int OPCION_SALIR = 0;
	static private final int TAM_MIN_TITULO = 3;
	static private final int TAM_MAX_TITULO = 254;
	static private final int TAM_CODIGO = 11;

	static int cont = 0;

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		dao = VideoYoutubeArrayDAO.getInstance();
		cargarVideo();
		pintarMenu();

	}

	private static void cargarVideo() {

		dao.insert(new VideoYoutube(0, "Manifiesto", "3A2KtOXRpOo"));
		dao.insert(new VideoYoutube(1, "Vivir para contarlo", "brwIP1wI-FA"));
		dao.insert(new VideoYoutube(2, "Para los mios", "fp47VcTlwWQ"));
		dao.insert(new VideoYoutube(3, "Paquito el chocolatero", "3A2KtOXRpOo"));
		dao.insert(new VideoYoutube(4, "Marijaia", "fp67VcT1wWQ"));
		cont = dao.getAll().size();// Utilizaremos cont para determinar el id de
									// los videos

	}

	private static void pintarMenu() {

		System.out.println("----------------------------------");
		System.out.println("--------------youtube-------------");
		System.out.println("----------------------------------");
		System.out.println("    1.Listar                      ");
		System.out.println("    2.A�adir                      ");
		System.out.println("    3.Eliminar                    ");
		System.out.println("    0.Salir                       ");
		System.out.println("----------------------------------");
		System.out.println("Elige una opcion");
		try {
			opcionSeleccionada = sc.nextInt();
		} catch (TypeMismatchException e) {
			System.out.println("Lo sentimos pero debe introducir un numero de la lista");
			sc.next();
			pintarMenu();

		} catch (Exception e) {
			System.out.println("Lo sentimoss pero no has introducido una opcion correcta");
			System.out.println("Introduzca una numero entre 0 y 3 por favor");
			sc.next();
			pintarMenu();
		}
		sc.nextLine();
		switch (opcionSeleccionada) {
		case OPCION_LISTAR:
			listarVideos();
			pintarMenu();
			break;

		case OPCION_ANADIR:
			a�adirVideo();
			pintarMenu();
			break;

		case OPCION_ELIMINAR:
			eliminarVideo();
			pintarMenu();
			break;

		case OPCION_SALIR:
			System.exit(0);
			break;

		default:
			System.out.println("Lo sentimos pero no has introducido una opcion correcta");
			System.out.println("Introduzca una numero entre 0 y 3 por favor");
			pintarMenu();
			break;
		}
	}

	private static void eliminarVideo() {
		int codElim = 0;
		listarVideos();
		System.out.println("Introduce el id del video que deseas eliminar");
		try {

			codElim = sc.nextInt();

		} catch (ArrayIndexOutOfBoundsException e) {

			System.out.println("Lo sentimos pero debe introducir un numero correcto");

		} catch (TypeMismatchException e) {

			System.out.println("Lo sentimos pero debe introducir un numero");

		} catch (Exception e) {

			System.out.println("Lo sentimos pero ha ocurrido un error, por favor intentelo de nuevo");
			pintarMenu();
		}
		sc.nextLine();
		try {
			dao.delete(codElim);
		} catch (ArrayIndexOutOfBoundsException e) {

			System.out.println("Lo sentimos pero debe introducir un numero correcto");

		}
		System.out.println("El video seleccionado ha sido eliminado");

	}

	private static void listarVideos() {
		for (VideoYoutube video : dao.getAll()) {
			System.out.println("." + video.toString());
		}

	}

	private static void a�adirVideo() {
		String cod = "";
		String tit = "";

		System.out.println("Introduzca el codigo del video");
		try {

			cod = sc.next();
			if (VideoClub.controlarCodigo(cod)) {
				System.out.println("Introduzca el titulo del video");
				tit = sc.next();
				if (VideoClub.controlarTitulo(tit)) {

					dao.insert(new VideoYoutube(cont, tit, cod));
					cont++;
					System.out.println("Video a�adido a la lista.");
				} else {
					System.out.println("Lo sentimos pero el titulo debe tener al menos 3 caracteres.");
					System.out.println("El video NO se a�adio a la lista");

				}
			}

		} catch (TypeMismatchException e) {

			System.out.println("Lo sentimos pero debe introducir un numero");

		} catch (Exception e) {

			System.out.println("Lo sentimos pero ha ocurrido un error, por favor intentelo de nuevo");

			pintarMenu();
		}

		pintarMenu();

	}

	/**
	 * Metodo que controla si el usuario introduce un titulo para el video<br>
	 * correcto. Para que sea as� debe tener al menos 3 caracteres, y como<br>
	 * maximo 254.
	 * 
	 * @param tit
	 *            el titulo introducido por el usuario.
	 * 
	 * @return false si el titulo no tiene entre 3 y 254 caracteres.
	 */
	private static boolean controlarTitulo(String tit) {
		boolean result = false;
		if (tit.length() >= TAM_MIN_TITULO && tit.length() <= TAM_MAX_TITULO) {
			result = true;
		}
		return result;
	}

	/**
	 * Metodo que controla si el codigo introducido por el usuario es
	 * correcto.<br>
	 * Para ello debe cumplir las siguientes pautas:<br>
	 * 
	 * Tiene que tener 11 caracteres El valor de cada caracter tiene que ser
	 * alfanumerico(entre 0 y 9, o, entre A y z
	 * 
	 * @param codigo
	 *            el codigo introducido por el usuario
	 * @return false si no cumple las reglas arriba descritas.
	 */
	private static boolean controlarCodigo(String codigo) {
		boolean result = false;
		try {

			if (codigo.length() == TAM_CODIGO) {
				for (int i = 0; i < codigo.length(); i++) {
					if (codigo.charAt(i) >= 45 && codigo.charAt(i) <= 122) {
						result = true;
					} else {
						System.out.println("El codigo contiene caracteres no permitidos");
						System.out.println("El codigo debe contener numeros entre 0 y 9");
						System.out.println("o caracteres entre A y Z o a y z");

					}

				}
			} else {
				System.out.println("Debes introducir un codigo de 11 caracteres");
			}

		} catch (TypeMismatchException e) {

			System.out.println("Lo sentimos pero debe introducir un numero");

		} catch (Exception e) {

			System.out.println("Lo sentimos pero ha ocurrido un error, por favor intentelo de nuevo");

		}
		return result;
	}
}