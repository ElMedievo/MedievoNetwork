package org.elmedievo.medievoapi.Database.Entries;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class ClanEntry {

    public static boolean clanExistsInSQLDatabase(String name) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM " + sql.clans_data_table + " WHERE name=?");
        statement.setString(1, name);
        ResultSet results = statement.executeQuery();
        return results.next();
    }

    public static void createClanInSQLDatabase(String name, UUID leader_uuid, String leader_name) {
        try {
            PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM " + sql.clans_data_table + " WHERE name=?");
            statement.setString(1, name);
            ResultSet results = statement.executeQuery();
            results.next();
            if (!clanExistsInSQLDatabase(name)) {
                PreparedStatement insert = Connect.getConnection().prepareStatement("INSERT INTO " + sql.clans_data_table + " (name,leader_uuid,leader_name) VALUES (?,?,?)");
                insert.setString(1, name);
                insert.setString(2, leader_uuid.toString());
                insert.setString(3, leader_name);
                insert.executeUpdate();
                addClanToEconomyTable(name);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void addClanToEconomyTable(String clan) throws SQLException {
        PreparedStatement insert = Connect.getConnection().prepareStatement(
                "INSERT INTO " + sql.clans_economy_data_table +
                        " (clan,gold_ingot,gold_nugget,golden_carrot,golden_horse_armor,gold_block,golden_sword,golden_shovel,golden_pickaxe,golden_axe,golden_hoe,golden_helmet,golden_chestplate,golden_leggings,golden_boots,golden_apple,alfonsos) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
        );
        insert.setString(1, clan);
        insert.setInt(2, 0);
        insert.setInt(3, 0);
        insert.setInt(4, 0);
        insert.setInt(5, 0);
        insert.setInt(6, 0);
        insert.setInt(7, 0);
        insert.setInt(8, 0);
        insert.setInt(9, 0);
        insert.setInt(10, 0);
        insert.setInt(11, 0);
        insert.setInt(12, 0);
        insert.setInt(13, 0);
        insert.setInt(14, 0);
        insert.setInt(15, 0);
        insert.setInt(16, 0);
        insert.setFloat(17, 0);
        insert.executeUpdate();
    }
}
