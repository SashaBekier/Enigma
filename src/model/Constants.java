package model;

public class Constants {
	public static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final int CHAR_COUNT = CHAR_SET.length();
	
	public static int normalise(int i) {
		while(i < 0) i += Constants.CHAR_COUNT;
		if(i >= Constants.CHAR_COUNT) i %= Constants.CHAR_COUNT;
		return i;
	}
	
}
