package Game.Entities.Hostile;

import java.io.File;

public class MushyZombie extends Library.Entities.GenericEntity {
    public MushyZombie(int x, int y) {
        super("mushyZombie", new File("assets/images/mushyZombie.png"), x, y, 3, 1, 20);
    }
}
