public class SprinklerLocation {
	public int x;
	public int y;
	public int plants;

	public SprinklerLocation(int x, int y, int plants) {
		this.x = x;
		this.y = y;
		this.plants = plants;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(this.x);
		sb.append(", ");
		sb.append(this.y);
		sb.append(") ");
		sb.append(this.plants);
		sb.append(" plants");
		return sb.toString();
	}
}
