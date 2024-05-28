package Game.Items.Tools;

import Game.Entities.Player.Player;
import Game.Entities.Player.PlayerController;
import Game.Items.GenericItem;
import Game.Items.ItemType;
import Library.Entities.GenericEntity;
import Library.Graphics.Renderer;
import Library.Graphics.Texture;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public abstract class GenericTool extends GenericItem {
    private final HashMap<GenericItem, Integer> requiredItems;
    private final int useTime;
    private final int speed;
    private final int radius;
    private final int damage;

    public GenericTool(int id, String name, Texture texture, int speed, int value, int radius, int useTime, int damage) {
        super(id, name, texture, value, ItemType.TOOL);
        requiredItems = new HashMap<>();
        this.speed = speed;
        this.radius = radius;
        this.useTime = useTime;
        this.damage = damage;
    }

    /**
     * @return The minimum time in ticks between uses of the tool
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return The time in ticks that the tool will be visible during usage.
     */
    public int getUseTime() {
        return useTime;
    }

    public int getRadius() {
        return radius;
    }

    public int getDamage() {
        return damage;
    }

    public abstract void logic(Player player, PlayerController playerController, Renderer renderer);
    public abstract void renderering(Player player, Graphics2D g, JPanel j);
}
