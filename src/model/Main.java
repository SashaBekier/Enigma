package model;


public class Main {

	public static void main(String[] args) {
		int seed = 1351253456;
		
		EnigmaMachine mach = new EnigmaMachine(seed,3);
		mach.configureRotors("obscure",MachineConfig.POSITION);
		String output = mach.encode("WHY SOMETIMES IVE BELIEVED AS MANY AS SIX IMPOSSIBLE THINGS BEFORE BREAKFAST");
		System.out.println(output);
		
		//reset machine to starting state in order to decode message
		mach = new EnigmaMachine(seed,3);
		mach.configureRotors("obscure",MachineConfig.POSITION);
				
		output = mach.encode(output);
		System.out.println(output);
	}
}
