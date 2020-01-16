import java.net.*;
import java.sql.*;
import java.text.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

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
              System.out.print("\nSQL: database connection error.\n");
              System.out.println(e.toString());
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

        public boolean customerCheck() {
                       String            queryText = "";     // The SQL text.
                       PreparedStatement querySt   = null;   // The query handle.
                       ResultSet         answers   = null;   // A cursor.

                       boolean           inDB      = false;  // Return.

                     System.out.println("**********YRB ONLINE BOOKSTORE**************");
                     System.out.println("Enter the id of the customer:");
                      Scanner sc = new Scanner(System.in);
                         int id = sc.nextInt();

                       queryText =
                           "SELECT name       "
                         + "FROM yrb_customer "
                         + "WHERE cid = ?     ";

                       // Prepare the query.
                       try {
                           querySt = conDB.prepareStatement(queryText);
                       } catch(SQLException e) {
                           System.out.println("SQL#1 failed in prepare");
                           System.out.println(e.toString());
                           System.exit(0);
                       }

                     // Execute the query.
                       try {
                           querySt.setInt(1, custID.intValue());
                           answers = querySt.executeQuery();
                       } catch(SQLException e) {
                           System.out.println("SQL#1 failed in execute");
                           System.out.println(e.toString());
                           System.exit(0);
                       }

                       // Any answer?
                       try {
                           if (answers.next()) {
                               inDB = true;
                               custName = answers.getString("name");
                           } else {
                               inDB = false;
                               custName = null;
                           }
                       } catch(SQLException e) {
                           System.out.println("SQL#1 failed in cursor.");
                           System.out.println(e.toString());
                           System.exit(0);
                       }

                       // Close the cursor.
                       try {
                         answers.close();
                       } catch(SQLException e) {
                           System.out.print("SQL#1 failed closing cursor.\n");
                           System.out.println(e.toString());
                           System.exit(0);
                       }
                       try {
                           querySt.close();
                       } catch(SQLException e) {
                           System.out.print("SQL#1 failed closing the handle.\n");
                           System.out.println(e.toString());
                           System.exit(0);
                       }

                       return inDB;
                   }

        public static void main(String[] args) {
                CustTotal ct = new CustTotal(args);

              customerCheck();

              }
}
