package Game.Biomes.StageThreeBiomes;

import Game.Biomes.GenericBiome;
import Game.Entities.Hostile.MushyZombie;
import Game.Entities.Hostile.Zombie;
import Game.Tiles.TileIndex;

import java.util.Random;

public class ChargedCave extends GenericBiome {
    public ChargedCave() {
        setTile(1, TileIndex.thunderStone);
        setTile(2, TileIndex.thunderStone);
        setTile(3, TileIndex.thunderStone);
        setTile(4, TileIndex.thunderStone);
        setTile(5, TileIndex.solidLightning);
        setTile(6, TileIndex.thunderStone);
        setTile(7, TileIndex.solidLightning);
        setTile(8, TileIndex.solidLightning);
        setTile(9, TileIndex.solidLightning);

        setExtraGeneration(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();

                if(r.nextBoolean())
                    Game.Main.summonEntity(new Zombie(r.nextInt(16), r.nextInt(16)), 2);
            }
        });
    }
}
