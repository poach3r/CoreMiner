package Library.Entities;

import Library.Graphics.Texture;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

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
    private final int id;
    private Rectangle hitbox;

    // time before the entity can be hit again, this helps prevents unfair damage
    // https://www.giantbomb.com/invincibility-frames/3015-9598/
    private int iTime;
    private int timeSinceHit;
    private int hostility;

    public GenericEntity(int id, String name, Texture texture, int x, int y, int hp, int dmg, int speed, int hostility, int iTime) {
        this.id = id;
        this.name = name;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.dmg = dmg;
        this.speed = speed;
        hitbox = new Rectangle(x, y, texture.getImage().getWidth() - 1, texture.getImage().getHeight() - 1);
        this.iTime = iTime;
        timeSinceHit = 10;
        this.hostility = hostility;
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
        return x ;
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

    public int getITime() {
        return iTime;
    }

    public void setITime(int iTime) {
        this.iTime = iTime;
    }

    public int getTimeSinceHit() {
        return timeSinceHit;
    }

    public void setTimeSinceHit(int timeSinceHit) {
        this.timeSinceHit = timeSinceHit;
    }

    public int getHostility() {
        return hostility;
    }

    public void setHostility(int hostility) {
        this.hostility = hostility;
    }

    public final BiConsumer<Graphics2D, JPanel> getRenderingCode() {
        return this::rendering;
    }

    public void rendering(Graphics2D g, JPanel j) {};
}
