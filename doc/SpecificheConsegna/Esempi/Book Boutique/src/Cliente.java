public class Cliente {

	private int id;
	private String nome;
	private String cognome;
	private String telefono;
	private String email;

	
	/*
	 *********************
	 * COSTRUTTORE
	 *********************
	 */	
	
	public Cliente()	{ }
	
	public Cliente(String nome, String cognome, String telefono, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
	}

	
	/*
	 *********************
	 * METODI VARI
	 *********************
	 */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	
	/**
	 * override del metodo toString
	 */
	public String toString(){
		return "Nome: " + this.nome + " Cognome: " + this.cognome + " Telefono: " + this.telefono + " Email: " + this.email;
	}

}
