import java.util.ArrayList;
import java.util.Random;


public class BogoSort {

	public static void main(String[] args) {
		int times = 0;
		String mainString = args[1];
		String changableString = args[0];
		while(!mainString.equals(changableString))
		{
			changableString = scramble(changableString);
			times++;
//			System.out.println(changableString);
		}
		System.out.printf("%d iterations", times);
		System.out.println("Hi");
	}

	public static String scramble(String str) {
		ArrayList<Character> in = new ArrayList<Character>();
		for(int i = 0; i<str.length();i++)
		{
			in.add(str.charAt(i));
		}
		ArrayList<Character> out = new ArrayList<Character>();
		Random rand = new Random(82297);
		String ret = "";
		int pick = 0;
		while(!in.isEmpty())
		{	
			pick = rand.nextInt(in.size()+1);
			out.add(in.remove(pick));
			in.trimToSize();
		}
		for(int i = 0; i<out.size();i++)
		{
			ret+=out.get(i);
		}
		return ret;
	}
}
