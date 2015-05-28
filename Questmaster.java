
public class Questmaster {
	
	private Inventory<Quest> allQuests;
	
	private Inventory<Quest> visibleQuests;
	
	private Inventory<Quest> finishedQuests;
	
	private Player costumer;
	
	public Questmaster(Player costumer) {
		
		MyReader reader = new MyReader("quest.csv");
		
		allQuests = reader.getQuests();
		
		this.costumer = costumer;
		//legt alle quests die sichtbar sind ind die entsprechende liste.
		for (int i = 0; i < allQuests.length(); i++) {
        	if (allQuests.getItem(i).isVisible()) {
        		visibleQuests.append(allQuests.getItem(i));
            }
        } 
		
		finishedQuests = new Inventory<>();
		
		
		
		
		
	}
	
	
	


}
