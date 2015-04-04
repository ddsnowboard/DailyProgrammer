import java.util.ArrayList;

public class Field {
	int length;
	int width;
	String[] lines;
	ArrayList<FieldRow> rows;

	public Field(int length, int width, String[] rows) {
		this.length = length;
		this.width = width;
		this.lines = rows;
		this.rows = new ArrayList<FieldRow>();
		for (String s : rows)
			this.rows.add(new FieldRow(s));
	}

	public boolean get(int x, int y) {
		return this.rows.get(y).get(x);
	}
}
