package github.regularrabbit05.noteblockitemsongloader;

import github.regularrabbit05.noteblockitemsongloader.commands.SongInfoCommand;
import github.regularrabbit05.noteblockitemsongloader.commands.SongPlayerCommand;
import github.regularrabbit05.noteblockitemsongloader.commands.SongRemoverCommand;
import github.regularrabbit05.noteblockitemsongloader.commands.SongSaverCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoteBlockItemSongLoader extends JavaPlugin {
    private boolean apiMode = false;
    public boolean isApiMode() {
        return apiMode;
    }

    @Override
    public void onEnable() {
        System.setProperty("http.agent", "NBTSongPlayer Commons-HttpClient/3.1"); //FIXES 403 error issue (WebServers rejecting 'java' http.agent)
        this.saveConfig();
        if (this.getConfig().getBoolean("apiMode")) apiMode = true;

        if (!this.apiMode) {
            this.getCommand("playSong").setExecutor(new SongPlayerCommand(this));
            this.getCommand("saveSong").setExecutor(new SongSaverCommand(this));
            this.getCommand("removeSong").setExecutor(new SongRemoverCommand(this));
            this.getCommand("getSong").setExecutor(new SongInfoCommand(this));
        }
    }

    @Override
    public void onDisable() {

    }
}
