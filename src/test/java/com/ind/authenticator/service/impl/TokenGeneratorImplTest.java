package com.ind.authenticator.service.impl;

import org.junit.Assert;
import org.junit.Test;
import com.ind.authenticator.service.impl.TokenGeneratorImpl;

public final class TokenGeneratorImplTest {
  
  private final TokenGeneratorImpl subject = new TokenGeneratorImpl();
  
  @Test
  public void test_generateToken() {
    final int token = this.subject.generateToken();
    
    Assert.assertTrue("Token should have 6 digits", token > 99999 && token < 1000000);
  }
}
