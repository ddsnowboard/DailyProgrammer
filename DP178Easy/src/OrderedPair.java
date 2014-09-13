public class OrderedPair {

	private double x;
	private double y;

	public OrderedPair(double x, double y)
	{
		this.x = x;
		this.y= y;
	}
	public OrderedPair(String pair)
	{
		pair = pair.replace("(", "");
		pair = pair.replace(")", "");
		pair = pair.replace(" ","");
		String[] coords = pair.split(",");
		this.x = new Double(coords[0]);
		this.y = new Double(coords[1]);
	}
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	public void translate(String s) {
		this.translate(new Double(s.split(",")[0]), new Double(s.split(",")[1]));
		
	}
	public void translate(double x, double y)
	{
		this.x += x;
		this.y += y;
	}
	public void rotate(String s) {
		this.rotate(new Double(s.split(",")[0]), new Double(s.split(",")[1]), new Double(s.split(",")[2]));
	}
	public void rotate(double x, double y, double theta)
	{
		double distance = Math.sqrt((this.x-x)*(this.x-x)+(this.y-y)*(this.y-y));
		double angle = Math.atan((this.y-y)/(this.x-x));
		angle-=theta;
		this.x = x+(distance*Math.cos(angle));
		this.y = y+(distance*Math.sin(angle));
	}
	public void scale(String s) {
		// TODO Auto-generated method stub
		
	}
	public void reflect(String s) {
		// TODO Auto-generated method stub
		
	}
	public void print()
	{
		System.out.print("("+this.x+","+this.y+")");
	}
}
