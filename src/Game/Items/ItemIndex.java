package Game.Items;

import Game.Items.Resources.*;
import Game.Items.Tools.EmeraldWand;
import Game.Items.Tools.RubyWand;
import Game.Items.Trinkets.GaiaCharm;
import Game.Items.Trinkets.HermesBoots;
import Game.Items.Tools.GenericTool;
import Game.Items.Trinkets.GenericTrinket;

import java.util.ArrayList;
import java.util.List;

public class ItemIndex {
    public static Copper copper = new Copper();
    public static Dirt dirt = new Dirt();
    public static Stone stone = new Stone();
    public static Wood wood = new Wood();
    public static Mushroom mushroom = new Mushroom();
    public static Iron iron = new Iron();
    public static SolidLightning solidLightning = new SolidLightning();
    public static List<GenericItem> resources = new ArrayList<>() {{
        add(wood);
        add(dirt);
        add(stone);
        add(wood);
        add(mushroom);
        add(iron);
        add(solidLightning);
    }};

    public static GenericTool rubyWand = new RubyWand();
    public static GenericTool emeraldWand = new EmeraldWand();
    public static List<GenericTool> tools = new ArrayList<>() {{
        add(rubyWand);
        add(emeraldWand);
    }};

    public static List<GenericTool> craftableTools = new ArrayList<>() {{
    }};

    public static GaiaCharm gaiaCharm = new GaiaCharm();
    public static GenericTrinket hermesBoots = new HermesBoots();
    public static List<GenericTrinket> trinkets = new ArrayList<>() {{
       add(gaiaCharm);
       add(hermesBoots);
    }};

    public static List<GenericTrinket> craftableTrinkets = new ArrayList<>() {{
       add(gaiaCharm);
       add(hermesBoots);
    }};
}
