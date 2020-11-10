package com.example.tictactoe.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class HttpConnection {
	private final String host;
	private final int port;
	
	public HttpConnection(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public String getRequest(String resource, Map<String, String> queryParams) {
		String jsonResult = null;
		
		// Create a URL Object 
		try {
			URL url = new URL(getCompleteURL(resource+getQueryParams(queryParams)));
			
			// Open a connection
			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			
			// Set the request method
			con.setRequestMethod("GET");
			
			// Set the Request Content-Type Header Parameter
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			
			// Set Response Format Type
			con.setRequestProperty("Accept", "application/json");

			// Read the Response from Input Stream
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
				try(BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getInputStream(), "utf-8"))) {
					StringBuilder response = new StringBuilder();
					String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				    	response.append(responseLine.trim());
				    }
				    jsonResult = response.toString();
				}
			else
				try(BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getErrorStream(), "utf-8"))) {
					StringBuilder response = new StringBuilder();
					String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				    	response.append(responseLine.trim());
				    }
				    jsonResult = response.toString();
				}
			
		} catch (MalformedURLException e) {
			System.err.println("Url mal-formada");
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Falha na comuniação!");
			System.exit(-1);
		}
		
		return jsonResult;
	}

	public String postRequest(String resource, Map<String, String> bodyParams) {
		String jsonResult = null;
		try {
			// Create a URL Object 
			URL url = new URL(getCompleteURL(resource));
			
			// Open a connection
			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			
			// Set the request method
			con.setRequestMethod("POST");
			
			// Set the Request Content-Type Header Parameter
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			
			// Set Response Format Type
			con.setRequestProperty("Accept", "application/json");

			// Ensure the Connection Will Be Used to Send Content
			con.setDoOutput(true);

			// Create the Request Body
			String jsonInputString = getBodyParamsJSON(bodyParams);
			try(OutputStream os = con.getOutputStream()) {
			    byte[] input = jsonInputString.getBytes("utf-8");
			    os.write(input, 0, input.length);			
			}
			
			// Read the Response from Input Stream
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
				try(BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getInputStream(), "utf-8"))) {
					StringBuilder response = new StringBuilder();
					String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				    	response.append(responseLine.trim());
				    }
				    jsonResult = response.toString();
				}
			else
				try(BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getErrorStream(), "utf-8"))) {
					StringBuilder response = new StringBuilder();
					String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				    	response.append(responseLine.trim());
				    }
				    jsonResult = response.toString();
				}
			
		} catch (MalformedURLException e) {
			System.err.println("Url mal-formada");
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Falha na comuniação!");
			System.exit(-1);
		}
		
		return jsonResult;
	}
	
	private String getBodyParamsJSON(Map<String,String> bodyParams) {
		if(bodyParams == null || bodyParams.isEmpty())
			return "";
		
		String json = "{";
		for(String key : bodyParams.keySet()) {
			json += "\"" + key + "\":\"" + bodyParams.get(key) + "\"";
			json += ",";
		}
		json = json.substring(0, json.length()-1);
		json += "}";
		
		return json;
	}
	
	private String getQueryParams(Map<String,String> queryParams) {
		if(queryParams == null || queryParams.isEmpty())
			return "";
		
		String params = "?";
		for(String key : queryParams.keySet()) {
			params += key + "=" + queryParams.get(key) + "&";
		}
		params = params.substring(0, params.length()-1);
		return params;
	}
	
	private String getCompleteURL(String resource) {
		return "http://" + host + ":" + port + resource;
	}
}
