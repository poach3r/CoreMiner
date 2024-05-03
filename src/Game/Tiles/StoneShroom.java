package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;
import Library.Map.Tile;

public class StoneShroom extends Tile {
    public StoneShroom() {
        super(11, "stoneShroom", TextureIndex.stoneShroom, false, false, SoundIndex.grassStep);
        addResource(ItemIndex.stone, 1);
        addResource(ItemIndex.mushroom, 1);
    }
}
