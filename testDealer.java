
public class testDealer {
    
    public static void main(String[] args){
        ItemReader ir = new ItemReader("item.csv");
        Inventory<Item> allItems = ir.getItems();
        Player p = new Player();
        for (int i = 0;i < allItems.length() ;i++ ) {
            p.getInventory().insert(allItems.getItem(i));
        }
        dealer d = new dealer(p);
        d.buy();
        
    }
}
