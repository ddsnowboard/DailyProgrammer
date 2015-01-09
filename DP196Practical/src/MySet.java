import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class MySet<T> implements Iterable<T> {
	private HashMap<T, Boolean> array;
	private boolean empty;

	public static <T> MySet<T> union(MySet<T> a, MySet<T> b) {
		ArrayList<T> out = new ArrayList<>();
		out.addAll((Collection<? extends T>) a.array.keySet());
		out.addAll((Collection<? extends T>) b.array.keySet());
		return new MySet<T>(out);
	}

	public static <T> MySet<T> intersection(MySet<T> a, MySet<T> b) {
		ArrayList<T> out = new ArrayList<T>();
		for (T o : a) {
			for (T c : b) {
				if (o.equals(c)) {
					out.add(c);
				}
			}
		}
		return new MySet<T>(out);

	}

	public MySet() {
		empty = true;
		array = new HashMap<>();
	}
	@SafeVarargs
	public MySet(T... args)
	{array = new HashMap<>();
		for(T o : args)
		{
			array.put(o, true);
		}
	}
	public MySet(ArrayList<T> orig) {
		array = new HashMap<T, Boolean>();
		for (T o : orig) {
			array.put(o, true);
			empty = false;
		}

	}

	public String toString() {
		return this.array.keySet().toString();
	}

	public boolean isEmpty() {
		return empty;
	}

	public boolean equals(MySet<T> other) {
		return this.array.equals(other.array);
	}
	public boolean contains(T o)
	{
		return this.array.containsKey(o);
	}
	

	@Override
	public Iterator<T> iterator() {
		return array.keySet().iterator();
	}

}
