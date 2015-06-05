/**
 * Bauplan fuer ein dummes Monster.
 * 
 * @author Nikita Kister 4569033 Gruppe 7b
 * @author Marvin Seiler 4496931 Gruppe 7b
 */
    
public class Monster extends Character {

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * The Name.
     */
    private String name;

    /**
     * Instantiates a new Monster.
     */
    public Monster() {
        this("Gegner", 40, 8, 0.9);
    }

    /**
     * Instantiates a new Monster.
     *
     * @param hp        the hp
     * @param atk       the atk
     * @param hitChance the hit chance
     */
    public Monster(int hp, int atk, double hitChance) {
        this("Gegner", hp, atk, hitChance);
    }

    /**
     * Instantiates a new Monster.
     *
     * @param name      the name
     * @param hp        the hp
     * @param atk       the atk
     * @param hitChance the hit chance
     */
    public Monster(String name, int hp, int atk, double hitChance) {
        super(hp, atk, hitChance);
        MyReader ir = new MyReader("item.csv");
        Inventory<Item> inv = ir.getItems();
        this.name = name;
        for (int i = 0; i < inv.length(); i++) {
            if (Tool.rand(0, inv.length()) <= 2) {
                this.inventory.insert(inv.getItem(i));
            }
        }
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return String.format("%s -- HP %d -- ATK %d%n", getName(), getHp(), getAtk());
    }

}
