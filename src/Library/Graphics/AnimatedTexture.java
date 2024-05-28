package Library.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class AnimatedTexture extends Texture {
    private int currentFrame;
    private ArrayList<BufferedImage> frames;

    public AnimatedTexture(final File file, final String name) {
        super(file, name);

        currentFrame = 0;
        frames = new ArrayList<>();
    }
}
