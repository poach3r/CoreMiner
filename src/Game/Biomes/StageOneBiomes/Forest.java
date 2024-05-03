package Game.Biomes.StageOneBiomes;

import Game.Biomes.GenericBiome;
import Game.Tiles.TileIndex;

public class Forest extends GenericBiome {
    public Forest() {
        addOre(0, TileIndex.log, TileIndex.grass1);
        setOreRarity(12);
        putTile(-10, TileIndex.dirt);
        putTile(-9, TileIndex.dirt);
        putTile(-8, TileIndex.grass1);
        putTile(-7, TileIndex.grass1);
        putTile(-6, TileIndex.grass1);
        putTile(-5, TileIndex.grass1);
        putTile(-4, TileIndex.grass1);
        putTile(-3, TileIndex.grass1);
        putTile(-2, TileIndex.grass1);
        putTile(-1, TileIndex.grass2);
        putTile(0, TileIndex.grass2);
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
    }
}
