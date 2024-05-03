package Game.Items.Tools;

import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;
import Library.Items.GenericTool;

public class CopperDrill extends GenericTool {
    public CopperDrill() {
        super(8, "Copper Drill", TextureIndex.copperDrill, 28, 15, 6);
        addRequiredItem(ItemIndex.copper, 10);
        addRequiredItem(ItemIndex.wood, 2);
    }
}
