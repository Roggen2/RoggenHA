import java.util.Scanner;

/**
 * Hier sitzt die main-Methode.
 * 
 * @author Nikita Kister 4569033 Gruppe 7b
 * @author Marvin Seiler 4496931 Gruppe 7b
 */
    
public class Crawler {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        MazeGenerator mg = new RecursiveBacktracker();
        Level m = new Level(mg.generate(31, 71));
        Scanner sc = new Scanner(System.in);
        Player p = new Player();
        while (!p.isDefeated()) {
            System.out.println(m);
            m.showPrompt();
            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Leere Eingabe, bitte eine Richtung eingeben");
            } else {
                input = input.toLowerCase();
                char direction = input.charAt(0);
                if (direction == 'i') {
                	//NEW
                    System.out.println(p.getItemInventory());
                    System.out.println(p.getGold() + " Gold.");
                    continue;
                }
                if (!m.canMove(direction)) {
                    System.out.println("Ungueltige Richtung");
                } else {
                    m.move(direction);
                    m.handleCurrentFieldEvent(p);
                }
            }
            //Monster bewegung?
        }
    }
}
