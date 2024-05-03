package Game.Tiles;

import Game.Graphics.TextureIndex;

public class Water extends Library.Map.Tile {
    public Water() {
        super(5, "water", TextureIndex.water, true, true, null);
    }
}
