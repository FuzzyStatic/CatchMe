package com.fuzzycraft.fuzzy;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
	
	public void onEnable() {		
		// Configuration setup.
		getDataFolder().mkdir();
		getConfig().addDefault(Paths.ALERT_CATCHEE, Defaults.ALERT_CATCHEE);
		getConfig().addDefault(Paths.ALERT_CATCHEE_CAUGHT, Defaults.ALERT_CATCHEE_CAUGHT);
		getConfig().addDefault(Paths.ALERT_CATCHEE_NONE, Defaults.ALERT_CATCHEE_NONE);
		getConfig().addDefault(Paths.MIN_PLAYERS, Defaults.MIN_PLAYERS);
		getConfig().addDefault(Paths.TIMER, Defaults.TIMER);
		getConfig().options().copyDefaults(true);
		saveConfig();	
		
		// Set catchee
		this.catchee = new Catchee(this, Permissions.CATCHEE, getConfig().getString(Paths.ALERT_CATCHEE), getConfig().getInt(Paths.MIN_PLAYERS), getConfig().getInt(Paths.TIMER));
		this.catchee.setNewCatchee(null);
		
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