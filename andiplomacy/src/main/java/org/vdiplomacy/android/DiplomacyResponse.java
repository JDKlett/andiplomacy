package org.vdiplomacy.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

public class DiplomacyResponse {

	private URLConnection connection = null;
	private String cookie = null;
	
	public DiplomacyResponse(URLConnection connection){
		this.connection = connection;
	}
	
	public DiplomacyResponse(URLConnection connection, String cookie){
		this(connection);
		this.cookie = cookie;
	}
	
	public  String getCookie(){
		int i = 0;
		String headerField = null;
		String cookie = null;
		while((headerField = connection.getHeaderField(i))!=null && !headerField.contains("wD-Key")){
			i++;
		}
		if(headerField!=null){
			String[] parsing = headerField.split(";");
			if(parsing!=null && parsing.length>0){
				for(String c: parsing){
					if(c.contains("wD-Key")){
						cookie = c;
					}
				}
			}
		}
		return cookie;
	}
	
	public String getBody() throws IOException{
		connection.addRequestProperty("Cookie", cookie);
		InputStream ins = connection.getInputStream();
		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader in = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		in.close();
		return sb.toString();
	}
	
}
