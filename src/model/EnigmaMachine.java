package model;

import java.util.ArrayList;
import java.util.Random;

public class EnigmaMachine {
	private ArrayList<Rotor> rotors = new ArrayList<Rotor>();
	private PlugBoard plugBoard;
	private Reflector reflector;
	
	public EnigmaMachine(int seed) {
		this(seed, 0);
	}
	
	public EnigmaMachine(int seed, int rotorCount) {
		Random rand = new Random(seed);
		plugBoard = new PlugBoard(rand.nextInt(),rand.nextInt(6)+3);
		reflector = new Reflector(rand.nextInt());
		if(rotorCount < 1) rotorCount = generateRotorCount(rand);
		for(int i = 0; i < rotorCount; i++) {
			rotors.add(new Rotor(rand.nextInt()));
			rotors.get(i).setPosition(i);
		}
	}
	
	public EnigmaMachine(PlugBoard plugBoard, ArrayList<Rotor> rotors, Reflector reflector ) {
		this.rotors = rotors;
		this.plugBoard = plugBoard;
		this.reflector = reflector;
	}
	
	public String encode(String msg) {
		String output = "";
		for(int i = 0; i< msg.length();i++) {
			if(!Common.inCharSet(msg.charAt(i))) {
				output += msg.charAt(i);
			} else {
				int signal = Common.encodeChar(msg.charAt(i));
				signal = plugBoard.getOutput(signal);
				for(int j = 0; j < rotors.size(); j++) {
					if(j == 0) {
						rotors.get(j).bumpPosition();
					} else if (rotors.get(j-1).checkNotch()) {
						rotors.get(j).bumpPosition();
					}
					signal = rotors.get(j).getOutboundOutput(signal);
				}
				signal = reflector.getOutput(signal);
				for(int j = rotors.size(); j > 0 ; j--) {
					signal = rotors.get(j-1).getInboundOutput(signal);
				}
				signal = plugBoard.getOutput(signal);
				output += Common.decodeInt(signal);
			}
		}
		return output;
	}
	
	public void configureRotors(String setting, MachineConfig component) {
		int steps = Math.min(setting.length(), rotors.size());
		for(int i = 0; i < steps; i++) {
			Integer value = Common.encodeChar(setting.charAt(i));
			if(value != null) {
				if(component == MachineConfig.POSITION) {
					rotors.get(i).setPosition(value);
				} else if (component == MachineConfig.OFFSET) {
					rotors.get(i).setOffset(value);
				}
			}
		}
	}
	
	public void setPlugBoard(PlugBoard plugBoard) {
		this.plugBoard = plugBoard;
	}
	
	public void setReflector(Reflector reflector) {
		this.reflector = reflector;
	}
	
	public void setRotor(int index, Rotor rotor) {
		if(index < rotors.size()) rotors.add(index, rotor);
	}
	
	private int generateRotorCount(Random rand) {
		return rand.nextInt(2) * 2 + 3; //(3 or 5)
	}
	
	
}
