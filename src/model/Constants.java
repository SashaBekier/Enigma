package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class Constants {
	public static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final int CHAR_COUNT = CHAR_SET.length();
	
	public static int normalise(int i) {
		while(i < 0) i += Constants.CHAR_COUNT;
		if(i >= Constants.CHAR_COUNT) i %= Constants.CHAR_COUNT;
		return i;
	}
	
	public static int[] buildMapping(Random rand){
		int[] mapping = new int[CHAR_COUNT];
		HashSet<Integer> chars = new HashSet<Integer>();
		for(int i = 0; i < CHAR_COUNT ; i++) {
			chars.add(i);
		}
		for(int i = 0; i < CHAR_COUNT ; i++) {
			int hit = rand.nextInt(chars.size());
			int count = 0;
			for(Integer c: chars) {
				if(count == hit) {
					mapping[i] = c;
					chars.remove(c);
					break;
				}
				count++;
			}
		}
		return mapping;
	}

	public static char intToChar(int in) {
		return  CHAR_SET.charAt(in);
	}
	
	public static void drawMapping(int[] mapping) {
		System.out.println(CHAR_SET);
		for(int i = 0; i< CHAR_COUNT; i++) {
			System.out.print(intToChar(mapping[i]));
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
	
}
