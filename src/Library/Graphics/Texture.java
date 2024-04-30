package Library.Graphics;

import java.io.File;

/** The Texture class represents texture files that are to be used in the game for graphics.
 * @author poacher
 */
public final class Texture {
    private final File asset;
    private final String name;

    public Texture(final File asset, final String name) {
        this.asset = asset;
        this.name = name;
    }

    public File getAsset() {
        return asset;
    }

    public String getName() {
        return name;
    }
}
