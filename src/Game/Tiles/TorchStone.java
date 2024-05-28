package Game.Tiles;

import Game.Audio.SoundIndex;
import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;

public class TorchStone extends Library.Map.Tile {
    public TorchStone() {
        super(6, "stone", TextureIndex.torchStone, false, SoundIndex.stoneStep, 12);
        addResource(ItemIndex.stone, 1);
    }
}
