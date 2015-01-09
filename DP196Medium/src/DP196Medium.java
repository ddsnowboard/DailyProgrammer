import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DP196Medium {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Pattern pattern = Pattern
				.compile("(?<action>(enc)|(dec)) (?<number>[0-9]+) (?<text>[A-Za-z]+)");
		String i;
		Matcher match;
		while (true) {
			i = input.nextLine();
			match = pattern.matcher(i);
			if (!match.matches())
				System.out
						.println("You didn't use the format \"enc/dec # TEXT\"");
			else {
				input.close();
				if (match.group("action").equals("enc")) {
					System.out.println(encode(match.group("text"),
							Integer.parseInt(match.group("number"))));
				} else if (match.group("action").equals("dec"))
					;
				{
					System.out.println(decode(match.group("text"),
							Integer.parseInt(match.group("number"))));
				}
				break;
			}
		}
	}

	public static String decode(String text, int lines) {
		Iterator<Integer> sequence = new SequenceIterator(lines);
		
		return null;
	}

	public static String encode(String text, int lines) {
		return null;

	}

}
