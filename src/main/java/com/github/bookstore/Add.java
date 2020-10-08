package com.github.bookstore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.bookstore.client.BookManagementClient;
import com.github.bookstore.entities.BookDetail;
import com.github.bookstore.entities.BookResponse;

public class Add extends HttpServlet {

	BookManagementClient bookManagementClient = new BookManagementClient();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Creating book....");
		String bookname = request.getParameter("name");
		System.out.println("Given book name: " + bookname);
		String bookdesc = request.getParameter("description");
		System.out.println("Given book description: " + bookdesc);
		// call client api to create book
		BookDetail bookDetail = new BookDetail();
		bookDetail.setName(bookname);
		bookDetail.setDescription(bookdesc);
		BookResponse bookResponse = bookManagementClient.createBook(bookDetail);
		
		PrintWriter print = response.getWriter();
		print.println("<html>");
		print.println("<body>");
		print.println("<h3>");
		print.println("Status");
		print.println("</h3>");
		print.println("<h3>");
		print.println(bookResponse.getStatusDetail().getMessage());
		print.println("</h3>");
		print.println("</body>");
		print.println("</html>");
		System.out.println("Book created");
	}
	
}
