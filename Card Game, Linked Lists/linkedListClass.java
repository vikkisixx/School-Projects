

public class linkedListClass <E> implements linkedList <E>
{
	private class Node<F>					// since LL is made up of Nodes, create inner private Node class
	{
		private F data;
		private Node<F> next;
		
		public Node(F data, Node<F> next)	// constructors: creates node with data and next link
		{
			this.data = data;
			this.next = next;
		}
		
		public Node(F data)					// constructor: creates a node with just data, no link
		{
			// this.data = data;
			// this.next = null;
			this(data, null);				// shorter way, calls first constructor
		}
	}										// end of Node class, still inside singleLinkedList class
	
	private Node<E> head;					// data for SLL
	private int size;
	
	public linkedListClass()				// constructor 
	{
		this.head = new Node<E>(null);		// data of head usually stays null. just stores next pointer
		this.size = 0;
	}
	
	@Override
	public void add(int index, E item) 				// adds element at index
	{
		if(index < 0 || index > size)
		{
			System.out.println("invalid index");
			return;
		}
		else if(index == 0)
			addFirst(item);
		else
		{
			Node<E> node = getNode(index);			// need node reference 				 !!! was index - 1
			addAfter(node, item);
		}
	}
	
	private Node<E> getNode(int index)			
	{
		Node<E> node = head;
		for(int x = 0; x < index && node != null; x++)
			node = node.next;							// iterates up to index
		
		return node;
	}
	
	private void addFirst(E item)
	{
		head.next = new Node<E>(item, head.next);	// create new node, have head point to new node: node(item, next pointer)
		size++;
	}

	private void addAfter(Node<E> node, E item) 	
	{
		node.next = new Node<E>(item, node.next);	// create new node, have incoming node point to new node: node(item, next pointer)
		size++;
		
	}

	@Override
	public E remove(int index) 
	{
		if(index < 0 || index >= size)
			System.out.println("invalid index");
		else if(index == 0)
			return removeFirst();
		else
		{
			Node<E> node = getNode(index - 1);
			return removeAfter(node);
		}
		return null;
	}

	public E removeAfter(Node<E> node) 				// FIX
	{
		Node<E> temp = node.next;					// new pointer at this node
		
		if(node == null)
			return null;
		
		if(node.next == null)
		{
			System.out.println("null here");
			return null;
		}
		
		node.next = node.next.next;
		size--;
		
		return temp.data;
	}

	private E removeFirst()							// fix 
	{
		Node<E> temp = head.next;
		
		if(head == null)
			return null;
		
		if(head.next == null)
		{
			System.out.println("empty list");
			return null;
		}

		head.next = head.next.next;
		size--;
		return temp.data; 
		
	}

	@Override
	public E get(int index) 
	{
		return getNode(index).data;
	}

	@Override
	public void set(E obj) 
	{
		
	}

	@Override
	public int size() 
	{
		return size;
	}
	
	@Override
	public String toString()
	{
		String s = "";
		Node<E> p = head;
		while(p.next != null)				// iterates through nodes to print
		{
			s += p.next.data + "  ";		// add data to string
			p = p.next;						// iterate
		}
		return s;
		
	}
}
