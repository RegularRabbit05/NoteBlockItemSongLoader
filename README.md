# NBT NoteBlock Songs loader
Library and Plugin to load NoteBlockSongs from an item (for example a music disc held by the player). <br>
The NBS file is saved as a byte array inside the item's NBT data and loaded back when needed. It's possible to store up to ~2MB per item. <br>
The only dependency of this plugin is NoteBlockAPI (can be shaded). "commons-io" and "NBTApi" are shaded inside the plugin. <br>
The "commands.*" package contains some examples on how to use this library. The commands can be disabled completely in the config.yml file. <br>
The library supports downloading any file type (you can restrict the file to .nbs manually when checking the url or changing the download process), the nbs metadata is kept (title, author, description, ...) and they can be retrieved when loading back the song. <br>
<br>
<br>
### Why?
With this plugin there is no need to save .nbs files anymore and sync them across servers. You can now attach songs to items (for example: armor sets in RPGs) and play them at any given time. Inventory syncing plugins will also copy the song to all their targets. Lastly in Creative servers players can use their "inventory hotbar save" feature "X+<1-9>" allowing them to play custom songs in their plots or to friends.<br>
Why not saving a link instead? No more file hosting websites needed! <br> 
Warning: this plugin is best suited for servers with many songs or where songs are added often, as it doesn't offer any "no duplicated songs" compression or similar.