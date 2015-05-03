package com.fuzzycraft.fuzzy;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.fuzzycraft.fuzzy.commands.CatchWho;
import com.fuzzycraft.fuzzy.constants.Defaults;
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
		this.catchee = new Catchee(this, Defaults.PERM_CATCHEE, Defaults.ALERT_CATCHEE, Defaults.TIMER);
		this.catchee.setNewCatchee(null);
		
		// Create listener instances.
		sc = new SelectCatchee(this.catchee);
		cc = new CaughtCatchee(this.catchee, Defaults.PERM_CATCHER, Defaults.ALERT_CATCHEE_CAUGHT);

		// Register listeners.
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(sc, this);
		pm.registerEvents(cc, this);
		
		// Register commands.
		getCommand("catchwho").setExecutor(new CatchWho(this.catchee, Defaults.ALERT_CATCHEE, Defaults.ALERT_CATCHEE_NONE));
	}		
}