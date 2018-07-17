package com.ipartek.formacion.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilidadesTest {

	@Test
	public void testBubbleSort() {

		int[] arrayDesordenado = { 0, 3, 1, 8, 7, 2, 5, 4, 6, 9 };
		int[] arrayOrdenadoAsc = Utilidades.bubbleSort(arrayDesordenado, true);
		int[] arrayOrdenadoDesc = Utilidades.bubbleSort(arrayDesordenado, false);

		for (int i = 0; i < 9; i++) {
			assertEquals(i, arrayOrdenadoAsc[i]);
		}

		for (int i = 0; i < 9; i++) {
			assertEquals(i, arrayOrdenadoDesc[i]);
		}

	}

}
