package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class Log extends Library.Map.Tile {
    public Log() {
        super(4, "log", TextureIndex.log, true, SoundIndex.grassStep); //stepSound can be null since you cant step on it
        addResource(ItemIndex.wood, 1);
    }
}
