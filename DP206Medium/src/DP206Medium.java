import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class DP206Medium {
	public static final int EMPTY = 0;
	public static final int PLANTED = 1;

	public static void main(String[] args) {
		File f = new File("input.txt");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("Something bad happened!");
			e.printStackTrace();
		}
		int[] dims = Arrays.stream(s.nextLine().split(" "))
				.mapToInt(str -> Integer.parseInt(str)).toArray();
		int height = dims[0] - 1;
		int width = dims[1] - 1;
		int radius = dims[2];
		ArrayList<String> rows = new ArrayList<String>();
		while (s.hasNextLine()) {
			rows.add(s.nextLine());
		}
		Field field = new Field(dims[0], dims[1], rows.toArray(new String[rows
				.size()]));
		ArrayList<SprinklerLocation> locations = new ArrayList<SprinklerLocation>();
		int currentCount;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				currentCount = 0;
				for (int currx = 0; currx < width; currx++) {
					for (int curry = 0; curry < height; curry++) {
						if (field.get(currx, curry)
								&& !(currx == x && curry == y)
								&& Math.ceil(dist(x, y, currx, curry)) <= radius) {
							currentCount++;
						}
					}
				}
				locations.add(new SprinklerLocation(x, y, currentCount));
			}
		}
		Collections.sort(locations,
				(SprinklerLocation s1, SprinklerLocation s2) -> s2.plants
						- s1.plants);
		System.out.println(locations.get(0).toString());
	}

	public static double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}
