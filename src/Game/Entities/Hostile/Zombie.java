package Game.Entities.Hostile;

import Game.Graphics.TextureIndex;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Zombie extends Library.Entities.GenericEntity {
    public Zombie(int x, int y) {
        super(2, "zombie", TextureIndex.zombie, x, y, 3, 2, 8, 1,10);
    }

    @Override
    public void rendering(Graphics2D g, JPanel j) {

    }
}
