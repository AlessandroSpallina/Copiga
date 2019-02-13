package it.aps.cdr.dominio;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Un oggetto di tipo Prezzo rappresenta l'importo di una 
 * componente di una colazione o di un tipo di colazione.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class Prezzo {
	
	/** il prezzo vero e proprio (importo) */
	private BigDecimal importo;
	
	/**
	 * Crea un nuovo Prezzo
	 * @param importo il prezzo 
	 */
	public Prezzo(BigDecimal importo) {
		this.importo = importo;
	}

	/**
	 * Crea un nuovo Prezzo 
	 * @param importo il prezzo 
	 */
	public Prezzo(double importo) {
		this(new BigDecimal(importo));
	}

	/**
	 * Restituisce l'importo
	 * @return importo
	 */
	public BigDecimal getImporto(){
		return this.importo;
	}
	
	/**
	 * Definisce l'importo
	 * @param importo
	 */
	public void setImporto(BigDecimal importo){
		this.importo = importo;
	}
	
	/**
	 * Calcola la somma di due prezzi
	 * @param prezzo il prezzo da sommare
	 * @return la somma dei due prezzi 
	 */
	public Prezzo somma(Prezzo prezzo) {
		BigDecimal somma = this.importo.add(prezzo.importo);
		Prezzo prezzoSomma = new Prezzo(somma);
		return prezzoSomma;
	}

	/**
	 * Calcola la differenza di due prezzi
	 * @param prezzo il prezzo da sottrarre
	 * @return la differenza dei due prezzi
	 */
	public Prezzo sottrai(Prezzo prezzo) {
		BigDecimal sottrazione = this.importo.subtract(prezzo.importo);
		Prezzo prezzoDifferenza = new Prezzo(sottrazione);
		return prezzoDifferenza;
	}

	/**
	 * Calcola il multiplo di un prezzo
	 * @param fattore il numero di volte per le quali deve 
	 * essere moltiplicato il prezzo
	 * @return il prodotto
	 */
	public Prezzo moltiplica(double fattore) {
		BigDecimal fattoreBD = new BigDecimal(fattore);
		BigDecimal prodotto = this.importo.multiply(fattoreBD);
		Prezzo prezzoProdotto = new Prezzo(prodotto);
		return prezzoProdotto;
	}

	/**
	 * Restituisce una rappresentazione testuale del prezzo 
	 * @return la rappresentazione testuale del prezzo
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		return df.format(this.importo);
	}
}