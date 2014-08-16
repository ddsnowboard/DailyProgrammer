package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LargestWord {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		String[] words = in.readLine().split(" ");
		String[] letters = in.readLine().split(" ");
		in.close();
		String longest = "";
		ArrayList<Character> tempWord = new ArrayList<Character>();
		ArrayList<Character> tempLetters = new ArrayList<Character>();
		for(String w : words)
		{
			tempWord.clear();
			for(int i = 0; i<w.length(); i++)
			{
				tempWord.add(w.charAt(i));
			}
			for(Character c : tempWord)
			{
				
			}
		}
	}

}
