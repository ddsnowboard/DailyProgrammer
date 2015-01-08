import java.util.function.BiConsumer;

public class HashToString implements BiConsumer<Object, Boolean> {
	private String s;

	public HashToString() {
		s = "";
	}

	@Override
	public void accept(Object t, Boolean u) {

		s += t.toString();
	}

	public String toString() {
		return s;
	}
}
