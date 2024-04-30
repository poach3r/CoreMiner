package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class ThunderStone extends Library.Map.Tile {
    public ThunderStone() {
        super("thunderStone", TextureIndex.thunderStone.getAsset(), false, true, SoundIndex.stoneStep);
        addResource(ItemIndex.stone, 1);
    }
}
