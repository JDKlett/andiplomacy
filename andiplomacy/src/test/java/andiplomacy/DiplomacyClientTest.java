package andiplomacy;


import org.junit.Assert;
import org.junit.Test;
import org.vdiplomacy.android.DiplomacyClient;

public class DiplomacyClientTest {

	@Test
	public void loginTest() {
		try {
			DiplomacyClient.login("user", "password");
			DiplomacyClient.checkMessages();
		} catch (Exception e) {
			Assert.fail("Could not login");
		}
	}

}
