package com.fuzzycraft.fuzzy.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.fuzzycraft.fuzzy.players.Catchee;
import com.fuzzycraft.fuzzy.utilities.ItemGiver;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class CaughtCatchee implements Listener {

	private Catchee catchee;
	private String perm;
	private String msg;
		
	/**
	 * Insert catchee to catch.
	 * @param catchee
	 */
	public CaughtCatchee(Catchee catchee, String perm, String msg) {
		this.catchee = catchee;
		this.perm = perm;
		this.msg = msg;
	}
	
	/**
	 * Give catcher random item when he catches catchee.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player catcher = event.getPlayer();

		if (event.getRightClicked().equals(this.catchee.getCatchee()) && catchee.ready() && catcher.hasPermission(this.perm)) {
			this.catchee.timer();
			catcher.sendMessage(this.msg.replaceAll("&c", this.catchee.getCatchee().getName()));
			new ItemGiver(catcher).giveItem();
		}
	}
}
