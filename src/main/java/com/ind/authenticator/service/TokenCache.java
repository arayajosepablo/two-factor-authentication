package com.ind.authenticator.service;

public interface TokenCache {
  
  void saveTokenInCache(String key, int token);
  
  int getToken(String key);
  
  boolean containKey(String key);

}
