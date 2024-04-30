package Library.Map;


import java.io.Serializable;

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
}
