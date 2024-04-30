package Game.Items.Resources;

import Game.Graphics.TextureIndex;
import Library.Items.GenericItem;

import java.io.File;

public class Wood extends GenericItem {
    public Wood() {
        super("wood", TextureIndex.log.getAsset(), 1);
    }
}
