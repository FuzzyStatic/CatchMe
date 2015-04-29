package com.fuzzycraft.fuzzy;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class ItemGiver {

	private Player catcher;
	
	/**
	 * Insert catcher to give item.
	 * @param catcher
	 */
	public ItemGiver(Player catcher) {
		this.catcher = catcher;
	}
	
	/**
	 * Give item to catcher.
	 */
	public void giveItem() {
		if (this.catcher.getInventory().firstEmpty() != -1) {
			this.catcher.getInventory().addItem(randomItem());
		} else {
            this.catcher.getWorld().dropItem(this.catcher.getLocation(), randomItem());
		}
	}
	
	/**
	 * Create random item with random amount.
	 * @return
	 */
	public ItemStack randomItem() {
		Material material = Material.values()[new Random().nextInt(Material.values().length)];
		int amount = new Random().nextInt(material.getMaxStackSize())+1;
		return new ItemStack(material, amount);
	}
}
