package Library.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** The Texture class represents texture files that are to be used in the game for graphics.
 * @author poacher
 */
public final class Texture {
    private final File file;
    private final String name;
    private final BufferedImage image;

    public Texture(final File file, final String name) {
        this.file = file;
        this.name = name;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }
}
