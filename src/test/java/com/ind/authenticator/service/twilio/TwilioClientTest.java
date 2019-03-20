package com.ind.authenticator.service.twilio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;
import com.twilio.http.TwilioRestClient;

public final class TwilioClientTest {
  
  @Spy
  private TwilioClient subject;
  
  @Before
  public void setUp() {
    this.subject = new TwilioClient("accountSid", "accoundToken", "fromNumber");
  }
  
  @Test
  public void test_getTwilioRestClient() {
    TwilioRestClient result = this.subject.getTwilioRestClient();
    
    Assert.assertNotNull(result);
  }

}
