
public class PromozioneSogliaMinima extends Promozione {

	private double sogliaMinima;
	private double percentuale;
	
	public PromozioneSogliaMinima(String nome, double sm, double p){
		this.nome = nome;
		this.sogliaMinima = sm;
		this.percentuale = p;
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
		if(result >= sogliaMinima){
			result = result - (result * (percentuale / 100));
		}
		
		return  (Math.round(result*100D))/100D;
	}
}
