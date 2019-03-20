# Two factor authentication service
Java two factor authentication service through SMS using Twilio and Cache2k.

It provides 3 different endpoints:
- api/v1/2fa/validate\_phone\_number: to validate if the received phone number is a mobile phone number.
- api/v1/2fa/send: to send a token to a particular phone number
- api/v1/2fa/validate\_token: to validate the sent token

Also it implements Spring Security base authentication validation.
