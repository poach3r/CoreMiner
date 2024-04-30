package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class Granite extends Library.Map.Tile {
    public Granite() {
        super("granite", TextureIndex.granite.getAsset(), false, true, SoundIndex.stoneStep);
        addResource(ItemIndex.stone, 1);
    }
}
