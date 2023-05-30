/*
    WGamemode 3, an automatic gamemode switching plugin for Spigot 1.18
    Updated for https://true-og.net by NotAlexNoyle
    Copyright (C) 2015 Nicholas Narsing <soren121@sorenstudios.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published 
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.sorenstudios.wgamemode;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class RemoveRegion implements CommandExecutor {

	private WGamemode plugin;

	public RemoveRegion(WGamemode instance) {
		this.plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Verify that argument length is correct
		if (args.length >= 1) {
			String regionName = args[0];
			ConfigurationSection regions = this.plugin.getConfig().getConfigurationSection("regions");

			// Verify that region is in the config file
			if (regions != null && regions.isSet(regionName)) {
				regions.set(regionName, null);
				this.plugin.saveConfig();

				sender.sendMessage(ChatColor.DARK_GREEN + 
						"Removed automatic gamemode rule for region '" + regionName + "'");
			}
			else if(!regions.isSet(regionName)) {
				sender.sendMessage(ChatColor.RED + "That region is not managed by WGamemode.");
			}
			else {
				// Returning false means the command failed unexpectedly
				return false;
			}
		}

		return true;
	}

}