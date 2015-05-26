import java.io.*;
import java.util.Scanner;
public class ItemReader{
    private File data;
    public ItemReader(String source){
        this.data = new File(source);
    }
    public Inventory<Item> getItems(){
        Inventory<Item> inv = new Inventory<>();
        try {
            Scanner sc = new Scanner(data);
            String line = sc.nextLine();
            while (sc.hasNextLine()) { 
                String s = sc.nextLine();
                s = s.replaceAll(" ", "");
                String[] stringArray = s.split(",");
                String name = stringArray[0];
                double value = Double.parseDouble(stringArray[1]);
                double weight = Double.parseDouble(stringArray[2]);
                Item item = new Item(name, weight, value);
                inv.append(item);
            } // end of while
        } catch(IOException e) {} 
        
        return inv;
    }
}                                        