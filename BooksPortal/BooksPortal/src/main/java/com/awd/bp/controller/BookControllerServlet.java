package com.awd.bp.controller;

import java.io.IOException;
import java.time.Year;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.awd.bp.beans.Book;
import com.awd.bp.data.BookDAO;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "BookControllerServlet", value = "/BookControllerServlet")
public class BookControllerServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	BookDAO bookdao;
	
	//@Resource(name = "jdbc/AWD2002")
	//private DataSource datasource;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		bookdao = new BookDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String option = request.getParameter("option");
		
		if(option == null)
			listAllBooks(request,response);
		else if(option.equals("LOAD"))
			bookDetails(request,response);
		else if(option.equals("EDIT"))
			bookEdit(request,response);
		else if(option.equals("DELETE"))
			try {
				bookDelete(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if(option.equals("UPDATE"))
			try {
				bookUdate(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	private void bookEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ISBN = request.getParameter("identifier");
		
		Book myBook = bookdao.getABook(ISBN);
		
		request.setAttribute("theBook", myBook);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/bookEdit.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void bookUdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		
		//From the bookE+dit Form
		
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		String tittle = request.getParameter("Tittle");
		String author = request.getParameter("Author");
		String ISBN = request.getParameter("ISBN");
		String publisher = request.getParameter("Publisher");
		int edition = Integer.parseInt(request.getParameter("Edition"));
		Year year = Year.of(Integer.parseInt(request.getParameter("Year")));
		String genre = request.getParameter("Genre");
		
		Book newBook = new Book(bookID, tittle, author, ISBN, publisher, edition, year, genre);
		
		// perform update on database
		//bookdao.updateBook(newBook);
		
		// send them back to the "list Books" page
		listAllBooks(request, response);
		
	}
	
	private void bookDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {

			// read Book id from form data
			int id = Integer.parseInt(request.getParameter("identifier"));
			
			System.out.println("*******  The ID is "+ id);
			
			// delete Book from database
			bookdao.deleteBook(id);
			
			// send them back to "list Books" page
			listAllBooks(request, response);
		}

	private void listAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Book> books = bookdao.getAllBooks();
		
		request.setAttribute("Book_List", books);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/allBooks.jsp");
		dispatcher.forward(request, response);
		
	}
	private void bookDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("identifier");
		
		Book myBook = bookdao.getABook(email);
		
		request.setAttribute("theBook", myBook);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/bookDetails.jsp");
		dispatcher.forward(request, response);
		
	}
}
