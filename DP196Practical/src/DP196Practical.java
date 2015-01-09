public class DP196Practical {

	public static void main(String[] args) {
		MySet<Integer> ints = new MySet<>(1, 3, 5, 7, 9);
		System.out.println(ints.contains(3));
		System.out.println(ints.contains(4));
		MySet<Integer> ints2 = new MySet<>(1,3,5,7,9);
		System.out.println(ints.equals(ints2));
	}

}
