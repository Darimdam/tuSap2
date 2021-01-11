package TuSap2.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    public static Connection get;
    public Connection databaseLink;
    public  Connection getConnection(){
        String databaseName="Sap2";
        String databaseUser="root";
        String databasePassword="proektSap2";
        String url="jdbc:mysql://localhost/"+databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink= DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();

        }
        return databaseLink;
    }
}
