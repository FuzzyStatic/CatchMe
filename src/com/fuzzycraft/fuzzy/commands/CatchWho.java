package com.fuzzycraft.fuzzy.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fuzzycraft.fuzzy.players.Catchee;

public class CatchWho implements CommandExecutor {
	
	private Catchee catchee;
	private String msg;
		
	public CatchWho(Catchee catchee, String msg) {
		this.catchee = catchee;
		this.msg = msg;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("catchwho")) {
			if (sender instanceof Player) {
				sender.sendMessage(ChatColor.DARK_GREEN + this.catchee.getCatchee().getName() + ChatColor.AQUA + this.msg);
			}
		}
		return false;
	}
}
