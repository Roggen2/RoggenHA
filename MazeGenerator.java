/**
 * Diese Attribute stehen jedem Labyrinthenerzeuger zur Verfuegung.
 * 
 * @author Nikita Kister 4569033 Gruppe 7b
 * @author Marvin Seiler 4496931 Gruppe 7b
 */
    
public interface MazeGenerator {

 
    /**
     * Generate char [ ] [ ].
     *
     * @param height the height
     * @param width  the width
     * @return the char [ ] [ ]
     */
    char[][] generate(int height, int width);
}
