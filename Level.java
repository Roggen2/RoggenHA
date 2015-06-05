import java.util.Scanner;

/**
 * Dies beschreibt den Aufbau eines Levels.
 * 
 * @author Nikita Kister 4569033 Gruppe 7b
 * @author Marvin Seiler 4496931 Gruppe 7b
 */
    
public class Level implements Tiles {
    /**
     * The constant ATKBONUS.
     */
    private static final int ATKBONUS = 10;
    /**
     * The Map data.
     */
    private char[][] mapData;

    private int steps; 
    /**
     * Haendler Feld
    */
    private Dealer[] dealerArray;
    
    private Questmaster master;

    private int playerX;
    /**
     * The Player y coordinate.
     */
    private int playerY;

    /**
     * Instantiates a new Level.
     *
     * @param mapData the map data
     */
    public Level(char[][] mapData, Dealer[] d, Questmaster master) {
    	this.dealerArray = d;
        if (mapData.length < 3 || mapData[0].length < 3) {
            throw new IllegalArgumentException("Invalid Map Data");
        }
        this.mapData = mapData;
        if (!findStart()) {
            throw new IllegalArgumentException("Invalid Map Data: No starting position");
        }
        
        this.master = master;
    }

    /**
     * Find start.
     *
     * @return true, wenn die Startposition gefunden wuerde
     */
    private boolean findStart() {
        for (int y = 0; y < mapData.length; y++) {
            for (int x = 0; x < mapData[0].length; x++) {
                if (mapData[y][x] == STARTCHAR) {
                    playerX = x;
                    playerY = y;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < mapData.length; ++y) {
            for (int x = 0; x < mapData[0].length; ++x) {
                if (y == playerY && x == playerX) {
                    sb.append(PLAYER_CHAR);
                } else {
                    sb.append(mapData[y][x]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Can move.
     *
     * @param c the direction
     * @return true, wenn die Richtung möglich ist
     */
    public boolean canMove(char c) {
        switch (c) {
            case 'w':
                return canMoveUp();
            case 's':
                return canMoveDown();
            case 'd':
                return canMoveRight();
            case 'a':
                return canMoveLeft();
            default:
                return false;
        }
    }

    /**
     * Move void.
     *
     * @param c the direction
     */
    public void move(char c) {
    	
    	this.steps++;
    	
    	switch (c) {
            case 'w':
                moveUp();
                break;
            case 's':
                moveDown();
                break;
            case 'd':
                moveRight();
                break;
            case 'a':
                moveLeft();
                break;
        }
    }


    /**
     * Is walkable position.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true, wenn das Feld x,y begehbar ist
     */
    public boolean isWalkablePosition(int x, int y) {
        return (y >= 0) && (x >=0) && (y < mapData.length) && (x < mapData[0].length) 
            && (mapData[y][x] == FREECHAR || mapData[y][x] == FOUNTAIN || mapData[y][x] == SMITHYCHAR
                || mapData[y][x] == BATTLECHAR || mapData[y][x] == GOALCHAR || mapData[y][x] == STARTCHAR
                || mapData[y][x] == QUESTMASTER || mapData[y][x] == DEALER);
    }

    /**
     * Can move up.
     *
     * @return true, wenn mögliche Bewegung
     */
    public boolean canMoveUp() {
        return isWalkablePosition(playerX, playerY - 1);
    }

    /**
     * Can move down.
     *
     * @return true, wenn mögliche Bewegung
     */
    public boolean canMoveDown() {
        return isWalkablePosition(playerX, playerY + 1);
    }

    /**
     * Can move left.
     *
     * @return true, wenn mögliche Bewegung
     */
    public boolean canMoveLeft() {
        return isWalkablePosition(playerX - 1, playerY);
    }

    /**
     * Can move right.
     *
     * @return true, wenn mögliche Bewegung
     */
    public boolean canMoveRight() {
        return isWalkablePosition(playerX + 1, playerY);
    }

    /**
     * Move up.
     */
    public void moveUp() {
        if (canMoveUp()) {
            playerY--;
        }
    }

    /**
     * Move down.
     */
    public void moveDown() {
        if (canMoveDown()) {
            playerY++;
        }
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (canMoveLeft()) {
            playerX--;
         }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (canMoveRight()) {
            playerX++;
        }
    }

    /**
     * Show prompt.
     */
    public void showPrompt() {
        System.out.println("------------------------------");
        System.out.println("i -> Inventar");
        System.out.println("q -> Questlog");
        if (canMoveUp()) {
            System.out.println("w -> Norden");
        }
        if (canMoveDown()) {
            System.out.println("s -> Sueden");
        }
        if (canMoveRight()) {
            System.out.println("d -> Osten");
        }
        if (canMoveLeft()) {
            System.out.println("a -> Westen");
        }
        System.out.println("------------------------------");
        System.out.print("Richtung? ");
    }

    /**
     * Gets field.
     *
     * @return the field
     */
    private char getField() {
        return mapData[playerY][playerX];
    }

    /**
     * Clear field.
     */
    private void clearField() {
        char field = getField();
        if (field == SMITHYCHAR || field == FOUNTAIN || field == BATTLECHAR) {
            mapData[playerY][playerX] = FREECHAR;
        }
    }

    /**
     * Handle current field event.
     *
     * @param p the player
     */
    public void handleCurrentFieldEvent(Player p) {
        char field = getField();
        switch (field) {
            case Level.SMITHYCHAR:
                p.setAtk(p.getAtk()+ATKBONUS);
                System.out.printf("Die ATK des Spielers wurde um %d erhöht.%n", ATKBONUS);
                clearField();
                break;
            case Level.FOUNTAIN:
                p.setHp(p.getMaxHp());
                System.out.println("Spieler wurde vollständig geheilt!");
                break;
            case Level.BATTLECHAR:
                startBattle(p);
                clearField();
                break;
            case Level.QUESTMASTER:
            	startAdventure(p);
            	break;
            case Level.GOALCHAR:
                if(LevelFinished()) {
                	System.out.println("Herzlichen Glückwunsch! Sie haben gewonnen!");
                	System.exit(0);
                }
                break;
            case Level.DEALER://NEW
                deal();
                break;
        }
        //restructure(20);//NEW
       // clearField();
    }
    //NOCH NICHT FERTIG
    public void startAdventure(Player p) {
    	master.questUpdate();
    	master.updateFinishedQuests();
    	master.updateVisibleQuests();
    	//TEST
    	/*System.out.println(master.getQ());
    	System.out.println(master.getW());
    	System.out.println(master.getE());*/
    	
    	master.givePlayerQuests();
    }
    
    
    
    /**
     * Random monster.
     *
     * @return the monster
     */
    private static Monster randomMonster() {
        Monster[] monsterFarm = {
                new Monster(),
                new ResistantMonster(),
                new WaitingMonster()
        };

        double bucketSize = 1.0 / monsterFarm.length;
        double bucket = Math.random() / bucketSize;
        int selectedMonster = (int) Math.floor(bucket);
        return monsterFarm[selectedMonster];
    }

    /**
     * Start battle.
     *
     * @param p the p
     */
    public void startBattle(Player p) {
        Character m = randomMonster();

        Scanner sc = new Scanner(System.in);
        
        System.out.println("                 Kampf Start                    ");
        System.out.print(p);
        System.out.print(m);

        while(true) {
            System.out.println("------------------------------------------------");
            System.out.println("Mögliche Aktionen:");
            System.out.println("1 -> Angriff");
            System.out.printf("2 -> Item (%d verbleibend)%n", p.getRemainingItemUses());
            System.out.printf("3 -> Harter Schlag (%d AP, %d%% Selbstschaden)%n", Player.HARD_HIT_COST, Player.HARD_HIT_SELF_DAMAGE_PERCENT);
            System.out.printf("4 -> Feuerball (%d AP)%n", Player.FIREBALL_COST);
            System.out.printf("5 -> ATK auswürfeln (%d AP)%n", Player.REROLL_COST);
            System.out.println("Welche Aktion?: ");
            System.out.println("------------------------------------------------");
            String aktion = sc.nextLine();
            int playerDamage;
            switch (aktion) {
                case "1":
                    playerDamage = p.attack(m);
                    if (playerDamage == -1) {
                        System.out.println("Spieler verfehlt!");
                    } else {
                        System.out.printf("Spieler trifft und macht %d Schaden!%n", playerDamage);
                    }
                    break;
                case "2":
                    if (p.heal()) {
                        System.out.println("Spieler heilt sich!");
                    } else {
                        System.out.println("Nicht genügend Heiltränke!");
                    }
                    break;
                case "3":
                    playerDamage = p.hardHit(m);
                    if (playerDamage != -1) {
                        System.out.println("Spieler schlägt hart zu!");
                        System.out.printf("Spieler verursacht %d Schaden!%n", playerDamage);
                        System.out.printf("Spieler verursacht %d Selbstschaden!%n", (int) (Player.HARD_HIT_SELF_DAMAGE_PERCENT / 100.0 * playerDamage));
                    } else {
                        System.out.println("Nicht genügend AP!");
                    }
                    break;
                case "4":
                    playerDamage = p.fireball(m);
                    if (playerDamage != -1) {
                        System.out.println("Spieler schießt einen Feuerball!");
                        System.out.printf("Spieler verursacht %d Schaden!%n", playerDamage);
                    } else {
                        System.out.println("Nicht genügend AP!");
                    }
                    break;
                case "5":
                    if (p.reroll()) {
                        System.out.println("ATK neu ausgewürfelt!");
                        System.out.print("Neue Statuswerte: ");
                        System.out.print(p);
                    } else {
                        System.out.println("Nicht genügend AP!");
                    }
                    break;
                default:
                    System.out.println("Fehlerhafte Aktion!");
                    continue;
            }

            if (p.isDefeated()) {
                System.out.println("Game Over!");
                System.exit(0);
            } else if (m.isDefeated()) {
                //NEW
            	Inventory<Item> mI = m.getItemInventory();
                Inventory<Item> pI = p.getItemInventory();
                p.setGold(m.getGold() + p.getGold());
                System.out.println("Spieler gewinnt!");
                for(int i = 0; i < mI.length(); i++) {
                    pI.insert(mI.getItem(i));
                    
                } 
                System.out.println(mI.toString());
                System.out.println("Sie erhalten " + m.getGold() + " Gold.");
                
                break;
            }

            System.out.print(p);
            System.out.print(m);

            System.out.println("Monster greift an!");
            int monsterDamage = m.attack(p);
            if (monsterDamage == -1) {
                System.out.println("Monster verfehlt!");
            } else if (monsterDamage == -2) {
                System.out.println("Monster tut nichts.");
            } else {
                System.out.printf("Monster trifft und macht %d Schaden!%n", monsterDamage);
            }

            if (p.isDefeated()) {
                System.out.println("Game Over!");
                System.exit(0);
            }

            p.regenerateAp();

            System.out.print(p);
            System.out.print(m);
        }
        
        
    }
    /**
     * NEW
     * handeln
     */
     private void deal() {
         Dealer d = this.dealerArray[0];
         for (int i = 0; i < dealerArray.length; i++) {
             if (this.dealerArray[i].getX() == playerX && this.dealerArray[i].getY() == playerY) {
                 d = this.dealerArray[i];
                 break;
             }
         }
         d.trade();
     }/**
      *NEW
      * erneutes Setzen der Kampffelder
      * @param count gibt an nach wie vielen Schritten neu gesetzt werden soll
      */ 
      private void restructure (int count) {
          if (this.steps == count) {
              for (int i = 0; i < this.mapData.length; ++i) {
                  for (int j = 0; j < this.mapData[0].length; ++j) {
                      if (this.mapData[i][j] == Level.FREECHAR) {
                          int neighbors = countVisitableNeighbors(j, i);
                          if (neighbors >= 3) {
                              this.mapData[i][j] = Level.BATTLECHAR;
                          }
                          
                      }
                  }
              }
              this.steps = 0;
          }
      }
      
      /**
       *NEW
       * Count visitable neighbors.
       * @param x the x
       * @param y the y
       * @return the neighbors
       */
       private int countVisitableNeighbors(int x, int y) {
           int n = 0;
           if (isWalkablePosition(x - 1, y))
           n++;
           if (isWalkablePosition(x, y - 1))
           n++;
           if (isWalkablePosition(x + 1, y))
           n++;
           if (isWalkablePosition(x, y + 1))
           n++;
           return n;
       }
       
       public boolean LevelFinished() {
    	   for(int i= 0; i < master.getQ().length(); i++) {
    		   if(!master.getQ().getItem(i).isFinished()) {
    			   return false;
    		   }
    	   }
    	   return true;
       }
     

}
