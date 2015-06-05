import java.io.*;
import java.util.Scanner;
public class MyReader{
    private File data;
    public MyReader(String source){
        this.data = new File(source);
    }
    public Inventory<Item> getItems(){
        Inventory<Item> inv = new Inventory<>();
        
        try {
            
        	Scanner sc = new Scanner(data);
            String[] stringArray;
            String line = sc.nextLine();
            
            while (sc.hasNextLine()) { 
                line = sc.nextLine();
                stringArray = line.split(", ");
                String name = stringArray[0];
                double value = Double.parseDouble(stringArray[1]);
                double weight = Double.parseDouble(stringArray[2]);
                Item item = new Item(name, weight, value);
                inv.append(item);
            }
        } catch(IOException e) {} 
        
        return inv;
    }
    private Item itemFromString(String toItem) {
        MyReader ir = new MyReader("item.csv");
        Inventory<Item> allItems = ir.getItems();
        Item it = null;
        
        for (int i = 0; i < allItems.length(); i++) {
            if (allItems.getItem(i).getName().equals(toItem)) {
                it = allItems.getItem(i);
                break;
            }
        }
        
        if (it == null) {
            throw new IllegalArgumentException("Item not found!");
        }
        
        return it;
    }
    
    public Inventory<Quest> getQuests() {
        Inventory<String> StringInv = new Inventory<>();
        Inventory<Quest> questList = new Inventory<>();
        
        try {
            Scanner sc = new Scanner(data);
            String line = sc.nextLine();
            while (sc.hasNextLine()) { 
                line = sc.nextLine();
                StringInv.append(line);
            }
            for (int i = 0; i < StringInv.length(); i++) {
                
            	String s = StringInv.getItem(i);
                String[] abc = s.split(", ");
                
                if (abc[1].equals("")) {
                    questList.insert(new Quest(abc[0], new Quest(), itemFromString(abc[2]), Integer.parseInt(abc[3])));
                    StringInv.delete(s);
                }
            }
            
            while (!StringInv.isEmpty()) { 
                for (int i = 0; i < StringInv.length(); i++) {
                    String s = StringInv.getItem(i);
                    String[] s1 = s.split(", ");
                    for (int j = 0; j < questList.length(); j++) {
                        if (s1[1].equals(questList.getItem(j).getName())) {
                            questList.append(new Quest(s1[0], questList.getItem(j), itemFromString(s1[2]), Integer.parseInt(s1[3])));
                            StringInv.delete(s);
                            break;
                        }
                    }
                }
            }
        } catch(IOException e) {} 
        return questList;
    }
}                                        