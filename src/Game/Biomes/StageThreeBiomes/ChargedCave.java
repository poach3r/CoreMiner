package Game.Biomes.StageThreeBiomes;

import Game.Biomes.GenericBiome;
import Game.Entities.Hostile.Zombie;
import Game.Tiles.TileIndex;

import java.util.Random;

public class ChargedCave extends GenericBiome {
    public ChargedCave() {
        setOreRarity(12);
        addOre(0, TileIndex.solidLightning, TileIndex.thunderStone);
        putTile(-10, TileIndex.stone);
        putTile(-9, TileIndex.stone);
        putTile(-8, TileIndex.drainedThunderStone);
        putTile(-7, TileIndex.drainedThunderStone);
        putTile(-6, TileIndex.drainedThunderStone);
        putTile(-5, TileIndex.drainedThunderStone);
        putTile(-4, TileIndex.drainedThunderStone);
        putTile(-3, TileIndex.drainedThunderStone);
        putTile(-2, TileIndex.thunderStone);
        putTile(-1, TileIndex.thunderStone);
        putTile(0, TileIndex.thunderStone);
        putTile(1, TileIndex.thunderStone);
        putTile(2, TileIndex.thunderStone);
        putTile(3, TileIndex.drainedThunderStone);
        putTile(4, TileIndex.drainedThunderStone);
        putTile(5, TileIndex.drainedThunderStone);
        putTile(6, TileIndex.drainedThunderStone);
        putTile(7, TileIndex.drainedThunderStone);
        putTile(8, TileIndex.stone);
        putTile(9, TileIndex.stone);
        putTile(10, TileIndex.stone);

        setExtraGeneration(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();

                if(r.nextBoolean())
                    Game.Main.summonEntity(new Zombie(r.nextInt(960), r.nextInt(960)), 2);
            }
        });
    }
}
