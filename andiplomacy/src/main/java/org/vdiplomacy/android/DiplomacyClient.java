package org.vdiplomacy.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * This example demonstrates basics of request execution with the HttpClient
 * fluent API.
 */
public class DiplomacyClient {

	private	static String httpURL = null;
	private static String cookie = null;
	private static boolean loggedIn = false;
	private static final String PROPERTY_FILE = "server.conf";
	
	public static boolean login(String user, String password) throws Exception{
		HttpURLConnection request = connect();
		request.setRequestMethod("POST");
		request.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(request.getOutputStream());
		writer.write("loginuser="+user+"&loginpass="+password);
		writer.flush();
		DiplomacyResponse response = new DiplomacyResponse(request);
		cookie = response.getCookie();
		loggedIn = (cookie!=null);
		return loggedIn;
	}
	
	public static List<Integer> getMatches() throws DiplomacyAuthenticationException{
		if(loggedIn){
			DiplomacyResponse response = new DiplomacyResponse(connect(), cookie);
			return DiplomacyResponseProcessor.parseMatches(response);
		} else {
			throw new DiplomacyAuthenticationException();
		}
	}
	
	public static boolean checkMessages(int matchid) throws DiplomacyAuthenticationException {
		if(loggedIn){
			DiplomacyResponse response = new DiplomacyResponse(connect(), cookie);
			return DiplomacyResponseProcessor.areNewMessagesPresent(response, matchid);
		} else {
			throw new DiplomacyAuthenticationException();
		}
	}

	private static void init(){
		try {
		if(httpURL == null){
				ClassLoader classLoader = DiplomacyClient.class.getClassLoader();
				InputStream input = classLoader.getResourceAsStream(PROPERTY_FILE);
				Properties prop = new Properties();
				prop.load(input);
				httpURL = prop.getProperty("url");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HttpURLConnection connect() throws DiplomacyAuthenticationException{
		init();
		try {
			URL myurl = new URL(httpURL);
			//if you need a proxy...
	//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
	//				"127.0.0.1", 800));
	//		HttpURLConnection con = (HttpURLConnection) myurl.openConnection(proxy);
			HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
			return con;
		} catch (IOException e) {
			throw new DiplomacyAuthenticationException();
		}
	}
	
	public static boolean isConnected(){
		return loggedIn;
	}
	
}
