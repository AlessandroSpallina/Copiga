import java.util.LinkedList;

public class PromozioneComposite extends Promozione	{

	private LinkedList<Promozione> listaPromozioni;
	
	public PromozioneComposite(String nome)	{
		this.nome = nome;
		listaPromozioni = new LinkedList<Promozione>();
	}
	
	
	@Override
	public double calcolaTotaleScontato(Acquisto acq, double tot) {
		double result = 0;
		if(tot == 0)  {
			result = acq.calcolaTotale();
		}
		else {
			result = tot;
		}
		
		for(Promozione p: listaPromozioni)	{
			result = p.calcolaTotaleScontato(acq,result);
		}
		
		return result;
	}
	
	// metodi per gestire i componenti
	
	public void add(Promozione p)	{
		listaPromozioni.add(p);
	}
	
	public void remove(Promozione p)	{
		listaPromozioni.remove(p);
	}
	
	public Promozione getPromozione(String nome)	{
		Promozione promo = null;
		
		for(Promozione p: listaPromozioni)	{
			if(p.getNome() == nome)	{
				promo = p;
			}
		}
		return promo;
	}	
}
