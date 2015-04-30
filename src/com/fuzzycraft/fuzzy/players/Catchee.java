package com.fuzzycraft.fuzzy.players;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.fuzzycraft.fuzzy.CatchMe;
import com.fuzzycraft.fuzzy.utilities.Constants;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class Catchee {
	
	private CatchMe plugin;
	private String perm;
	private int timer;
	private boolean ready;
	
	private Collection<Player> onlinePlayers;
	private Player catchee;
	
	/**
	 * Set up initial catchee.
	 * @param plugin
	 * @param timer
	 */
	public Catchee(CatchMe plugin, String perm, int timer) {
		this.plugin = plugin;
		this.perm = perm;
		this.timer = timer;
		this.ready = true;
		this.setOnlinePlayersWithPerm();
		this.newCatchee(null);
	}
	
	/**
	 * Find online players.
	 */
	@Deprecated
	public void setOnlinePlayers() {
		for (Player player : this.plugin.getServer().getOnlinePlayers()) {
			this.onlinePlayers.add(player);
		}
	}
	
	/**
	 * Find online players with permission.
	 */
	public void setOnlinePlayersWithPerm() {
		for (Player player : this.plugin.getServer().getOnlinePlayers()) {
			if (player.hasPermission(this.perm)) {
				this.onlinePlayers.add(player);
			}
		}
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
				this.plugin.getServer().broadcastMessage(ChatColor.GREEN + this.catchee.getName() + Constants.CATCHEE_ALERT);
			}
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
	 * Return permission.
	 * @return
	 */
	public String getPerm() {
		return this.perm;
	}
	
	/**
	 * Return timer.
	 * @return
	 */
	public int getTimer() {
		return this.timer;
	}
	
	/**
	 * Return ready.
	 * @return
	 */
	public boolean ready() {
		return ready;
	}
}
