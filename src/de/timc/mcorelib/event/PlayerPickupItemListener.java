package de.timc.mcorelib.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.timc.mcorelib.core.MCore;
import de.timc.mcorelib.plugin.MCorePlayer;

public class PlayerPickupItemListener extends ListenerProperty{

	protected PlayerPickupItemListener(JavaPlugin plugin) {
		super(plugin);
	}
	
	
	@EventHandler
	public void onPickupItem(PlayerPickupItemEvent e){
		MCorePlayer p = MCore.get().getMCorePlayer(e.getPlayer().getName());
		e.setCancelled(!p.isPickupItemAllowed());
	}

}
