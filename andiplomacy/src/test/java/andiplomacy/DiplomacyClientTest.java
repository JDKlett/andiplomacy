package andiplomacy;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.vdiplomacy.android.DiplomacyClient;

public class DiplomacyClientTest {

	@Test
	public void loginTest() {
		try {
			Assert.assertFalse(DiplomacyClient.login("jdk5", "password6"));
			Assert.assertTrue(DiplomacyClient.login("jdk5", "password5"));
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
	
	@Test
	public void isValidCookie() {
		try {
			Assert.assertTrue(DiplomacyClient.login(DiplomacyClient.getCookie()));
			Assert.assertFalse(DiplomacyClient.login("WRONG"));
		} catch (Exception e){
			Assert.fail(e.getMessage());
		}
	}
}
