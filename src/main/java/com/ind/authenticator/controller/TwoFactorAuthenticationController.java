package com.ind.authenticator.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ind.authenticator.dto.PhoneValidatorResponseDto;
import com.ind.authenticator.dto.TokenRequestDto;
import com.ind.authenticator.dto.TokenResponseDto;
import com.ind.authenticator.dto.TokenValidationRequestDto;
import com.ind.authenticator.dto.TokenValidationResponseDto;
import com.ind.authenticator.service.PhoneValidator;
import com.ind.authenticator.service.SmsSender;
import com.ind.authenticator.service.TokenValidator;

@RestController
@RequestMapping("api/v1/2fa")
public class TwoFactorAuthenticationController {
  
  private final SmsSender smsSender;
  
  private final PhoneValidator phoneValidator;
  
  private final TokenValidator tokenValidator;
  
  @Autowired
  public TwoFactorAuthenticationController(final SmsSender smsSender,
      final PhoneValidator phoneValidator,
      final TokenValidator tokenValidator) {
    this.smsSender = smsSender;
    this.phoneValidator = phoneValidator;
    this.tokenValidator = tokenValidator;
  }
  
  @RequestMapping(value="/send", method=RequestMethod.POST)
  public TokenResponseDto sendSms(@Valid @RequestBody final TokenRequestDto tokenRequestDto) {
    return new TokenResponseDto(this.smsSender.sendSms(tokenRequestDto.getToPhoneNumber()));
  }
  
  @RequestMapping(value="/validate_phone_number", method=RequestMethod.POST)
  public PhoneValidatorResponseDto isMobileNumber(@Valid @RequestBody final TokenRequestDto tokenRequestDto) {
    return new PhoneValidatorResponseDto(
        this.phoneValidator.isMobileNumber(tokenRequestDto.getToPhoneNumber()));
  }
  
  @RequestMapping(value="/validate_token", method=RequestMethod.POST)
  public TokenValidationResponseDto validateToken(@Valid @RequestBody final TokenValidationRequestDto tokenValidationDto) {
    return new TokenValidationResponseDto(
        this.tokenValidator.isValidToken(tokenValidationDto.getToPhoneNumber(), tokenValidationDto.getToken()));
    
  }

}
