package Game.Biomes.StageTwoBiomes;

import Game.Biomes.GenericBiome;
import Game.Entities.Hostile.Zombie;
import Game.Structures.Mineshaft;
import Game.Tiles.*;

import java.util.Random;

public class GraniteCave extends GenericBiome {
    public GraniteCave() {
        setOreRarity(10);
        addOre(0, TileIndex.copper, TileIndex.stone);
        putTile(-10, TileIndex.granite);
        putTile(-9, TileIndex.granite);
        putTile(-8, TileIndex.granite);
        putTile(-7, TileIndex.granite);
        putTile(-6, TileIndex.granite);
        putTile(-5, TileIndex.granite);
        putTile(-4, TileIndex.granite);
        putTile(-3, TileIndex.granite);
        putTile(-2, TileIndex.granite);
        putTile(-1, TileIndex.granite);
        putTile(0, TileIndex.stone);
        putTile(1, TileIndex.stone);
        putTile(2, TileIndex.stone);
        putTile(3, TileIndex.stone);
        putTile(4, TileIndex.stone);
        putTile(5, TileIndex.stone);
        putTile(6, TileIndex.andesite);
        putTile(7, TileIndex.andesite);
        putTile(8, TileIndex.andesite);
        putTile(9, TileIndex.andesite);
        putTile(10, TileIndex.andesite);

        setExtraGeneration(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();

                if(r.nextInt(10) == 1) {
                    placeStructure(new Mineshaft(), r.nextInt(16), r.nextInt(16));
                    Game.Main.summonEntity(new Zombie(r.nextInt(960), r.nextInt(960)), 2);
                }

                if(r.nextBoolean())
                    Game.Main.summonEntity(new Zombie(r.nextInt(960), r.nextInt(960)), 2);
            }
        });
    }
}
