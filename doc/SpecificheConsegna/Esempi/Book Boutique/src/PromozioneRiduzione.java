
public class PromozioneRiduzione extends Promozione{
	
	private double riduzione;
	
	public PromozioneRiduzione(String nome, double riduzione){
		this.nome = nome;
		this.riduzione = riduzione;
	}
	
	@Override
	public double calcolaTotaleScontato(Acquisto acq, double tot) {
		double result = 0;
		if(tot == 0)  {
			result = acq.calcolaTotale() - riduzione;
		}
		else {
			result = tot - riduzione;
		}
				
		if(result < 0){
			result = 0;
		}
		
		return  (Math.round(result*100D))/100D;
	}	
}