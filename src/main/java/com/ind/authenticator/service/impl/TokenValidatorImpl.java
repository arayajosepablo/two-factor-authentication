package com.ind.authenticator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ind.authenticator.service.TokenCache;
import com.ind.authenticator.service.TokenValidator;

@Service
public class TokenValidatorImpl implements TokenValidator {
  
  private final TokenCache tokenCache;
  
  @Autowired
  public TokenValidatorImpl(final TokenCache tokenCache) {
    this.tokenCache = tokenCache;
  }
  
  @Override
  public boolean isValidToken(final String key, final int token) {
    if(this.tokenCache.containKey(key)) {
      return this.tokenCache.getToken(key) == token;
    }
    return false;
  }

}
