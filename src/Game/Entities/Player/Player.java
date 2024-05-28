package Game.Entities.Player;

import Game.Graphics.TextureIndex;
import Game.Items.GenericItem;
import Game.Items.ItemIndex;
import Game.Items.Tools.GenericTool;
import Game.Items.Trinkets.GenericTrinket;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/** This is our player character, they are a generic entity with the special property of invincibility frames as they are the only entity which can
 * directly take damage from other entities.
 * @author poacher
 */
public class Player extends Library.Entities.GenericEntity {
    private HashMap<GenericItem, Integer> inventory;
    private ArrayList<GenericTool> tools;
    private int activeTool;
    private int timeSinceLastToolUse;
    private ArrayList<GenericTrinket> trinkets;
    private int mouseX;
    private int mouseY;

    public Player() {
        super(0, "player", TextureIndex.player, 0, 0, 10, 1, 16, 3, 20);
        timeSinceLastToolUse = 60;
        tools = new ArrayList<>();
        tools.add(ItemIndex.rubyWand);
        tools.add(ItemIndex.emeraldWand);
        activeTool = 0;
        trinkets = new ArrayList<>();
        inventory = new HashMap<>();
        mouseX = 0;
        mouseY = 0;
    }

    public GenericTool getActiveTool() {
        return tools.get(activeTool);
    }

    public void setTool(GenericTool tool, int index) {
        tools.set(index, tool);
    }

    public ArrayList<GenericTool> getTools() {
        return tools;
    }

    public int getActiveToolIndex() {
        return activeTool;
    }

    public void setActiveTool(int index) {
        activeTool = index;
    }

    public void iterateTool() {
        if(activeTool >= tools.size() - 1) {
            activeTool = 0;
            return;
        }

        activeTool += 1;
    }

    public int getTimeSinceLastToolUse() {
        return timeSinceLastToolUse;
    }

    public void setTimeSinceLastToolUse(int timeSinceLastToolUse) {
        this.timeSinceLastToolUse = timeSinceLastToolUse;
    }

    public void addTrinket(GenericTrinket trinket) {
        trinket.getPickupEffect().accept(this);
        trinkets.add(trinket);
    }

    public ArrayList<GenericTrinket> getTrinkets() {
        return trinkets;
    }

    public HashMap<GenericItem, Integer> getInventory() {
        return inventory;
    }

    public void addItem(GenericItem item) {
        if(inventory.containsKey(item))
            inventory.replace(item, inventory.get(item) + 1);

        else
            inventory.put(item, 1);
    }

    public void removeItem(GenericItem item, Integer value) {
        if(inventory.containsKey(item)) {
            if(inventory.get(item) <= value)
                inventory.remove(item);
            else
                inventory.replace(item, inventory.get(item) - value);
        }
    }

    public void removeItem(GenericItem item) {
        if(inventory.containsKey(item)) {
            if(inventory.get(item) <= 1)
                inventory.remove(item);
            else
                inventory.replace(item, inventory.get(item) - 1);
        }
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    @Override
    public void rendering(Graphics2D g, JPanel j) {
        g.setColor(Color.RED);
        g.drawRect(getX() - (getActiveTool().getRadius() / 2), getY() - (getActiveTool().getRadius() / 2), getActiveTool().getRadius() + getTexture().getImage().getWidth(), getActiveTool().getRadius() +  getTexture().getImage().getHeight());
    }
}
