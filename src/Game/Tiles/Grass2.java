package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class Grass2 extends Library.Map.Tile {
    public Grass2() {
        super(3, "grass2", TextureIndex.grass2, false, SoundIndex.grassStep);
        addResource(ItemIndex.dirt, 1);
    }
}
