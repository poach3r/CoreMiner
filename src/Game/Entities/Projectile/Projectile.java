package Game.Entities.Projectile;

import Game.Graphics.TextureIndex;
import Library.Entities.GenericEntity;

import javax.swing.*;
import java.awt.*;

public class Projectile extends GenericEntity {
    private int endX;
    private int endY;

    public Projectile(int x, int y, int endX, int endY) {
        super(2, "arrow", TextureIndex.water, x, y, 1, 2, 32, 4, 20);
        this.endX = endX;
        this.endY = endY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    @Override
    public void rendering(Graphics2D g, JPanel j) {

    }
}
