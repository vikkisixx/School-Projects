
public interface ArrayListInterface <E>
{
	public void add(E a);				// add to end of ArrayList
	public void add(E a, int index); 	// adds element at index
	public E remove(int index); 		// removes data at index]
	public E get(int index); 			// method returns object at index
	public void set(E a, int index); 	// updates value at index
	public int getSize(); 				// returns total number of elements
	public int indexOf(E a); 			// returns index of element a
}
