package model;

public class Reflector extends PairMappedTransformer {
	
	public Reflector() {
		super();
	}
	
	public Reflector(int seed) {
		super(seed, Constants.CHAR_COUNT / 2);
	}
}
