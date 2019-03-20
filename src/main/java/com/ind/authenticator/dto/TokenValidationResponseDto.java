package com.ind.authenticator.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TokenValidationResponseDto {
  
  private final boolean isSuccessful;

  public TokenValidationResponseDto(boolean isSuccessful) {
    this.isSuccessful = isSuccessful;
  }

  public boolean isSuccessful() {
    return isSuccessful;
  }

}
