package com.fuzzycraft.fuzzy;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.fuzzycraft.fuzzy.listeners.CaughtCatchee;
import com.fuzzycraft.fuzzy.listeners.SelectCatchee;

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
		this.catchee = new Catchee(this, Constants.TIMER);
		this.catchee.newCatchee(null);
		
		// Create listener instances
		sc = new SelectCatchee(this.catchee);
		cc = new CaughtCatchee(this.catchee);

		// Register listeners
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(sc, this);
		pm.registerEvents(cc, this);
	}		
}