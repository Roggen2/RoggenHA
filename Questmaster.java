
public class Questmaster {
	
	public Inventory<Quest> allQuests;
	
	public Inventory<Quest> visibleQuests;
	
	public Inventory<Quest> finishedQuests;
	
	private Player costumer;
	
	public Questmaster(Player costumer) {
		
		MyReader reader = new MyReader("quest.csv");
		
		allQuests = reader.getQuests();
		finishedQuests = new Inventory<>();
		visibleQuests = new Inventory<>();
		
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
	
	private boolean isQuestfinished(Quest quest) {
		if( !quest.isVisible()) {
			return false;
		}
		for(int i= 0; i < costumer.getItemInventory().length(); i++) {
			if( costumer.getItemInventory().getItem(i).getName().equals( quest.getTarget() )) { // es wird geprüft ob das item im inventar ist.
				return costumer.getItemInventory().getQuantity(costumer.getItemInventory().getItem(i)) == quest.getQuantity(); // wenn geung da sind gibt es true.
			}
		}
		return false;
	}
	//ändert den status der quests bei spieler und questmaster
	public void questUpdate() {
		//setzt beendete quests in der liste des masters beendet
		for(int i= 0; i < allQuests.length(); i++) {
			if(isQuestfinished(allQuests.getItem(i))) {
				allQuests.getItem(i).setFinished(true); 
			}
		}
		for(int i= 0; i < costumer.getQuestList().length(); i++) {
			if(isQuestfinished(costumer.getQuestList().getItem(i))) {
				costumer.getQuestList().delete(costumer.getQuestList().getItem(i));//entfernt beendete quests aus der liste des spielers.
			}
		}
	}
	public Inventory<Quest> updateFinishedQuests() {
		for(int i= 0; i < allQuests.length(); i++) {
			if(allQuests.getItem(i).isFinished() && !finishedQuests.isInList(allQuests.getItem(i)) ) {
				finishedQuests.append(allQuests.getItem(i));// hängt neue beendete quests hinten an die entsprechende liste dran.
				
			}
		}
		return this.finishedQuests;
	}
	
	private void setQuestToVisible(Quest quest) {
		for(int i= 0; i < allQuests.length(); i++) {
			if (quest.getPreQuest().equals( allQuests.getItem(i).getName() ) && allQuests.getItem(i).isFinished() ) {//schaut ob vorquest der übergebenen quest erfüllt ist 
				quest.setVisible(true);
			}
		}
	}
	
	public Inventory<Quest> updateVisibleQuests() {
		for(int i= 0; i < allQuests.length(); i++) {
			this.setQuestToVisible(allQuests.getItem(i));
			if(!visibleQuests.isInList(allQuests.getItem(i)) && allQuests.getItem(i).isVisible() ) {
				visibleQuests.append(allQuests.getItem(i));   // hängt neue beendete quests hinten an die entsprechende liste dran.
			}
		}
		return this.visibleQuests;
	}
	/*
	private Inventory<Item> removeQuestItem(Item item, Quest quest) {
		
		int quantity = quest.getQuantity();
		String target = quest.getTarget();
		
		while (costumer.getItemInventory())
	}*/
	


}
