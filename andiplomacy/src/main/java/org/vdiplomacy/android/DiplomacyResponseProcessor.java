package org.vdiplomacy.android;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DiplomacyResponseProcessor {

	private static Elements select(DiplomacyResponse response, String selector){
		String body = response.getBody();
		if(body!=null){
			Document doc = Jsoup.parse(body);
			return doc.select(selector);
		} else {
			return null;
		}
	}
	
	private static Elements select(Elements select, String selector){
		if(select.isEmpty()){
			return select;
		}
		return select.select(selector);
	}
	
	public static boolean areNewMessagesPresent(DiplomacyResponse response, int matchid){
		Elements messageTab = select(response, "a[gameid=\""+matchid+"\"] img");
		Elements notify = select(messageTab, "[alt=\"New Messages\"]");
		return !notify.isEmpty();
	}
	
	public static List<Integer> parseMatches(DiplomacyResponse response){
		Elements gamePanel = select(response, ".gamePanelHome");
		ArrayList<Integer> games = new ArrayList<Integer>();
		for(Element game: gamePanel){
			Integer gameID= Integer.parseInt(game.attributes().get("gameID"));
			games.add(gameID);
		}
		return games;
	}
	
}
