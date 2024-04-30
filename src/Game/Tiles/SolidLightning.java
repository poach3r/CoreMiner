package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class SolidLightning extends Library.Map.Tile {
    public SolidLightning() {
        super("solidLightning", TextureIndex.solidLightning.getAsset(), false, false, SoundIndex.stoneStep);
        addResource(ItemIndex.solidLightning, 1);
    }
}
