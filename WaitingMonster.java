/**
 * Dummes langsames Monster.
 * 
 * @author Nikita Kister 4569033 Gruppe 7b
 * @author Marvin Seiler 4496931 Gruppe 7b
 */
    
public class WaitingMonster extends Monster {

    /**
     * The Wait chance.
     */
    private double waitChance = 0.5;
    /**
     * The Waited.
     */
    private boolean waited = false;

    /**
     * Instantiates a new Waiting monster.
     */
    public WaitingMonster() {
        this(80,8,0.9);
    }

    /**
     * Instantiates a new Waiting monster.
     *
     * @param hp        the hp
     * @param atk       the atk
     * @param hitChance the hit chance
     */
    public WaitingMonster(int hp, int atk, double hitChance) {
        super( "Wartender Gegner", hp, atk, hitChance);
    }

    /**
     * Instantiates a new Waiting monster.
     *
     * @param name      the name
     * @param hp        the hp
     * @param atk       the atk
     * @param hitChance the hit chance
     */
    public WaitingMonster(String name, int hp, int atk, double hitChance) {
        super(name, hp, atk, hitChance);
    }

    /**
     * Attack int.
     *
     * @param c the enemy
     * @return the damage
     */
    @Override
    public int attack(Character c) {
        if (waited) {
            int atkOld = getAtk();
            setAtk(getAtk()*2);
            int damage = super.attack(c);
            setAtk(atkOld);
            waited = false;
            return damage;
        } else if (Math.random() > waitChance) {
            return super.attack(c);
        } else {
            waited = true;
            return -2;
        }
    }
}
