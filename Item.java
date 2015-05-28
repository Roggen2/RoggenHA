/**
 * So sieht ein Item aus.
 * 
 * @author Nikita Kister 4569033 Gruppe 7b
 * @author Marvin Seiler 4496931 Gruppe 7b
 */

public class Item implements Comparable<Item> {
    
    /**
    * Name des Items
    */
    private String name;
    
    /**
    * Gewicht des Items
    */
    private double weight;
    
    /**
    * Wert des Items
    */
    private double value;
    
    /**
    * erzeugt zufaelliges Item-Objekt
    */
    public Item() {
        String[] iList = {"Juwel", "Eisen", "Erde", "Holz", "Schwert", "Schild", "Helm"};
        this.name = iList[(int) (Math.random() * iList.length)];
        this.weight = (int) (30 * Math.random() + 1);
        this.value = (int) (50 * Math.random() + 1);
    }
    
    /**
    * erzeugt zufaelliges Item-Objekt mit dem uebergebenen Namen
    * @param name ist der Name
    */
    public Item(String name) {
        this.name = name;
        this.weight = (int) (30 * Math.random() + 1);
        this.value = (int) (50 * Math.random() + 1);
    }
    /**
    * gibt den Namen zurueck
    * @return name
    */
    public String getName(){
        return this.name;
    }
    /**
    * gibt das Gewicht zurueck
    * @return name
    */
    public double getWeight(){
        return this.weight;
    }
    /**
    * gibt den Wert zurueck
    * @return name
    */
    public double getValue(){
        return this.value;
    }
    
    
    
    /**
    * erzeugt ein neues Item-Objekt mit den uebergebenen Parametern
    * @param name ist der Name
    * @param weight ist das Gewicht
    * @param value ist der Wert
    */
    public Item(String name, double weight, double value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
    
    /**
    * Ueberladung der equals-Methode
    * @param o ist das zu vergleichende Object
    * @return ob das uebergebene Object diesem gleicht 
    */
    public boolean equals(Object o) {
        Item a = (Item) o;
        return ((this.name.equals(a.name)) && (this.weight == a.weight) && (this.value == a.value));
    }
    
    /**
    * vergleicht dieses Objekt mit dem uebergebenen
    * @param o ist das zu vergleichende Object
    * @return -1 wenn kleiner, 1 wenn groesser, 0 wenn gleich
    */
    public int compareTo(Item o) {
        
        if (this.name.equals(o.name)) {
            if (this.value == o.value) {
                if (this.weight == o.weight) {
                    return 0;
                } else {
                    return ((o.weight - this.weight) < 0.0) ? -1 : 1;
                }
                
            } else {
                return ((o.value - this.value) < 0.0) ? -1 : 1;
            }
            
            
            
        }
        return Integer.signum(this.name.compareTo(o.name));
    }
    
    /**
    * erzeugt einen String mit den Informationen ueber dieses Item
    * @return den String
    */
    public String toString() {
        return "Name: " + this.name + "  Gewicht: " + this.weight + "  Wert: " + this.value;
        
        
    } 
    
}

