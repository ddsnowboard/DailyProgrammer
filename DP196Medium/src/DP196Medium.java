import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DP196Medium {
	public static final String CHARACTER = "CHARACTER";

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Pattern pattern = Pattern
				.compile("(?<action>(enc)|(dec)) (?<number>[0-9]+) (?<text>[A-Za-z0-9]+)");
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
				} else if (match.group("action").equals("dec")) {
					System.out.println(decode(match.group("text"),
							Integer.parseInt(match.group("number"))));
				}
				break;
			}
		}
	}

	public static String decode(String text, int lines) {
		Iterator<Integer> sequence = new SequenceIterator(lines);
		String[][] fence = new String[lines][text.length()];
		for (int i = 0; i < text.length(); i++) {
			fence[sequence.next()][i] = CHARACTER;
		}
		int cursor = 0;
		for (int i = 0; i < fence.length; i++) {
			for (int j = 0; j < fence[i].length; j++) {
				if (fence[i][j] == CHARACTER) {
					fence[i][j] = String.valueOf(text.charAt(cursor));
					cursor++;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		cursor = 0;
		sequence = new SequenceIterator(lines);
		for (int i = 0; i < text.length(); i++) {
			sb.append(fence[sequence.next()][cursor]);
			cursor++;
		}
		return sb.toString();
	}

	public static String encode(String text, int lines) {
		String[][] fence = new String[lines][text.length()];
		SequenceIterator sequence = new SequenceIterator(lines);
		for (int i = 0; i < text.length(); i++) {
			fence[sequence.next()][i] = String.valueOf(text.charAt(i));
		}
		StringBuilder sb = new StringBuilder();
		for (String[] a : fence) {
			for (String s : a) {
				if (s != null)
					sb.append(s);
			}
		}
		return sb.toString();
	}

}
