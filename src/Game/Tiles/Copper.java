package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

import java.util.Random;

public class Copper extends Library.Map.Tile {
    public Copper() {
        super("copper", TextureIndex.copper.getAsset(), false, false, SoundIndex.stoneStep);
        addResource(ItemIndex.copper, 1);
    }
}
