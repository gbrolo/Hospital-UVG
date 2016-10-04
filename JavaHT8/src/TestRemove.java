
import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

/**
 * 
 * VOLVIMOS A ENVIAR LA HOJA DE TRABAJO 8 PORQUE HABIA UN ERROR EN EL JAVA DOC ESTA VERSION YA ESTA CORREGIDA
 * 
 * https://github.com/gbrolo/Hospital-UVG.git
 * 
 * Pruebas Unitarias para la eliminacion de elementos en la implementacion VectorHeap.
 * @author Gabriel Brolo, Jose Luis Mendez
 *
 */

public class TestRemove {

	/**
	 * Prueba eliminar una cadena en la implementacion VectorHeap.
	 */
	@Test
	public void testRemove() {
		VectorHeap<String> pQueue = new VectorHeap<String>();
		pQueue.add("C");
		pQueue.add("A");
		
		// Test: remover un elemento del VectorHeap
		String result = pQueue.remove();
		assertEquals("A", result); // Si se removio correctamente, debe regresar el valor con mayor prioridad, que es A.
	}
	
	/**
	 * Prueba eliminar un entero en la implementacion VectorHeap.
	 */
	@Test
	public void testRemoveInt() {
		VectorHeap<Integer> pQueue = new VectorHeap<Integer>();
		pQueue.add(34);
		pQueue.add(21);
		
		// Test: remover un elemento del VectorHeap
		int result = pQueue.remove();
		assertEquals(21, result); // Si se removio correctamente, debe regresar el valor con mayor prioridad, que es 21.
	}
	
	/**
	 * Prueba eliminar un objeto de tipo Paciente en la implementacion VectorHeap.
	 */
	@Test
	public void testRemovePaciente() {
		VectorHeap<Paciente> pQueue = new VectorHeap<Paciente>();
		Paciente pPrueba = new Paciente("Juan", "Sifilis", "B");
		Paciente pPrueba2 = new Paciente("Rodrigo", "34 heridas de bala", "A");
		pQueue.add(pPrueba);
		pQueue.add(pPrueba2);
		
		// Test: remover un elemento del VectorHeap
		Paciente result = pQueue.remove();
		assertEquals(pPrueba2, result); // Si se removio correctamente, debe regresar el valor con mayor prioridad, que es pPrueba2.
	}

}
