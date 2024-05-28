package Game.Items.Trinkets;

import Game.Entities.Player.Player;
import Game.Items.GenericItem;
import Game.Items.ItemType;
import Library.Graphics.Texture;

import java.util.HashMap;
import java.util.function.Consumer;

public class GenericTrinket extends GenericItem {
    private final HashMap<GenericItem, Integer> requiredItems;
    private Consumer<Player> pickupEffect;
    private Consumer<Player> continualEffect;

    public GenericTrinket(int id, String name, Texture texture, int value) {
        super(id, name, texture, value, ItemType.TRINKET);
        requiredItems = new HashMap<>();
        pickupEffect = null;
        continualEffect = null;
    }

    public void setPickupEffect(Consumer<Player> pickupEffect) {
        this.pickupEffect = pickupEffect;
    }

    public Consumer<Player> getPickupEffect() {
        return pickupEffect;
    }

    public void setContinualEffect(Consumer<Player> continualEffect) {
        this.continualEffect = continualEffect;
    }

    public Consumer<Player> getContinualEffect() {
        return continualEffect;
    }
}
