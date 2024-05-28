package Library.Map;

import Library.Audio.Sound;
import Library.Entities.GenericEntity;
import Library.Graphics.Texture;
import Game.Items.GenericItem;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Standard tiles which make up the majority of maps.
 *
 * @author poacher
 */
public class Tile {
    private final String name;
    private final Texture texture;
    private final boolean collision;
    private final ArrayList<GenericItem> resources;
    private final Sound stepSound;
    private final int id;
    private final int lightLevel;

    public Tile(int id, String name, Texture texture, boolean collision, Sound stepSound, int lightLevel) {
        this.id = id;
        this.name = name;
        this.texture = texture;
        this.collision = collision;
        this.stepSound = stepSound;
        this.lightLevel = lightLevel;
        resources = new ArrayList<>();
    }

    public Tile(int id, String name, Texture texture, boolean collision, Sound stepSound) {
        this.id = id;
        this.name = name;
        this.texture = texture;
        this.collision = collision;
        this.stepSound = stepSound;
        this.lightLevel = 0;
        resources = new ArrayList<>();
    }

    public Tile(Tile tile) {
        this.id = tile.getId();
        this.name = tile.getName();
        this.texture = tile.getTexture();
        this.collision = tile.hasCollision();
        this.resources = tile.getResources();
        this.stepSound = tile.getStepSound();
        lightLevel = tile.getLightLevel();
    }

    public int getLightLevel() {
        return  lightLevel;
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

    public Consumer<GenericEntity> getOnStepCode() {
        return this::onStep;
    }

    /**
     * Code to be activated when an entity steps on the tile
     */
    public void onStep(GenericEntity entity) {};
}
