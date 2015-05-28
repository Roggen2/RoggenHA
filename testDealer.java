
public class testDealer {
    
    public static void main(String[] args){
        MyReader ir = new MyReader("item.csv");
        MyReader qr = new MyReader("quest.csv");
        Inventory<Item> allItems = ir.getItems();
        Inventory<Quest> quests = qr.getQuests();
        System.out.println(quests);
        Player p = new Player();
        for (int i = 0;i < allItems.length() ;i++ ) {
            p.getInventory().insert(allItems.getItem(i));
        }
        Dealer d = new Dealer(p);
        d.buy();
        
    }
}
