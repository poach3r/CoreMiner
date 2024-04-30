package Game.Items.Resources;

import Game.Graphics.TextureIndex;
import Library.Items.GenericItem;

import java.io.File;

public class Copper extends GenericItem {
    public Copper() {
        super("copper", TextureIndex.copper.getAsset(), 5);
    }
}
