public class LinkedListDeque<T>{

	private class IntNode{
		public T item;
		public IntNode next;
		public IntNode prev;

		public IntNode(T i, IntNode n, IntNode p){
			item = i;
			next = n;
			prev = p;
		}
	}

	private IntNode sentinel;
	private int size;

	public LinkedListDeque(){
		sentinel = new IntNode(null, sentinel, sentinel);
		size = 0;
	}

	public LinkedListDeque(LinkedListDeque other){
		sentinel = new IntNode(null, sentinel, sentinel);
		size = 0;
		if (other == null){
			return;
		}
		for (int i = 0; i < other.size(); i++){
			addLast((T)other.get(i));
		}
	}

	public void addFirst(T x){
		if (sentinel.next == null){
			IntNode add = new IntNode(x, sentinel, sentinel);
			sentinel.next = add;
			sentinel.prev = add;
		}else{
			IntNode add = new IntNode(x, sentinel.next, sentinel);
			sentinel.next.prev = add;
			sentinel.next = add;
		}
		size += 1;
	}

	public void addLast(T x){
		if (sentinel.next == null){
			IntNode add = new IntNode(x, sentinel, sentinel);
			sentinel.next = add;
			sentinel.prev = add;
		}else{
			IntNode add = new IntNode(x, sentinel, sentinel.prev);
			sentinel.prev.next = add;
			sentinel.prev = add;
		}
		size += 1;
	}

	public int size(){
		return size;
	}

	public T get(int index){
		if (size == 0 || index > size -1)
			return null;

		IntNode p = sentinel.next;

		while (index != 0 && p.next != sentinel){
			p = p.next;
			index -= 1;
		}
		return p.item;
	}

	private T getRecursive(IntNode p, int index)
	{	
		if (index == 0){
			return p.item;
		}
		return getRecursive(p.next, index - 1);
	}

	public T getRecursive(int index){
		if (size == 0 || index > size -1)
			return null;

		IntNode p = sentinel.next;
		return getRecursive(p, index);
	}

	public T removeFirst(){
		if (sentinel.next == sentinel)
			return null;

		T temp = sentinel.next.item;
		if (sentinel.next.next == sentinel){
			sentinel = new IntNode(null, sentinel, sentinel);
			size = 0;
			return temp;
		}

		sentinel.next = sentinel.next.next;
		sentinel.next.prev = sentinel;
		size -= 1;
		return temp;
	}

	public T removeLast(){
		if (sentinel.next == sentinel)
			return null;

		T temp = sentinel.prev.item;
		if (sentinel.next.next == null){
			sentinel = new IntNode(null, sentinel, sentinel);
			size = 0;
			return temp;
		}

		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.next = sentinel;
		size -= 1;
		return temp;
	}

	public boolean isEmpty(){
		return (size == 0);
	}

	public void printDeque(){
		if (size == 0)
			return;

		IntNode p = sentinel;
		while (p.next != sentinel){
			System.out.print(p.next.item + " ");
			p = p.next;
		}
		System.out.println("\n");
	}

	public static void main(String[] args){
		LinkedListDeque L = new LinkedListDeque(null);
		L.addFirst(10);
		L.addFirst(20);
		L.addFirst(30);
		L.addFirst(40);
		L.removeLast();
		L.removeLast();
		L.removeLast();
		L.removeLast();
		L.removeLast();
		L.removeFirst();
		System.out.println(L.size());
	}
}