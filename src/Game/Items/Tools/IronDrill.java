package Game.Items.Tools;

import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;
import Library.Items.GenericTool;

public class IronDrill extends GenericTool {
    public IronDrill() {
        super(9, "Iron Drill", TextureIndex.ironDrill, 25, 15, 2);
        addRequiredItem(ItemIndex.iron, 10);
        addRequiredItem(ItemIndex.wood, 2);
    }
}
