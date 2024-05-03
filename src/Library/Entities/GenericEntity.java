package Library.Entities;

import Library.Graphics.Texture;
import Library.Items.GenericItem;

import java.awt.*;
import java.io.File;
import java.util.HashMap;

/** Generic entity template
 * @author poacher
 */
public class GenericEntity {
    private int x;
    private int y;
    private int hp;
    private int dmg;
    private Texture texture;
    private String name;
    private int speed;
    private HashMap<GenericItem, Integer> inventory;
    private final int id;
    private Rectangle hitbox;

    public GenericEntity(int id, String name, Texture texture, int x, int y, int hp, int dmg, int speed) {
        this.id = id;
        this.name = name;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.dmg = dmg;
        this.speed = speed;
        inventory = new HashMap<>();
        hitbox = new Rectangle(x, y, texture.getImage().getWidth(), texture.getImage().getHeight());
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public int getId() {
        return id;
    }

    public void setX(int x) {
        this.x = x;
        hitbox.setLocation(x, y);
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
        hitbox.setLocation(x, y);
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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
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
}
