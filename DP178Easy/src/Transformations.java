import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//Spec:
//	translate(A, B)     - translate by (A, B) [DONE]
//	rotate(A, B, C)     - rotate around (A, B) by angle C (in radians) clockwise
//	scale(A, B, C)      - scale relative to (A, B) with scale-factor C
//	reflect(axis)       - reflect over the given axis
//	finish()            - end input and print the modified location
//Also, make a pair of functions for each function, one that takes two floats, and one
//that takes a string, parses it, and sends it to the first. Except on reflect and finish, 
//you don't need it for those. 

// Output like this? http://docs.oracle.com/javase/tutorial/2d/basic2d/index.html


public class Transformations {

	public static void main(String[] args) throws IOException {
		String input = readInput();
		ArrayList<String> instructions = new ArrayList<String>(Arrays.asList(input.split("\n")));
		OrderedPair point = new OrderedPair(instructions.get(0));
		instructions.remove(0);
		String current = new String();
		for(String s : instructions)
		{
			current = s;
			if (current.contains("*"))
			{
				
			}
			else if(current.contains("translate"))
			{
				current = clean(current, "translate");
				point.translate(current);
			}
			else if(current.contains("rotate"))
			{
				current = clean(current, "rotate");
				point.rotate(current);
			}
			else if(current.contains("scale"))
			{
				current = clean(current, "scale");
				point.scale(current);
			}
			else if(current.contains("reflect"))
			{
				current = clean(current, "reflect");
				point.reflect(current);
			}
		}
		point.print();

	}

	private static String readInput() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
		String end = "";
		String newest = reader.readLine();
		try
		{
			while(newest != null)
			{
				end+=newest;
				end+="\n";
				newest = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally 
		{
			reader.close();
		}
		return end;
	}

	private static String clean(String toClean, String s)
	{
		toClean = toClean.replace(s, "");
		toClean = toClean.replace("(", "");
		toClean = toClean.replace(")", "");
		toClean = toClean.replace(" ", "");
		return toClean;
	}
}
