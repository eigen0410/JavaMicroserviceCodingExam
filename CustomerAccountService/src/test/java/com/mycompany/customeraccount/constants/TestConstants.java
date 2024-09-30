package com.mycompany.customeraccount.constants;

/**
 * The Class TestConstants
 */
public final class TestConstants {
  
  public static final String ACCOUNT_INQUIRY_SUCCESS = "{\r\n"
      + "    \"customerAccount\": {\r\n"
      + "        \"customerNumber\": 10000000,\r\n"
      + "        \"customerName\": \"Test\",\r\n"
      + "        \"customerMobile\": \"09081234567\",\r\n"
      + "        \"customerEmail\": \"test12345@gmail.com\",\r\n"
      + "        \"address1\": \"test\",\r\n"
      + "        \"address2\": \"test\",\r\n"
      + "        \"savings\": [\r\n"
      + "            {\r\n"
      + "                \"accountNumber\": 10000,\r\n"
      + "                \"accountType\": \"Savings\",\r\n"
      + "                \"availableBalance\": 0\r\n"
      + "            }\r\n"
      + "        ]\r\n"
      + "    },\r\n"
      + "    \"transactionStatusCode\": 302,\r\n"
      + "    \"transactionStatusDescription\": \"Customer Account found\"\r\n"
      + "}";
  
  public static final String ACCOUNT_INQUIRY_FAILED = "{\r\n"
      + "    \"transactionStatusCode\": 404,\r\n"
      + "    \"transactionStatusDescription\": \"Customer not found\"\r\n"
      + "}";
  
  public static final String ACCOUNT_CREATION_SUCCESS = "{\r\n"
      + "    \"customerNumber\": 10000000,\r\n"
      + "    \"transactionStatusCode\": 201,\r\n"
      + "    \"transactionStatusDescription\": \"Customer account created\"\r\n"
      + "}";
  
  public static final String ACCOUNT_CREATION_FAILED = "{\r\n"
      + "    \"transactionStatusCode\": 400,\r\n"
      + "    \"transactionStatusDescription\": \"Email is required field\"\r\n"
      + "}";
  
  public static final String ACCOUNT_CREATION_REQUEST = "{\r\n"
      + "   \"customerName\":\"Test\",\r\n"
      + "   \"customerMobile\":\"09081234567\",\r\n"
      + "   \"customerEmail\": \"test12345@gmail.com\",\r\n"
      + "   \"address1\":\"test\",\r\n"
      + "   \"address2\":\"test\",\r\n"
      + "   \"accountType\": \"S\"\r\n"
      + "}";
  
  public static final String INVALID_CHAR_LENGTH = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
}
