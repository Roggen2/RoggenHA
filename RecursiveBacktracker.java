import java.util.Random;

/**
 * Erzeugt ein Level mit Hilfe von Rekursion.
 * 
 * @author Nikita Kister 4569033 Gruppe 7b
 * @author Marvin Seiler 4496931 Gruppe 7b
 */
    
public class RecursiveBacktracker implements MazeGenerator, Tiles {

    /**
     * The Offsets.
     * (x, y)
     */
    private static int[][] offsets = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    /**
     * The constant HALLWAY_OFFSET.
     */
    private static int HALLWAY_OFFSET = 2;

    /**
     * The Random Instance.
     */
    private Random r = new Random();

    /**
     *Array mit den Haendlern
     */
     private Dealer[] dealerArray; //NEW
    /**
     * The Goal set.
     */
    private boolean goalSet = false;
    
    
    public RecursiveBacktracker(Dealer[] array) { //NEW
        this.dealerArray = array;
    }
    
    /**
     * Valid new position.
     *
     * @param maze   the maze
     * @param x      the x
     * @param y      the y
     * @param offset the offset
     * @return the boolean
     */
    private boolean validNewPosition(char[][] maze, int x, int y, int[] offset) {
        int newX = x + HALLWAY_OFFSET * offset[1];
        int newY = y + HALLWAY_OFFSET * offset[0];
        if (newY < 0 || newY >= maze.length || newX < 0 || newX >= maze[0].length) {
            return false;
        }
        return maze[newY][newX] == WALLCHAR;

    }
    
    /**
     * NEW
     * setzt einen dealer in ein 3x3 feld um die parameter
     * @param centerW x koordinate des Zentrums
     * @param centerH y koordinate des Zentrums
     * @param maze maze
     * @d das Dealer Objekt das dort platziert werden soll
     * @return das maze
     */
     private char[][] placeIn3x3Cluster(int centerW, int centerH, char[][] maze, Dealer d) {
         if (maze[centerH][centerW] == FREECHAR) {
             maze[centerH][centerW] = DEALER;
             System.out.println(d);
             d.setPos(centerW, centerH);
         } else {
             a: for (int i = -1; i <= 1; i++) {
                 for (int j = -1; j <= 1; j++) {
                     if (maze[centerH + i][centerW + j] == FREECHAR) {
                         maze[centerH + i][centerW + j] = DEALER;
                         d.setPos(centerW + j, centerH + i);
                         break a; 
                     }
                 }
             }
         }
         return maze;
     }
     
     /**
      * NEW
      * platziert 4 dealer in die 4 Quardranten des maze
      * @param maze maze
      */
      private void placeDealer(char[][] maze) {
          int height = maze.length;
          int width = maze[0].length;
          maze = placeIn3x3Cluster((width + 3) / 4, (height + 3) / 4, maze, dealerArray[0]);
          maze = placeIn3x3Cluster((3 * width + 1) / 4, (height + 3) / 4, maze, dealerArray[1]);
          maze = placeIn3x3Cluster((width + 3) / 4, (3 * height + 1) / 4, maze, dealerArray[2]);
          maze = placeIn3x3Cluster((3 * width + 1) / 4, (3 * height + 1) / 4, maze, dealerArray[3]);
      }
    
    /**
     * Init maze.
     *
     * @param height the height
     * @param width  the width
     * @return the map as char[][]
     */
    private char[][] initMaze(int height, int width) {
        assert height % 2 == 0 && width % 2 == 0;
        char[][] map = new char[height][width];
        for (int i = 0; i< map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                map[i][j] = WALLCHAR;
            }
        }
        return map;
    }

    /**
     * Border char [ ] [ ].
     *
     * @param maze the maze
     * @return the map as char[][]
     */
    private char[][] border(char[][] maze) {
        char[][] borderedMaze = new char[maze.length+2][maze[0].length+2];
        for (int i = 0; i < borderedMaze.length; ++i) {
            for (int j = 0; j < borderedMaze[0].length; ++j) {
                if (i == 0 || j == 0 || i == borderedMaze.length-1 || j == borderedMaze[0].length-1) {
                    borderedMaze[i][j] = WALLCHAR;
                } else {
                    borderedMaze[i][j] = maze[i-1][j-1];
                }
            }
        }
        return borderedMaze;
    }

    /**
     * In maze.
     *
     * @param maze the maze
     * @param x the x
     * @param y the y
     * @return true, wenn sich die Koordinatem im Spielfeld befinden
     */
    private boolean inMaze(char[][] maze, int x, int y) {
        return !(y < 0 ||  y >= maze.length || x < 0 || x >= maze[0].length);
    }

    /**
     * Count visitable neighbors.
     *
     * @param maze the maze
     * @param x the x
     * @param y the y
     * @return the neighbors
     */
    private int countVisitableNeighbors(char[][] maze, int x, int y) {
        int n = 0;
        for (int[] offset: offsets) {
            int newX = x + offset[1];
            int newY = y + offset[0];
            if (inMaze(maze, newX, newY) && maze[newY][newX] != WALLCHAR) {
                n++;
            }
        }
        return n;
    }

    /**
     * Place special fields.
     *
     * @param maze the maze
     */
    private void placeSpecialFields(char[][] maze) {
    	placeDealer(maze);
    	for (int i = 0; i < maze.length; ++i) {
            for (int j = 0; j < maze[0].length; ++j) {
                if (maze[i][j] == FREECHAR) {
                    int neighbors = countVisitableNeighbors(maze, j, i);
                    if (neighbors >= 3) {
                        maze[i][j] = BATTLECHAR;
                    } else if (neighbors == 1) {
                        if (r.nextDouble() > 0.5) {
                            maze[i][j] = FOUNTAIN;
                        } else {
                            maze[i][j] = SMITHYCHAR;
                        }
                    }  
                }
            }
        }
    }
    /**
     * Generate char [ ] [ ].
     *yx
     * @param height the height
     * @param width the width
     * @return the map as char[][]
     */

    public char[][] generate(int height, int width) {
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Non-valid maze dimensions");
        }
        goalSet = false;
        char[][] maze = initMaze(height, width);
        int startx = 2*r.nextInt(width/2)+1;
        int starty = 2*r.nextInt(height/2)+1;
        maze = generate(startx, starty,  maze);
        maze[starty][startx] = STARTCHAR;
        for(int[] direction:offsets) {
        	if(maze[starty+direction[0]][startx+direction[1]] != WALLCHAR ) {
        		maze[starty+direction[0]][startx+direction[1]] = QUESTMASTER;
        	}
        }
        maze = border(maze);
        placeSpecialFields(maze);
        removeStones(maze);
        return maze;
    }

    /**
     * Build hallway.
     *
     * @param maze the maze
     * @param curX the cur x
     * @param curY the cur y
     * @param offset the offset
     * @param length the length
     */
    private void buildHallway(char[][] maze, int curX, int curY, int[] offset, int length) {

        for (int i = 1; i <= length; ++i) {
            
            curX += offset[1];
            curY += offset[0];

            if (curY < 0 ||  curY >= maze.length || curX < 0 || curX >= maze[0].length) {
                return;
            }
            
            maze[curY][curX] = FREECHAR;
        }
    }

    /**
     * Generate char [ ] [ ].
     *
     * @param curX the cur x
     * @param curY the cur y
     * @param maze the maze
     * @return the map as char [] []
     */
    private char[][] generate(int curX, int curY, char[][] maze) {
        maze[curY][curX] = FREECHAR;
        int[] validPositions = new int[offsets.length];
        int validPositionCount = offsets.length;
        for (int i = 0; i < offsets.length; ++i) {
                validPositions[i] = i;
        }
        boolean deadEnd = true;
        while (validPositionCount != 0) {
            int newPosIndex = r.nextInt(validPositionCount);
            int[] newPosOffset = offsets[validPositions[newPosIndex]];
            int newX = curX + HALLWAY_OFFSET * newPosOffset[1];
            int newY = curY + HALLWAY_OFFSET * newPosOffset[0];

            if (validNewPosition(maze, curX, curY, newPosOffset)) {
                deadEnd = false;
                buildHallway(maze, curX, curY, newPosOffset, HALLWAY_OFFSET);
                generate(newX, newY, maze);
            }

            validPositions = ArrayHelpers.delete(validPositions, newPosIndex);
            validPositionCount--;
            
        }
        if (!goalSet && deadEnd) {
            goalSet = true;
            maze[curY][curX] = GOALCHAR;
        }
        return maze;
    }
    
    /**
     * test methode zum entfernen aller steine
     * für TESTZWECKE
     */
    
    private void removeStones(char[][] maze) {
    	for(int i = 1; i < maze.length -1; i++) {
    		for (int j = 1; j < maze[i].length-1; j++) {
    			if(maze[i][j] == WALLCHAR) {
    				maze[i][j] = FREECHAR;
    			}
    			
    		}
    	}
    }

}
