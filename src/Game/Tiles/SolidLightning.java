package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class SolidLightning extends Library.Map.Tile {
    public SolidLightning() {
        super(14, "solidLightning", TextureIndex.solidLightning, false, SoundIndex.stoneStep, 18);
        addResource(ItemIndex.solidLightning, 1);
    }
}
