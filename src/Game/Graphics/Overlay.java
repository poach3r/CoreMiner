package Game.Graphics;

import Game.Entities.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public class Overlay {
    private Player player;
    private int prevHp;

    public Overlay(Player player) {
        this.player = player;
        this.prevHp = 0;
    }

    public BiConsumer<Graphics2D, JPanel> getRenderingCode() {
        return (g, j) -> {

            // draw hp
            for(int n = 0; n < player.getHp(); n++) {
                g.drawImage(TextureIndex.heart.getImage(), 16 + (n * 44), 16, j);
            }

            // draw tool
            g.drawImage(player.getActiveTool().getTexture().getImage(), 1024 - player.getActiveTool().getTexture().getImage().getWidth() - 16, 16, j);
        };
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
