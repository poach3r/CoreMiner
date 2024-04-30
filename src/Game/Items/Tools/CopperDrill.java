package Game.Items.Tools;

import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;
import Game.Items.Resources.Copper;
import Game.Items.Resources.Wood;
import Library.Items.GenericTool;

public class CopperDrill extends GenericTool {
    public CopperDrill() {
        super("Copper Drill", TextureIndex.copperDrill.getAsset(), 55, 15, 6);
        addRequiredItem(ItemIndex.copper, 10);
        addRequiredItem(ItemIndex.wood, 2);
    }
}
