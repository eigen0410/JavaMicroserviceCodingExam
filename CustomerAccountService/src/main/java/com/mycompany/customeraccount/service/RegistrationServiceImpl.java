package com.mycompany.customeraccount.service;

import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.ACCOUNT_CREATED_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.ACCOUNT_SUCCESSFULLY_CREATED;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.ACCOUNT_TYPE_REQUIRED_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.ADDRESS1_INVALID_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.ADDRESS1_REQUIRED_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.ADDRESS2_INVALID_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.EMAIL_INVALID_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.EMAIL_REQUIRED_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.INVALID_ACCOUNT_TYPE_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.INVALID_ACCOUNT_TYPE_MESSAGE;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.MAX_ADDRESS_CHAR_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.MAX_CUSTOMER_CHAR_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.MAX_EMAIL_CHAR_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.MAX_MOBILE_CHAR_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.MOBILE_INVALID_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.MOBILE_REQUIRED_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.NAME_INVALID_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.NAME_REQUIRED_DESC;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import com.mycompany.customeraccount.constants.AccountTypeEnum;
import com.mycompany.customeraccount.model.CustomerAccount;
import com.mycompany.customeraccount.model.RegistrationRequest;
import com.mycompany.customeraccount.model.RegistrationResponse;
import com.mycompany.customeraccount.model.Savings;
import com.mycompany.customeraccount.repository.CustomerAccountRepository;

/**
 * The Class RegistrationServiceImpl
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {
  
  /** The Constant LOGGER. */
  public static final Logger LOGGER = LogManager.getLogger(RegistrationServiceImpl.class);
  
  /** The customer account repository. */  
  @Autowired
  private CustomerAccountRepository accountRepo;
  
  /**
   * Registers new customer account.
   * 
   * @param request the request
   * @return RegistrationResponse the registration response
   */
  @Override
  public RegistrationResponse registerAccount(RegistrationRequest request) {
    CustomerAccount customerAccount = new CustomerAccount();
    RegistrationResponse response = new RegistrationResponse();
    try {
      if(this.isRequestFieldsEmpty(request)) {
        throw new NullPointerException("Empty Field");
      }
      customerAccount.setCustomerName(request.getCustomerName());
      customerAccount.setCustomerMobile(request.getCustomerMobile());
      customerAccount.setCustomerEmail(request.getCustomerEmail());
      customerAccount.setAddress1(request.getAddress1());
      customerAccount.setAddress2(request.getAddress2());
      
      List<Savings> savingsList = new ArrayList<>();
      Savings savings = new Savings();
      String accType = request.getAccountType();
      
      if (EnumUtils.isValidEnum(AccountTypeEnum.class, accType)) {
        savings.setAccountType(AccountTypeEnum.valueOf(accType).getType());
      } else {
        LOGGER.info(INVALID_ACCOUNT_TYPE_MESSAGE, accType);
        response.setCustomerNumber(null);
        response.setTransactionStatusCode(HttpStatus.BAD_REQUEST.value());
        if (StringUtils.isEmpty(accType)) {
          response.setTransactionStatusDescription(ACCOUNT_TYPE_REQUIRED_DESC);
        } else {
          response.setTransactionStatusDescription(INVALID_ACCOUNT_TYPE_DESC);
        }
        return response;
      }
      
      savings.setAvailableBalance(0);
      savingsList.add(savings);
      customerAccount.setSavings(savingsList);

      CustomerAccount createdAccount = accountRepo.save(customerAccount);
      
      response.setCustomerNumber(createdAccount.getCustomerNumber());
      response.setTransactionStatusCode(HttpStatus.CREATED.value());
      response.setTransactionStatusDescription(ACCOUNT_CREATED_DESC);
      LOGGER.info(ACCOUNT_SUCCESSFULLY_CREATED, createdAccount.getCustomerNumber());
      
      return response;
    } catch (TransactionSystemException e) {
      LOGGER.info(e.getMessage());
      response.setCustomerNumber(null);
      response.setTransactionStatusCode(HttpStatus.BAD_REQUEST.value());
      this.setStatusDescForFieldsWithInvalidLength(request, response);
      return response;
    } catch (Exception e) {
      LOGGER.info(e.getMessage());
      response.setCustomerNumber(null);
      response.setTransactionStatusCode(HttpStatus.BAD_REQUEST.value());
      this.setStatusDescForMissingField(request, response);
      return response;
    }
  }
  
  /**
   * Checks for missing field.
   * 
   * @param request the request
   * @return boolean the boolean
   */
  private boolean isRequestFieldsEmpty(RegistrationRequest request) {
    if (StringUtils.isEmpty(request.getCustomerName()) || StringUtils.isEmpty(request.getCustomerMobile())
        || StringUtils.isEmpty(request.getCustomerEmail()) || StringUtils.isEmpty(request.getAddress1())) {
      return true;
    }
    
    return false;
  }
  
  /**
   * Sets status description for missing field.
   * 
   * @param request the request
   * @param response the response 
   * @return response the response
   */
  private RegistrationResponse setStatusDescForMissingField(RegistrationRequest request, RegistrationResponse response) {
    if (StringUtils.isEmpty(request.getCustomerName())) {
      response.setTransactionStatusDescription(NAME_REQUIRED_DESC);
    } else if (StringUtils.isEmpty(request.getCustomerMobile())) {
      response.setTransactionStatusDescription(MOBILE_REQUIRED_DESC);
    } else if (StringUtils.isEmpty(request.getCustomerEmail())) {
      response.setTransactionStatusDescription(EMAIL_REQUIRED_DESC);
    } else if (StringUtils.isEmpty(request.getAddress1())) {
      response.setTransactionStatusDescription(ADDRESS1_REQUIRED_DESC);
    } 
    
    return response;
  }
  
  /**
   * Sets status description for fields with invalid length.
   * 
   * @param request the request
   * @param response the response 
   * @return response the response
   */
  private RegistrationResponse setStatusDescForFieldsWithInvalidLength(RegistrationRequest request, RegistrationResponse response) {
    if (request.getCustomerName().length() > MAX_CUSTOMER_CHAR_LENGTH) {
      response.setTransactionStatusDescription(NAME_INVALID_LENGTH);
    } else if (request.getCustomerMobile().length() > MAX_MOBILE_CHAR_LENGTH) {
      response.setTransactionStatusDescription(MOBILE_INVALID_LENGTH);
    } else if (request.getCustomerEmail().length() > MAX_EMAIL_CHAR_LENGTH) {
      response.setTransactionStatusDescription(EMAIL_INVALID_LENGTH);
    } else if (request.getAddress1().length() > MAX_ADDRESS_CHAR_LENGTH) {
      response.setTransactionStatusDescription(ADDRESS1_INVALID_LENGTH);
    } else if (request.getAddress2().length() > MAX_ADDRESS_CHAR_LENGTH) {
      response.setTransactionStatusDescription(ADDRESS2_INVALID_LENGTH);
    }  
    
    return response;
  }
  
}
