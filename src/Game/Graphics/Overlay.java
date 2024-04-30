package Game.Graphics;

import Game.Entities.Player.Player;
import Library.Graphics.SquarePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/** This is a simple JPanel which displays information about the game such as our inventory, hp, and tools.
 *
 */
public class Overlay extends JPanel {
    private JPanel hp;
    private JLabel tool;
    private Player player;

    public Overlay(Player player) {
        this.player = player;
        hp = new JPanel();
        hp.setOpaque(false);
        tool = new JLabel();
        tool.setName("");

        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel p = new JPanel();
        p.setOpaque(false);

        p.add(hp);
        p.add(tool);
        add(p, BorderLayout.NORTH);

        setVisible(true);
    }

    public void reload() {
        displayHp();
        displayTool();
        revalidate();
        repaint();
    }

    public void displayTool() {
        if(tool.getName().equals(player.getTool().getName()))
            return;

        try {
            tool.setIcon(new ImageIcon(ImageIO.read(player.getTool().getAsset())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tool.setName(player.getTool().getName());
    }

    public void displayHp() {
        if(player.getHp() == hp.getComponentCount())
            return;

        hp.removeAll();

        try {
            for(int i = 0; i < player.getHp(); i++) {
                hp.add(new JLabel(new ImageIcon(ImageIO.read(new File("assets/images/heart.png")))));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
