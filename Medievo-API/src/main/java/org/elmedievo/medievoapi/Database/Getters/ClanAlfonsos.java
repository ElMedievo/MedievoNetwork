package org.elmedievo.medievoapi.Database.Getters;

import org.elmedievo.medievoapi.Database.Connect;

import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class ClanAlfonsos {
    public static float getClanAlfonsos(String clan) {
        try {
            return lookUpClanAlfonsos(clan);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static float lookUpClanAlfonsos(String clan) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT alfonsos FROM " + sql.clans_economy_data_table + " WHERE clan=?");
        statement.setString(1, clan);
        ResultSet results = statement.executeQuery();
        results.next();

        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        decimalFormat.setRoundingMode(RoundingMode.UP);

        float alfonsos = results.getFloat("alfonsos");

        return Float.parseFloat(decimalFormat.format((alfonsos)));
    }
}
