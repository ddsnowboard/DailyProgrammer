import java.util.Iterator;

public class SequenceIterator implements Iterator<Integer> {

	private int[] sequence;
	private int cursor = 0;

	public SequenceIterator(int lines) {
		sequence = new int[(lines * 2) - 1];
		for (int i = 0; i < lines; i++) {
			sequence[i] = i;
		}
		for (int i = lines; i < sequence.length; i++)
			sequence[i] = (lines - i) + 1;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Integer next() {
		return sequence[cursor % sequence.length];
	}

}
