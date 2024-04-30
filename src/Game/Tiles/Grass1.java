package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;
import Game.Items.Resources.Dirt;

public class Grass1 extends Library.Map.Tile {
    public Grass1() {
        super("grass", TextureIndex.grass1.getAsset(), false, true, SoundIndex.grassStep);
        addResource(ItemIndex.dirt, 1);
    }
}
