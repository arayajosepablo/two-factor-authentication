package com.ind.authenticator.controller;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ind.authenticator.dto.PhoneValidatorRequestDto;
import com.ind.authenticator.dto.TokenRequestDto;
import com.ind.authenticator.dto.TokenValidationRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public final class TwoFactorAuthenticationControllerIT {
  
  private static final String BASE_URL = "/api/v1/2fa%s";
  
  ObjectMapper mapper = new ObjectMapper();
  
  @Autowired
  private MockMvc mockMvc;
  
  @Test
  @WithMockUser(value = "tester")
  public void test_isMobileNumber_when_true() throws Exception {
    PhoneValidatorRequestDto payload = new PhoneValidatorRequestDto();
    payload.setToPhoneNumber("+50688886655");
    
    this.mockMvc.perform(
        MockMvcRequestBuilders.post(String.format(BASE_URL, "/validate_phone_number"))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(payload)))
    .andExpect(MockMvcResultMatchers.status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("valid", Matchers.is(true)));
  }
  
  @Test
  @WithMockUser(value = "tester")
  public void test_isMobileNumber_when_false() throws Exception {
    PhoneValidatorRequestDto payload = new PhoneValidatorRequestDto();
    payload.setToPhoneNumber("+50622334488");
    
    this.mockMvc.perform(
        MockMvcRequestBuilders.post(String.format(BASE_URL, "/validate_phone_number"))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(payload)))
    .andExpect(MockMvcResultMatchers.status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("valid", Matchers.is(false)));
  }
  
  @Test
  @Ignore
  @WithMockUser(value = "tester")
  public void test_sendSms_when_success() throws Exception {
    TokenRequestDto payload = new TokenRequestDto();
    payload.setToPhoneNumber("+50688553334");
    
    this.mockMvc.perform(
        MockMvcRequestBuilders.post(String.format(BASE_URL, "/send"))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(payload)))
    .andExpect(MockMvcResultMatchers.status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("successful", Matchers.is(true)));
  }
  
  @Test
  @WithMockUser(value = "tester")
  public void test_sendSms_when_unsuccess() throws Exception {
    TokenRequestDto payload = new TokenRequestDto();
    payload.setToPhoneNumber("+50622334488");
    
    this.mockMvc.perform(
        MockMvcRequestBuilders.post(String.format(BASE_URL, "/send"))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(payload)))
    .andExpect(MockMvcResultMatchers.status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("successful", Matchers.is(false)));
  }
  
  @Test
  @WithMockUser(value = "tester")
  public void test_validateToken_when_unsuccess() throws Exception {
    TokenValidationRequestDto payload = new TokenValidationRequestDto();
    payload.setToPhoneNumber("+50622334488");
    payload.setToken(12345);
    
    this.mockMvc.perform(
        MockMvcRequestBuilders.post(String.format(BASE_URL, "/validate_token"))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(payload)))
    .andExpect(MockMvcResultMatchers.status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("successful", Matchers.is(false)));
  }
}
