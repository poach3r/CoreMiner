package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class Grass1 extends Library.Map.Tile {
    public Grass1() {
        super(2, "grass1", TextureIndex.grass1, false, SoundIndex.grassStep);
        addResource(ItemIndex.dirt, 1);
    }
}
