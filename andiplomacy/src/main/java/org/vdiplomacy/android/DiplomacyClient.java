package org.vdiplomacy.android;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * This example demonstrates basics of request execution with the HttpClient
 * fluent API.
 */
public class DiplomacyClient {

	private	static String httpsURL = "_INDEX_";
	
	public static void request(String cookie) throws Exception {
		URL myurl = new URL(httpsURL);
		//if you need a proxy...
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
//				"127.0.0.1", 800));
//		HttpURLConnection con = (HttpURLConnection) myurl.openConnection(proxy);
		HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
		con.setRequestProperty("Cookie", cookie);
		InputStream ins = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader in = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		in.close();
		Document doc = Jsoup.parse(sb.toString());
		Elements select = doc.select("a[gameid=\"31137\"] img");
		Elements notify = select.select("[alt=\"New Messages\"");
		if(!notify.isEmpty()){
			System.out.println(notify.toString());
		} else {
			System.out.println("No items found");
		}
	}

	public static void main(String[] args) {
		try {
			DiplomacyClient.request("COOKIECOOKIECOOKIE YEAH");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
