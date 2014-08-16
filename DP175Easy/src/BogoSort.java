import java.util.ArrayList;
import java.util.Random;


public class BogoSort {

	public static void main(String[] args) {
		ArrayList<Integer> times = new ArrayList<Integer>();
		for(int i=0; i<100000;i++)
		{
			times.add(bogoSort(args[0], args[1]));
		}
		float avg = 0;
		long tot = 0;
		for(int d : times)
		{
			tot+=d;
		}
		avg = 1.0f * tot/times.size();
		System.out.printf("Average after 100000 times: %f", avg);
		
	}
	public static int bogoSort(String shuffled, String main)
	{
		int times = 0;
		while(!main.equals(shuffled))
		{
			shuffled = scramble(shuffled);
			times++;
		}
		return times;
	}
	public static String scramble(String str) {
		ArrayList<Character> in = new ArrayList<Character>();
		for(int i = 0; i<str.length();i++)
		{
			in.add(str.charAt(i));
		}
		ArrayList<Character> out = new ArrayList<Character>();
		Random rand = new Random();
		String ret = "";
		int pick = 0;
		while(!in.isEmpty())
		{	
			pick = rand.nextInt(in.size());
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
