package Game.Structures;

import Game.Tiles.*;
import Library.Map.Structure;

public class Mineshaft extends Structure {
    public Mineshaft() {
        super(3, 3);
        setTile(TileIndex.log, 0, 0); setTile(TileIndex.stone, 0, 1); setTile(TileIndex.log, 0, 2);
        setTile(TileIndex.stone, 1, 0); setTile(TileIndex.stone, 1, 1); setTile(TileIndex.stone, 1, 2);
        setTile(TileIndex.log, 2, 0); setTile(TileIndex.stone, 2, 1); setTile(TileIndex.log, 2, 2);
    }
}
