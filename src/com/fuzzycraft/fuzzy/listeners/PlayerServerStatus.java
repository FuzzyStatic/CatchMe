package com.fuzzycraft.fuzzy.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.fuzzycraft.fuzzy.CatchMe;
import com.fuzzycraft.fuzzy.PlayerChecker;

public class PlayerServerStatus extends PlayerChecker implements Listener {

	public PlayerServerStatus(CatchMe plugin) {
		super(plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onplayerjoin(PlayerJoinEvent event) {
		for (Player player : super.onlinePlayers()) {
			System.out.println(player.getName());
		}
		if (super.catchee != null) {
			System.out.println(super.catchee.getName());
		} else {
			System.out.println("Not enough players on.");
		}
    }
	
	
}
