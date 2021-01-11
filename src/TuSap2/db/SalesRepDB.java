package TuSap2.db;

import TuSap2.model.Product;
import TuSap2.model.SalesRep;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SalesRepDB {

    public void addSalesRep(SalesRep salesRep, boolean status) throws SQLException {
        DataBaseConnection connection = new DataBaseConnection();
        Connection connectDB = connection.getConnection();
        if(!status) {
            try {


                String insertFields = "INSERT INTO sales_representatives (first_name,last_name,username,password,status) VALUES ('" + salesRep.getFirstName() + "','" + salesRep.getLastNAme() + "','" + salesRep.getUsername() + "','" + salesRep.getPassword() + "',+1)";
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertFields);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            int id=salesRep.getId();
            String updateStatus= "UPDATE sales_representatives SET first_name= '"+salesRep.getFirstName()+"', last_name='"+salesRep.getLastNAme()+"', username = '"+salesRep.getUsername()+"', password = '"+salesRep.getPassword()+"', status ='" +1+"' WHERE sr_id="+id;
            Statement statement=connectDB.createStatement();
            statement.executeUpdate(updateStatus);
        }
    }
    public SalesRep validateByStatus(String name) throws SQLException {
        DataBaseConnection connection = new DataBaseConnection();
        Connection connectDB = connection.getConnection();
        String query="SELECT * FROM sales_representatives WHERE first_name='"+name+"'";
        ResultSet rs = connectDB.createStatement().executeQuery(query);
        SalesRep salesRep=new SalesRep();
        try {
            if(rs.next()) {
                if (rs.getInt(6) == 0) {
                    salesRep.setId(rs.getInt(1));
                    salesRep.setFirstName(rs.getString(2));
                    salesRep.setLastNAme(rs.getString(3));
                    salesRep.setUsername(rs.getString(4));
                    salesRep.setPassword(rs.getString(5));
                    salesRep.setStatus(1);
                    return salesRep;
                } else {
                    return null;
                }
            }else return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public SalesRep validateSalesRepByName(String name) throws SQLException {
        DataBaseConnection connection = new DataBaseConnection();
        Connection connectDB = connection.getConnection();
        ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM sales_representatives WHERE first_name= '" + name+"'");
        SalesRep salesRep=new SalesRep();
        if(rs.next()){
            salesRep.setId(rs.getInt(1));
            salesRep.setFirstName(rs.getString(2));
            salesRep.setLastNAme(rs.getString(3));
            salesRep.setUsername(rs.getString(4));
            salesRep.setPassword(rs.getString(5));


            return salesRep;
        }
        else return null;
    }
    public SalesRep validateSalesRepById(int id) throws SQLException {
        DataBaseConnection connection = new DataBaseConnection();
        Connection connectDB = connection.getConnection();
        ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM sales_representatives WHERE sr_id="+id);
        SalesRep salesRep=new SalesRep();
        if(rs.next()){
            salesRep.setId(rs.getInt(1));
            salesRep.setFirstName(rs.getString(2));
            salesRep.setLastNAme(rs.getString(3));
            salesRep.setUsername(rs.getString(4));
            salesRep.setPassword(rs.getString(5));


            return salesRep;
        }
        else return null;
    }
    public void updateSalesRep(int id,String firstName,String lastName,String username,String password) throws SQLException {

        DataBaseConnection connection = new DataBaseConnection();
        Connection connectDB = connection.getConnection();
        Statement statement=connectDB.createStatement();
        statement.executeUpdate("UPDATE sales_representatives SET first_name= '"+firstName+"', last_name='"+lastName+"', username = '"+username+"', password = '"+password+"' WHERE sr_id="+id );
    }

    public ResultSet updateTable() throws SQLException {
        DataBaseConnection connection=new DataBaseConnection();
        Connection connectDB=connection.getConnection();

        ResultSet rs=connectDB.createStatement().executeQuery("SELECT * FROM sales_representatives WHERE status="+1);
        return rs;
    }

    public void removeSalesRep(int id) throws SQLException {
        DataBaseConnection connection=new DataBaseConnection();
        Connection connectDB=connection.getConnection();

        String insertToDelete="UPDATE sales_representatives SET status="+0+" WHERE sr_id="+id;

        Statement statement=connectDB.createStatement();
        statement.executeUpdate(insertToDelete);

    }

}
