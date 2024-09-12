package com.awd.bp.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.awd.bp.beans.Book;

public class BookDAO {
	
	private DataSource datasource;
	
	/*public BookDAO(DataSource dataSource)
	{
		datasource = dataSource;
	}*/
	public BookDAO()
	{}
	
	/**
	 * Gets a list of all Books in the database and return a List object
	 */
	public List<Book> getAllBooks(){
		
		List<Book> BookList = new ArrayList<>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		String databaseURL="jdbc:mysql:http://localhost/phpmyadmin/book";
		String driverName="com.mysql.jdbc.Driver";
		 
		try {
			
			
			Class.forName (driverName);
			connection = DriverManager.getConnection (databaseURL, "root", "");
			
			//System.out.println("connected to.."+databaseURL);
			
			statement = connection.createStatement();
			String sql = "Select * From Book_Table Order by Title";
			resultset = statement.executeQuery(sql);
			
			while(resultset.next()) {
				
				
				int bookID = resultset.getInt("BookID");
				String tittle = resultset.getString("Title");
				String author = resultset.getString("Author");
				String ISBN = resultset.getString("ISBN");
				String publisher = resultset.getString("Publisher");
				int edition = resultset.getInt("Edition");
				Year year = Year.of(resultset.getInt("Year"));
				String genre = resultset.getString("Genre");
				
				Book book = new Book(bookID, tittle, author, ISBN, publisher, edition, year, genre);
				
				BookList.add(book);
				
			}
			
			return BookList;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		finally {
		
			closeSQL(connection, statement, resultset);
			
		}	
	}
	public List<Book> getBookByTittle(String bookTittle){
		
		List<Book> BookList = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		try {
			
			Context context = new InitialContext();
	       // DataSource datasource = (DataSource)context.lookup("java:comp/env/name of the database);
			
			connection = datasource.getConnection();
			
			statement = connection.prepareStatement("Select * From Book_Table WHERE Tittle = ?");
			statement.setString(1, bookTittle);
			
			resultset = statement.executeQuery();
			
			while(resultset.next()) {
				
				
				int bookID = resultset.getInt("BookID");
				String tittle = resultset.getString("Tittle");
				String author = resultset.getString("Author");
				String ISBN = resultset.getString("ISBN");
				String publisher = resultset.getString("Publisher");
				int edition = resultset.getInt("Edition");
				Year year = Year.of(resultset.getInt("Year"));
				String genre = resultset.getString("Genre");
				
				Book book = new Book(bookID, tittle, author, ISBN, publisher, edition, year, genre);
				
				BookList.add(book);
				
			}
			
			return BookList;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		finally {
		
			closeSQL(connection, statement, resultset);
			
		}	
	}



	public Book getABook(String bookISBN){
	
		Book bookInstance = null;
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		try {
			
			Context context = new InitialContext();
	        DataSource datasource = (DataSource)context.lookup("java:comp/env/jdbc/AWD2022");
			
			connection = datasource.getConnection();
			
			String sql = "Select * From Book_Table where ISBN=?";
			
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, bookISBN);
			
			resultset = statement.executeQuery();
			
			
			if(resultset.next()) {
				
				int bookID = resultset.getInt("BookID");
				String tittle = resultset.getString("Tittle");
				String author = resultset.getString("Author");
				String ISBN = resultset.getString("ISBN");
				String publisher = resultset.getString("Publisher");
				int edition = resultset.getInt("Edition");
				Year year = Year.of(resultset.getInt("Year"));
				String genre = resultset.getString("Genre");
				
				bookInstance = new Book(bookID, tittle, author, ISBN, publisher, edition, year, genre);
				
			}
			
			
			return bookInstance;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;	
		}
		
		finally {
			closeSQL(connection, statement, resultset);	
		}
		
		
		
	}
	
	
	public void addBook(Book theBook) throws Exception {

		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			Context context = new InitialContext();
	        DataSource datasource = (DataSource)context.lookup("java:comp/env/jdbc/AWD2022");
			// get db connection
			connection = datasource.getConnection();
			
			// create sql for insert
			String sql = "Insert into Book_Table "
					   + "(Title, Author, Publisher, Genre, Edition, Year, ISBN)"
					   + "values (?, ?, ?, ?, ?, ?, ?)";
			
			statement = connection.prepareStatement(sql);
			
			// set the param values for the Book
			statement.setString(1, theBook.getTittle());
			statement.setString(2, theBook.getAuthor());
			statement.setString(3, theBook.getPublisher());
			statement.setString(4, theBook.getGenre());
			statement.setInt(5, theBook.getEdition());
			statement.setString(6, theBook.getYear().toString());
			statement.setString(7, theBook.getISBN());
			
			// execute sql insert
			statement.execute();
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			// clean up JDBC objects
			closeSQL(connection, statement, null);
		}
	}
	
	

	public void deleteBook(int BookID) throws Exception {

		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			Context context = new InitialContext();
	        DataSource datasource = (DataSource)context.lookup("java:comp/env/jdbc/AWD2022");
			
			// get connection to database
			connection = datasource.getConnection();
			
			// create sql to delete Book
			String sql = "DELETE FROM Book_Table WHERE BookID=?";
			
			// prepare statement
			statement = connection.prepareStatement(sql);
			
			// set params
			statement.setInt(1, BookID);
			
			// execute sql statement
			statement.execute();
		}
		catch(Exception e) {
			System.out.println("*********\n\n");
			e.printStackTrace();
		}
		finally {
			// clean up JDBC code
			closeSQL(connection, statement, null);
		}	
	}

	private void closeSQL(Connection connection, Statement statement, ResultSet resultset) {
		// TODO Auto-generated method stub
		try {
			if(resultset != null)
				resultset.close();
			
			if(statement != null)
				statement.close();
			
			if(connection != null) // frees the connection for use in connection pulling
				connection.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
}
