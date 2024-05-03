package Game.Logic;

import Game.Entities.Player.Player;
import Game.Items.ItemIndex;
import Game.Items.ItemType;
import Library.Items.GenericItem;
import Library.Items.GenericTool;
import Library.Items.GenericTrinket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Crafter {
    private Player player;
    private ArrayList<GenericTool> craftableTools;

    public Crafter(Player player) {
        this.player = player;

        craftableTools = new ArrayList<>();
        craftableTools.add(ItemIndex.copperDrill);
    }

    public boolean attemptToCraft(GenericItem item) {
        AtomicBoolean crafted = new AtomicBoolean(true);

        // loop through each item in the recipe and compare it to the players inventory
        item.getRecipe().forEach((key, value) -> {
            if(!crafted.get())
                return;

            if(!player.getInventory().containsKey(key)) {
                crafted.set(false);
                return;
            }

            if(player.getInventory().get(key) < value) {
                crafted.set(false);
            }
        });

        if(crafted.get()) {
            item.getRecipe().forEach((key, value) -> {
                player.removeItem(key, value);
            });

            if(item.getItemType().equals(ItemType.RESOURCE))
                player.addItem(item);

            else if(item.getItemType().equals(ItemType.TOOL))
                player.setTool((GenericTool) item);

            else if(item.getItemType().equals(ItemType.TRINKET))
                player.addTrinket((GenericTrinket) item);

            else
                System.err.println("Could not identify the type of item " + item);

            return true;
        }

        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
