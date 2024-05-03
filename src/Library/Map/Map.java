package Library.Map;


import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** todo
 * @author poacher
 */
public class Map implements Serializable {
    private final Tile[][] tiles;

    public Map() {
        this.tiles = new Tile[16][16];
    }

    public Map(Map map) {
        tiles = new Tile[map.getTiles().length][map.getTiles()[0].length];
        for (int i = 0; i < map.getTiles().length; i++) {
            for (int n = 0; n < map.getTiles()[i].length; n++) {
                tiles[i][n] = new Tile(map.getTiles()[i][n]);
            }
        }
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public void setTile(Tile tile, int row, int column) {
        this.tiles[row][column] = tile;
    }

    public void placeStructure(Structure structure, int row, int column) {
        for (int i = 0; i < structure.getTiles().length; i++) {
            for (int n = 0; n < structure.getTiles().length; n++) {
                if(i + row > 15)
                    continue;
                if(n + column > 15)
                    continue;

                tiles[i + row][n + column] = structure.getTiles()[n][i];
            }
        }
    }

    /**
     * Check if an entity will collide with a tile
     * @param x The x position of the entity
     * @param y The y position of the entity
     * @return Whether the tile at the specified position collides or not
     */
    public List<Tile> getTileAtPos(int x, int y) {
        int i = x / 64;
        int n = y / 64;

        return new ArrayList<Tile>() {{
            add(tiles[n][i]);
            add(tiles[n + 1][i + 1]);
            add(tiles[n + 1][i]);
            add(tiles[n][i + 1]);
        }};
    }
}
