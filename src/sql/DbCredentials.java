package sql;

public class DbCredentials {

    private String HOST;
    private String USER;
    private String PASS;
    private String DB_NAME;
    private int PORT;

    public DbCredentials(String HOST, String USER, String PASS, String DB_NAME, int PORT) {

        this.HOST = HOST;
        this.USER = USER;
        this.PASS = PASS;
        this.DB_NAME = DB_NAME;
        this.PORT = PORT;

    }

    public String TO_URL(){

        final StringBuilder SB = new StringBuilder();

        SB.append("jdbc:mysql://")
                .append(HOST)
                .append(":")
                .append(PORT)
                .append("/")
                .append(DB_NAME);

        return SB.toString();

    }

    public String GET_HOST() {
        return HOST;
    }

    public String GET_USER() {
        return USER;
    }

    public String GET_PASS() {
        return PASS;
    }

    public String GET_DB_NAME() {
        return DB_NAME;
    }

    public int GET_PORT() {
        return PORT;
    }

}
