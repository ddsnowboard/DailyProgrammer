import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SymanticVersionSort {

	public static void main(String[] args) {
		File f = new File("input.txt");
		try {
			Scanner i = new Scanner(f);
			i.nextInt();
			String temp;
			ArrayList<Version> versions = new ArrayList<Version>();
			while (i.hasNext()) {
				temp = i.nextLine();
				if(!temp.equals(""))
					versions.add(new Version(temp));
			}
			i.close();
			// Bubble sort, I know. Please forgive me.
			boolean done = false;
			while (!done) {
				done = true;
				for (int q = 1; q < versions.size(); q++) {
					if(switchable(versions.get(q-1),versions.get(q)))
					{
						Collections.swap(versions, q, q-1);
						done = false;
					}
				}
			}
			for(Version v : versions)
				System.out.println(v.orig);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static boolean switchable(Version earlier, Version later) {
		if(earlier.major>later.major)
			return true;
		if(earlier.minor>later.minor && earlier.major == later.major)
			return true;
		if (earlier.patch>later.patch && earlier.major == later.major && earlier.minor == later.minor)
			return true;
		if (earlier.patch == later.patch && earlier.minor == later.minor && earlier.major == later.major)
			return later.label != null;
		return false;
	}

}
