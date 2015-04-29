package com.fuzzycraft.fuzzy.listeners;

import org.bukkit.entity.Player;
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
			this.catchee.setCatchee(this.catchee.getCatchee());
		}
		
		for (Player player : this.catchee.onlinePlayers()) {
			System.out.println(player.getName());
		}
		
		if (this.catchee.getCatchee() != null) {
			System.out.println(this.catchee.getCatchee().getName());
		} else {
			System.out.println("Not enough players on.");
		}
		
    }
	
	/**
	 * Check if there are enough players, if catchee exists, and ifcatchee is ready.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
		this.catchee.setOnlinePlayers();
		if (this.catchee.ready()) {
			this.catchee.setCatchee(this.catchee.getCatchee());
		}
		
		for (Player player : this.catchee.onlinePlayers()) {
			System.out.println(player.getName());
		}
		
    }
}
