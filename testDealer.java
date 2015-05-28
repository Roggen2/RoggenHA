
public class testDealer {
    
    public static void main(String[] args){
        MyReader ir = new MyReader("item.csv");
        MyReader qr = new MyReader("quest.csv");//NEW
        Inventory<Item> allItems = ir.getItems();
        Inventory<Quest> quests = qr.getQuests();//NEW
        System.out.println(quests);//NEW
        Player p = new Player();
        for (int i = 0;i < allItems.length() ;i++ ) {
            p.getItemInventory().insert(allItems.getItem(i));
        }
        Dealer d = new Dealer(p);
        d.buy();
        
    }
}
