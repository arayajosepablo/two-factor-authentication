package com.ind.authenticator.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PhoneValidatorRequestDto {
  
  private String toPhoneNumber;

  public String getToPhoneNumber() {
    return toPhoneNumber;
  }

  public void setToPhoneNumber(String toPhoneNumber) {
    this.toPhoneNumber = toPhoneNumber;
  }

}
