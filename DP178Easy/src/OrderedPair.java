import java.math.*;
public class OrderedPair {

	private float x;
	private float y;

	public OrderedPair(float x, float y)
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
		this.x = Float.valueOf(coords[0]);
		this.y = Float.valueOf(coords[1]);
	}
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	public void translate(String s) {
		this.translate(Float.valueOf(s.split(",")[0]), Float.valueOf(s.split(",")[1]));
		
	}
	public void translate(float x, float y)
	{
		this.x += x;
		this.y += y;
	}
	public void rotate(String s) {
		this.rotate(Float.valueOf(s.split(",")[0]), Float.valueOf(s.split(",")[0]), Float.valueOf(s.split(",")[2]));
	}
	public void rotate(float x, float y, float theta)
	{
//		THIS IS BROKEN
		double distance = Math.sqrt((this.x-x)*(this.x-x)+(this.y-y)(this.y-y));
		this.x = 
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
