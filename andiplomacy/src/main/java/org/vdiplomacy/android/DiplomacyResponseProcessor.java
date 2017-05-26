package org.vdiplomacy.android;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DiplomacyResponseProcessor {

	public static boolean areNewMessagesPresent(DiplomacyResponse response, int matchid){
		try{
			Document doc = Jsoup.parse(response.getBody());
			Elements select = doc.select("a[gameid=\""+matchid+"\"] img");
			Elements notify = select.select("[alt=\"New Messages\"");
			return !notify.isEmpty();
		} catch (IOException ex){
			return false;
		}
	}
	
}
