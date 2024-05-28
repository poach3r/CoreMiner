package Game.Biomes.StageOneBiomes;

import Game.Biomes.GenericBiome;
import Game.Entities.Passive.Sheep;
import Game.Tiles.*;

import java.util.Random;

public class DirtyPlains extends GenericBiome {
    public DirtyPlains() {
        putTile(-10, TileIndex.dirt);
        putTile(-9, TileIndex.dirt);
        putTile(-8, TileIndex.dirt);
        putTile(-7, TileIndex.dirt);
        putTile(-6, TileIndex.dirt);
        putTile(-5, TileIndex.dirt);
        putTile(-4, TileIndex.dirt);
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
                if(r.nextInt(4) == 1) {
                    addEntity(new Sheep(r.nextInt(960), r.nextInt(960)));
                }
            }
        });
    }
}
