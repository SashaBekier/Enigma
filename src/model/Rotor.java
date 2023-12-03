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
		mapping = buildMapping(rand);
		notches.add(rand.nextInt(Common.CHAR_COUNT));
	}
	
	public Rotor(String map, String notch) throws InvalidConfigException{
		setMapping(map);
		setNotches(notch);
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
	
	public void setMapping(String map) throws InvalidConfigException {
		int[] oldmap = mapping.clone();
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
			if(charCheck[i]==false) {
				mapping = oldmap;
				throw new InvalidConfigException("Each CharSet character must be represented once in the mapping.\n" + Common.CHAR_SET);
			}
		}
	}
	
	public int[] getMapping() {
		return mapping;
	}
	
	public void setNotches(String notch) throws InvalidConfigException{
		HashSet<Integer> oldNotches = new HashSet<Integer>();
		for(Integer i: notches) {
			oldNotches.add(i);
		}
		for (int i = 0; i < notch.length(); i++) {
			Integer charCode = Common.encodeChar(notch.charAt(i));
			if(charCode != null && !notches.contains(charCode)){
				notches.add(charCode);
			}
		}
		if(notches.size()<1) {
			notches = oldNotches;
			throw new InvalidConfigException("At least one notch is required");
		}
	}
	
	public HashSet<Integer> getNotches(){
		return notches;
	}
	
	public void setOffset(int offset) {
		this.offset = Common.normalise(offset);
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setPosition(int pos) {
		position = Common.normalise(pos);
	}
	
	public int getPosition() {
		return position;
	}
	
	public void bumpPosition() {
		setPosition(++position);
	}
	
	public boolean checkNotch() {
		if(notches.contains(Common.normalise(position+offset))) return true;
		return false;
	}
	
	private int[] buildMapping(Random rand){
		int[] mapping = new int[Common.CHAR_COUNT];
		HashSet<Integer> chars = new HashSet<Integer>();
		for(int i = 0; i < Common.CHAR_COUNT ; i++) {
			chars.add(i);
		}
		for(int i = 0; i < Common.CHAR_COUNT ; i++) {
			mapping[i] = Common.randomMember(rand, chars);
			chars.remove(mapping[i]);
		}
		return mapping;
	}
}
