# Two factor authentication service
Java two factor authentication service through SMS using Twilio and Cache2k.

It provides 3 different endpoints:
- api/v1/2fa/validate\_phone\_number: to validate if the received phone number is a mobile phone number. Request payload:
```
{
  "to_phone_number": "+11239895560" //Phone number to be validated
}
```
- api/v1/2fa/send: to send a token to a particular phone number. Request payload:
```
{
  "to_phone_number": "+11239895560" //Phone number to send the token
}
```
- api/v1/2fa/validate\_token: to validate the sent token. Request payload:
```
{
  "to_phone_number": "+11239895560", //Phone number which received the token
  "token":794100                     //Token to be validated
}
```

In order to be used a Twilio account should be created, and application.properties file should be updated with the Twilio sid, token, and from number values.

It also performs base authentication validation using Spring Security, so a strong password should be set in application.properties file, specifically the property _spring.security.user.password_.
