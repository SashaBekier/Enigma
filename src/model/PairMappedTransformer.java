package model;

import java.util.HashSet;
import java.util.Random;

public abstract class PairMappedTransformer {
	private int[] mapping = new int[Constants.CHAR_COUNT];
	private boolean[] slotAvailable = new boolean[Constants.CHAR_COUNT];
	
	public PairMappedTransformer() {
		for(int i =0; i < slotAvailable.length; i++) {
			slotAvailable[i] = true;
		}
	}
	
	public PairMappedTransformer(int seed, int pairs) {
		this();
		Random rand = new Random(seed);
		HashSet<Integer> pool = new HashSet<Integer>();
		for(int i = 0; i<Constants.CHAR_COUNT ; i++) {
			pool.add(i);
		}
		for(int i = 0; i < pairs; i++) {
			int a = rand.nextInt(pool.size());
			int count = 0;
			for(Integer hit: pool) {
				if(a == count) {
					a = hit;
					break;
				} 
				count++;
			}
			pool.remove(a);
			int b = rand.nextInt(pool.size());
			count = 0;
			for(Integer hit: pool) {
				if(b == count) {
					b = hit;
					break;
				} 
				count++;
			}
			pool.remove(b);
			addPair(a,b);
		}
		
		
		
	}
	
	public int getOutput(int in) {
		return mapping[in];
	}
	
	public void addPair(int letter, int toLetter) {
		//System.out.println("adding " + letter + " and " + toLetter );
		//System.out.println( slotAvailable[letter] + " and " + slotAvailable[toLetter] );
		if(slotAvailable[letter] && slotAvailable[toLetter]) {
			//System.out.println("adding " + letter + " and " + toLetter );
			
			mapping[letter] = toLetter;
			mapping[toLetter] = letter;
			slotAvailable[letter] = false; 
			slotAvailable[toLetter] = false;
		}
	}
	
	public String getOpenSlots() {
		String output = "";
		for(int i=0 ; i < Constants.CHAR_COUNT; i++) {
			if(slotAvailable[i]) output += Constants.CHAR_SET.charAt(i);
		}
		return output;
	}
	
	public int[] getMapping() {
		return mapping;
	}
}
