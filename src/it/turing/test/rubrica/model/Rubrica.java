package it.turing.test.rubrica.model;

import java.util.ArrayList;

public class Rubrica {
	private static ArrayList<Persona> persone = new ArrayList<>();
	
	public static void addPersona(Persona p) {
		persone.add(p);
	}
	
	public static void removePersona(int index) {
		persone.remove(index);
	}
	
	public static void updatePersona(int index, Persona p) {
		persone.set(index, p);
	}
	
	public static Persona getPersona(int index) {
		return persone.get(index);
	}
	
	public static ArrayList<Persona> getPersone(){
		return persone;
	}
}
