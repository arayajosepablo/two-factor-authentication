package com.ind.authenticator.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.ind.authenticator.service.impl.TokenCacheImpl;

public final class TokenCacheImplTest {
  
  private TokenCacheImpl subject = new TokenCacheImpl();
  
  private final String key = "+1555444888";
  
  private final int token = 123456;
  
  @Before
  public void setUp() {
    this.subject.saveTokenInCache(this.key, this.token);
  }
  
  @After
  public void after() {
    this.subject = null;
  }
  
  @Test
  public void test_getToken() {
    Assert.assertEquals(this.token, this.subject.getToken(this.key));
  }
  
  @Test
  public void test_containKey_when_true() {
    Assert.assertTrue(this.subject.containKey(this.key));
  }
  
  @Test
  public void test_containKey_when_false() {
    Assert.assertFalse(this.subject.containKey("anotherKey"));
  }

}
