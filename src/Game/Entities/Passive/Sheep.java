package Game.Entities.Passive;

import java.io.File;

public class Sheep extends Library.Entities.GenericEntity {
    public Sheep(int x, int y) {
        super("sheep", new File("assets/images/sheep.png"), x, y, 1, 1, 120);
    }
}
