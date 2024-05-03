package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class Copper extends Library.Map.Tile {
    public Copper() {
        super(9, "copper", TextureIndex.copper, false, false, SoundIndex.stoneStep);
        addResource(ItemIndex.copper, 1);
    }
}
