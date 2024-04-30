package Game.Items.Resources;

import Game.Graphics.TextureIndex;
import Library.Items.GenericItem;

public class Iron extends GenericItem {
    public Iron() {
        super("iron", TextureIndex.iron.getAsset(), 5);
    }
}
