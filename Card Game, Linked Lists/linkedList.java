
public interface linkedList <E>
{
	public void add(int index, E item);	// add an element to the LL at the specific index
	public E remove(int index);			// remove element from LL at index
	public E get(int index);			// get data from node at index
	public void set(E obj);				// update value at node given by index
	public int size();					// get number of nodes in LL
}
