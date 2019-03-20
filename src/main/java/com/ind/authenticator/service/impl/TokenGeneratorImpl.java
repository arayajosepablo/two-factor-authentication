package com.ind.authenticator.service.impl;

import java.security.SecureRandom;
import org.springframework.stereotype.Service;
import com.ind.authenticator.service.TokenGenerator;

@Service
public class TokenGeneratorImpl implements TokenGenerator {
  
  private static final int TOKEN_BOUND = 1000000;
  private static final int TOKEN_ORIGIN = 100000;
  
  @Override
  public int generateToken() {
    return new SecureRandom().nextInt(TOKEN_BOUND - TOKEN_ORIGIN) + TOKEN_ORIGIN;
  }

}
