package projeto;

public class ArrayList<T> implements List<T> {
        private static final int DEFAULT_CAPACITY = 4;
    
	private T[] array;
	private int capacity;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ArrayList() {
		capacity = DEFAULT_CAPACITY;
		array = (T[]) new Object[capacity];
		size = 0;
	}
	
	public void add(T element) {
		if(isFull()) {
			alargeArray();
		}
		
		array[size] = element;
		size++;
	}
	
	public void clear() {
		size = 0;
	}
	
	public void add(T element, int index) {
		if(index >= 0 && index <= size) {
			if(isFull()) {
				alargeArray();
			}
			
			for(int i = size; i > index; i--)
				array[i] = array[i-1];
			
			array[index] = element;
			size++;
		}
	}
	
	public T remove() {
		if(!isEmpty()) {
			T removedElement = array[size-1];
			size--;
			return removedElement;
		}
		else
                    throw new ArrayIndexOutOfBoundsException("Invalid index.");
	}
	
	public T remove(int index) {
		if(index >= 0 && index < size) {
			T removedItem = array[index];
				
			for(int i = index; i < size-1; i++)
				array[i] = array[i+1];
				
			size--;
			return removedItem;
		}
		else
			throw new ArrayIndexOutOfBoundsException("Invalid index.");
	}
	
	public T get(int index) {
		if(index >= 0 && index < size) {
			return array[index];
		}
		else
			throw new ArrayIndexOutOfBoundsException("Invalid index.");
	}
	
	public T set(int index, T element) {
		if(index >= 0 && index <= size) {
			T replacedElement = array[index];
			array[index] = element;
			
			return replacedElement;
		}
		else
			throw new ArrayIndexOutOfBoundsException("Invalid index.");
	}
	
	public boolean contains(T element) {
		if(!isEmpty()) {
			for(int i = 0; i < size; i++) {
				if(array[i] == element)
					return true;
			}
			
			return false;
		}
		else
			throw new ArrayIndexOutOfBoundsException("List is empty.");
	}
	
	public int indexOf(T element) {
		if(!isEmpty()) {
			for(int i = 0; i < size; i++) {
				if(array[i] == element)
					return i;
			}
			
			return -1;
		}
		else
			throw new ArrayIndexOutOfBoundsException("List is empty.");
	}
	
	@SuppressWarnings("unchecked")
	private void alargeArray() {
		capacity = capacity * 2;
		
		T[] newArray = (T[]) new Object[capacity];
		
		for(int i = 0; i < size; i++)
			newArray[i] = array[i];
		
		array = newArray;
	}
	
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] newArray = (T[]) new Object[capacity];
			
		for(int i = 0; i < size; i++)
			newArray[i] = array[i];
			
		return newArray;
	}
	
	private boolean isFull() {
		return size == capacity;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
}

