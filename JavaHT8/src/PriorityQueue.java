/**
 * Interfaz de una Cola con prioridad. Basada en el libro Java Structures. Define las caracteristicas basicas de
 * una cola con prioridad utilizando Heaps, para ser mas especifico, de un MinHeap.
 * 
 * @author D. Bailey (Java Structures), Gabriel Brolo 15105, Jose Luis Mendez 15024
 *
 * @param <E> Cualquier objeto
 */
public interface PriorityQueue<E extends Comparable<E>>
{
	/**
	 * pre: !isEmpty()
	 * @return returns the minimum value in priority queue
	 */
	public E getFirst();

	/**
	 * pre: !isEmpty()
	 * @return returns and removes minimum value from queue
	 */
	public E remove();

	/**
	 * pre: value is non-null comparable
	 * @param value, value is added to priority queue
	 */
	public void add(E value);
	
	/**
	 * 
	 * @return returns true if no elements are in queue
	 */
	public boolean isEmpty();
	
	/**
	 * 
	 * @return returns number of elements within queue
	 */
	public int size();
	
	/**
	 * post: removes all elements from queue
	 */
	public void clear();
}