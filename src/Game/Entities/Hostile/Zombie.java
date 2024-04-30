package Game.Entities.Hostile;

import java.io.File;

public class Zombie extends Library.Entities.GenericEntity {
    public Zombie(int x, int y) {
        super("zombie", new File("assets/images/zombie.png"), x, y, 3, 2, 10);
    }
}
