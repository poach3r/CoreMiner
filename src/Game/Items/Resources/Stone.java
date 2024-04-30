package Game.Items.Resources;

import Game.Graphics.TextureIndex;
import Library.Items.GenericItem;

import java.io.File;

public class Stone extends GenericItem {
    public Stone() {
        super("stone", TextureIndex.stone.getAsset(), 2);
    }
}
