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
		
	/**
	 * Insert catchee to catch.
	 * @param catchee
	 */
	public CaughtCatchee(Catchee catchee, String perm) {
		this.catchee = catchee;
		this.perm = perm;
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
			catcher.sendMessage("You've caught " + this.catchee.getCatchee().getName() + "! Congratulations! Have a reward!");
			new ItemGiver(catcher).giveItem();
		}
	}
}
