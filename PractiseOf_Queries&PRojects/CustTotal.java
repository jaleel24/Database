/*============================================================================

Name       :jaleel Ahmad Sayal
Student Id :215800493
project    :Application
============================================================================*/
import java.util.*;
import java.net.*;
import java.text.*;
import java.lang.*;
import java.io.*;
import java.sql.*;

/*============================================================================
CLASS CustTotal
============================================================================*/

public class CustTotal {
    private Connection conDB;   // Connection to the database system.
    private String url;         // URL: Which database?

    private Integer custID;     // Who are we tallying?

    public String  custName; // Name of that customer.
    public String  city;

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
        //Initialize the connection.
        try {
            // Connect with a fall-thru id & password
        conDB = DriverManager.getConnection(url);
        } catch(SQLException e) {
            System.out.print("\nSQL: DATABASE CONNECTION ERROR :(.\n");
            System.out.println(e.toString());
            System.exit(0);
        }
        // Let's have autocommit turned off.  No particular reason here.
        try {
            conDB.setAutoCommit(false);
        } catch(SQLException e) {
            System.out.print("\nFAILED TRYING TO TURN AUTCOMIT OFF\n");
            e.printStackTrace();
            System.exit(0);
        }
          customerCheck();
          //  Who are we tallying?   THESE COMMENTS ARE LITERALLY COPIED FROM PARK GODFREYS EXAMPLE
        if (args.length != 1) {
            // Don't know what's wanted.  Bail.
            System.out.println("\nBYE BYE");
            System.exit(0);
        } else {
            try {
                custID = new Integer(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("\nYOU PUT SOMTHING OTHER THAN INTEGER");
                System.out.println("PROVIDE AN INTEGER FOR THE CUSOTOMER NUMBER:");
                System.exit(0);
            }
        // CLOSING THE CONNECTION
        try {
            conDB.close();
        } catch(SQLException e) {
            System.out.print("\nFAILED TRYING TO CLOSE THE CONNECTION\n");
            e.printStackTrace();
            System.exit(0);
        }
      }
    }
public boolean customerCheck(){
          String            queryText = "";     // The SQL text.
          PreparedStatement querySt   = null;   // The query handle.
          ResultSet         answers   = null;   // A cursor.
          Integer id;


          boolean           inDB      = false;  // Return.
          System.out.println("-----------YORK REGIONAL ONLINE BOOKSTORE----------------");
          System.out.println("----------ENTER THE ID OF THE CUSTOMER-----------:");
          Scanner sc = new Scanner(System.in);
          id = sc.nextInt();
          queryText = "Select * from yrb_customer where cid=?" ;
          //prepare the query
          try {
            querySt = conDB.prepareStatement(queryText);
          } catch(SQLException e){
            System.out.println("SQL#1 FAILED IN PREPARE");
            System.out.println(e.toString());
            System.exit(0);
                  }
          try {//
            querySt.setInt(1, id);
            answers = querySt.executeQuery();
          } catch(SQLException e) {
            System.out.println("SQL#1 FAILED IN EXECUTE");
            System.out.println(e.toString());
            System.exit(0);
          }
          try {
            if (answers.next()) {
                inDB = true;
                custID = answers.getInt("cid");
                custName = answers.getString("name");
                city = answers.getString("city");
                System.out.println("THE DATA OF THE PERSON IS AS FOLLOWS :)"+ "\n"+"1 CUSTOMER_ID = " +  custID + "\n" + "2 CUSTOMER_NAME = " +  custName + "\n"+"3 CITY = " + city);

                // ASK A QUESTION IF A USER WANT TO UPDATE CUSTOMER DATA
                System.out.println("WOULD YOU LIKE TO UPDATE THE CUSTOMER DATA ? IF YES PRESS:Y OTHERWISE N ");
                Scanner sc2 = new Scanner(System.in);
                String Input = sc2.nextLine();
                      if (Input.equals("Y") || Input.equals("y")) {
                          System.out.println("\n-------------YOU CHOSE YES YEYYYYYY!!----------------");
                        // HERE WE ARE GOIN TO CALL UPDATECUSTOMER FUNCTION SO THAT WE CAN MAKE CHANGES IN THE FILE
                          UpdateCustomer(id);
                        // AFTER UPDATING THE CUSTOMER ACCORDING THE INFORMATION PROVIDED IN PROJECT 4 PDF WE WILL SHOW THE CATEGORIES OF THE
                          bookCategory(id);
                      } else {
                        System.out.println("HEY LETS CALL CATEGORIES THERE YOU GO:");
                          // AFTER UPDATING THE CUSTOMER ACCORDING THE INFORMATION PROVIDED IN PROJECT 4 PDF WE WILL SHOW THE CATEGORIES OF THE
                       bookCategory(id);
                }
              } else {
              System.out.println("SORRY THE CUSTOMER DOES NOT EXIST IN THE DATABASE \n YOU WANNA GIVE ANOTHER SHOT IF YES PRESS:Y OTHERWISE N  ");
                inDB = false;
                custID = null;
                custName = null;
                city = null;
                customerCheck();
            }
                  }catch(SQLException e) {
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
                  // We're done with the handle.
                  try {
                      querySt.close();
                  } catch(SQLException e) {
                      System.out.print("SQL#1 failed closing the handle.\n");
                      System.out.println(e.toString());
                      System.exit(0);
                  }
                  return inDB;
    }

public void UpdateCustomer(int cid){

      String            queryText = "";     // The SQL text.
      Statement querySt   = null;
      //PreparedStatement querySt=null;   // The query handle.
  		ResultSet result = null; // A cursor.
  		int CID = 0;
  		String Name = "";          //VARIABLE FOR THE NAME YOU WANNA UPDATE
  		String City = "";           //VARIABLE FOR THE CITY YOU WANNA UPDATE
        //TAKE THE USER INPUT FOR THE CHANGES IN THE NAME
          System.out.println("ENTER CHANGES YOU WANNA MAKE:");
          System.out.println("------ENTER THE NEW NAME------: ");

          Scanner inputN = new Scanner(System.in);
          String newName = inputN.nextLine();
          //TAKE THE USER INPUT FOR THE CHANGES IN THE CITY
          System.out.println("------ENTER THE NEW CITY-------: ");
          Scanner inputCity = new Scanner(System.in);
          String newCity = inputCity.nextLine();

          //---- this code was not working and i dont know why i spent literally more than 12 hours I WILL FIX IT ONCE I AM DONE WITH MY FINAL EXAMS
                //queryText  = "UPDATE YRB_CUSTOMER SET NAME = '" + newName + "', CITY = '" + newCity + "' WHERE CID = " + cid;
                //queryText  = "UPDATE YRB_CUSTOMER SET NAME(?,?,?)";
                //Update record set wins=?,losses=? where pid=? and year=?";
                //  queryText  = "UPDATE YRB_CUSTOMER SET NAME=?,CITY=? WHERE CID=?";
            try{
              queryText  = "UPDATE YRB_CUSTOMER SET NAME = '" + newName + "', CITY = '" + newCity + "' WHERE CID = " + cid;
              //queryText = "UPDATE YRB_CUSTOMER SET NAME=?,CITY=? WHERE CID=?";  ---EVEN THIS ONE DID NOT WORK
              querySt = conDB.createStatement();
            }catch(SQLException e) {
                System.out.print("query is wrong\n");
                System.out.println(e.toString());
                System.exit(0);
            }
            try {
                querySt.executeUpdate(queryText);
                queryText ="SELECT * FROM YRB_CUSTOMER WHERE CID =" +cid;
                result = querySt.executeQuery(queryText);
                System.out.println("DATABASE UPDATED SUCCESSFULLY :)");
                while(result.next()){
                    System.out.println("CHANGES THAT YOU MADE");
                    CID = cid;
                    Name = result.getString("NAME");
                    City = result.getString("CITY");
                    System.out.println("CID: " + CID);
                    System.out.println("NAME: " + Name);
                    System.out.println("CITY: " + City);
                  }
              }catch(SQLException e) {
              System.out.println("SOMETHING WENT WRONG");
              System.out.println(e.toString());
              System.exit(0);
            }
}
// USER CAN SELECT THE BOOK WITH PARTICULAR CATEGORY SHOWN ON THE SCREEN
// IT CHECKS WHETHER A BOOK IS VALID OR NOT iT IS CASE SENSITIVE
public void bookSearch(int cid, String s_Cat) {

        String query; // The SQL text.
        ResultSet result = null; // A cursor.
        Statement querySt = null;
        String inputTitle = "";
        String title = "";
        int year = 0;
        String language = "";
        String cat = "";
        int weight = 0;
        boolean validBook = false;
        int counter = 0;
        LinkedHashMap<Integer, LinkedHashMap<String, String>> books = new LinkedHashMap<Integer, LinkedHashMap<String, String>>();

  try {
      System.out.println("WOULD YOU LIKE TO CONTINUE IF YES PRESS:Y OTHERWISE N)");
      Scanner obj = new Scanner(System.in);
      String Continue = obj.nextLine();
      if (Continue.equals("Y") || Continue.equals("y")) {

        // CHECK IF A BOOK IS VALID
        while (!validBook) {
          System.out.println("ENTER THE TITLE OF THE BOOK PLEASE: ");
          Scanner appos = new Scanner(System.in);
          inputTitle = appos.nextLine();
          System.out.println("Title: " + inputTitle);
          String appost[] = inputTitle.split("'");
          if (appost.length == 1) {
            query = "SELECT * FROM YRB_BOOK WHERE TITLE = '" + inputTitle + "' AND CAT = '" + s_Cat
                + "'";
          } else {
            query = "SELECT * FROM YRB_BOOK WHERE TITLE = '" + appost[0] + "''" + appost[1]
                + "' AND CAT = '" + s_Cat + "'";
          }
          querySt = conDB.createStatement();
          result = querySt.executeQuery(query);
          // CHECK BOOK OR CATEGORY EXISTS
          if (!result.next()) {
            System.out.println("SELECTED BOOK AND CATEGORY DOES NOT EXIST");
          } else {
            // FORMAT FOR THE BOOK TO BE DISPLAYED ON THE
            System.out.printf("%20s %20s %10s %10s %10s", "TITLE", "YEAR", "LANGUAGE", "CAT", "WEIGHT");
            System.out.println();
            do {
              LinkedHashMap<String, String> bookData = new LinkedHashMap<String, String>();
              counter++;
              System.out.printf(counter + ": ");
              title = result.getString("TITLE");
              year = result.getInt("YEAR");
              language = result.getString("LANGUAGE");
              cat = result.getString("CAT");
              weight = result.getInt("WEIGHT");
              bookData.put("TITLE", title);
              bookData.put("YEAR", String.valueOf(year));
              bookData.put("LANGUAGE", language);
              bookData.put("CAT", cat);
              bookData.put("WEIGHT", String.valueOf(weight));
              books.put(counter, bookData);
              System.out.printf("%25s %10s %10s %10s %10d", title, year, language, cat, weight);
              System.out.println();
              validBook = true;
          } while (result.next());
          // code to store values of book
          for (Map.Entry<Integer, LinkedHashMap<String, String>> entry : books.entrySet()) {
            int number = entry.getKey();
            LinkedHashMap<String, String> value = entry.getValue();
            title = value.get("TITLE"); //
            year = Integer.parseInt(value.get("YEAR"));
            language = value.get("LANGUAGE");
            cat = value.get("CAT");
            weight = Integer.parseInt(value.get("WEIGHT"));
          }
          System.out.println();
          // user select the book number from list
          System.out.println("SELECT A BOOK NUMBER: ");
          Scanner bnum = new Scanner(System.in);
          int whichBook = bnum.nextInt();

        }
      }
    } else if (Continue.equals("N") || Continue.equals("n")) {
      System.out.println("GOODBYE");
    } else {
      System.out.println("INVALID INPUT SELF DISTRUCTION MODE ON RUN FOR YOUR LIFE ");
    }
  } catch (SQLException e) {
    System.out.println("INVALID INPUT SELF DISTRUCTION MODE ON RUN FOR YOUR LIFE ");
    System.out.println(e.toString());
    System.exit(0);
  }
}
//https://javaconceptoftheday.com/statement-vs-preparedstatement-vs-callablestatement-in-java/

        // IN THE FOLLOWING FUNCTION YOU WILL USER WILL CHOOSE THE CATEGORIES ACCORDING TO THOSE WHICH WILL BE SHOWN ON THE SCREEN
public void bookCategory(int cid) {

        String query;
        ResultSet result = null;
        String s_Cat = "";
        int catNum = 0;
        Statement querySt = null;
        LinkedHashMap<Integer, String> catHashMap = new LinkedHashMap<Integer, String>();
        int counter = 0;
        String getCat = "";
  try {
        System.out.println("WOULD YOU LIKE TO CONTINUE IF YES PRESS:Y OTHERWISE N");
        Scanner obj = new Scanner(System.in);
        String input = obj.nextLine();
        if (input.equals("Y") || input.equals("y")) {
          // the code
          System.out.println("-----------------(BOOK CATEGORIES)-------------------");

          query = "SELECT * FROM YRB_CATEGORY";
          querySt = conDB.createStatement();
          result = querySt.executeQuery(query);
          boolean validCat = false;
          while (result.next()) {
            counter++;
            getCat = result.getString("CAT");
            catHashMap.put(counter, getCat);
            System.out.println(counter + " = " + getCat);
          }
          System.out.println();
          // NUMBER SHOULD BE IN THE RANGE OTHERWISE LOOP WILL NOT RUN
          while (!validCat) {
            System.out.println("-------CHOOSE A CATEGORY NUMBER--------: ");
            Scanner inputNum = new Scanner(System.in);
            catNum = inputNum.nextInt();
            s_Cat = catHashMap.get(catNum);
            if (s_Cat == null) {
              System.out.println("YOU HAVE ENTERED: " + catNum + ". WHICH IS NOT IN RANGE!");
            } else {
              System.out.println( s_Cat + " CATEGORY IS SELECTED.");
              validCat = true;
              // CALLING BOOK FUNCTION TO SELECT THE BOOK WITH PARTICULAR CATEGORY
             bookSearch(cid, s_Cat);
            }
          }
        } else if (input.equals("N") || input.equals("n")) {
          System.out.println("BETTER TRY NEXT TIME");
        } else {
          System.out.println("INVALID INPUT MACHINE WILL EXPLODE SOON RUN");
        }
        } catch (SQLException e) {
          System.out.println("NOT A VALID CATEGORY");
          System.out.println(e.toString());
          System.exit(0);
        }
}

public static void main(String[] args){
        CustTotal ct = new CustTotal(args);
    }
  }
