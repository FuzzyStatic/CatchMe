package com.fuzzycraft.fuzzy;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.fuzzycraft.fuzzy.listeners.PlayerServerStatus;

public class CatchMe extends JavaPlugin {

	private PlayerServerStatus pj;
	
	public void onEnable() {
		// Create listener instances
		pj = new PlayerServerStatus(this);

		// Register listeners
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(pj, this);
	}		
}