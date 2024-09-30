package com.mycompany.customeraccount.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.customeraccount.model.AccountInquiryResponse;
import com.mycompany.customeraccount.model.RegistrationRequest;
import com.mycompany.customeraccount.model.RegistrationResponse;
import com.mycompany.customeraccount.service.InquiryService;
import com.mycompany.customeraccount.service.RegistrationService;

/**
 * The Class CustomerAccountController
 */
@RestController
public class CustomerAccountController {
  
  /** The Constant LOGGER. */
  public static final Logger LOGGER = LogManager.getLogger(CustomerAccountController.class);

  /** The registration service. */
  @Autowired
  private RegistrationService registrationService;

  /** The inquiry service. */
  @Autowired
  private InquiryService inquiryService;

  /**
   * Gets the customer account information.
   * 
   * @param customerNumber the customer number
   * @return
   */
  @GetMapping(path = "/api/v1/account/{customerNumber}")
  public ResponseEntity<AccountInquiryResponse> getCustomerAccountInformation(@PathVariable String customerNumber) {
    AccountInquiryResponse inquiry = inquiryService.inquireAccount(customerNumber);
    ResponseEntity<AccountInquiryResponse> response = null;
    if(HttpStatus.FOUND.value() == inquiry.getTransactionStatusCode()) {
      response = new ResponseEntity<>(inquiry, HttpStatus.FOUND);
    } else if (HttpStatus.NOT_FOUND.value() == inquiry.getTransactionStatusCode()) {
      response = new ResponseEntity<>(inquiry, HttpStatus.NOT_FOUND);
    } 
    return response;
  }

  /**
   * Creates the customer account.
   * 
   * @param request the request
   * @return
   */
  @PostMapping("/api/v1/account")
  public ResponseEntity<RegistrationResponse> createCustomerAccount(@Valid @RequestBody RegistrationRequest request) {
    RegistrationResponse registration = registrationService.registerAccount(request);
    ResponseEntity<RegistrationResponse> response = null;
    if(HttpStatus.CREATED.value() == registration.getTransactionStatusCode()) {
      response = new ResponseEntity<>(registration, HttpStatus.CREATED);
    } else if (HttpStatus.BAD_REQUEST.value() == registration.getTransactionStatusCode()) {
      response = new ResponseEntity<>(registration, HttpStatus.BAD_REQUEST);
    } 
    return response;
  }

}
