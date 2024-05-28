package Game.Entities.Passive;

import Game.Graphics.TextureIndex;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Sheep extends Library.Entities.GenericEntity {
    public Sheep(int x, int y) {
        super(1, "sheep", TextureIndex.sheep, x, y, 1, 0, 8, 0, 10);
    }

    @Override
    public void rendering(Graphics2D g, JPanel j) {
        return;
    }
}
