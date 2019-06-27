package org.elmedievo.medievoapi.Database.Getters;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class ClansToList {
    public static List<String> getClansList() {
        try {
            return lookupEveryClan();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static List<String> getRankedClansList() {
        try {
            return lookUpRankedClans();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static List<String> lookupEveryClan() throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT name FROM " + sql.clans_data_table);
        ResultSet results = statement.executeQuery();
        List<String> clans = new ArrayList<>();

        while (results.next()) {
            clans.add(results.getString("name"));
        }

        return clans;
    }

    private static List<String> lookUpRankedClans() throws SQLException {
        List<String> orderedClans = new ArrayList<>();
        List<Float> alfonsos = new ArrayList<>();
        PreparedStatement alfonsos_statement = Connect.getConnection().prepareStatement("SELECT alfonsos FROM " + sql.clans_economy_data_table);
        ResultSet alfonsos_results = alfonsos_statement.executeQuery();

        while (alfonsos_results.next()) {
            alfonsos.add(alfonsos_results.getFloat("alfonsos"));
        }
        alfonsos.sort(Collections.reverseOrder());

        alfonsos.forEach(alfonso -> {
            try {
                PreparedStatement clans_statement = Connect.getConnection().prepareStatement("SELECT DISTINCT clan FROM " + sql.clans_economy_data_table + " WHERE alfonsos=?");
                clans_statement.setDouble(1, alfonso);
                ResultSet clans_results = clans_statement.executeQuery();
                while (clans_results.next()) {
                    String clan = clans_results.getString("clan");
                    if (!orderedClans.contains(clan)) {
                        orderedClans.add(clan);
                    } else {
                        return;
                    }
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
        return orderedClans;
    }
}
