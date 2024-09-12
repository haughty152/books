 package com.awd.bp.beans;

import java.io.Serializable;
import java.time.Year;

public class Book implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	int bookID;
	String tittle;
	String author;
	String ISBN;
	String publisher;
	int edition;
	Year year;
	String genre;
	
	public Book() {	
	}
	
	public Book(int bookID, String tittle, String author, String iSBN, String publisher, int edition, Year year,
			String genre) {
		super();
		this.bookID = bookID;
		this.tittle = tittle;
		this.author = author;
		this.ISBN = iSBN;
		this.publisher = publisher;
		this.edition = edition;
		this.year = year;
		this.genre = genre;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	

}
