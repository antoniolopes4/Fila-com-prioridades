package projeto;

public interface List<T> {

    public void add(T element);
    public void clear();
    public void add(T element, int index);
    public T remove();
    public T remove(int index);
    public T get(int index);
    public T set(int index, T element);
    public boolean contains(T element);
    public int indexOf(T element);
    public T[] toArray();
    public boolean isEmpty();
    public int size();
}
