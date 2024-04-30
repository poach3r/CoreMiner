package Game.Tiles;

import Library.Map.Tile;

/** This is a global class that houses all the tiles used in the game in order to prevent unnecessary loading of files.
 * @author poacher
 */
public class TileIndex {
    public static Tile Void = new Void();
    public static Tile dirt = new Dirt();
    public static Tile grass1 = new Grass1();
    public static Tile grass2 = new Grass2();
    public static Tile log = new Log();
    public static Tile stone = new Stone();
    public static Tile andesite = new Andesite();
    public static Tile granite = new Granite();
    public static Tile copper = new Copper();
    public static Tile water = new Water();
    public static Tile stoneShroom = new StoneShroom();
    public static Tile iron = new Iron();
    public static Tile thunderStone = new ThunderStone();
    public static Tile solidLightning = new SolidLightning();
}
