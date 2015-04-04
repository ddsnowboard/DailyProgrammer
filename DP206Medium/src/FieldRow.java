import java.util.ArrayList;

public class FieldRow {
	ArrayList<Integer> plots;

	public FieldRow(String row) {
		plots = new ArrayList<Integer>();
		for(int i = 0;i<row.length();i++)
		{
			plots.add(row.charAt(i) == 'x' ? DP206Medium.PLANTED : DP206Medium.EMPTY);
		}
	}
	public boolean get(int i)
	{
		return plots.get(i) == DP206Medium.PLANTED;
	}

}
