package org.elmedievo.medievoapi.Database.Setters;

import org.elmedievo.medievoapi.Database.Connect;

import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;

import static org.elmedievo.medievoapi.Database.Getters.ClanAlfonsos.getClanAlfonsos;
import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class AlfonsosToClan {
    public static void addAlfonsosToClan(String clan, double alfonsosIn) {
        try {
            lookUpClanAlfonsos(clan, alfonsosIn);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpClanAlfonsos(String clan, double alfonsosIn) throws SQLException {
        double actualAlfonsos = getClanAlfonsos(clan);

        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        decimalFormat.setRoundingMode(RoundingMode.UP);

        float finalAlfonsos = Float.parseFloat(decimalFormat.format(actualAlfonsos + alfonsosIn));

        PreparedStatement insert = Connect.getConnection().prepareStatement("UPDATE " + sql.clans_economy_data_table + " SET alfonsos=? WHERE clan=?");
        insert.setFloat(1, finalAlfonsos);
        insert.setString(2, clan);
        insert.executeUpdate();
    }
}
