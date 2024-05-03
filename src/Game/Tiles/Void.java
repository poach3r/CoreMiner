package Game.Tiles;

import Game.Graphics.TextureIndex;
import Library.Map.Tile;

import java.io.File;

public class Void extends Tile {
    public Void() {
        super(0, "void", TextureIndex.Void, false, false, null);
    }
}
