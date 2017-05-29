package andiplomacy;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.vdiplomacy.android.DiplomacyClient;

public class DiplomacyClientTest {

	@Test
	public void loginTest() {
		try {
			DiplomacyClient.login("jdk1", "password1");
		} catch (Exception e) {
			Assert.fail("Could not login");
		}
	}
	
	@Test
	public void checkMessagesTest() {
		try {
			if(DiplomacyClient.isConnected()){
				List<Integer> games = DiplomacyClient.getMatches();
				if(games.size()>0){
					DiplomacyClient.checkMessages(games.get(0));
				}
			}
		} catch (Exception e){
			Assert.fail(e.getMessage());
		}
	}
}
