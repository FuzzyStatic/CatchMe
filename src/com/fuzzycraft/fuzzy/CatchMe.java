package com.fuzzycraft.fuzzy;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.fuzzycraft.fuzzy.commands.CatchWho;
import com.fuzzycraft.fuzzy.listeners.CaughtCatchee;
import com.fuzzycraft.fuzzy.listeners.SelectCatchee;
import com.fuzzycraft.fuzzy.players.Catchee;
import com.fuzzycraft.fuzzy.utilities.Constants;

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
		this.catchee = new Catchee(this, Constants.PERM_CATCHEE, Constants.CATCHEE_ALERT, Constants.TIMER);
		this.catchee.newCatchee(null);
		
		// Create listener instances.
		sc = new SelectCatchee(this.catchee);
		cc = new CaughtCatchee(this.catchee, Constants.PERM_CATCHER);

		// Register listeners.
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(sc, this);
		pm.registerEvents(cc, this);
		
		// Register commands.
		getCommand("catchwho").setExecutor(new CatchWho(this.catchee, Constants.CATCHEE_ALERT, Constants.CATCHEE_ALERT_NONE));
	}		
}