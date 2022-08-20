package github.regularrabbit05.noteblockitemsongloader.api;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.io.ByteArrayInputStream;

import static github.regularrabbit05.noteblockitemsongloader.api.Constants.PATH;

@SuppressWarnings("unused")
public class SongLoader {
    private final byte[] songBytes;

    public SongLoader(byte[] data) {
        this.songBytes = data;
    }

    public SongLoader(ItemStack item) {
        NBTItem iParser = new NBTItem(item);
        if (!iParser.hasKey(PATH)) {
            this.songBytes = null;
            return;
        }
        this.songBytes = iParser.getByteArray(PATH);
    }

    public Song parseSong() {
        if (songBytes == null) return null;
        return NBSDecoder.parse(new ByteArrayInputStream(this.songBytes));
    }
}
