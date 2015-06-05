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
        //MazeGenerator mg = new RecursiveBacktracker();
        //Level m = new Level(mg.generate(31, 71));
        Player p = new Player(); 
        Questmaster master = new Questmaster(p);
        
        Dealer[] dealerFeld = new Dealer [4];//NEW
        for (int i = 0; i < 4; i++) {
            dealerFeld[i] = new Dealer(p);
        
        }
        MazeGenerator mg = new RecursiveBacktracker(dealerFeld);//NEW
        Level m = new Level(mg.generate(31, 71), dealerFeld, master);//NEW
        
        Scanner sc = new Scanner(System.in);
        
       
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
                if (direction == 'q') {
                	System.out.println(p.getQuestList());
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
