package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class Common {
	public static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final int CHAR_COUNT = CHAR_SET.length();
	
	public static int normalise(int i) {
		while(i < 0) i += Common.CHAR_COUNT;
		if(i >= Common.CHAR_COUNT) i %= Common.CHAR_COUNT;
		return i;
	}
	
	public static int[] buildMapping(Random rand){
		int[] mapping = new int[CHAR_COUNT];
		HashSet<Integer> chars = new HashSet<Integer>();
		for(int i = 0; i < CHAR_COUNT ; i++) {
			chars.add(i);
		}
		for(int i = 0; i < CHAR_COUNT ; i++) {
			mapping[i] = randomMember(rand, chars);
			chars.remove(mapping[i]);
		}
		return mapping;
	}

	public static void drawMapping(int[] mapping) {
		System.out.println(CHAR_SET);
		for(int i = 0; i< CHAR_COUNT; i++) {
			System.out.print(CHAR_SET.charAt(mapping[i]));
		}
	}
	
	public static int randomMember(Random rand, Collection<Integer> pool) {
		int a = rand.nextInt(pool.size());
		int count = 0;
		for(Integer hit: pool) {
			if(a == count) {
				a = hit;
				break;
			} 
			count++;
		}
		return a;
	}
	
	public static boolean inCharSet(char c) {
		if(Common.CHAR_SET.contains("" + c)) return true;
		return false;
	}
	
	public static Integer encodeChar(char c) {
		for(int i = 0; i < CHAR_COUNT; i++) {
			if(CHAR_SET.charAt(i)==c) return i;
		}
		return null;
	}
	
	public static Character decodeInt(int i) {
		return CHAR_SET.charAt(normalise(i));
	}
	
}
