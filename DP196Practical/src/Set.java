import java.util.ArrayList;
import java.util.HashMap;

public class Set<T> {
	private HashMap<Object, Boolean> array;
	private boolean empty;

	public static Set union(Set a, Set b)
	{
		ArrayList<Object> out = new ArrayList<>();
			
	}
	public Set() {
		empty = true;
		array = new HashMap<>();
	}

	public Set(Object[] orig) {
		for (Object o : orig) {
			array.put(o, true);
		}
		empty = false;

	}

	public Set(ArrayList<T> orig) {
		for (T o : orig) {
			array.put(o, true);
			empty = false;
		}

	}

	public String toString() {
		HashToString h2s = new HashToString();
		for (int i = 0; i < array.size(); i++) {
			array.forEach(h2s);
		}
		return h2s.toString();
	}

	public boolean isEmpty() {
		return empty;
	}

}
