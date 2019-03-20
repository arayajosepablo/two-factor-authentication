package com.ind.authenticator.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.ind.authenticator.service.TokenCache;
import com.ind.authenticator.service.impl.TokenValidatorImpl;

public final class TokenValidatorImplTest {
  
  @Mock
  private TokenCache tokenCache;
  
  @InjectMocks
  private TokenValidatorImpl subject;
  
  private final String phoneNumber = "+1555444888";
  
  private final int token = 123456;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }
  
  @After
  public void verify() {
    Mockito.verify(this.tokenCache).containKey(this.phoneNumber);
  }
  
  @Test
  public void test_isValidToken_when_key_does_not_exist() {
    Mockito.when(this.tokenCache.containKey(this.phoneNumber)).thenReturn(false);
    
    Assert.assertFalse(this.subject.isValidToken(this.phoneNumber, this.token));
  }
  
  @Test
  public void test_isValidToken_when_is_valid_token() {
    Mockito.when(this.tokenCache.containKey(this.phoneNumber)).thenReturn(true);
    Mockito.when(this.tokenCache.getToken(this.phoneNumber)).thenReturn(this.token);
    
    Assert.assertTrue(this.subject.isValidToken(this.phoneNumber, this.token));
    Mockito.verify(this.tokenCache).getToken(this.phoneNumber);
  }
  
  @Test
  public void test_isValidToken_when_is_invalid_token() {
    Mockito.when(this.tokenCache.containKey(this.phoneNumber)).thenReturn(true);
    Mockito.when(this.tokenCache.getToken(this.phoneNumber)).thenReturn(678901);
    
    Assert.assertFalse(this.subject.isValidToken(this.phoneNumber, this.token));
    Mockito.verify(this.tokenCache).getToken(this.phoneNumber);
  }

}
