CREATE DATABASE bobsbooks;
USE bobsbooks;

# GRANT ALL ON bobsbooks.* TO 'bob'@'ubdesk01.mann.home';
# ALL WRONG
#GRANT ALL PRIVILEGES ON bobsbooks TO 'bob'@'127.0.0.1' IDENTIFIED BY 'Pa$$w0rd';
#GRANT ALL PRIVILEGES ON bobsbooks TO 'bob'@'10.%' IDENTIFIED BY 'Pa$$w0rd';
#GRANT ALL PRIVILEGES ON bobsbooks TO 'bob'@'ubdesk01.mann.home' IDENTIFIED BY 'Pa$$w0rd';
# CORRECT
#GRANT ALL ON bobsbooks.* TO 'bob'@'ubdesk01.mann.home' IDENTIFIED BY 'Pa$$w0rd';

CREATE TABLE Customer (
	CustomerID INT PRIMARY KEY NOT NULL,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    EMail VARCHAR(255),
    Phone VARCHAR(255)
    );
#DROP TABLE Customer;    

CREATE TABLE Author (
	AuthorID INT PRIMARY KEY NOT NULL,
    FirstName VARCHAR(255),
    LastName VARCHAR(255)
    );
 #DROP TABLE Author;   
    
CREATE TABLE Book (
	ISBN VARCHAR(255) PRIMARY KEY NOT NULL,
    Title VARCHAR(255),
    PubDate Date, 
    BookFormat VARCHAR(255),
    UnitPrice FLOAT 
);   
#DROP TABLE Book;

CREATE TABLE Books_by_Author (
	AuthorID INT NOT NULL,
    ISBN VARCHAR(255) NOT NULL,
    CONSTRAINT FK_BooksByAuthor_Author FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID),
    CONSTRAINT FK_BooksByAuthor_Book FOREIGN KEY (ISBN) REFERENCES Book(ISBN)
);

# ---------------------------------------------------------------------------
INSERT INTO Customer ( CustomerID, FirstName, LastName, Email, Phone ) VALUES 
( 5000, 'John', 'Smith', 'john.smith@verizon.net', '555-340-1230'),
( 5001, 'Mary', 'Johnson', 'mary.johnson@comcast.net', '555-123-4567'),
( 5002, 'Bob', 'Collins', 'bob.collins@yahoo.com', '555-012-3456'),
( 5003, 'Rebecca', 'Mayer', 'rebecca.mayer@gmail.com', '555-205-8212'),
( 5006, 'Anthony', 'Clark', 'anthony.clark@gmail.com', '555-256-1901'),
( 5007, 'Judy', 'Sousa', 'judy.sousa@verizon.net', '555-751-1207'),
( 5008, 'Christopher', 'Patriquin', 'patriquinc@yahoo.com', '555-316-1803'),
( 5009, 'Debora', 'Smith', 'debsmith@comcast.net', '555-256-3421'),
( 5010, 'Jennifer', 'McGinn', 'jmcginn@comcast.net', '555-250-0918')
;
#SELECT * FROM Customer;
#DELETE FROM Customer;

INSERT INTO Book ( ISBN, Title, PubDate, BookFormat, UnitPrice ) VALUES 
( '142311339X', 'The Lost Hero (Heroes of Olympos, Book 1)', '2010-10-12', 'Hardcover', '10.95'),
( '0689852223', 'The House of the Scorpion', '2002-01-01', 'Hardcover', '16.95'),
( '0525423656', 'Crossed (Matched Trilogy, Boook 2)', '2011-11-01', 'Hardcover', '12.95'),
( '1423153627', 'The Kane Chronicles Survival Guide', '2012-03-01', 'Hardcover', '13.95'),
( '0439371112', 'Howliday Inn', '2001-11-01', 'Paperback', '14.95'),
( '0439861306', 'The Lightning Thief', '2006-03-12', 'Paperback', '11.95'),
( '031673737X', 'How to Train Your Dragon', '2010-02-01', 'Hardcover', '10.95'),
( '0545078059', 'The White Giraffe', '2008-05-01', 'Paperback', '6.95'),
( '0803733428', 'The Last Leopard', '2009-03-05', 'Hardcover', '13.95'),
( '9780545236', 'Freaky Monday', '2010-01-15', 'Paperback', '12.95')
;

 SELECT * FROM Book;
 #DELETE FROM Book;
 
 INSERT INTO Author ( AuthorID, FirstName, LastName ) VALUES 
 ( '1000', 'Rick', 'Riordian' ),
 ( '1001', 'Nancy', 'Farmer' ),
 ( '1002', 'Ally', 'Condie' ),
 ( '1003', 'Cressida', 'Cowell' ),
 ( '1004', 'Lauren', 'St. John' ),
 ( '1005', 'Eoin', 'Colfer' ),
 ( '1006', 'Esther', 'Freisner' ),
 ( '1007', 'Chris', 'D\'lacey' ),
 ( '1008', 'Mary', 'Rodgers' ),
 ( '1009', 'Heather', 'Hatch' )
 ;
 
 SELECT * FROM Author;
 
 INSERT INTO Books_by_Author ( AuthorID, ISBN ) VALUES 
( '1000', '142311339X'),
( '1001', '0689852223'),
( '1002', '0525423656'),
( '1000', '1423153627'),
( '1003', '031673737X'),
( '1004', '0545078059'),
( '1004', '0803733428'),
( '1008', '9780545236'),
( '1009', '9780545236')
;

SELECT * FROM Books_by_Author;

# --------------------------------------------------------------------
# DROP USER bob@127.0.0.1
# DROP USER bob@ubdesk01.mann.home;
# DROP USER 'bob'@'10.%';
# GRANT ALL ON bobsbooks.* TO 'bob'@'ubdesk01.mann.home';


