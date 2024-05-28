package Library.Map;

import java.io.Serializable;

/**
 * Worlds are a 2D array of 9 maps
 * [0] = x
 * [1] = y;
 * @author poacher
 */
public class World implements Serializable {
    private final Map[][] maps;
    private int[] currentMap;

    public World() {
        maps = new Map[3][3];
        currentMap = new int[2];
        currentMap[0] = 1;
        currentMap[1] = 1;
    }

    public void setCurrentMap(int[] currentMap) {
        this.currentMap = currentMap;
    }

    public void setCurrentMap(int x, int y) {
        this.currentMap = new int[] { x, y };
    }

    public int[] getCurrentMapIndex() {
        return currentMap;
    }

    public Map getCurrentMap() {
        return maps[currentMap[0]][currentMap[1]];
    }

    public Map getMap(int[] cords) {
        return maps[cords[0]][cords[1]];
    }

    public void setMap(int[] cords, Map map) {
        maps[cords[0]][cords[1]] = map;
    }

    public void setMap(int x, int y, Map map) {
        maps[x][y] = map;
    }
}
