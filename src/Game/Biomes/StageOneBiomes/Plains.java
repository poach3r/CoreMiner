package Game.Biomes.StageOneBiomes;

import Game.Biomes.GenericBiome;
import Game.Entities.Passive.Sheep;
import Game.Tiles.*;

import java.util.Random;

public class Plains extends GenericBiome {
    public Plains() {
        putTile(-10, TileIndex.dirt);
        putTile(-9, TileIndex.dirt);
        putTile(-8, TileIndex.grass1);
        putTile(-7, TileIndex.grass1);
        putTile(-6, TileIndex.grass1);
        putTile(-5, TileIndex.grass1);
        putTile(-4, TileIndex.grass1);
        putTile(-3, TileIndex.grass1);
        putTile(-2, TileIndex.grass1);
        putTile(-1, TileIndex.grass1);
        putTile(0, TileIndex.grass1);
        putTile(1, TileIndex.grass2);
        putTile(2, TileIndex.grass2);
        putTile(3, TileIndex.grass2);
        putTile(4, TileIndex.grass2);
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
                addEntity(new Sheep(r.nextInt(960), r.nextInt(960)));
            }
        });
    }
}
