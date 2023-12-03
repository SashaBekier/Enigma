package model;


public class Main {

	public static void main(String[] args) {
		int seed = 1351253456;
		
		EnigmaMachine mach = new EnigmaMachine(seed,3);
		mach.configureRotors("obscure",true);
		
		String output = mach.encode("THIS IS SOME TEXT it will IGNORE CHARACTERS NOT IN ITS ALPHABET");
		System.out.println(output);
		
		//reset machine to starting state in order to decode message
		mach = new EnigmaMachine(seed,3);
		mach.configureRotors("obscure",true);
				
		output = mach.encode(output);
		System.out.println(output);
	}
}
