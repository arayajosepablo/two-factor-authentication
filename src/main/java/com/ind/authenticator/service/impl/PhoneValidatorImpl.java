package com.ind.authenticator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ind.authenticator.service.PhoneValidator;
import com.ind.authenticator.service.twilio.TwilioClient;

@Service
public class PhoneValidatorImpl implements PhoneValidator {
  
  private final TwilioClient twilioClient;
  
  @Autowired
  public PhoneValidatorImpl(final TwilioClient twilioClient) {
    this.twilioClient = twilioClient;
  }
  
  @Override
  public boolean isMobileNumber(final String phoneNumber) {
    return this.twilioClient.isMobileNumber(phoneNumber);
  }

}
