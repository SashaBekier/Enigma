package model;

public class PlugBoard {
	private PlugBoard instance = new PlugBoard();
	private int[] mapping = new int[Constants.CHAR_COUNT];
	private boolean[] plugAvailable = new boolean[Constants.CHAR_COUNT];
	
	
	private PlugBoard() {
		for(int i=0 ; i < Constants.CHAR_COUNT; i++) {
			mapping[i] = i;
			plugAvailable[i] = true;
		}
	}
	
	public PlugBoard getPlugBoard() {
		return instance;
	}
	
	public int plugBoardOut(int in) {
		return mapping[in];
	}
	
	public void addPlug(int letter, int toLetter) {
		if(plugAvailable[letter] && plugAvailable[toLetter]) {
			mapping[letter] = toLetter;
			mapping[toLetter] = letter;
			plugAvailable[letter] = false; 
			plugAvailable[toLetter] = false;
		}
	}
	
	public String getOpenPorts() {
		String output = "";
		for(int i=0 ; i < Constants.CHAR_COUNT; i++) {
			if(plugAvailable[i]) output += Constants.CHAR_SET.charAt(i);
		}
		return output;
	}
	
	public int[] getMapping() {
		return mapping;
	}
}
