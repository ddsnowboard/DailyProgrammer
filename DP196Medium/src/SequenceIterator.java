import java.util.Iterator;

public class SequenceIterator implements Iterator<Integer> {

	private int[] sequence;
	private int cursor = 0;

	public SequenceIterator(int lines) {
		int counter = 0;
		sequence = new int[(lines * 2) - 2];
		for (int i = 0; i < lines; i++) {
			sequence[counter] = i;
			counter++;
		}
		for (int i = 1; i <= sequence.length - lines; i++) {
			sequence[counter] = (lines - i) - 1;
			counter++;
		}
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Integer next() {
		cursor++;
		return sequence[cursor++ % sequence.length];
	}

}
