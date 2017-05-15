package de.simonsator.partyandfriends.clans.stats.bedwarsnb;

import de.simonsator.partyandfriends.communication.sql.SQLCommunication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * @author simonbrungs
 * @version 1.0.0 17.01.17
 */
public class BWNBStatsConnection extends SQLCommunication {
	protected BWNBStatsConnection(String pDatabase, String pURL, String pUserName, String pPassword) {
		super(pDatabase, pURL, pUserName, pPassword);
	}

	public PlayerData getPlayerData(UUID pUUID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select DESTROYEDBEDS, KILLS, DEATHS, DEFEATS, WINS, PLAYEDGAMES from `"
					+ DATABASE + "`." + "Stats WHERE UUID='" + pUUID.toString() + "' LIMIT 1");
			if (rs.next())
				return new PlayerData(rs.getInt("WINS"), rs.getInt("DESTROYEDBEDS"), rs.getInt("DEATHS"),
						rs.getInt("KILLS"), rs.getInt("DEFEATS"), rs.getInt("PLAYEDGAMES"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, stmt);
		}
		return null;
	}
}
