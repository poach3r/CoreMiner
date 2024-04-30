package Game.Structures;

import Game.Tiles.*;
import Library.Map.Structure;

public class GraniteClump extends Structure {
    public GraniteClump() {
        super(3, 3);
        setTile(TileIndex.copper, 0, 0); setTile(TileIndex.granite, 0, 1); setTile(TileIndex.stone, 0, 2);
        setTile(TileIndex.granite, 1, 0); setTile(TileIndex.granite, 1, 1); setTile(TileIndex.granite, 1, 2);
        setTile(TileIndex.granite, 2, 0); setTile(TileIndex.granite, 2, 1); setTile(TileIndex.stone, 2, 2);
    }
}
