package model;

import java.util.HashSet;
import java.util.Random;

public abstract class PairMappedTransformer {
	private int[] mapping = new int[Constants.CHAR_COUNT];
	private boolean[] slotAvailable = new boolean[Constants.CHAR_COUNT];
	
	public PairMappedTransformer() {
		for(int i =0; i < Constants.CHAR_COUNT; i++) {
			slotAvailable[i] = true;
			mapping[i] = i;
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
			int a = Constants.randomMember(rand, pool);
			pool.remove(a);
			int b = Constants.randomMember(rand, pool);
			pool.remove(b);
			addPair(a,b);
		}
	}
	
	public int getOutput(int in) {
		return mapping[in];
	}
	
	public void addPair(int a, int b) {
		if(slotAvailable[a] && slotAvailable[b]) {
			mapping[a] = b;
			mapping[b] = a;
			slotAvailable[a] = false; 
			slotAvailable[b] = false;
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
