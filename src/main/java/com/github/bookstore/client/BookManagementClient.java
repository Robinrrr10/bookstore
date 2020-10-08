package com.github.bookstore.client;

import java.io.IOException;
import java.net.http.HttpClient;

import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bookstore.entities.BookDetail;
import com.github.bookstore.entities.BookResponse;

public class BookManagementClient {
	
	String host = "";
	
	public BookManagementClient() {
		host = System.getenv("bookmanagementhost");
		if (host == null || host.equals("")) {
			host = "http://localhost:8080";
		}
	}

	public BookResponse createBook(BookDetail bookDetail) {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		String url = host + "/bookmanagement/v1/book/create";
		CloseableHttpResponse closeableHttpResponse = null;
		 int statusCode = 0;
		 String body = null;
		 BookResponse bookResponse = null;
		try {
			System.out.println("Request url: " + url);
			ObjectMapper objectMapper = new ObjectMapper();
			String string =objectMapper.writeValueAsString(bookDetail);
			System.out.println("Request body: " + string);
			HttpEntity entity = new StringEntity(string, ContentType.APPLICATION_JSON);
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(entity);
			 closeableHttpResponse = closeableHttpClient.execute(httpPost);
			 statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
			 body = EntityUtils.toString(closeableHttpResponse.getEntity());
			 bookResponse =objectMapper.readValue(body, BookResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("StatusCode: " + statusCode);
		System.out.println("response body: " + body);
		return bookResponse;
	}
	
	public BookResponse getAllBooks() {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		String url = host + "/bookmanagement/v1/book/allbooks";
		System.out.println("Request url: " + url);
		HttpGet httpGet  = new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse = null;
		 int statusCode = 0;
		 String body = null;
		 BookResponse bookResponse = null;
		try {
			 closeableHttpResponse = closeableHttpClient.execute(httpGet);
			 statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
			 body = EntityUtils.toString(closeableHttpResponse.getEntity());
			 ObjectMapper objectMapper = new ObjectMapper();
			 bookResponse =objectMapper.readValue(body, BookResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("StatusCode: " + statusCode);
		System.out.println("response body: " + body);
		return bookResponse;
	}
	
}
