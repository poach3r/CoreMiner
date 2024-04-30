package Game.Items.Tools;

import Game.Graphics.TextureIndex;
import Library.Items.GenericTool;

import java.io.File;

public class StonePickaxe extends GenericTool {
    public StonePickaxe() {
        super("Stone Pickaxe", TextureIndex.stonePickaxe.getAsset(), 60, 0, 2);
    }
}
