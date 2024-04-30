package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;
import Game.Items.Resources.Dirt;

public class Grass2 extends Library.Map.Tile {
    public Grass2() {
        super("grass", TextureIndex.grass2.getAsset(), false, true, SoundIndex.grassStep);
        addResource(ItemIndex.dirt, 1);
    }
}
