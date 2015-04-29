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
		this.setOnlinePlayers();
		this.setCatchee(null);
	}
	
	public void setOnlinePlayers() {
		this.onlinePlayers = this.plugin.getServer().getOnlinePlayers();
	}

	public void setCatchee(Player currentCatchee) {
		if (this.onlinePlayers.size() > 1) {
			if (currentCatchee == null) {
				// Return random player
				this.catchee = (Player) this.onlinePlayers.toArray()[new Random().nextInt(this.onlinePlayers.size())];
				return;
			}
		}
		this.catchee = null;
	}
	
	public Collection<? extends Player> onlinePlayers() {
		return this.onlinePlayers;
	}
	
	public Player getCatchee() {
		return this.catchee;
	}
	
}
