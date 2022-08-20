package github.regularrabbit05.noteblockitemsongloader.commands;

import github.regularrabbit05.noteblockitemsongloader.NoteBlockItemSongLoader;
import github.regularrabbit05.noteblockitemsongloader.api.SongSaver;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class SongSaverCommand implements CommandExecutor {
    private final NoteBlockItemSongLoader plugin;
    public SongSaverCommand(NoteBlockItemSongLoader noteBlockItemSongLoader) {
        this.plugin = noteBlockItemSongLoader;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (plugin.isApiMode()) return false;
        if (!(sender instanceof Player)) return false;

        if (args.length < 1) {
            sender.sendMessage("Please supply an URL after the command name!");
            return false;
        }

        Player player = (Player) sender;

        //Check if the item is valid
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType() == null || item.getType() == Material.AIR) {
            sender.sendMessage("Please hold a valid item!");
            return true;
        }

        //Check if it can overwrite the current song
        boolean overwrite = false;
        if (args.length > 1) {
            if (args[1].equalsIgnoreCase("true")) overwrite = true;
        }

        //Check if the url was valid
        try {
            item = SongSaver.save(item, args[0], overwrite); //Omitting overwrite means "false"
        } catch (IOException | NullPointerException e) {
            sender.sendMessage("The URL provided was invalid or the server timed out! (" + e.getMessage() + ")");
            return true;
        }

        //Check if you can overwrite the item and if the file was valid
        if (item == null) {
            sender.sendMessage("Unable to save the song to the item...");
            return true;
        }

        //Save the file
        player.getInventory().setItemInMainHand(item);
        sender.sendMessage("Song saved successfully!");

        return true;
    }
}
