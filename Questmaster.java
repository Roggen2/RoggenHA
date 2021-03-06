
public class Questmaster {
	
	private Inventory<Quest> allQuests;
	
	private Inventory<Quest> visibleQuests;
	
	private Inventory<Quest> finishedQuests;
	
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
		for(Item item : costumer.getItemInventory()) {
			if( item.equals( quest.getTarget() )) { // es wird geprüft ob das item im inventar ist.
				return costumer.getItemInventory().getQuantity(item) >= quest.getQuantity(); // wenn geung da sind gibt es true.
			}
		}
		return false;
	}
	private void setQuestToVisible(Quest quest) {
		/*for(int i= 0; i < allQuests.length(); i++) {
			if (quest.getPreQuest().equals( allQuests.getItem(i) ) && allQuests.getItem(i).isFinished() ) {//schaut ob vorquest der übergebenen quest erfüllt ist 
				quest.setVisible(true);
			}
		}*/
		if(quest.getPreQuest().isFinished()) {
			 quest.setVisible(true) ;
		}
		
	}

	private Inventory<Item> removeQuestItem(Quest quest) {
		
		int quantity = quest.getQuantity();
		Item target = quest.getTarget();
		
		
		while (quantity != 0) {
			costumer.getItemInventory().delete(target);
			quantity--;
		}
		return costumer.getItemInventory();
	}

	//ändert den status der quests bei spieler und questmaster
	public Inventory<Quest> questUpdate() {
		//setzt beendete quests in der liste des masters beendet
		for(Quest quest : allQuests) {
			if(isQuestfinished(quest)) {
				quest.setFinished(true);
				removeQuestItem(quest);
			}
		}
		for(Quest quest : costumer.getQuestList()) {
			if(isQuestfinished(quest)) {
				costumer.getQuestList().delete(quest);//entfernt beendete quests aus der liste des spielers.
			}
		}
		return costumer.getQuestList();
	}
	public Inventory<Quest> updateFinishedQuests() {
		for(Quest quest : allQuests) {
			if(quest.isFinished() && !finishedQuests.isInList(quest) ) {
				finishedQuests.append(quest);// hängt neue beendete quests hinten an die entsprechende liste dran.
				
			}
		}
		return this.finishedQuests;
	}
	
	public Inventory<Quest> updateVisibleQuests() {
		for(Quest quest : allQuests) {
			this.setQuestToVisible(quest);
			if(!visibleQuests.isInList(quest) && quest.isVisible() ) {
				visibleQuests.append(quest);   // hängt neue beendete quests hinten an die entsprechende liste dran.
			}
		}
		return this.visibleQuests;
	}
	
	public void givePlayerQuests() {
		for(Quest quest : visibleQuests) {
			if(!costumer.getQuestList().isInList(quest) && !quest.isFinished()) {
				costumer.getQuestList().append(quest);
			}
		}
	}
	public Inventory<Quest> getQ() {
		return this.allQuests;
	}
	
	public Inventory<Quest> getW() {
		return this.visibleQuests;
	}
	public Inventory<Quest> getE() {
		return this.finishedQuests;
	}
}
