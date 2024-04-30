package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;
import Game.Items.Resources.Wood;

public class Log extends Library.Map.Tile {
    public Log() {
        super("log", TextureIndex.log.getAsset(), true, false, SoundIndex.grassStep); //stepSound can be null since you cant step on it
        addResource(ItemIndex.wood, 1);
    }
}
