import java.io.*;
import java.util.Scanner;
public class MyReader{
    
	private File data;
    
    public MyReader(String source) {
        this.data = new File(source);
    }
    
    public Inventory<Item> getItems() {
        Inventory<Item> inv = new Inventory<>();
        
        try {
            
        	Scanner sc = new Scanner(data);
            String line = sc.nextLine();
            
            while (sc.hasNextLine()) { 
                line = sc.nextLine();
               // s = s.replaceAll(" ", "");
                String[] stringArray = line.split(", ");
                String name = stringArray[0];
                double value = Double.parseDouble(stringArray[1]);
                double weight = Double.parseDouble(stringArray[2]);
                Item item = new Item(name, weight, value);
                inv.append(item);
            } // end of while
        } catch(IOException e) {} 
        
        return inv;
    }
    
    public Inventory<Quest> getQuests() {
    	Inventory<Quest> inv = new Inventory<>();
        
        try {
            
        	Scanner sc = new Scanner(data);
            String line = sc.nextLine();
            
            while (sc.hasNextLine()) { 
                line = sc.nextLine();
               // s = s.replaceAll(" ", "");
                String[] stringArray = line.split(", ");
                String name = stringArray[0];
                String prequest = stringArray[1];
                String target = stringArray[2];
                int quantity = Integer.parseInt(stringArray[3]);
                Quest quest = new Quest(name, prequest, target, quantity );
                inv.append(quest);
            } // end of while
        } catch(IOException e) {} 
        
        return inv;
    }
    
}                                        