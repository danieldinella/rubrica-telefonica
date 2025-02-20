package it.turing.test.rubrica.model;

public class Persona {
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private int eta;
	
	public Persona(String n, String cn, String i, String t, int e) {
		this.nome = n;
		this.cognome = cn;
		this.indirizzo = i;
		this.telefono = t;
		this.eta = e;
	}
	
	public void setNome(String n) {
		this.nome = n;
	}
	
	public void setCognome(String cn) {
		this.cognome = cn;
	}
	
	public void setIndirizzo(String i) {
		this.indirizzo = i;
	}
	
	public void setTelefono(String t) {
		this.telefono = t;
	}
	
	public void setEta(int e) {
		this.eta = e;
	}

	public String getNome() {  
	    return this.nome;  
	}  

	public String getCognome() {  
	    return this.cognome;  
	}  

	public String getIndirizzo() {  
	    return this.indirizzo;  
	}  

	public String getTelefono() {  
	    return this.telefono;  
	}  

	public int getEta() {  
	    return this.eta;  
	}  

}
