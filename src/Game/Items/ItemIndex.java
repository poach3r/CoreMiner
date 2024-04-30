package Game.Items;

import Game.Items.Resources.*;
import Game.Items.Tools.CopperDrill;
import Game.Items.Tools.IronDrill;
import Game.Items.Tools.StonePickaxe;
import Game.Items.Trinkets.GaiaCharm;
import Library.Items.GenericItem;
import Library.Items.GenericTool;
import Library.Items.GenericTrinket;

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

    public static StonePickaxe stonePickaxe = new StonePickaxe();
    public static CopperDrill copperDrill = new CopperDrill();
    public static IronDrill ironDrill = new IronDrill();
    public static List<GenericTool> tools = new ArrayList<>() {{
       add(stonePickaxe);
       add(copperDrill);
       add(ironDrill);
    }};

    public static List<GenericTool> craftableTools = new ArrayList<>() {{
        add(copperDrill);
        add(ironDrill);
    }};

    public static GaiaCharm gaiaCharm = new GaiaCharm();
    public static List<GenericTrinket> trinkets = new ArrayList<>() {{
       add(gaiaCharm);
    }};

    public static List<GenericTrinket> craftableTrinkets = new ArrayList<>() {{
       add(gaiaCharm);
    }};
}
