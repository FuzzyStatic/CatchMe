package com.fuzzycraft.fuzzy;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class Catchee {
	
	private CatchMe plugin;
	private int timer;
	private boolean ready;
	
	private Collection<? extends Player> onlinePlayers;
	private Player catchee;
	
	/**
	 * Set up initial catchee.
	 * @param plugin
	 * @param timer
	 */
	public Catchee(CatchMe plugin, int timer) {
		this.plugin = plugin;
		this.timer = timer;
		this.ready = true;
		this.setOnlinePlayers();
		this.newCatchee(null);
	}
	
	/**
	 * Find online players.
	 */
	public void setOnlinePlayers() {
		this.onlinePlayers = this.plugin.getServer().getOnlinePlayers();
	}

	/**
	 * Set a new catchee if there are enough players online.
	 * @param currentCatchee
	 */
	public void newCatchee(Player currentCatchee) {
		if (this.onlinePlayers.size() >= Constants.MIN_PLAYERS) {
			if (currentCatchee == null) {
				// Return random player
				this.catchee = (Player) this.onlinePlayers.toArray()[new Random().nextInt(this.onlinePlayers.size())];
			}
			this.plugin.getServer().broadcastMessage(ChatColor.GREEN + this.catchee.getName() + Constants.CATCHEE_ALERT);
			return;
		}
		this.catchee = null;
	}
	
	/**
	 * Turn event off for a certain time.
	 */
	public void timer() {
		this.ready = false;
		
		// Create the task anonymously and set run to true when finished.
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
			public void run() {
				ready = true;
				newCatchee(catchee);
			}	
		}, this.timer);
	}
	
	/**
	 * Return onlinePlayers.
	 * @return
	 */
	public Collection<? extends Player> onlinePlayers() {
		return this.onlinePlayers;
	}
	
	/**
	 * Return catchee.
	 * @return
	 */
	public Player getCatchee() {
		return this.catchee;
	}
	
	/**
	 * Return ready.
	 * @return
	 */
	public boolean ready() {
		return ready;
	}
}
