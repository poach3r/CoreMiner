package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class Iron extends Library.Map.Tile {
    public Iron() {
        super(10, "iron", TextureIndex.iron, false, false, SoundIndex.stoneStep);
        addResource(ItemIndex.iron, 1);
    }
}
