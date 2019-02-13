
public abstract class Promozione {
	
	String nome;

	public abstract double calcolaTotaleScontato(Acquisto acq, double tot);

	public String getNome() {
		return nome;
	}
	
	// metodi per gestire i componenti
	
	public void add(Promozione p)	{
	}
	
	public void remove(Promozione p)	{
	}
	
	public Promozione getPromozione(String nome)	{
		return null;
	}
	
}
