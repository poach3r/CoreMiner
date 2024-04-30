package Game.Tiles;

import Library.Map.Tile;

import java.io.File;

public class Void extends Tile {
    public Void() {
        super("void", new File("assets/images/void.png"), false, false, null);
    }
}
