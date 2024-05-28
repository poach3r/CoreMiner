package Game.Biomes.StageTwoBiomes;

import Game.Biomes.GenericBiome;
import Game.Entities.Hostile.Zombie;
import Game.Structures.GraniteClump;
import Game.Structures.Mineshaft;
import Game.Tiles.*;

import java.util.Random;

public class StonyCave extends GenericBiome {
    public StonyCave() {
        setOreRarity(12);
        addOre(0, TileIndex.copper, TileIndex.stone);
        addOre(1, TileIndex.iron, TileIndex.stone);
        putTile(-10, TileIndex.stone);
        putTile(-9, TileIndex.stone);
        putTile(-8, TileIndex.stone);
        putTile(-7, TileIndex.stone);
        putTile(-6, TileIndex.stone);
        putTile(-5, TileIndex.stone);
        putTile(-4, TileIndex.stone);
        putTile(-3, TileIndex.stone);
        putTile(-2, TileIndex.dirt);
        putTile(-1, TileIndex.dirt);
        putTile(0, TileIndex.stone);
        putTile(1, TileIndex.stone);
        putTile(2, TileIndex.stone);
        putTile(3, TileIndex.stone);
        putTile(4, TileIndex.andesite);
        putTile(5, TileIndex.andesite);
        putTile(6, TileIndex.andesite);
        putTile(7, TileIndex.andesite);
        putTile(8, TileIndex.andesite);
        putTile(9, TileIndex.andesite);
        putTile(10, TileIndex.andesite);

        setExtraGeneration(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();

                if(r.nextInt(10) == 1)
                    placeStructure(new GraniteClump(), r.nextInt(16), r.nextInt(16));

                if(r.nextInt(10) == 1) {
                    placeStructure(new Mineshaft(), r.nextInt(960), r.nextInt(960));
                    addEntity(new Zombie(r.nextInt(960), r.nextInt(960)));
                }

                if(r.nextBoolean())
                    addEntity(new Zombie(r.nextInt(960), r.nextInt(960)));

                boolean torchPlaced = false;
                if(r.nextBoolean()) {
                    while(!torchPlaced) {
                        int x = r.nextInt(15);
                        int y = r.nextInt(15);
                        if(getTiles()[x][y].getId() == 6) {
                            setTile(TileIndex.torchStone, x, y);
                            torchPlaced = true;
                        }
                    }
                }
            }
        });
    }
}

