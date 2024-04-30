package Library.Entities;

import Library.Items.GenericItem;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/** Generic entity template
 * @author poacher
 */
public class GenericEntity {
    private int pos;
    private int x;
    private int y;
    private int hp;
    private int dmg;
    private File asset;
    private String name;
    private int speed;
    private HashMap<GenericItem, Integer> inventory;
    private int timeSinceLastMove;

    public GenericEntity(String name, File asset, int x, int y, int hp, int dmg, int speed) {
        this.name = name;
        this.asset = asset;
        this.x = x;
        this.y = y;
        this.pos = y * 16 + x;
        this.hp = hp;
        this.dmg = dmg;
        this.speed = speed;
        inventory = new HashMap<>();
        timeSinceLastMove = 0;
    }

    public int getPos() {
        return pos;
    }

    public void setX(int x) {
        this.x = x;
        this.pos = y * 16 + x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
        this.pos = y * 16 + x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public File getAsset() {
        return asset;
    }

    public void setAsset(File asset) {
        this.asset = asset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public HashMap<GenericItem, Integer> getInventory() {
        return inventory;
    }

    public int getTimeSinceLastMove() {
        return timeSinceLastMove;
    }

    public void setTimeSinceLastMove(int timeSinceLastMove) {
        this.timeSinceLastMove = timeSinceLastMove;
    }
}
