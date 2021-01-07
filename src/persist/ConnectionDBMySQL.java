package persist;

import java.sql.*;

/**
 * This Singleton class constructs a connection to the MySQL database
 */
public class ConnectionDBMySQL implements ConnectionDB {
    /**
     * The connection to the database
     */
    private Connection connection;

    /**
     * Singleton attribute
     */
    private static ConnectionDBMySQL connectionDBMySQL;

    /**
     * Constructs an instance of ConnectionDBMySQL
     */
    private ConnectionDBMySQL() {
        connectionToDB();
        connectionDBMySQL = this;
    }

    /**
     * This method connects to the database and is called when an instance of the class is created.
     * @return boolean Indicates if the connection is set or not
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

    /**
     * Connection is the connection to the database
     *  @return connection Connection to the database
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Singleton get instance method
     */
    public static ConnectionDBMySQL getInstance() {
        if(connectionDBMySQL != null){
            return connectionDBMySQL;
        }else{
            return new ConnectionDBMySQL();
        }
    }

}
