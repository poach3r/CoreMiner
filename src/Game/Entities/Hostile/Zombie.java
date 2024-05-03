package Game.Entities.Hostile;

import Game.Graphics.TextureIndex;

import java.io.File;

public class Zombie extends Library.Entities.GenericEntity {
    public Zombie(int x, int y) {
        super(2, "zombie", TextureIndex.zombie, x, y, 3, 2, 8);
    }
}
