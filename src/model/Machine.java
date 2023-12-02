package model;

import java.util.ArrayList;
import java.util.Random;

public class Machine {
	private ArrayList<Rotor> rotors = new ArrayList<Rotor>();
	private PlugBoard plugBoard;
	private Reflector reflector;
	
	public Machine(int seed) {
		Random rand = new Random(seed);
		plugBoard = new PlugBoard(rand.nextInt(),rand.nextInt(6)+3);
		reflector = new Reflector(rand.nextInt());
		int rotorCount = rand.nextInt(4) + 3;
		for(int i = 0; i < rotorCount; i++) {
			rotors.add(new Rotor(rand.nextInt()));
		}
	}
	
	public String encode(String msg) {
		return encode(msg, false);
	}
	
	public String encode(String msg, boolean verbose) {
		String output = "";
		for(int i = 0; i< msg.length();i++) {
			int signal = msg.charAt(i) - Constants.CHAR_SET.charAt(0);
			if(verbose)System.out.println("New Signal = " + signal);
			signal = plugBoard.getOutput(signal);
			if(verbose)System.out.println("After Plug Board pass 1 Signal = " + signal);
			for(int j = 0; j < rotors.size(); j++) {
				if(j == 0) {
					rotors.get(j).bumpPosition();
				} else if (rotors.get(j-1).checkNotch()) {
					rotors.get(j).bumpPosition();
				}
				signal = rotors.get(j).getOutboundOutput(signal);
				if(verbose)System.out.println("After rotor " + j + "outbound Signal = " + signal);
			}
			signal = reflector.getOutput(signal);
			if(verbose)System.out.println("After Reflector Signal = " + signal);
			for(int j = 0; j < rotors.size(); j++) {
				signal = rotors.get(j).getInboundOutput(signal);
				if(verbose)System.out.println("After rotor " + j + "inbound Signal = " + signal);
			}
			signal = plugBoard.getOutput(signal);
			if(verbose)System.out.println("After plugboard pass 2 Signal = " + signal);
			output += Constants.CHAR_SET.charAt(signal);
		}
		return output;
	}

	
}
