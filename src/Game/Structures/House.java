package Game.Structures;

import Game.Tiles.Dirt;
import Game.Tiles.Log;
import Game.Tiles.TileIndex;
import Library.Map.Structure;

public class House extends Structure {
    public House() {
        super(3, 3);
        setTile(TileIndex.log, 0, 0); setTile(TileIndex.dirt, 0, 1); setTile(TileIndex.log, 0, 2);
        setTile(TileIndex.dirt, 1, 0); setTile(TileIndex.dirt, 1, 1); setTile(TileIndex.dirt, 1, 2);
        setTile(TileIndex.log, 2, 0); setTile(TileIndex.dirt, 2, 1); setTile(TileIndex.log, 2, 2);
    }
}
