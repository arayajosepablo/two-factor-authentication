package com.ind.authenticator.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.ind.authenticator.service.impl.PhoneValidatorImpl;
import com.ind.authenticator.service.twilio.TwilioClient;

public final class PhoneValidatorImplTest {
  
  @Mock
  private TwilioClient twilioClient;
  
  @InjectMocks
  private PhoneValidatorImpl subject;
  
  private static final String PHONE_NUMBER = "+1555444888";
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }
  
  @After
  public void verify() {
    Mockito.verify(this.twilioClient).isMobileNumber(PHONE_NUMBER);
  }
  
  @Test
  public void test_isMobileNumber_when_true() {
    Mockito.when(this.twilioClient.isMobileNumber(PHONE_NUMBER)).thenReturn(true);
    
    Assert.assertTrue(this.subject.isMobileNumber(PHONE_NUMBER)); 
  }
  
  @Test
  public void test_isMobileNumber_when_false() {
    Mockito.when(this.twilioClient.isMobileNumber(PHONE_NUMBER)).thenReturn(false);
    
    Assert.assertFalse(this.subject.isMobileNumber(PHONE_NUMBER));
  }

}
