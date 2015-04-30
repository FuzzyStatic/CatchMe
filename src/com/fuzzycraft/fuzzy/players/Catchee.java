package com.fuzzycraft.fuzzy.players;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
	
	private Collection<? extends Player> players;
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
		this.setOnlinePlayersWithPerm(null);
		this.newCatchee(null);
	}
	
	/**
	 * Find online players.
	 */
	@Deprecated
	public void setOnlinePlayers(Player leavingPlayer) {
		// Create mutable list of players.
		List<Player> players = new ArrayList<Player>();
				
		for (Player player : this.plugin.getServer().getOnlinePlayers()) {					
			players.add(player);
		}
				
		// Remove leaving player.
		if (leavingPlayer != null) {
			players.remove(leavingPlayer);
		}
				
		// Set collection of players to list.
		this.players = players;
	}
	
	/**
	 * Find online players with permission.
	 * @param player 
	 */
	public void setOnlinePlayersWithPerm(Player leavingPlayer) {
		// Create mutable list of players.
		List<Player> players = new ArrayList<Player>();
		
		for (Player player : this.plugin.getServer().getOnlinePlayers()) {			
			if (player.hasPermission(this.perm)) {
				players.add(player);
			}
		}
		
		// Remove leaving player.
		if (leavingPlayer != null) {
			players.remove(leavingPlayer);
		}
		
		// Set collection of players to list.
		this.players = players;	
	}

	/**
	 * Set a new catchee if there are enough players online.
	 * @param currentCatchee
	 */
	public void newCatchee(Player currentCatchee) {
		if (this.players != null && this.players.size() >= Constants.MIN_PLAYERS) {
			if (currentCatchee == null) {
				// Return random player.
				this.catchee = (Player) this.players.toArray()[new Random().nextInt(this.players.size())];
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
				newCatchee(null);
			}	
		}, this.timer);
	}
	
	/**
	 * Return onlinePlayers.
	 * @return
	 */
	public Collection<? extends Player> onlinePlayers() {
		return this.players;
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
