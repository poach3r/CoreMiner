package Game.Tiles;

import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;
import Library.Map.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Water extends Library.Map.Tile {
    public Water() {
        super("water", TextureIndex.water.getAsset(), true, true, null);
    }
}
