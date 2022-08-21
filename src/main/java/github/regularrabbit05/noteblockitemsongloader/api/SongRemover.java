package github.regularrabbit05.noteblockitemsongloader.api;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static github.regularrabbit05.noteblockitemsongloader.api.Constants.PATH;

@SuppressWarnings("unused")
public final class SongRemover {
    private SongRemover() {}
    public static ItemStack removeSong(ItemStack stack) {
        if (stack == null || stack.getType() == null || stack.getType() == Material.AIR) return null;
        NBTItem item = new NBTItem(stack);
        if (!item.hasKey(PATH)) return stack;
        item.removeKey(PATH);
        return item.getItem();
    }
}
