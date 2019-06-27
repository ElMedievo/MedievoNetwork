package org.elmedievo.medievoapi.Database.Getters;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class ClanMaterials {
    public static int getClanMaterialAmount(String clan, String material) {
        try {
            return lookupMaterialsAmountinClan(clan, material);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static int lookupMaterialsAmountinClan(String clan, String material) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT " + material + " FROM " + sql.clans_economy_data_table + " WHERE clan=?");
        statement.setString(1, clan);
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getInt(material);
    }
}
