package model;

import java.util.Random;

public class EnigmaMachine {

	public static void main(String[] args) {
		Machine mach = new Machine(434345);
		String output = mach.encode("ASHORTTESTMESSAGE");
		System.out.println(output);
		mach = new Machine(434345);
		output = mach.encode(output);
		System.out.println(output);
		
	}

}
