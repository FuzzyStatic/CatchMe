package com.fuzzycraft.fuzzy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.fuzzycraft.fuzzy.commands.CatchWho;
import com.fuzzycraft.fuzzy.constants.Defaults;
import com.fuzzycraft.fuzzy.constants.Paths;
import com.fuzzycraft.fuzzy.constants.Permissions;
import com.fuzzycraft.fuzzy.listeners.CaughtCatchee;
import com.fuzzycraft.fuzzy.listeners.SelectCatchee;
import com.fuzzycraft.fuzzy.players.Catchee;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class CatchMe extends JavaPlugin {

	private Catchee catchee;
	private SelectCatchee sc;
	private CaughtCatchee cc;
	
	private ScoreboardManager manager;
	private Scoreboard board;
	private Team team;
	
	public void onEnable() {		
		// Configuration setup.
		getDataFolder().mkdir();
		getConfig().addDefault(Paths.ALERT_CATCHEE, Defaults.ALERT_CATCHEE);
		getConfig().addDefault(Paths.ALERT_CATCHEE_CAUGHT, Defaults.ALERT_CATCHEE_CAUGHT);
		getConfig().addDefault(Paths.ALERT_CATCHEE_NONE, Defaults.ALERT_CATCHEE_NONE);
		getConfig().addDefault(Paths.MIN_PLAYERS, Defaults.MIN_PLAYERS);
		getConfig().addDefault(Paths.TIMER, Defaults.TIMER);
		getConfig().addDefault(Paths.SUFFIX_TAG, Defaults.SUFFIX_TAG);
		getConfig().addDefault(Paths.SUFFIX_ALLOW, Defaults.SUFFIX_ALLOW);
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		// Set catchee
		this.catchee = new Catchee(this, Permissions.CATCHEE, getConfig().getString(Paths.ALERT_CATCHEE), getConfig().getInt(Paths.MIN_PLAYERS), getConfig().getInt(Paths.TIMER));
		
		// Set scoreboard
		if (getConfig().getBoolean(Paths.SUFFIX_ALLOW)) {
			this.manager = Bukkit.getScoreboardManager();
			this.board = manager.getNewScoreboard();
			this.team = board.registerNewTeam("catchee");
			this.team.setSuffix(ChatColor.YELLOW + " [IT]");
			this.catchee.setBoard(this.board);
			this.catchee.setTeam(this.team);
		}
		
		// Start next game
		this.catchee.timer();
		
		// Create listener instances.
		sc = new SelectCatchee(this.catchee);
		cc = new CaughtCatchee(this.catchee, Permissions.CATCHER, getConfig().getString(Paths.ALERT_CATCHEE_CAUGHT));

		// Register listeners.
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(sc, this);
		pm.registerEvents(cc, this);
		
		// Register commands.
		getCommand("catchwho").setExecutor(new CatchWho(this.catchee, Permissions.CATCHWHO, getConfig().getString(Paths.ALERT_CATCHEE), getConfig().getString(Paths.ALERT_CATCHEE_NONE)));
	}		
}