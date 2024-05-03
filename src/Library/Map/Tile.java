package Library.Map;

import Library.Audio.Sound;
import Library.Graphics.Texture;
import Library.Items.GenericItem;

import java.util.ArrayList;

/**
 * Standard tiles which make up the majority of maps.
 *
 * @author poacher
 */
public class Tile {
    private final String name;
    private final Texture texture;
    private final boolean collision;
    private final boolean growing;
    private final ArrayList<GenericItem> resources;
    private final Sound stepSound;
    private final int id;

    public Tile(int id, String name, Texture texture, boolean collision, boolean growing, Sound stepSound) {
        this.id = id;
        this.name = name;
        this.texture = texture;
        this.collision = collision;
        this.growing = growing;
        this.stepSound = stepSound;
        resources = new ArrayList<>();
    }

    public Tile(Tile tile) {
        this.id = tile.getId();
        this.name = tile.getName();
        this.texture = tile.getTexture();
        this.collision = tile.hasCollision();
        this.growing = tile.grows();
        this.resources = tile.getResources();
        this.stepSound = tile.getStepSound();
    }

    public int getId() {
        return id;
    }

    public Texture getTexture() {
        return texture;
    }

    public boolean hasCollision() {
        return collision;
    }

    public boolean grows() {
        return growing;
    }

    public void addResource(GenericItem item, int count) {
        for (int i = 0; i < count; i++) {
            resources.add(item);
        }
    }

    public ArrayList<GenericItem> getResources() {
        return resources;
    }

    public String getName() {
        return name;
    }

    public Sound getStepSound() {
        return stepSound;
    }
}
