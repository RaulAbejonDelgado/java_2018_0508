package com.ipartek.formacion.ejercicios.bucles.anidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Curso
 *1. Mostrar los números perfectos entre 1 y 1000.
 */
public class Ejercicio1 {
	
	public static void main(String[] args) {
		
		int  suma;
		
		System.out.println("Números perfectos entre 1 y 1000: ");
		for (int i = 1; i <= 1000; i++) { 
			suma = 0;
			for (int j = 1; j < i; j++) { 
				if (i % j == 0) {
					suma = suma + j;
				}
			}
			if (i == suma) { 
				System.out.println(i);
			}
		}
	}
}