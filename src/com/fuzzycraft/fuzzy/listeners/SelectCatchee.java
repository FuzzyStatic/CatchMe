package com.fuzzycraft.fuzzy.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.fuzzycraft.fuzzy.Catchee;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class SelectCatchee implements Listener {

	private Catchee catchee;
	
	/**
	 * Get catchee instance.
	 * @param catchee
	 */
	public SelectCatchee(Catchee catchee) {
		this.catchee = catchee;
	}
	
	/**
	 * Check if there are enough players, if catchee exists, and ifcatchee is ready.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
		this.catchee.setOnlinePlayers();
		if (this.catchee.ready()) {
			this.catchee.newCatchee(this.catchee.getCatchee());
		}
    }
	
	/**
	 * Check if there are enough players, if catchee exists, and ifcatchee is ready.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
		this.catchee.setOnlinePlayers();
		if (this.catchee.ready() && event.getPlayer().equals(this.catchee.getCatchee())) {
			this.catchee.newCatchee(null);
		}
    }
}
