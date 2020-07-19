public class LinkedListDeque{

	private class IntNode{
		public int item;
		public IntNode next;
		public IntNode prev;

		public IntNode(int i, IntNode n, IntNode p){
			item = i;
			next = n;
			prev = p;
		}
	}

	private IntNode sentinel;
	private int size;

	public LinkedListDeque(){
		sentinel = new IntNode(63, null, null);
		size = 0;
	}

	public LinkedListDeque(LinkedListDeque other){
		sentinel = new IntNode(63, null, null);
		size = 0;
		for (int i = 0; i < other.size(); i++){
			this.addLast(other.get(i));
		}
	}

	public void addFirst(int x){
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

	public void addLast(int x){
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

	public java.lang.Integer get(int index){
		if (size == 0 || index > size -1)
			return null;

		IntNode p = sentinel.next;

		while (index != 0 && p.next != sentinel){
			p = p.next;
			index -= 1;
		}
		return p.item;
	}

	private java.lang.Integer getRecursive(IntNode p, int index)
	{	
		if (index == 0){
			return p.item;
		}
		return getRecursive(p.next, index - 1);
	}

	public java.lang.Integer getRecursive(int index){
		if (size == 0 || index > size -1)
			return null;

		IntNode p = sentinel.next;
		return getRecursive(p, index);
	}

	public java.lang.Integer removeFirst(){
		if (sentinel.next == null)
			return null;

		int temp = sentinel.next.item;
		if (sentinel.next.next == null){
			sentinel = new IntNode(63, null, null);
			size = 0;
			return temp;
		}

		sentinel.next = sentinel.next.next;
		sentinel.next.prev = sentinel;
		size -= 1;
		return temp;
	}

	public java.lang.Integer removeLast(){
		if (sentinel.next == null)
			return null;

		int temp = sentinel.next.item;
		if (sentinel.next.next == null){
			sentinel = new IntNode(63, null, null);
			size = 0;
			return temp;
		}

		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.next = sentinel;
		size -= 1;
		return temp;
	}

	public boolean isEmpty(){
		return (sentinel.next == null);
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
		LinkedListDeque L = new LinkedListDeque();
		L.addFirst(10);
		L.addFirst(20);
		L.addLast(30);
		L.addLast(50);

		System.out.println(L.removeFirst());
		System.out.println(L.removeLast());
		System.out.println(L.isEmpty());

		L.printDeque();

		System.out.println("\n");
		System.out.println(L.getRecursive(2));
	}

}