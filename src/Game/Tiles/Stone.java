package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class Stone extends Library.Map.Tile {
    public Stone() {
        super(6, "stone", TextureIndex.stone, false, true, SoundIndex.stoneStep);
        addResource(ItemIndex.stone, 1);
    }
}
