package Game.Items.Resources;

import Game.Graphics.TextureIndex;
import Library.Items.GenericItem;

public class SolidLightning extends GenericItem {
    public SolidLightning() {
        super("solidLightning", TextureIndex.solidLightning.getAsset(), 10);
    }
}
