package model;

public class PlugBoard extends PairMappedTransformer{
	
	public PlugBoard() {
		super();
	}
	
	public PlugBoard(int seed) {
		super(seed,0);
	}
	
	public PlugBoard(int seed, int plugs) {
		super(seed, plugs);
	}
	
}
