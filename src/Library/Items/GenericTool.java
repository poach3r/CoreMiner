package Library.Items;

import Game.Items.ItemType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class GenericTool extends GenericItem {
    private final HashMap<GenericItem, Integer> requiredItems;
    private final int speed;
    private final int radius;

    public GenericTool(String name, File asset, int speed, int value, int radius) {
        super(name, asset, value, ItemType.TOOL);
        requiredItems = new HashMap<>();
        this.speed = speed;
        this.radius = radius;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRadius() {
        return radius;
    }
}
