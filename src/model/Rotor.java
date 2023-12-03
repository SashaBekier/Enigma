package model;

import java.util.HashSet;
import java.util.Random;

public class Rotor {
	private int[] mapping = new int[Common.CHAR_COUNT];
	private int offset = 0;
	private int position = 0;
	private HashSet<Integer> notches= new HashSet<Integer>();
	
	public Rotor(int seed) {
		Random rand = new Random(seed);
		mapping = Common.buildMapping(rand);
		notches.add(rand.nextInt(Common.CHAR_COUNT));
	}
	
	public Rotor(String map, String notch) throws InvalidConfigException{
		boolean[] charCheck = new boolean[Common.CHAR_COUNT];
		for (int i = 0; i< charCheck.length;i++ )charCheck[i] = false;
		for (int i = 0; i < mapping.length; i++) {
			Integer charCode = Common.encodeChar(map.charAt(i));
			if(charCode != null) {
				mapping[i] = charCode;
				charCheck[charCode] = true;
			}
		}
		for (int i = 0; i< charCheck.length;i++ ) {
			if(charCheck[i]==false) throw new InvalidConfigException("Each letter must be represented once in the mapping");
		}
		
		for (int i = 0; i < notch.length(); i++) {
			Integer charCode = Common.encodeChar(notch.charAt(i));
			if(charCode != null && !notches.contains(charCode)){
				notches.add(charCode);
			}
		}
		if(notches.size()<1) throw new InvalidConfigException("At least one notch is required");
	}
	
	public void setOffset(int offset) {
		this.offset = Common.normalise(offset);
	}
	
	public void setPosition(int pos) {
		position = Common.normalise(pos);
	}
	
	public void bumpPosition() {
		setPosition(++position);
	}
	
	public boolean checkNotch() {
		if(notches.contains(Common.normalise(position+offset))) return true;
		return false;
	}
	
	public int getOutboundOutput(int in) {
		return mapping[Common.normalise(in+offset+position)];
	}
	
	public Integer getInboundOutput(int in) {
		int i = 0;
		for(int input: mapping) {
			if(input == in) return Common.normalise(i-offset-position);
			i++;
		}
		return null; //this should never happen
	}
	
	public int getPosition() {
		return position;
	}

	public int[] getMapping() {
		return mapping;
	}
	
}
