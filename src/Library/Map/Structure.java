package Library.Map;

/** Structures are predefined groups of tiles that can be placed in the map.
 * @author poacher
 */
public class Structure {
    private final Tile[][] tiles;

    public Structure(int rows, int columns) {
        this.tiles = new Tile[rows][columns];
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public void setTile(Tile tile, int row, int column) {
        this.tiles[row][column] = tile;
    }
}
