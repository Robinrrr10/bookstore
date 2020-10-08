package com.github.bookstore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.bookstore.client.BookManagementClient;
import com.github.bookstore.entities.BookDetail;
import com.github.bookstore.entities.BookResponse;

public class Show extends HttpServlet {
	
	BookManagementClient bookManagementClient = new BookManagementClient();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("showing all books");
		
		// call some backend api and show all books
		BookResponse bookResponse = bookManagementClient.getAllBooks();
		
		PrintWriter print = response.getWriter();
		print.println("<html>");
		print.println("<body>");
		print.println("<h3>");
		print.println("Below are the books");
		print.println("</h3>");
		print.println("<h4>" + "book name" + "</h4>");
		for(BookDetail bookDetail : bookResponse.getData()) {
			print.println("<h5>" + bookDetail.getName() + "</h5>");
		}
		print.println("</body>");
		print.println("</html>");
		System.out.println("done");
	}
	
}
