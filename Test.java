
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Test {
   public static void main(String[] args) throws IOException  {
      /*Inventory<Item> i = new Inventory<Item>();
      i.insert(new Item());
      i.insert(new Item());
      i.insert(new Item());
      i.insert(new Item());
      i.insert(new Item());
      i.insert(new Item());
      i.insert(new Item());
      i.insert(new Item("gold"));
      i.insert(new Item("aaaa"));
      i.insert(new Item("ababjb"));
      i.insert(new Item("zzz"));
      System.out.println(i);
      */
      BufferedReader b = new BufferedReader(new FileReader("item.csv"));
      System.out.println(b.readLine());
      System.out.println(b.readLine());
      System.out.println(b.readLine());
      System.out.println(b.readLine());
      System.out.println(b.readLine());
	   //BufferedReader br = Files.newBufferedReader(Paths.get("item.csv"));
 
   }
}
