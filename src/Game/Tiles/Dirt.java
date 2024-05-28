package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;
import Library.Entities.GenericEntity;

public class Dirt extends Library.Map.Tile {
    public Dirt() {
        super(1, "dirt", TextureIndex.dirt, false, SoundIndex.grassStep);
        addResource(ItemIndex.dirt, 1);
    }
}
