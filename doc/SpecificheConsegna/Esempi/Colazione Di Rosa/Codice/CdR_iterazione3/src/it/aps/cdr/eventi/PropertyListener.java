package it.aps.cdr.eventi;

public interface PropertyListener {

	public abstract void onPropertyEvent(Object source, String property,
			Object value);
	
}
