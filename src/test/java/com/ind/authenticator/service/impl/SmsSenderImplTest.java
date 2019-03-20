package com.ind.authenticator.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.ind.authenticator.service.TokenCache;
import com.ind.authenticator.service.TokenGenerator;
import com.ind.authenticator.service.impl.SmsSenderImpl;
import com.ind.authenticator.service.twilio.TwilioClient;

public final class SmsSenderImplTest {
  
  @Mock
  private TwilioClient twilioClient;
  
  @Mock
  private TokenGenerator tokenGenerator;
  
  @Mock
  private TokenCache tokenCache;
  
  private SmsSenderImpl subject;
  
  private final String phoneNumber = "+1555444888";
  
  private final int token = 123456;
  
  private final String messageTemplate = "Please insert this token %d";
  
  private final String message = String.format(this.messageTemplate, this.token);
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    
    this.subject = new SmsSenderImpl(
        this.twilioClient, this.tokenGenerator, this.tokenCache, this.messageTemplate);
    
    Mockito.when(this.tokenGenerator.generateToken()).thenReturn(this.token);
  }
  
  @After
  public void verify() {
    Mockito.verify(this.tokenGenerator).generateToken();
    Mockito.verify(this.twilioClient).sendSms(this.phoneNumber, this.message);
  }
  
  @Test
  public void test_sendSms_when_success() {
    Mockito.when(
        this.twilioClient.sendSms(this.phoneNumber, this.message))
    .thenReturn(true);
    
    Assert.assertTrue(this.subject.sendSms(this.phoneNumber));
    
    Mockito.verify(this.tokenCache).saveTokenInCache(this.phoneNumber, this.token);
  }
  
  @Test
  public void test_sendSms_when_unsuccess() {
    Mockito.when(
        this.twilioClient.sendSms(this.phoneNumber, this.message))
    .thenReturn(false);
    
    Assert.assertFalse(this.subject.sendSms(this.phoneNumber));
    
    Mockito.verifyZeroInteractions(this.tokenCache);
  }

}
