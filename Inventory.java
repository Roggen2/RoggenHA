  /**
 * Dies ist das Inventar modelliert mit einer einfachverketteten Liste
 * 
 * @author Nikita Kister 4569033 Gruppe 7b
 * @author Marvin Seiler 4496931 Gruppe 7b
 */
    
public class Inventory<T extends Comparable<T> > implements List<T> {
    /**
     *Item des Knotens.
     */
    private T item;
    /**
     * Nächster Knoten.
     */
    private Inventory<T> next;
    /**
     * Konstruiert das Inventar
     */
    public Inventory() {
        item = null;
        next = null;
    }

    /**
     * Liefert das Item des Knotens.
     * @return Item
     */
    public T getItem() {
        return item;
    }
    
    /**
     * Gibt das Item an der i-ten Stelle wieder.
     * @param i Index des Items.
     * @return Item.
     */
    public T getItem(int i) {
        if (i < 0 || i >= this.length()) {
            throw new IndexOutOfBoundsException();
        } else {
            if (i != 0)  {
                i = i - 1;
                return next.getItem(i);
            } else {
                return next.item;
            }
        }
    }
    //NEW
    public int getQuantity(T x) {
    	int count = 0;
    	for(int i = 0; i < this.length(); i++) {
    		if(this.getItem(i).equals(x)) {
    			count++; 
    		}
    	}
    	return count;
    }
    
    /**
     * Liefert nächstes Inventar.
     * @return Inventar
     */
    public Inventory<T> getNext() {
        return next;
    }
    /**
     * Ist die Liste leer?
     * @return Antwort
     */
    public boolean isEmpty() {
        return next == null;
    }
    
    /**
     * Liefert erstes item der Liste.
     * @return Item
     */
    public T firstItem() {
        if (isEmpty()) {
            throw new IllegalStateException();
        	//return null;
        } else {
            return next.item;
        }
            
    }
    
    /**
     * Liefert Laenge der Liste.
     * @return Laenge
     */
    public int length() {
        if (this.isEmpty()) {
            return 0;
        } else {
            return 1 + next.length();
        }
    }

    /**
     * Fuegt das Item an den Anfang der Liste an
     * @param x Item
     * @return Veraenderte Liste.
     */
    private Inventory<T> insert1(T x) {
        Inventory<T> l = new Inventory<T>();
        l.item = x;
        l.next = next;
        next = l;
        return this;
    }   

   /**
    * Haengt das Item hinten an.
    * @param x Item
    * @return Veraenderte Liste.
    */
    public Inventory<T> append(T x) {
        if (isEmpty()) {
            return insert1(x);
        } else {
            return next.append(x);
        }
    }
    
    /**
     * Fügt das Item sortiert in die Liste ein.
     * @param x Item
     * @return Veraenderte Liste
     */
    public Inventory<T> insert(T x) {
        if (this.isEmpty()) {
            return this.insert1(x);
        }
      
        switch(this.firstItem().compareTo(x)) {
            case 1:  
                return this.insert1(x);

            case 0:  

                return this.insert1(x);
            default:  
                return next.insert(x);
        }
    }
    /**
     * Findet Inventarplatz mit diesem Item
     * @param x Item
     * @return Inventarplatz
     */
    private Inventory<T> find(T x) {
        if (isEmpty()) {
            return null;
        } else {
            if (firstItem().equals(x)) {
                return this;
            } else {
                return next.find(x);
            }
        }
    }

    /**
     * Entfernt das erste Item dieser Art aus der Liste
     * @param x Item
     * @return veraendertes Inventar
     */
    public Inventory<T> delete(T x) {
        Inventory<T> l = find(x);
        if (l != null) {
            l.next = l.next.next;
        }
        return this;
    }

    /**
     * Entfernt das Erste Item der Liste.
     * 
     * @return veraenderte Liste
     */
    public Inventory<T> delete() {  
        if (!isEmpty()) {
            next = next.next;
        }
        return this;
    }
    
    /**
     * Ist das Item drin?
     * @param x Item
     * @return Antwort
     */
    public boolean isInList(T x) {
        return (find(x) != null);
    }
    
    /**
     * Gibt das Inventar als String wieder.
     * @return Zeichenkette
     */
    public String toString() {
        String s = "";
        if (!this.isEmpty()) {
            for (int i = 0; i < this.length(); i++) {
                s = s + "-" + (i+1) + "." + getQuantity(getItem(i)) + " mal " + getItem(i).toString() + "\n";
                i += getQuantity(getItem(i)) - 1;
            }
        } else {
            s = "leeres Inventar";
        }
        return s;
        //return isEmpty() ? "leeres Inventar" : "-" + next.item.toString()+next.next.toString();
    }
    
    
}
