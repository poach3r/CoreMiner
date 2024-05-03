package Game.Items.Trinkets;

import Game.Graphics.TextureIndex;
import Game.Items.ItemIndex;
import Library.Items.GenericTrinket;

public class HermesBoots extends GenericTrinket {
    public HermesBoots() {
        super(11, "Hermes Boots", TextureIndex.gaiaCharm, 20);
        setPickupEffect(player -> {
            if(player.getSpeed() > 8)
                player.setSpeed(player.getSpeed() - 8);
        });
        addRequiredItem(ItemIndex.iron, 4);
        addRequiredItem(ItemIndex.solidLightning, 2);
    }
}
