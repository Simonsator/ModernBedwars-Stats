package de.simonsator.partyandfriends.clans.stats.bedwarsnb;

import de.simonsator.partyandfriends.utilities.Language;
import de.simonsator.partyandfriends.utilities.LanguageConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 30.11.16
 */
public class BWNBStatsMessages extends LanguageConfiguration {

	public BWNBStatsMessages(Language pLanguage, File pFile) throws IOException {
		super(pLanguage, pFile);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("Name", "BedWars");
		set("Kills", "&7The clan has &a[KILLS] &7kills.");
		set("Deaths", "&7The clan has died &c[DEATHS] &7times.");
		set("DestroyedBeds", "&7The clan has &a[DESTROYED] &7beds.");
		set("Wins", "&7The clan has won &a[WINS] &7times.");
		set("Defeats", "&7The clan has &c[DEFEATS] &7defeated.");
		set("Played", "&7The clan has &a[PLAYED] &7times.");
	}

	public void reloadConfiguration() throws IOException {
		configuration = (new BWNBStatsMessages(Language.OWN, FILE)).getCreatedConfiguration();
	}
}
