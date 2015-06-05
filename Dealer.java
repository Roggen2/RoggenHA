import java.util.Scanner;
public class Dealer {
    /**
    * Inventar des Haendlers
    */
    private Inventory<Item> inventory = new Inventory<>();
    /**
    *x-Koordinate
    */
    private int x;
    /**
    * y-Koordinate
    */
    private int y;
    /**
    *alle Gegenestaende
    */
    private Scanner sc = new Scanner(System.in);
    
    private Inventory<Item> allItems;
    
    private Player customer;
    
    public Dealer(Player p) {
        this.customer = p;
        //this.customer.setGold(200);
        MyReader ir = new MyReader("item.csv");
        allItems = ir.getItems();
        //System.out.println(allItems);
        
        
        for (int i = 0; i < allItems.length(); i++) {
            if (Tool.rand(0, 3) != 3) {
                int anz = Tool.rand(2, 6);
                for (int j = 0; j < anz; j++) {
                    this.inventory.insert(allItems.getItem(i));
                }
            }    
        }
    } 
    /**
    * liefert die x-koordinate
    * @return x
    */
    public int getX() {
        return this.x;
    }
    
    /**
    * liefert die y-koordinate
    * @return y
    */
    public int getY() {
        return this.y;
    }
    
    /**
    * setzt die Position
    * @param x x
    * @param y y
    */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
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
            int quantity = inventar.getQuantity(currentItem);
            System.out.println((i + 1) + ". " + itemName + leerzeichen + price + " Gold " + quantity + " Stück");
            i += quantity -1;
        }
    }
    
    public void sell(){
        int laenge; 
        int eingabe;
        boolean continueDealing = true;
        a: while (continueDealing) {
            laenge = this.inventory.length() + 1;
            showTrades(this.inventory, false);
            System.out.println("\nWas moechten Sie kaufen?\n");
            System.out.println("Sie haben " + customer.getGold() + " Gold.\n");
            System.out.println("Geben Sie Zahl des Artikels ein oder " + 0 + " wenn Sie nichts mehr kaufen moechten:");
            String input = sc.nextLine();
            System.out.println("");
            input = input.replaceAll(" ", "");
            input = input.toLowerCase(); //NEW            
            try {
                if (input.equals("i")) { //NEW
                    System.out.println(customer.getItemInventory() + "\n");
                    continue a;
                }
                eingabe = Integer.parseInt(input);
                if (eingabe < 0 | eingabe >= laenge) {
                    throw new NumberFormatException();
                }
                if (eingabe == 0) {
                    continueDealing = false;
                    continue a;
                }
                Item i0 = this.inventory.getItem(eingabe - 1);
                int price = (int) i0.getValue() + 5;
                if (price > customer.getGold()) {
                    System.out.println("Sie haben leider nicht genug Gold fuer diesen Artikel...");
                    continue a;
                }
                customer.getItemInventory().insert(i0);
                this.inventory.delete(i0);
                customer.setGold(customer.getGold() - price);
            } catch(NumberFormatException e) {
                System.out.println("Ungueltige Eingabe!");
            } finally {
                continue a;
            }
        }   
    }
    public void buy(){
        int laenge;
        int eingabe;
        boolean continueDealing = true;
        b: while (continueDealing) {
            laenge = customer.getItemInventory().length() + 1; 
            showTrades(customer.getItemInventory(), true);
            System.out.println("\nSie haben gerade " + customer.getGold() + " Gold.\n");
            System.out.println("\nWas moechten Sie verkaufen?\n");
            System.out.println("Geben Sie Zahl des Artikels ein oder " + 0 + " wenn Sie nichts mehr verkaufen moechten:");
            String input = sc.nextLine();
            input = input.replaceAll(" ", "");
            input = input.toLowerCase();//NEW
            try {
                if (input.equals("i")) {//NEW
                    System.out.println(customer.getItemInventory() + "\n");
                    continue b;
                }
                eingabe = Integer.parseInt(input);
                if (eingabe < 0 | eingabe >= laenge ) {
                    throw new NumberFormatException();
                }
                if (eingabe == 0) {
                    continueDealing = false;
                    continue b;
                }
                Item i0 = customer.getItemInventory().getItem(eingabe - 1);
                int price = (int) i0.getValue() + 1;
                customer.setGold(customer.getGold() + price);
                customer.getItemInventory().delete(i0);
                this.inventory.insert(i0);
            } catch(NumberFormatException e) {
                System.out.println("Ungueltige Eingabe!");
            } finally {
                continue b;
            }
        }   
    }
    
    public void trade() {
        String entry;
        boolean a = true;
        while (a) {
            System.out.println("\nAktion:\n1 -> kaufen\n2 -> verkaufen\n3 -> Shop verlassen");
            entry = sc.nextLine();
            System.out.println("");
            entry = entry.replaceAll(" ","");
            entry = entry.toLowerCase();
            switch (entry) {
                case "1":
                sell();
                break;
                case "2":
                buy();
                break;
                case "3":
                a = false;
                break;
                case "i":
                System.out.println(customer.getItemInventory());
                System.out.println("\nGold: " + customer.getGold());
                break;
                default : System.out.println("Ungueltige Eingabe!");
            }
        }
        
        
    }
    
    
    
    
}    
