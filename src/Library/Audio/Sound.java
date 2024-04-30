package Library.Audio;

import java.io.File;
import java.io.Serializable;

/** The Sound class represents audio files that are to be played via the AudioPlayer class.
 * @author poacher
 */
public class Sound implements Serializable {
    private final File file;
    private final String name;

    public Sound(final File file, final String name) {
        this.file = file;
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }
}
