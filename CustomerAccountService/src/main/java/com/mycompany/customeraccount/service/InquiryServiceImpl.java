package com.mycompany.customeraccount.service;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import static com.mycompany.customeraccount.constants.InquiryServiceConstants.*;
import com.mycompany.customeraccount.model.AccountInquiryResponse;
import com.mycompany.customeraccount.model.CustomerAccount;
import com.mycompany.customeraccount.repository.CustomerAccountRepository;

/**
 * The Class InquiryServiceImpl
 */
@Service
public class InquiryServiceImpl implements InquiryService{
  
  /** The Constant LOGGER. */
  public static final Logger LOGGER = LogManager.getLogger(RegistrationServiceImpl.class);


  /** The customer account repository. */  
  @Autowired
  private CustomerAccountRepository accountRepo;
  
  /**
   * Inquiries the customer account.
   * 
   * @param customerNumber the customer number
   * @return AccountInquiryResponse the account inquiry response
   */
  @Override
  public AccountInquiryResponse inquireAccount(String customerNumber) {
    AccountInquiryResponse response = new AccountInquiryResponse();
    Long custNum = Long.valueOf(customerNumber);
    try {
      Optional<CustomerAccount> idFound =
          accountRepo.findById(custNum);
       if (idFound.isPresent()) {
         LOGGER.info(CUSTOMER_ACCOUNT_FOUND);
         CustomerAccount customer = idFound.get();
         response.setCustomerAccount(customer);
         response.setTransactionStatusCode(HttpStatus.FOUND.value());
         response.setTransactionStatusDescription(CUSTOMER_ACCOUNT_FOUND);
         return response;
       } else {
         LOGGER.info(CUSTOMER_NOT_FOUND);
         response.setCustomerAccount(null);
         response.setTransactionStatusCode(HttpStatus.NOT_FOUND.value());
         response.setTransactionStatusDescription(CUSTOMER_NOT_FOUND);
         return response;
       }
    } catch (Exception e) {
      LOGGER.info(e.getMessage());
      response.setCustomerAccount(null);
      response.setTransactionStatusCode(HttpStatus.NOT_FOUND.value());
      response.setTransactionStatusDescription(CUSTOMER_NOT_FOUND);
      return response;
    }
  }
  
  
}
