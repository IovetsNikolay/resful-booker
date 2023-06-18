package services;

import org.junit.Assert;
import org.junit.Test;
import restassured.Client;

public class PingTest {

    @Test
    public void pingBookingService() {
        Client client = new Client();
        String pingMessage = client.ping.pingHealthCheck();
        Assert.assertEquals("Ping message incorrect", "Created", pingMessage);
    }

}
