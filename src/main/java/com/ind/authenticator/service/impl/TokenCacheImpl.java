package com.ind.authenticator.service.impl;

import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.ind.authenticator.service.TokenCache;
import java.util.concurrent.TimeUnit;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TokenCacheImpl implements TokenCache {
  
  private static final Cache<String, Integer> CACHE = new Cache2kBuilder<String, Integer>() {}
    .name("token")
    .entryCapacity(Long.MAX_VALUE)
    .expireAfterWrite(2, TimeUnit.MINUTES)
    .build();

  @Override
  public void saveTokenInCache(final String key, final int token) {
    CACHE.put(key, token);
  }
  
  @Override
  public int getToken(final String key) {
    return CACHE.get(key);
  }
  
  @Override
  public boolean containKey(final String key) {
    return CACHE.containsKey(key);
  }
}
