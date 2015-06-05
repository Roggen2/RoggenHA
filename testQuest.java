
public class testQuest {
    public static void main(String[] args){
        MyReader ir = new MyReader("item.csv");
        Inventory<Item> allItems = ir.getItems();
        Player p = new Player();
        p.setGold(200);
        
        for (int i = 0;i < allItems.length() ;i++ ) {
            p.getItemInventory().insert(allItems.getItem(i));
        }
        
        Quest q = new Quest("a", new Quest(), new Item("Wolfsfell", 5.0, 4.0), 1);
        Quest q1 = new Quest("ab", q, new Item("Trollohren", 1.0, 2.0), 1);
        Quest q2 = new Quest("abc", q1, new Item("Trollknopf", 0.5, 1.0), 1);
        Quest q3 = new Quest("abcd", q2, new Item("Schild", 15.0, 40.0), 1);
        Quest q4 = new Quest("abcde", q3, new Item("Edelstein", 100.0, 0.1), 1);
        
        System.out.println(q);
        System.out.println(q1);
        System.out.println(q2);
        System.out.println(q3);
        System.out.println(q4);
        //System.out.println(p.getItemInventory());
       // System.out.println(p.getItemInventory());
    }
}