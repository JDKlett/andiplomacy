package org.vdiplomacy.android;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This example demonstrates basics of request execution with the HttpClient
 * fluent API.
 */
public class DiplomacyClient {

	private	static String httpsURL = "_INDEX_";
	private static String cookie = null;
	
	public static void login(String user, String password) throws Exception{
		HttpURLConnection request = connect();
		request.setRequestMethod("POST");
		request.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(request.getOutputStream());
		writer.write("loginuser="+user+"&loginpass="+password);
		writer.flush();
		DiplomacyResponse response = new DiplomacyResponse(request);
		cookie = response.getCookie();
	}
	
	public static boolean checkMessages() throws Exception {
		DiplomacyResponse response = new DiplomacyResponse(connect(), cookie);
		return DiplomacyResponseProcessor.areNewMessagePresent(response);
	}

	public static HttpURLConnection connect() throws Exception{
		URL myurl = new URL(httpsURL);
		//if you need a proxy...
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
//				"127.0.0.1", 800));
//		HttpURLConnection con = (HttpURLConnection) myurl.openConnection(proxy);
		HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
		return con;
	}
	
}
