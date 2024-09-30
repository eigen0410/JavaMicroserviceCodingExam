package com.mycompany.customeraccount.service;

import com.mycompany.customeraccount.model.RegistrationRequest;
import com.mycompany.customeraccount.model.RegistrationResponse;

/**
 * The Interface RegistrationService
 */
public interface RegistrationService {
  
  /**
   * Registers new customer account.
   * 
   * @param request the request
   * @return RegistrationResponse the registration response
   */
  public RegistrationResponse registerAccount(RegistrationRequest request);

}
