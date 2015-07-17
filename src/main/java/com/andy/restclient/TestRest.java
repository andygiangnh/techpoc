package com.andy.restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * @author hganguyen
 *
 */
public class TestRest {
	public static void main(String[] args) throws Exception {
		restPost();
		restGet();
	}
	
	public static void restGet() throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(
				"http://localhost:8080/RESTfulExample/rest/hello/postman");
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}
	}

	public static void restPost() throws JSONException, ClientProtocolException, IOException{
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:8080/RESTfulExample/rest/hello/post");
		  
		JSONObject json = new JSONObject();
		json.put("description", "mathematics problem with circle");
		json.put("year", "1999");
		json.put("title", "circle");
		json.put("movieRow", "3.5");
		json.put("dustinRating", "5");
		
		StringEntity se = new StringEntity( json.toString());
		se.setContentType("application/json");
		post.setEntity(se);
		
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
		   System.out.println(line);
		}
	}
}
