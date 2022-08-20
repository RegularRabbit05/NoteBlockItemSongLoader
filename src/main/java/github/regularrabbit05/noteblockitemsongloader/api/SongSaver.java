package github.regularrabbit05.noteblockitemsongloader.api;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.apache.commons.io.IOUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.net.URL;

import static github.regularrabbit05.noteblockitemsongloader.api.Constants.PATH;

@SuppressWarnings("unused")
public class SongSaver {
    private static byte[] copyURLToByteArray(final String urlStr) throws IOException, NullPointerException {
        return IOUtils.toByteArray(new URL(urlStr));
    }

    public static ItemStack save(ItemStack currentItem, String url) throws IOException, NullPointerException {
        return save(currentItem, url, false);
    }

    public static ItemStack save(ItemStack currentItem, String url, boolean override) throws IOException, NullPointerException {
        if (currentItem == null || currentItem.getType() == null || currentItem.getType() == Material.AIR) return null;
        NBTItem nbtWriter = new NBTItem(currentItem);
        if (!override && nbtWriter.hasKey(PATH)) return null; else if (nbtWriter.hasKey(PATH)) nbtWriter.removeKey(PATH);

        byte[] data = copyURLToByteArray(url);
        if (data == null) throw new NullPointerException("Data downloaded is null.");

        nbtWriter.setByteArray(PATH, data);

        return nbtWriter.getItem();
    }
}
