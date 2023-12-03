package model;


public class EnigmaMachine {

	public static void main(String[] args) {
		int seed = 135723457;
		
		Machine mach = new Machine(seed);
		
		String output = mach.encode("A FANCIFUL MESSAGE TO MY SUPERIORS LONG ENOUGH TO TRIGGER MORE THAN ONE BUMP TO THE SECOND ROTOR",false);
		System.out.println(output);
		mach = new Machine(seed);
		output = mach.encode(output, false);
		System.out.println(output);
	
	}
	
	
	
}
