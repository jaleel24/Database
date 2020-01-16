import java.net.*;
import java.sql.*;
import java.text.*;
import java.lang.*;
import java.io.*;
import java.util.*;

public class CustTotal {
     private Connection conDB;   // Connection to the database system.
      private String url;         // URL: Which database?

      private Integer custID;     // Who are we tallying?
      private String  custName;   // Name of that customer.

     // Constructor
      public CustTotal (String[] args) {
          // Set up the DB connection.
          try {
              // Register the driver with DriverManager.
              Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
              System.exit(0);
                    } catch (InstantiationException e) {
          e.printStackTrace();
              System.exit(0);
          } catch (IllegalAccessException e) {
              e.printStackTrace();
              System.exit(0);
          }

          // URL: Which database?
          url = "jdbc:db2:c3421a";

          // Initialize the connection.
          try {
            // Connect with a fall-thru id & password
              conDB = DriverManager.getConnection(url);
          } catch(SQLException e) {
              System.out.print("\nSQL: database connection error.\n");             System.out.println(e.toString());
            System.exit(0);
          }

          // Let's have autocommit turned off.  No particular reason here.
          try {
                         conDB.setAutoCommit(false);
              } catch(SQLException e) {
              System.out.print("\nFailed trying to turn autocommit off.\n");
              e.printStackTrace();
              System.exit(0);
                     }
       // Commit.  Okay, here nothing to commit really, but why not...
              try {
                   conDB.commit();
                 } catch(SQLException e) {
                      System.out.print("\nFailed trying to commit.\n");
                      e.printStackTrace();
                      System.exit(0);
                 }
               // Close the connection.
              try {
                  conDB.close();
                  } catch(SQLException e) {
                    System.out.print("\nFailed trying to close the connection.\n");
                    e.printStackTrace();
                    System.exit(0);
                  }
            // return inDB;
        }

        public static void main(String[] args) {
                CustTotal ct = new CustTotal(args);
              }
}
