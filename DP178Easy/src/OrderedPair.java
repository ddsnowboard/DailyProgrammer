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
	public void clean()
	{
		// Make this round properly. 
		if(Math.abs(this.x%1) < .01 || Math.abs(this.x%1) > .99)
		{
			this.x = new Double(String.valueOf(Math.round(this.x)));
		}
		if(Math.abs(this.y%1) < .01 || Math.abs(this.y%1) > .99)
		{
			this.y = new Double(String.valueOf(Math.round(this.y)));
		}
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
	//Try to cut down on this splitting() many times stuff, I bet it slows things down with a longer file. 
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
		this.x = x+(-1*distance*Math.cos(angle));
		this.y = y+(-1*distance*Math.sin(angle));
	}
	public void scale(String s) {
		this.scale(new Double(s.split(",")[0]), new Double(s.split(",")[1]), new Double(s.split(",")[2]));
	}
	
	public void scale (double x, double y, double factor)
	{
		this.x = x+(factor*(this.x-x));
		this.y = y+(factor*(this.y-y));
	}
	public void reflect(String s) {
		if (s.equals("X"))
		{
			this.y*=-1;
		}
		else if (s.equals("Y"))
		{
			this.x*=-1;
		}
	}
	public void print()
	{
		this.clean();
		System.out.print("("+this.x+","+this.y+")");
	}
}
