public class ArrayDeque<T>implements Deque<T>{
	private T[] items;
	private int size;

	public ArrayDeque(){
		T[] a = (T[]) new Object[8];
		items = a;
		size = 0;
	}

	public ArrayDeque(ArrayDeque other){
		int length = other.size();
		T[] items = (T[]) new Object[length];
		for (int i = 0; i < length; i++){
			addLast((T)other.items[i]);
		}
	}

	private void resize(int start, int capacity)
	{
		T[] a = (T[]) new Object[capacity];
		System.arraycopy(items, 0, a, start, size);
		items = a;
	}

	@Override
	public void addFirst(T x){
		int len = items.length;
		if (size >= items.length){
			len = 4 * len;
		}
		resize(1, len);
		items[0] = x;
		size += 1;
	}

	@Override
	public void addLast(T x){
		if (size >= items.length){
			resize(0, items.length * 4);
		}
		items[size] = x;
		size += 1;
	}

//	public boolean isEmpty(){
//		return (size == 0);
//	}

	@Override
	public int size(){
		return size;
	}

	@Override
	public void printDeque(){
		for (int i = 0; i < size; i++){
			System.out.print(items[i] + " ");
		}
		System.out.println("\n");
	}

	@Override
	public T removeFirst(){
		if (size == 0)
			return null;
		T temp = items[0];
		if (size == 1) {
			items[0] = null;
			size = 0;
			return temp;
		}

		T[] a = (T[]) new Object[items.length];
		System.arraycopy(items, 1, a, 0, size-1);
		items = a;
		size -= 1;
		if (size < items.length / 4){
			resize(0, items.length / 4);
		} 
		return temp;
	}

	@Override
	public T removeLast(){
		if (size == 0)
			return null;
		T temp = items[size - 1];
		if (size == 1) {
			items[0] = null;
			size = 0;
			return temp;
		}
		items[size - 1] = null;
		size -= 1;
		if (size < items.length / 4){
			resize(0, items.length / 4);
		}
		return temp;
	}

	@Override
	public T get(int index){
		if (index >= size){
			return null;
		}
		return items[index];
	}
}
