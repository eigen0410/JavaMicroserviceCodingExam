package com.mycompany.customeraccount.service;

import com.mycompany.customeraccount.model.AccountInquiryResponse;

/**
 * The Interface InquiryService
 */
public interface InquiryService {

  /**
   * Inquiries the customer account.
   * 
   * @param customerNumber the customer number
   * @return AccountInquiryResponse the account inquiry response
   */
  public AccountInquiryResponse inquireAccount(String customerNumber);

}
