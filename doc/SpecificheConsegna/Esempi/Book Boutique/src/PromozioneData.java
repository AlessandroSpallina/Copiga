import java.util.Date;


public class PromozioneData extends Promozione {
	private double percentuale;
	private long dataInizio;
	private long dataFine;
	
	public PromozioneData(String nome, double percentuale, long ti, long tf){
		this.nome = nome;
		this.percentuale = percentuale;
		this.dataInizio = ti * 1000;
		this.dataFine = tf * 1000;
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
		long timestamp = ((Date) new Date()).getTime();
		
		if((timestamp > dataInizio) && (timestamp < dataFine)){
			result = result - (result * (percentuale / 100));
		}
		
		return  (Math.round(result*100D))/100D;
	}
}
