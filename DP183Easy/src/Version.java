import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Version {
	private Pattern pattern;
	private Matcher matcher;
	public Integer major;
	public Integer minor;
	public Integer patch;
	String label;
	String metadata;

	public Version(String v) {
		try {
			pattern = Pattern
					.compile("(?<major>\\p{Digit}+?)\\.(?<minor>\\p{Digit}+?)\\.(?<patch>\\p{Digit}+)(?<label>\\-.+)?(?<meta>\\+.+)?");
			matcher = pattern.matcher(v);
			if (matcher.matches()) {
				major = Integer.decode(matcher.group("major"));
				minor = Integer.decode(matcher.group("minor"));
				patch = Integer.decode(matcher.group("patch"));
				label = matcher.group("label");
				metadata = matcher.group("meta");
			}
		} catch (IllegalStateException e) {
			System.out.println(v);
			e.printStackTrace();
		}
	}
	public void print()
	{
		Integer[] l = {major, minor, patch};
		StringBuilder b = new StringBuilder();
		for(Integer s : l)
		{
			b.append(s);
		}
		b.append("\n");
		System.out.print(b.toString());
	}
}
