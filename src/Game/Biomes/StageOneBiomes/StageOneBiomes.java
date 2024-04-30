package Game.Biomes.StageOneBiomes;

import Library.Map.Map;

import java.util.Random;

public class StageOneBiomes {
    public StageOneBiomes() {
    }

    public Map generate() {
        switch(new Random().nextInt(3)) {
            case 0 -> {return new Forest().generate();}
            case 1 -> {return new DirtyPlains().generate();}
            case 2 -> {return new Plains().generate();}
        }
        return new Map();
    }
}