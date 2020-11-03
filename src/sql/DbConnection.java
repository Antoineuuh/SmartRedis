package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private DbCredentials DB_CREDENTIALS;
    private Connection CONNECTION;

    public DbConnection(DbCredentials DB_CREDENTIALS) {
        this.DB_CREDENTIALS = DB_CREDENTIALS;

        CONNECT();

    }

    private void CONNECT() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            CONNECTION = DriverManager.getConnection(DB_CREDENTIALS.TO_URL(), DB_CREDENTIALS.GET_USER(), DB_CREDENTIALS.GET_PASS());

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

        }

    }

    public void CLOSE() {

        try {
            if(CONNECTION != null) {
                if(!CONNECTION.isClosed()) {
                    CONNECTION.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection GET_CONNECTION(){

        try {
            if(CONNECTION != null) {
                if(!CONNECTION.isClosed()) {
                    return CONNECTION;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CONNECT();
        return CONNECTION;

    }
}
