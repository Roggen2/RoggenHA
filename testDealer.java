
public class testDealer {
    
    public static void main(String[] args){
       Player p = new Player();
       for(int i = 0; i<5; i++) {
    	   p.getItemInventory().insert(new Item("Wolfsfell",5.0, 4.0 ));
       }
        //MyReader qr = new MyReader("quest.csv");//NEW
        
       // Inventory<Quest> quests = qr.getQuests();//NEW
        Questmaster master = new Questmaster(p);
        
    	System.out.println(p.getItemInventory());
    	//System.out.println(p.getItemInventory().getQuantity()
    	master.questUpdate();
    	//master.updateFinishedQuests();
    	master.updateVisibleQuests();
        //System.out.println(master.allQuests);
        //System.out.println(master.finishedQuests);
    	System.out.println(master.getW());//NEW
    	
        
        
        
        
        
        
        
         MyReader ir = new MyReader("item.csv");
        Inventory<Item> allItems = ir.getItems();
        /*for (int i = 0;i < allItems.length() ;i++ ) {
            p.getItemInventory().insert(allItems.getItem(i));
        }
        Dealer d = new Dealer(p);
        d.buy();*/
        
    }
}
