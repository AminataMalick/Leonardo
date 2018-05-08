package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexionsgbd {
   
    public static void main( String args[] ) {
    
        
            String  url,login, passwd;

            url = "jdbc:mysql://localhost:3306/leornardo?autoReconnect=true&useSSL=false";
            login = "cel2";
            passwd = "";
            Connection cn = null ;
            Statement st = null ;
            
            try {
            // Load the database driver
            Class.forName("com.mysql.jdbc.Driver");


            // Get a connection to the database
            cn = DriverManager.getConnection(url, login, passwd);
            // Create a statement
            st = cn.createStatement();
               		
    		System.out.print("Aurevoir");
    		
    		
            // Print information about connection warnings
            SQLWarningsExceptions.printWarnings(cn);
            cn.close() ;
          //  sc.close();
        }
        catch( SQLException se ) {
        	se.printStackTrace();
        
        } catch( ClassNotFoundException e ) {
        e.printStackTrace();
        } finally {
        	try {
        		cn.close();
        		st.close();
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        }
          
   }
}


