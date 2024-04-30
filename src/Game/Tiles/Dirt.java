package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class Dirt extends Library.Map.Tile {
    public Dirt() {
        super("dirt", TextureIndex.dirt.getAsset(), false, true, SoundIndex.grassStep);
        addResource(ItemIndex.dirt, 1);
    }
}