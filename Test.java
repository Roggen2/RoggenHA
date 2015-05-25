
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{
   public static void main(String[] args)  {
      Inventory<Item> i = new Inventory<Item>();
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
}}
