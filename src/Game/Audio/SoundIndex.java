package Game.Audio;

import Game.Audio.Sounds.Dig1;
import Game.Audio.Sounds.DigFail;
import Game.Audio.Sounds.GrassStep;
import Game.Audio.Sounds.StoneStep;
import Library.Audio.Sound;

public class SoundIndex {
    public static final Sound dig1 = new Dig1();
    public static final Sound digFail = new DigFail();
    public static final Sound stoneStep = new StoneStep();
    public static final Sound grassStep = new GrassStep();
}
