package Game.Entities.Hostile;

import Game.Graphics.TextureIndex;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MushyZombie extends Library.Entities.GenericEntity {
    public MushyZombie(int x, int y) {
        super(3, "mushyZombie", TextureIndex.mushyZombie, x, y, 3, 1, 6, 1, 20);
    }

    @Override
    public void rendering(Graphics2D g, JPanel j) {

    }
}
