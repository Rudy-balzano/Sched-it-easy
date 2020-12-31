package persist;

import java.sql.*;

public class ConnectionDBMySQL implements ConnectionDB {
    private Connection connection;

    /**
     * Singleton attribute
     */
    private static ConnectionDBMySQL connectionDBMySQL;

    /**
     * @return
     */
    private ConnectionDBMySQL() {
        connectionToDB();
        connectionDBMySQL = this;
    }

    /**
     * @return
     */
    public boolean connectionToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            System.out.println("No MySQL driver found...");
            e.printStackTrace();
            return false;
        }

        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + "ec2-3-139-84-242.us-east-2.compute.amazonaws.com" + ":3306/schediteasy", "remoteu", "scheditig4");
        } catch(SQLException e) {
            System.out.println("Connection failed !");
            return false;
        }

        if (this.connection != null) {
            System.out.println("Connection established !");
            return true;
        }else{
            return false;
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Singleton getInstance()
     */
    public static ConnectionDBMySQL getInstance() {
        if(connectionDBMySQL != null){
            return connectionDBMySQL;
        }else{
            return new ConnectionDBMySQL();
        }
    }

    public static void main(String[] args) {
        ConnectionDBMySQL c = ConnectionDBMySQL.getInstance();
    }

}
