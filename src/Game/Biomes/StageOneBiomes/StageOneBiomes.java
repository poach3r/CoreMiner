package Game.Biomes.StageOneBiomes;

import Game.Biomes.StageTwoBiomes.StonyCave;
import Library.Map.Map;

import java.util.Random;

public class StageOneBiomes {
    public StageOneBiomes() {
    }

    public Map generate() {
        switch(new Random().nextInt(4)) {
            case 0 -> {return new Forest().generate();}
            case 1 -> {return new DirtyPlains().generate();}
            case 2 -> {return new Plains().generate();}
            case 3 -> {return new StonyCave().generate();}
        }

        return new Map();
    }
}