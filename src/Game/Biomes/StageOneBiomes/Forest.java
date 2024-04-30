package Game.Biomes.StageOneBiomes;

import Game.Biomes.GenericBiome;
import Game.Tiles.Grass1;
import Game.Tiles.Grass2;
import Game.Tiles.Log;
import Game.Tiles.TileIndex;
import Library.Map.Tile;

import java.util.HashMap;
public class Forest extends GenericBiome {
    public Forest() {
        setTile(1, TileIndex.grass1);
        setTile(2, TileIndex.grass2);
        setTile(3, TileIndex.grass1);
        setTile(4, TileIndex.grass2);
        setTile(5, TileIndex.log);
        setTile(6, TileIndex.grass2);
        setTile(7, TileIndex.log);
        setTile(8, TileIndex.log);
        setTile(9, TileIndex.log);
    }
}
