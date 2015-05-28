
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
	
	public String getTarget() {
		return this.target;
	}
	
	public String getPreQuest() {
		return this.prequest;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public String getName() {
		return this.name;
	}
	public boolean isFinished() {
		return this.finished;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public String toString() {
		if(this.visible) {
			return  "Name: " + name + "\n" +
					"Abgeschlossen: " + (finished ? "ja" : "nein") + "\n" +
					"Vorquest: " + prequest + "\n" +
					"Notwendiger Questgegenstand: " + target + "\n" +
					"Notewendige Anzahl an Gegenständen: " + quantity + "\n" ;
		}
		
		return  "";
				
	}
	
	

















}
