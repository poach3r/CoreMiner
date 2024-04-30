package Game.Items.Trinkets;

import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;
import Library.Items.GenericTrinket;

public class GaiaCharm extends GenericTrinket {
    public GaiaCharm() {
        super("Gaia Charm", TextureIndex.gaiaCharm.getAsset(), 20);
        setPickupEffect(player -> {
            player.setHp(player.getHp() + 1);
        });
        addRequiredItem(ItemIndex.dirt, 4);
        addRequiredItem(ItemIndex.copper, 1);
        addRequiredItem(ItemIndex.mushroom, 1);
    }
}
