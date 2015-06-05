
public class Quest implements Comparable<Quest> {
	
	private String name;
	
	private Quest prequest;
	
	private Item target;
	
	private int quantity;
	
	private boolean finished;
	
	private boolean visible;
	
	public Quest(String name, Quest prequest, Item target, int quantity) {
		this.name = name;
		this.prequest = prequest;
		this.target = target;
		this.quantity = quantity;
		this.finished = false;
		this.visible = (prequest.getName().equals("Emptyquest"));
	}
	
	public Quest() {
		this.name = "Emptyquest";
		this.prequest = null;
		this.target = null;
		this.quantity = 0;
	}
	
	
	public Item getTarget() {
		return this.target;
	}
	
	public Quest getPreQuest() {
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
	
	public int compareTo(Quest quest) {
		if(this.name.equals(quest.getName())) {
			return 0;
		} else {
			return 1;
		}
		
	}
	public String toString() {
		if(!this.getName().equals("Emptyquest")) {
			return  "Name: " + name + "\n" +
					"Abgeschlossen: " + (finished ? "ja" : "nein") + "\n" +
					"Vorquest: " + prequest.getName() + "\n" +
					"Notwendiger Questgegenstand: " + target.getName() + "\n" +
					"Notewendige Anzahl an Gegenständen: " + quantity + "\n" ;
		
		}
		else {
			return  "Dies ist die erste Quest dieser Reihe!";
		}		
	}
}	
	


















