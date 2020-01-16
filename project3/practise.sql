SELECT * FROM yrb_offer where TITLE= 'Humor in SQL'
SELECT * FROM yrb_book
SELECT * FROM yrb_book Where TITLE ='Humor in SQL'
DELETE FROM yrb_book
SELECT * from yrb_club

SELECT * from yrb_member

SELECT * from yrb_customer


select yrb_member.CID FROM yrb_club JOIN yrb_member ON yrb_member.Club= yrb_club.Club
SELECT C.CID,C.NAME FROM yrb_member AS M JOIN yrb_customer AS C ON C.CID=M.CID


SELECT M.CID FROM yrb_member AS M JOIN yrb_purchase AS P ON P.CID=M.CID AND M.Club =P.Club

DELETE  FROM yrb_book WHERE TITLE = 'Humor in SQL'


INSERT INTO yrb_customer(CID,NAME,CITY) VALUES(215,'JALEEL','TORONTO')
SELECT * FROM yrb_member WHERE CID=215

INSERT INTO yrb_customer(CID,NAME,CITY) VALUES(217,'REM','TORONTO')

INSERT INTO yrb_customer(CID,NAME,CITY) VALUES(218,'RE','TONTO')

cid    smallint     not null,
club   varchar(15)  not null,
title  varchar(25)  not null,
year   smallint     not null,
when   timestamp    not null,
qnty   smallint     not null,





INSERT INTO YRB_PURCHASE(cid,club,title,year,when,qnty)values(2000,'English','humor',2000,'2001-07-23-09.27.00.000000',1)
club  varchar(15)  DEFAULT 'Basic',
cid   smallint     not null,

INSERT INTO yrb_member(CLUB,CID) VALUES('',215)

create trigger m_be_club_basic_member
      AFTER INSERT ON YRB_CUSTOMERS REFERENCING NEW ROW AS NEWTUPLE
      FOR EACH ROW
      WHEN(NEWTUPLE.CID NOT IN
      (SELECT CLUB,CID FROM yrb_member))
      INSERT INTO yrb_member(CLUB,CID)
      VALUES('Basic',NEWTUPLE.CID);










































         public boolean customerCheck() {
               String            queryText = "";     // The SQL text.
               PreparedStatement querySt   = null;   // The query handle.
               ResultSet         answers   = null;   // A cursor.

               boolean           inDB      = false;  // Return.

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

               // We're done with the handle.
