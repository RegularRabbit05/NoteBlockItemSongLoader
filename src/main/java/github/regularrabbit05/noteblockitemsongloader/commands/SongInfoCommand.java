package github.regularrabbit05.noteblockitemsongloader.commands;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import github.regularrabbit05.noteblockitemsongloader.NoteBlockItemSongLoader;
import github.regularrabbit05.noteblockitemsongloader.api.SongLoader;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SongInfoCommand implements CommandExecutor {
    private final NoteBlockItemSongLoader plugin;

    public SongInfoCommand(NoteBlockItemSongLoader noteBlockItemSongLoader) {
        this.plugin = noteBlockItemSongLoader;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (plugin.isApiMode()) return false;
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        //Check if the item is valid
        ItemStack item;
        item = player.getItemInHand(); //Support for < 1.9
        if (item == null || item.getType() == Material.AIR) {
            sender.sendMessage("You must be holding a valid item with a song saved in it!");
            return true;
        }

        //Check if there is a song and load it
        SongLoader songPlayer = new SongLoader(item);
        Song song = songPlayer.parseSong();
        if (song == null) {
            sender.sendMessage("The item you are holding does not have a valid song stored inside.");
            return true;
        }

        //Print out the song data
        String out = "--SONG DATA--\n";
        out+="Title: " + ((song.getTitle() == null) ? "---" : song.getTitle()) + "\n";
        out+="Author: " + ((song.getAuthor() == null) ? "---" : song.getAuthor()) + "\n";
        out+="Original Author: " + ((song.getOriginalAuthor() == null) ? "---" : song.getOriginalAuthor()) + "\n";
        out+="Description: " + ((song.getDescription() == null) ? "---" : song.getDescription()) + "\n";

        sender.sendMessage(out);

        return true;
    }
}
