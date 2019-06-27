package org.elmedievo.medievoapi.Database;

import org.elmedievo.medievoapi.MedievoAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.elmedievo.medievoapi.Util.Generic.Messages.SQL_CANNOT_CONNECT;
import static org.elmedievo.medievoapi.Util.Generic.Messages.SQL_CONNECT_SUCCESS;
import static org.elmedievo.medievoapi.Util.Methods.Console.consoleAlert;

public class Connect {
    private static Connection connection;
    private String host;
    private String database;
    private String username;
    private String password;
    public String player_data_table;
    public String clans_data_table;
    public String clans_economy_data_table;
    private int port;

    public static Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        Connect.connection = connection;
    }

    public void connectSQLDatabase() {
        try {
            establishSQLConnection();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            consoleAlert(SQL_CANNOT_CONNECT);
        }
    }

    private void establishSQLConnection() throws SQLException, ClassNotFoundException {
        host = MedievoAPI.instance.getConfig().getString("SQL.host");
        port = Integer.parseInt(MedievoAPI.instance.getConfig().getString("SQL.port"));
        database = MedievoAPI.instance.getConfig().getString("SQL.database");
        username = MedievoAPI.instance.getConfig().getString("SQL.username");
        password = MedievoAPI.instance.getConfig().getString("SQL.password");
        player_data_table = MedievoAPI.instance.getConfig().getString("SQL.player_data_table");
        clans_data_table = MedievoAPI.instance.getConfig().getString("SQL.clans_data_table");
        clans_economy_data_table = MedievoAPI.instance.getConfig().getString("SQL.clans_economy_data_table");

        synchronized (this) {
            if (getConnection() != null && !getConnection().isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            String URL = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true&useSSL=false";
            setConnection(DriverManager.getConnection(URL, this.username, this.password));
            consoleAlert(SQL_CONNECT_SUCCESS);
        }
    }
}
