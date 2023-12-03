package model;

public class Reflector extends PairMappedTransformer {
	
	public Reflector() {
		super();
	}
	
	public Reflector(int seed) {
		super(seed, Common.CHAR_COUNT / 2);
	}
}
