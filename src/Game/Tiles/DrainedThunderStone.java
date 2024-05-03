package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class DrainedThunderStone extends Library.Map.Tile {
    public DrainedThunderStone() {
        super(13, "drainedThunderStone", TextureIndex.drainedThunderStone, false, true, SoundIndex.stoneStep);
        addResource(ItemIndex.stone, 1);
    }
}
