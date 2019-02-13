
public class Editore {
	private int id;
	private String nome;
	
	public Editore(){
		
	}
	
	public Editore(String string) {
		this.nome = string;
	}

	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
}
