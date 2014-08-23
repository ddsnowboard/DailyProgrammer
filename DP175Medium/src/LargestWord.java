package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LargestWord {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		final String[] words = in.readLine().split(" ");
		final String[] letters = in.readLine().split(" ");
		in.close();
		String longest = "";
		ArrayList<String> longList = new ArrayList<String>();
		boolean works = false;
		boolean tie = false;
		ArrayList<Character> tempWord = new ArrayList<Character>();
		ArrayList<Character> tempLetters = new ArrayList<Character>();
		for(String w : words)
		{
			works = true;
			tempWord.clear();
			tempLetters.clear();
			for(int i = 0; i<w.length(); i++)
			{
				tempWord.add(w.charAt(i));
			}
			for(String s : letters)
			{
				tempLetters.add(s.charAt(0));
			}
			for(Character c : tempWord)
			{
				if(!tempLetters.remove(c))
				{
					works = false;
				}
			}
			if(works && longest.length() < w.length())
			{
				longest = w;
				tie = false;
			}
			else if(works && longest.length() == w.length())
			{
				tie = true;
				if(longList.isEmpty())
					longList.add(longest);
				longList.add(w);
			}
		}
		if(tie)
		{
			for(String s : longList)
			{
				System.out.print(s+" ");
			}
		}
		else
		{
			System.out.print(longest);
		}
	}

}
