package Game.Biomes.StageThreeBiomes;

import Game.Biomes.GenericBiome;
import Game.Entities.Hostile.MushyZombie;
import Game.Entities.Hostile.Zombie;
import Game.Tiles.TileIndex;

import java.util.Random;

public class UndergroundForest extends GenericBiome {
    public UndergroundForest() {
        setOreRarity(8);
        addOre(0, TileIndex.stoneShroom, TileIndex.stone);
        putTile(-10, TileIndex.dirt);
        putTile(-9, TileIndex.dirt);
        putTile(-8, TileIndex.grass1);
        putTile(-7, TileIndex.grass1);
        putTile(-6, TileIndex.grass1);
        putTile(-5, TileIndex.stone);
        putTile(-4, TileIndex.stone);
        putTile(-3, TileIndex.stone);
        putTile(-2, TileIndex.stone);
        putTile(-1, TileIndex.stone);
        putTile(0, TileIndex.stone);
        putTile(1, TileIndex.stone);
        putTile(2, TileIndex.stone);
        putTile(3, TileIndex.stone);
        putTile(4, TileIndex.stone);
        putTile(5, TileIndex.grass2);
        putTile(6, TileIndex.grass2);
        putTile(7, TileIndex.grass2);
        putTile(8, TileIndex.grass2);
        putTile(9, TileIndex.dirt);
        putTile(10, TileIndex.dirt);

        setExtraGeneration(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();

                Game.Main.summonEntity(new MushyZombie(r.nextInt(960), r.nextInt(960)), 2);

                if(r.nextBoolean())
                    Game.Main.summonEntity(new MushyZombie(r.nextInt(960), r.nextInt(960)), 2);

                if(r.nextBoolean())
                    Game.Main.summonEntity(new Zombie(r.nextInt(960), r.nextInt(960)), 2);
            }
        });
    }
}
