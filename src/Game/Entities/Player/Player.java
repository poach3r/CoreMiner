package Game.Entities.Player;

import Game.Graphics.TextureIndex;
import Game.Items.Tools.StonePickaxe;
import Library.Items.GenericTool;
import Library.Items.GenericTrinket;

import java.io.File;
import java.util.ArrayList;

/** This is our player character, they are a generic entity with the special property of invincibility frames as they are the only entity which can
 * directly take damage from other entities.
 * @author poacher
 */
public class Player extends Library.Entities.GenericEntity {

    // time before the player can be hit again, this helps prevents unfair damage
    // https://www.giantbomb.com/invincibility-frames/3015-9598/
    private int iTime;
    private int timeSinceHit;

    private GenericTool tool;
    private int timeSinceLastMine;
    private ArrayList<GenericTrinket> trinkets;

    public Player() {
        super(0, "player", TextureIndex.player, 0, 0, 10, 1, 32);
        iTime = 10;
        timeSinceHit = 10;
        timeSinceLastMine = 60;
        tool = new StonePickaxe();
        trinkets = new ArrayList<>();
    }

    public int getITime() {
        return iTime;
    }

    public void setITime(int iTime) {
        this.iTime = iTime;
    }

    public int getTimeSinceHit() {
        return timeSinceHit;
    }

    public void setTimeSinceHit(int timeSinceHit) {
        this.timeSinceHit = timeSinceHit;
    }

    public GenericTool getTool() {
        return tool;
    }

    public void setTool(GenericTool tool) {
        this.tool = tool;
    }

    public int getTimeSinceLastMine() {
        return timeSinceLastMine;
    }

    public void setTimeSinceLastMine(int timeSinceLastMine) {
        this.timeSinceLastMine = timeSinceLastMine;
    }

    public void addTrinket(GenericTrinket trinket) {
        trinket.getPickupEffect().accept(this);
        trinkets.add(trinket);
    }

    public ArrayList<GenericTrinket> getTrinkets() {
        return trinkets;
    }
}
