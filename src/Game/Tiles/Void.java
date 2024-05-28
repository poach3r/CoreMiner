package Game.Tiles;

import Game.Graphics.TextureIndex;
import Library.Map.Tile;

public class Void extends Tile {
    public Void() {
        super(0, "void", TextureIndex.Void, false, null);
    }
}
