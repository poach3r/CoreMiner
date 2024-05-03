package Game.Graphics;

import Game.Entities.Player.Player;
import Game.Items.ItemIndex;
import Game.Logic.Crafter;
import Library.Items.GenericItem;
import Library.Items.GenericTool;
import Library.Items.GenericTrinket;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class CraftingUI extends JPanel {
    private boolean opened;
    private final JFrame frame;
    private final JPanel leftPanel;
    private final JPanel rightPanel;
    private final Crafter crafter;
    private ArrayList<GenericItem> items;
    private HashMap<GenericItem, Integer> currentInventory;
    private ArrayList<GenericTrinket> currentTrinkets;
    private Player player;

    public CraftingUI(JFrame frame, Crafter crafter, Player player) {
        this.frame = frame;
        this.crafter = crafter;
        this.player = player;

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(new JLabel("Inventory"), BorderLayout.NORTH);
        leftPanel.add(new JLabel(), BorderLayout.SOUTH);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        items = new ArrayList<>();
        items.addAll(ItemIndex.craftableTools.stream().map(GenericTool::toGenericItem).toList());
        items.addAll(ItemIndex.craftableTrinkets.stream().map(GenericTrinket::toGenericItem).toList());

        currentInventory = new HashMap<>();
        currentTrinkets = new ArrayList<>();

        this.setOpaque(false);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.add(rightPanel, BorderLayout.EAST);
        this.add(leftPanel, BorderLayout.WEST);
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpenStatus(boolean opened) {
        this.opened = opened;
    }

    public void displayInv() {
        if(!currentInventory.equals(player.getInventory())) {
            currentInventory = new HashMap<>(player.getInventory());
            leftPanel.remove(0);

            final String[] i = {"<html>"};
            i[0] = i[0].concat("Inventory<br>");
            currentInventory.forEach((key, value) -> {
                i[0] = i[0].concat(key.getName() + ": " + value + "<br>");
            });
            i[0] = i[0].concat("</html>");

            leftPanel.add(new JLabel(i[0]), BorderLayout.NORTH, 0);
            revalidate();
            repaint();
        }

        if(!currentTrinkets.equals(player.getTrinkets())) {
            leftPanel.remove(1);
            JPanel trinketsPanel = new JPanel();

            currentTrinkets.forEach(e -> {
                try {
                    trinketsPanel.add(new JLabel(new ImageIcon(ImageIO.read(e.getTexture().getFile()))));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            leftPanel.add(trinketsPanel, BorderLayout.SOUTH, 1);

            currentTrinkets = new ArrayList<>(player.getTrinkets());
            revalidate();
            repaint();
        }
    }

    public void displayRecipes() {
        rightPanel.removeAll();

        for(GenericItem tool : items) {
            JPanel p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

            final String[] i = {"<html>"};
            i[0] = i[0].concat(tool.getName() + "<br>");
            tool.getRecipe().forEach((key, value) -> {
                i[0] = i[0].concat(key.getName() + ": " + value + "<br>");
            });
            i[0] = i[0].concat("</html>");

            p.add(new JLabel(i[0]));

            JButton craft = new JButton("Craft");
            craft.addActionListener(e -> {
                if(crafter.attemptToCraft(tool))
                    items.remove(tool);

                displayRecipes();

                frame.requestFocus();
            });

            p.add(craft);

            rightPanel.add(p);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
