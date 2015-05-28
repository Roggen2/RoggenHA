
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
		//keine beendeten Quests zu beginn.
		finishedQuests = new Inventory<>();
	}
	
	public boolean isQuestfinished(Quest quest) {
		for(int i= 0; i < costumer.getItemInventory().length(); i++) {
			if(costumer.getItemInventory().getItem(i).getName().equals(quest.getTarget())) {
				return costumer.getItemInventory().getQuantity(costumer.getItemInventory().getItem(i)) == quest.getQuantity();
			}
		}
		return false;
	}
	//ändert den status er quests bei spieler und questmaster
	public void questUpdate() {
		//setzt beendete quests in der liste des masters beendet
		for(int i= 0; i < allQuests.length(); i++) {
			if(isQuestfinished(allQuests.getItem(i))) {
				allQuests.getItem(i).setFinished(true);
			}
		}
		for(int i= 0; i < costumer.getQuestList().length(); i++) {
			if(isQuestfinished(costumer.getQuestList().getItem(i))) {
				costumer.getQuestList().getItem(i).setFinished(true);
			}
		}
	}
	public Inventory<Quest> updateFinishedQuests() {
		
	}
	
	public Inventory<Quest> updateVisibleQuests() {
		
	}
	
	
	


}
