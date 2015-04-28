package com.fuzzycraft.fuzzy;

import java.util.Collection;
import java.util.Random;

import org.bukkit.entity.Player;

public class PlayerChecker {
	
	private CatchMe plugin;
	private Collection<? extends Player> onlinePlayers;
	protected Player catchee;
	
	public PlayerChecker(CatchMe plugin) {
		this.plugin = plugin;
		this.onlinePlayers = this.plugin.getServer().getOnlinePlayers();
		setCatchee();
	}
	
	public Collection<? extends Player> onlinePlayers() {
		return onlinePlayers;
	}
	
	public void setCatchee() {
		if (this.onlinePlayers.size() >= 2) {
			// Return random player
			this.catchee = (Player) this.onlinePlayers.toArray()[new Random().nextInt(this.onlinePlayers.size())];
		}
		this.catchee = null;
	}
}
