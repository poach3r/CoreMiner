package Game.Items.Resources;

import Game.Graphics.TextureIndex;
import Library.Items.GenericItem;

import java.io.File;

public class Dirt extends GenericItem {
    public Dirt() {
        super("dirt", TextureIndex.dirt.getAsset(), 1);
    }
}
