package Game.Graphics;

import Library.Graphics.Texture;

import java.io.File;

/** This is a global class that houses all the textures used in the game in order to prevent unnecessary loading of files.
 * @author poacher
 */
public class TextureIndex {
    public static Texture dirt = new Texture(new File("assets/images/dirt.png"), "dirt");;
    public static Texture grass1 = new Texture(new File("assets/images/grass1.png"), "grass1");
    public static Texture grass2 = new Texture(new File("assets/images/grass2.png"), "grass2");
    public static Texture log = new Texture( new File("assets/images/log.png"), "log");
    public static Texture stone = new Texture(new File("assets/images/stone.png"), "stone");
    public static Texture andesite = new Texture(new File("assets/images/andesite.png"), "andesite");
    public static Texture granite = new Texture(new File("assets/images/granite.png"), "granite");
    public static Texture copper = new Texture(new File("assets/images/copper.png"), "copper");
    public static Texture water = new Texture(new File("assets/images/water.png"), "water");
    public static Texture stoneShroom = new Texture(new File("assets/images/stoneShroom.png"), "stoneShroom");
    public static Texture iron = new Texture(new File("assets/images/iron.png"), "iron");
    public static Texture thunderStone = new Texture(new File("assets/images/thunderStone.png"), "thunderStone");
    public static Texture solidLightning = new Texture(new File("assets/images/solidLightning.png"), "solidLightning");
    public static Texture drainedThunderStone = new Texture(new File("assets/images/drainedThunderStone.png"), "drainedThunderStone");
    public static Texture Void = new Texture(new File("assets/images/void.png"), "void");
    public static Texture player = new Texture(new File("assets/images/sheep.png"), "player");
    public static Texture sheep = new Texture(new File("assets/images/sheep.png"), "sheep");
    public static Texture zombie = new Texture(new File("assets/images/zombie.png"), "zombie");
    public static Texture mushyZombie = new Texture(new File("assets/images/mushyZombie.png"), "mushyZombie");

    public static Texture stonePickaxe = new Texture(new File("assets/images/stonePickaxe.png"), "stonePickaxe");
    public static Texture copperDrill = new Texture(new File("assets/images/copperDrill.png"), "copperDrill");
    public static Texture ironDrill = new Texture(new File("assets/images/ironDrill.png"), "ironDrill");

    public static Texture gaiaCharm = new Texture(new File("assets/images/gaiaCharm.png"), "gaiaCharm");
}
