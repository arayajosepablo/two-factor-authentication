package com.ind.authenticator.service;

public interface TokenValidator {
  
  boolean isValidToken(String key, int token);

}
