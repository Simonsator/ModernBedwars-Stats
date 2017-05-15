package de.simonsator.partyandfriends.clans.stats.bedwarsnb;

import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.clan.commands.subcommands.Stats;
import de.simonsator.partyandfriends.utilities.Language;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author simonbrungs
 * @version 1.0.0 17.01.17
 */
public class BWNBStatsMain extends Plugin {
	private Configuration config;
	private BWNBStatsConnection connection;
	private Configuration messagesConfig;

	public void onEnable() {
		try {
			config = (new BedWarsNBStatsConfig(new File(getDataFolder(), "config.yml"))).getCreatedConfiguration();
			messagesConfig = (new BWNBStatsMessages(Language.OWN, new File(getDataFolder(), "messages.yml"))).getCreatedConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection = new BWNBStatsConnection(config.getString("database.db"), "jdbc:mysql://" + config.getString("database.host") + ":" + config.getInt("database.port"), config.getString("database.user"), config.getString("database.password"));
		((Stats) ClanCommands.getInstance().getSubCommand(Stats.class)).registerClanStats(new BWNBStats(messagesConfig.getString("Name"), connection, Pattern.compile("[KILLS]", Pattern.LITERAL).matcher(messagesConfig.getString("Kills")), Pattern.compile("[WINS]", Pattern.LITERAL).matcher(messagesConfig.getString("Wins")), Pattern.compile("[DEFEATS]", Pattern.LITERAL).matcher(messagesConfig.getString("Defeats")), Pattern.compile("[DEATHS]", Pattern.LITERAL).matcher(messagesConfig.getString("Deaths")), Pattern.compile("[DESTROYED]", Pattern.LITERAL).matcher(messagesConfig.getString("DestroyedBeds")), Pattern.compile("[PLAYED]", Pattern.LITERAL).matcher(messagesConfig.getString("Played")), Pattern.compile("[POINTS]", Pattern.LITERAL).matcher(messagesConfig.getString("Points"))), this);
	}
}
