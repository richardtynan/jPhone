package jphone;

public class Utils {

	public static String chew(int num, String message) {
		String copy = message;
		for (int i = 0; i < num; i++) {
			copy = copy.substring(copy.indexOf(' ') + 1);
		}
		return copy;
	}
}
