package com.mycompany.customeraccount.controller;

import static com.mycompany.customeraccount.constants.TestConstants.ACCOUNT_CREATION_FAILED;
import static com.mycompany.customeraccount.constants.TestConstants.ACCOUNT_CREATION_REQUEST;
import static com.mycompany.customeraccount.constants.TestConstants.ACCOUNT_CREATION_SUCCESS;
import static com.mycompany.customeraccount.constants.TestConstants.ACCOUNT_INQUIRY_FAILED;
import static com.mycompany.customeraccount.constants.TestConstants.ACCOUNT_INQUIRY_SUCCESS;
import static org.mockito.ArgumentMatchers.anyString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import com.google.gson.Gson;
import com.mycompany.customeraccount.model.AccountInquiryResponse;
import com.mycompany.customeraccount.model.RegistrationRequest;
import com.mycompany.customeraccount.model.RegistrationResponse;
import com.mycompany.customeraccount.service.InquiryService;
import com.mycompany.customeraccount.service.RegistrationService;

/**
 * The Class CustomerAccountControllerTest
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerAccountControllerTest {

  /** The registration service. */
  @Mock
  RegistrationService registrationService;
  /** The inquiry service. */
  @Mock
  InquiryService inquiryService;
  /** The controller. */
  @InjectMocks
  CustomerAccountController controller;
  /** The gson. */
  private Gson gson = new Gson();

  /**
   * The setUp.
   * 
   */
  @BeforeEach
  void setUp() {
    ReflectionTestUtils.setField(controller, "registrationService", registrationService);
    ReflectionTestUtils.setField(controller, "inquiryService", inquiryService);
  }

  /**
   * Test customer account information success.
   * 
   */
  @Test
  void getCustomerAccountInformation_Success() {
    AccountInquiryResponse inquiry = gson.fromJson(ACCOUNT_INQUIRY_SUCCESS, AccountInquiryResponse.class);
    Mockito.when(inquiryService.inquireAccount(anyString())).thenReturn(inquiry);

    ResponseEntity<AccountInquiryResponse> response =
        controller.getCustomerAccountInformation("10000000");
    
    ResponseEntity<AccountInquiryResponse> expectedResponse = new ResponseEntity<>(inquiry, HttpStatus.FOUND);
    Assertions.assertEquals(expectedResponse, response);
  }

  /**
   * Test customer account information fail.
   * 
   */
  @Test
  void getCustomerAccountInformation_Failed() {
    AccountInquiryResponse inquiry = gson.fromJson(ACCOUNT_INQUIRY_FAILED, AccountInquiryResponse.class);
    Mockito.when(inquiryService.inquireAccount(anyString())).thenReturn(inquiry);

    ResponseEntity<AccountInquiryResponse> response =
        controller.getCustomerAccountInformation("asd");
    
    ResponseEntity<AccountInquiryResponse> expectedResponse = new ResponseEntity<>(inquiry, HttpStatus.NOT_FOUND);
    Assertions.assertEquals(expectedResponse, response);
  }
  
  /**
   * Test create customer account success.
   * 
   */
  @Test
  void createCustomerAccount_Success() {
    RegistrationResponse register = gson.fromJson(ACCOUNT_CREATION_SUCCESS, RegistrationResponse.class);
    RegistrationRequest request = gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    
    Mockito.when(registrationService.registerAccount(ArgumentMatchers.any())).thenReturn(register);
    
    ResponseEntity<RegistrationResponse> response =
        controller.createCustomerAccount(request);
    
    ResponseEntity<RegistrationResponse> expectedResponse = new ResponseEntity<>(register, HttpStatus.CREATED);
    Assertions.assertEquals(expectedResponse, response);
  }

  /**
   * Test create customer account fail.
   * 
   */
  @Test
  void createCustomerAccount_Failed() {
    RegistrationResponse register = gson.fromJson(ACCOUNT_CREATION_FAILED, RegistrationResponse.class);
    RegistrationRequest request = gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    Mockito.when(registrationService.registerAccount(ArgumentMatchers.any())).thenReturn(register);

    ResponseEntity<RegistrationResponse> response =
        controller.createCustomerAccount(request);
    
    ResponseEntity<RegistrationResponse> expectedResponse = new ResponseEntity<>(register, HttpStatus.BAD_REQUEST);
    Assertions.assertEquals(expectedResponse, response);
  }

}
