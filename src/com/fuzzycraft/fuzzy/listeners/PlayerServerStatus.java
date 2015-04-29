package com.fuzzycraft.fuzzy.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.fuzzycraft.fuzzy.CatchMe;
import com.fuzzycraft.fuzzy.PlayerChecker;

public class PlayerServerStatus implements Listener {

	private PlayerChecker pc;
	
	public PlayerServerStatus(CatchMe plugin) {
		this.pc = new PlayerChecker(plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
		this.pc.setOnlinePlayers();
		this.pc.setCatchee(this.pc.getCatchee());
		
		for (Player player : this.pc.onlinePlayers()) {
			System.out.println(player.getName());
		}
		
		if (this.pc.getCatchee() != null) {
			System.out.println(this.pc.getCatchee().getName());
		} else {
			System.out.println("Not enough players on.");
		}
		
    }
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
		this.pc.setOnlinePlayers();
		this.pc.setCatchee(this.pc.getCatchee());
		
		for (Player player : this.pc.onlinePlayers()) {
			System.out.println(player.getName());
		}
		
    }
}
