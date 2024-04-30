package Library.Map;

import Library.Audio.Sound;
import Library.Items.GenericItem;

import java.io.File;
import java.util.ArrayList;

/**
 * Standard tiles which make up the majority of maps.
 *
 * @author poacher
 */
public class Tile {
    private final String name;
    private final File asset;
    private final boolean collision;
    private final boolean growing;
    private final ArrayList<GenericItem> resources;
    private final Sound stepSound;

    public Tile(String name, File asset, boolean collision, boolean growing, Sound stepSound) {
        this.name = name;
        this.asset = asset;
        this.collision = collision;
        this.growing = growing;
        this.stepSound = stepSound;
        resources = new ArrayList<>();
    }

    public Tile(Tile tile) {
        this.name = tile.getName();
        this.asset = tile.getAsset();
        this.collision = tile.hasCollision();
        this.growing = tile.grows();
        this.resources = tile.getResources();
        this.stepSound = tile.getStepSound();
    }

    public File getAsset() {
        return asset;
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
