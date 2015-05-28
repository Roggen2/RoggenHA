
public class Quest implements Comparable<Quest> {
	
	private String name;
	
	private String prequest;
	
	private String target;
	
	private int quantity;
	
	private boolean finished;
	
	private boolean visible;
	
	public Quest(String name, String prequest, String target, int quantity) {
		this.name = name;
		this.prequest = prequest;
		this.target = target;
		this.quantity = quantity;
		this.finished = false;
		this.visible = (prequest.equals(""));
	}

	public int compareTo(Quest quest) {
		return 0;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public String toString() {
		return  "Name: " + name + "\n" +
				"Abgeschlossen: " + (finished ? "ja" : "nein") + "\n" +
				"Vorquest: " + prequest + "\n" +
				"Notwendiger Questgegenstand: " + target + "\n" +
				"Notewendige Anzahl an Gegenständen: " + quantity + "\n" ; 
				
	}
	
	

















}
