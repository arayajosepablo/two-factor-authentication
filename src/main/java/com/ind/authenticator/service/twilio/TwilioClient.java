package com.ind.authenticator.service.twilio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.lookups.v1.PhoneNumber;

@Component
public class TwilioClient {
  
  private final String accountSid;
  
  private final String accountToken;
  
  private static final String ACCEPTED_PHONE_TYPE = "mobile";
  
  private static final String QUEUED = "queued";
  
  private final String fromNumber;
  
  @Autowired
  public TwilioClient(@Value("${twilio.account.sid}") final String accountSid,
      @Value("${twilio.account.token}") final String accountToken,
      @Value("${twilio.from.number}") final String fromNumber) {
    this.accountSid = accountSid;
    this.accountToken = accountToken;
    this.fromNumber = fromNumber;
    
    Twilio.init(this.accountSid, this.accountToken);
  }
  
  public TwilioRestClient getTwilioRestClient() {
    return new TwilioRestClient
      .Builder(this.accountSid, this.accountToken).build();
  }
  
  public boolean isMobileNumber(String phoneNumber) {
    PhoneNumber number = this.performLookup(phoneNumber);
    return ACCEPTED_PHONE_TYPE.equalsIgnoreCase(number.getCarrier().get("type"));
  }
  
  public boolean sendSms(final String toNumber, final String sms) {
    if(this.isMobileNumber(toNumber)) {
      Message message = Message.creator(
          new com.twilio.type.PhoneNumber(toNumber),
          new com.twilio.type.PhoneNumber(this.fromNumber),
          sms)
      .create(this.getTwilioRestClient());
      
      return message.getStatus().toString().equalsIgnoreCase(QUEUED);
    }
    return false;
  }
  
  private PhoneNumber performLookup(String phoneNumber) {
    return PhoneNumber
        .fetcher(new com.twilio.type.PhoneNumber(phoneNumber))
        .setType("carrier")
        .fetch();
  }

}
