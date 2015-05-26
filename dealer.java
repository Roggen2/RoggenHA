import java.util.Scanner;

public class dealer {
    /**
    * Inventar des Händlers
    */
    private Inventory<Item> inventory = new Inventory<>();
    /**
    *alle Gegenestaende
    */
    
    private Inventory<Item> allItems;
    private Player customer;
    public dealer(Player p){
        this.customer = p;
        this.customer.setGold(200);
        ItemReader ir = new ItemReader("item.csv");
        allItems = ir.getItems();
        //System.out.println(allItems);
        while (this.inventory.isEmpty()) { 
            
            
            for (int i = 0; i < allItems.length(); i++) {
                if (((int) (Math.random() * 4)) != 3) {
                    this.inventory.insert(allItems.getItem(i));
                }
            } 
            
        }
    }
    public void showTrades(Inventory<Item> inventar, boolean buy){
        
        String leerzeichen = "";
        int laenge = inventar.length();
        int maxlength = 0;
        for (int i = 0; i < laenge ; i++) {
            maxlength = Math.max(inventar.getItem(i).getName().length(), maxlength);
        }
        for (int i = 0; i < laenge; i++ ) {
            Item currentItem = inventar.getItem(i);
            String itemName = currentItem.getName();
            leerzeichen = "";
            for (int j = 0; j < (maxlength + 4 - itemName.length()); j++) {
                leerzeichen += " ";
            }
            int price = ((int) currentItem.getValue()) + ((buy) ? 1 : 5);
            System.out.println((i + 1) + ". " + itemName + leerzeichen + price + " Gold");
        }
    }
    
    public void sell(){
        int laenge = this.inventory.length() + 1; 
        int eingabe;
        Scanner sc = new Scanner(System.in);
        boolean continueDealing = true;
        a: while (continueDealing) {
            
            showTrades(this.inventory, false);
            System.out.println("\nWas moechten Sie kaufen?\n");
            System.out.println("Sie haben " + customer.getGold() + " Gold.\n");
            System.out.println("Geben Sie Zahl des Artikels ein oder " + laenge + " wenn Sie nichts mehr kaufen moechten:");
            String input = sc.nextLine();
            input = input.replaceAll(" ", "");
            try {
                eingabe = Integer.parseInt(input);
                if (eingabe < 1 | eingabe > laenge ) {
                    throw new NumberFormatException();
                }
                if (eingabe == laenge) {
                    continueDealing = false;
                    continue a;
                }
                Item i0 = this.inventory.getItem(eingabe - 1);
                int price = (int) i0.getValue() + 5;
                if (price > customer.getGold()) {
                    System.out.println("Sie haben leider nicht genug Gold fuer diesen Artikel...");
                    continue a;
                }
                customer.getInventory().insert(i0);
                customer.setGold(customer.getGold() - price);
            } catch(NumberFormatException e) {
                System.out.println("Ungueltige Eingabe!");
            } finally {
                continue a;
            }
        }   
    }
    public void buy(){
        int laenge = customer.getInventory().length() + 1;
        int eingabe;
        Scanner sc = new Scanner(System.in);
        boolean continueDealing = true;
        b: while (continueDealing) {
            laenge = customer.getInventory().length() + 1; 
            showTrades(customer.getInventory(), true);
            System.out.println("\nSie haben gerade " + customer.getGold() + " Gold.\n");
            System.out.println("\nWas moechten Sie verkaufen?\n");
            System.out.println("Geben Sie Zahl des Artikels ein oder " + laenge + " wenn Sie nichts mehr verkaufen moechten:");
            String input = sc.nextLine();
            input = input.replaceAll(" ", "");
            try {
                eingabe = Integer.parseInt(input);
                if (eingabe < 1 | eingabe > laenge ) {
                    throw new NumberFormatException();
                }
                if (eingabe == laenge) {
                    continueDealing = false;
                    continue b;
                }
                Item i0 = customer.getInventory().getItem(eingabe - 1);
                int price = (int) i0.getValue() + 1;
                customer.setGold(customer.getGold() + price);
                customer.getInventory().delete(i0);
                if (!this.inventory.isInList(i0)) {
                    this.inventory.insert(i0);
                }
            } catch(NumberFormatException e) {
                System.out.println("Ungueltige Eingabe!");
            } finally {
                continue b;
            }
        }   
    } 
}    