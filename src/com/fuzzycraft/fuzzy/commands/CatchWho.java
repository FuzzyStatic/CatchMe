package com.fuzzycraft.fuzzy.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fuzzycraft.fuzzy.players.Catchee;

public class CatchWho implements CommandExecutor {
	
	private Catchee catchee;
	private String msgCatchee, msgNoCatchee;
		
	public CatchWho(Catchee catchee, String msgCatchee, String msgNoCatchee) {
		this.catchee = catchee;
		this.msgCatchee = msgCatchee;
		this.msgNoCatchee = msgNoCatchee;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("catchwho")) {
			if (sender instanceof Player) {
				if (this.catchee.exists()) {
					sender.sendMessage(ChatColor.DARK_GREEN + this.catchee.getCatchee().getName() + ChatColor.AQUA + this.msgCatchee);
				} else {
					sender.sendMessage(this.msgNoCatchee);
				}
			}
		}
		return false;
	}
}
