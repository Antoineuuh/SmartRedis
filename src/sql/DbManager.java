package sql;

import sample.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbManager {

    private DbConnection ACCOUNTS_CONNECTION;

    public DbManager(){

        ACCOUNTS_CONNECTION = new DbConnection(new DbCredentials(" db4free.net", "antoineuuh", "rosabelle2013", "smartredis", 3306));

    }

    public boolean CHECK_CONNECTION(){

        if(ACCOUNTS_CONNECTION.GET_CONNECTION() != null) {
            return true;
        }

        return false;
    }

    public boolean USER_HAS_ACCOUNT(String ID, String PASS) {

        PreparedStatement STATEMENT = null;
        String SQL = "SELECT * FROM `accounts` WHERE `ID`=? AND `PASSWORD`=?";

        try {

            STATEMENT = ACCOUNTS_CONNECTION.GET_CONNECTION().prepareStatement(SQL);
            STATEMENT.setString(1, ID);
            STATEMENT.setString(2, PASS);

            ResultSet RESULT = STATEMENT.executeQuery();

            if(RESULT.next()) {

                Main.CURRENT_USERNAME = RESULT.getString(1);

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }


}
