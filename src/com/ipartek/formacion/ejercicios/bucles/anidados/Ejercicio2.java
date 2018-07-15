package com.ipartek.formacion.ejercicios.bucles.anidados;

import java.util.Scanner;

/**
 * 2. Leer un n�mero N y calcular el factorial de los n�meros desde 0 hasta N.
 * 
 * @author apero
 *
 */
public class Ejercicio2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n;
		int factorial = 1;

		System.out.println("Dime un numero:");
		n = sc.nextInt();

		System.out.println("Factoriales:");
		for (int i = 0; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				factorial *= j;
			}
			System.out.println("El factorial de " + i + " es " + factorial);
			factorial = 1;
		}

		sc.close();
	}

}
