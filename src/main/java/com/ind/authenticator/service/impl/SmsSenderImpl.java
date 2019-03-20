package com.ind.authenticator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ind.authenticator.service.SmsSender;
import com.ind.authenticator.service.TokenCache;
import com.ind.authenticator.service.TokenGenerator;
import com.ind.authenticator.service.twilio.TwilioClient;

@Service
public class SmsSenderImpl implements SmsSender {
  
  private final TwilioClient twilioClient;
  
  private final TokenGenerator tokenGenerator;
  
  private final TokenCache tokenCache;
  
  private final String messageTemplate;
  
  @Autowired
  public SmsSenderImpl(final TwilioClient twilioClient,
      final TokenGenerator tokenGenerator, final TokenCache tokenCache,
      @Value("${token.message.template}") final String messageTemplate) {
    this.twilioClient = twilioClient;
    this.tokenGenerator = tokenGenerator;
    this.tokenCache = tokenCache;
    this.messageTemplate = messageTemplate;
  }
  
  @Override
  public boolean sendSms(final String toNumber) {
    final int token = this.tokenGenerator.generateToken();
    
    final boolean isQueued = this.twilioClient.sendSms(
        toNumber, 
        String.format(this.messageTemplate, token));
    
    if(isQueued) {
      this.tokenCache.saveTokenInCache(toNumber, token);
    }
    
    return isQueued;
  }

}
