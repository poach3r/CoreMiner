package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class Andesite extends Library.Map.Tile {
    public Andesite() {
        super(7, "andesite", TextureIndex.andesite, false, true, SoundIndex.stoneStep);
        addResource(ItemIndex.stone, 1);
    }
}
