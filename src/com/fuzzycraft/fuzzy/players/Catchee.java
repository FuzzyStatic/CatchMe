package com.fuzzycraft.fuzzy.players;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.fuzzycraft.fuzzy.CatchMe;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class Catchee {
	
	private CatchMe plugin;
	private String perm;
	private String msg;
	private int minPlayers, timer;
	private boolean ready;
	
	private Collection<? extends Player> players;
	private Player catchee;
	private Scoreboard board;
	private Team team;
	
	/**
	 * Set up initial catchee.
	 * @param plugin
	 * @param timer
	 */
	public Catchee(CatchMe plugin, String perm, String msg, int minPlayers, int timer) {
		this.plugin = plugin;
		this.perm = perm;
		this.msg = msg;
		this.minPlayers = minPlayers;
		this.timer = timer;
		this.ready = true;
		this.setOnlinePlayersWithPerm(null);
		this.setNewCatchee(null);
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
				
				if (board != null) {
					player.setScoreboard(this.board);
				}
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
	public void setNewCatchee(Player currentCatchee) {
		if (this.players != null && this.players.size() >= this.minPlayers) {
			if (currentCatchee == null) {
				// Return random player.
				this.catchee = (Player) this.players.toArray()[new Random().nextInt(this.players.size())];
				
				if (this.team != null) {
					this.team.addPlayer(this.catchee);
				}
				
				this.plugin.getServer().broadcastMessage(ChatColor.GREEN + this.msg.replaceAll("&c", this.catchee.getName()));
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
				setOnlinePlayersWithPerm(null);
				setNewCatchee(null);
				ready = true;
			}	
		}, this.timer * 20);
	}
	
	/**
	 * Set board.
	 * @param board
	 */
	public void setBoard(Scoreboard board) {
		this.board = board;
	}
	
	/**
	 * Set team.
	 * @param team
	 */
	public void setTeam(Team team) {
		this.team = team;
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
	 * Return if catchee exists.
	 * @return
	 */
	public boolean exists() {
		if (this.catchee != null && this.ready) {
			return true;
		}
		
		return false;
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
	
	/**
	 * Set board.
	 * @param board
	 */
	public Scoreboard getBoard() {
		return this.board;
	}
	
	/**
	 * Set team.
	 * @param team
	 */
	public Team getTeam() {
		return this.team;
	}
}
