package com.ipartek.formacion.ejercicios.basicos.estructura.secuencial;

/**
 * Programa que pida por teclado la fecha de nacimiento de una persona (dia, mes, a�o) 
 * y calcule su n�mero de la suerte.
 * El n�mero de la suerte se calcula sumando el d�a, mes y a�o de la fecha de 
 * nacimiento y a continuaci�n sumando las cifras obtenidas en la suma.
 * Por ejemplo:
 * Si la fecha de nacimiento es 12/07/1980 
 * Calculamos el n�mero de la suerte as�: 12+7+1980 = 1999  1+9+9+9 = 28
 * N�mero de la suerte: 28
 */

import java.util.Scanner;

public class Ejercicio13 {

	public static void main(String[] args) {
		int dia, mes, anyo, suerte, suma, cifra1, cifra2, cifra3, cifra4;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Escribe el dia de tu cumplea�os");
		dia = sc.nextInt();
		
		System.out.println("Escribe el mes de tu cumplea�os");
		mes = sc.nextInt();
		
		System.out.println("Escribe el a�o de tu cumplea�os");
		anyo = sc.nextInt();
		
		suma = dia + mes + anyo;
		
		cifra1 = suma/1000;
		cifra2 = suma/100 % 10;
		cifra3 = suma/10 % 10;
		cifra4 = suma %10;
		
		suerte = cifra1 + cifra2 + cifra3 + cifra4;
		
		System.out.println("Tu numero de la suerte es: " + suerte);
		
		sc.close();
	}

}