package github.regularrabbit05.noteblockitemsongloader.commands;

import github.regularrabbit05.noteblockitemsongloader.NoteBlockItemSongLoader;
import github.regularrabbit05.noteblockitemsongloader.api.SongRemover;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SongRemoverCommand implements CommandExecutor {
    private final NoteBlockItemSongLoader plugin;

    public SongRemoverCommand(NoteBlockItemSongLoader noteBlockItemSongLoader) {
        this.plugin = noteBlockItemSongLoader;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (plugin.isApiMode()) return false;
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        //Get the item in the main hand and check if it's valid
        ItemStack item = player.getInventory().getItemInMainHand();

        //Remove the song, the item will become null if it was invalid
        item = SongRemover.removeSong(item);

        //Return if the item became null
        if (item == null || item.getType() == null || item.getType() == Material.AIR) {
            sender.sendMessage("The item you are holding is invalid and can't contain any song.");
            return true;
        }

        //Set the new item as main hand item
        player.getInventory().setItemInMainHand(item);
        sender.sendMessage("Item's Song removed successfully!");

        return true;
    }
}
