package com.fuzzycraft.fuzzy;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Catchee {
	
	private CatchMe plugin;
	private int timer;
	private boolean run;
	
	private Collection<? extends Player> onlinePlayers;
	private Player catchee;
	
	public Catchee(CatchMe plugin, int timer) {
		this.plugin = plugin;
		this.timer = timer;
		this.run = true;
		this.setOnlinePlayers();
		this.setCatchee(null);
	}
	
	public void setOnlinePlayers() {
		this.onlinePlayers = this.plugin.getServer().getOnlinePlayers();
	}

	public void setCatchee(Player currentCatchee) {
		if (this.onlinePlayers.size() >= Constants.MIN_PLAYERS) {
			if (currentCatchee == null) {
				// Return random player
				this.catchee = (Player) this.onlinePlayers.toArray()[new Random().nextInt(this.onlinePlayers.size())];
				return;
			}
		}
		this.catchee = null;
	}
	
	public void timer() {
		this.run = false;
		
		// Create the task anonymously and set run to true when finished.
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
			public void run() {
				run = true;
				setCatchee(getCatchee());
			}	
		}, this.timer);
	}
	
	public Collection<? extends Player> onlinePlayers() {
		return this.onlinePlayers;
	}
	
	public Player getCatchee() {
		return this.catchee;
	}
	
	public boolean run() {
		return run;
	}
}
