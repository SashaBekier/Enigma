package model;

import java.util.HashSet;
import java.util.Random;

public class Rotor {
	private int[] mapping = new int[Constants.CHAR_COUNT];
	private int offset = 0;
	private int position = 0;
	private HashSet<Integer> notches= new HashSet<Integer>();
	
	public Rotor(int seed) {
		Random rand = new Random(seed);
		mapping = Constants.buildMapping(rand);
		notches.add(rand.nextInt(Constants.CHAR_COUNT));
	}
	
	public Rotor(String map, String notch) throws InvalidConfigException{
		boolean[] charCheck = new boolean[Constants.CHAR_COUNT];
		for (int i = 0; i< charCheck.length;i++ )charCheck[i] = false;
		for (int i = 0; i < mapping.length; i++) {
			try {
				mapping[i] = map.charAt(i) - 'A';
				charCheck[map.charAt(i) - 'A'] = false;
			} catch (IndexOutOfBoundsException e){
				throw new InvalidConfigException("All letters must be represented in the mapping.");
			}
		}
		for (int i = 0; i< charCheck.length;i++ ) {
			if(charCheck[i]==false) throw new InvalidConfigException("Each letter must be represented once in the mapping");
		}
		
		for (int i = 0; i < notch.length(); i++) {
			if(!notches.contains(notch.charAt(i)-'A')){
				notches.add(notch.charAt(i)-'A');
			}
		}
		if(notches.size()<1) throw new InvalidConfigException("At least one notch is required");
	}
	
	public void setOffset(int offset) {
		this.offset = Constants.normalise(offset);
	}
	
	public void setPosition(int pos) {
		position = Constants.normalise(pos);
	}
	
	public void bumpPosition() {
		position = Constants.normalise(++position);
	}
	
	public boolean checkNotch() {
		if(notches.contains(Constants.normalise(position))) return true;
		return false;
	}
	
	public int getOutboundOutput(int in) {
		return mapping[Constants.normalise(in+offset)];
	}
	
	public Integer getInboundOutput(int in) {
		int i = 0;
		in = Constants.normalise(in+offset);
		for(int input: mapping) {
			if(input == in) return i;
			i++;
		}
		return null; //this should never happen
	}
	
}
