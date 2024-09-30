package com.mycompany.customeraccount.model;

import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class CustomerAccountResponse
 */
@JsonInclude(Include.NON_EMPTY)
public class CustomerAccountResponse {
  
  @Nullable
  /** The customer account. */
  private CustomerAccount customerAccount;
  
  /** The transaction status code. */
  private String transactionStatusCode;
  
  /** The transaction status description. */
  private String transactionStatusDescription;

  /**
   * Gets customer account.
   * 
   * @return customerAccount the customer account
   */
  public CustomerAccount getCustomerAccount() {
    return customerAccount;
  }
  
  /**
   * Sets customer account.
   * 
   * @param customerAccount the customer account
   */
  public void setCustomerAccount(CustomerAccount customerAccount) {
    this.customerAccount = customerAccount;
  }

  /**
   * Gets transaction status code.
   * 
   * @return transactionStatusCode the transaction status code
   */
  public String getTransactionStatusCode() {
    return transactionStatusCode;
  }
  
  /**
   * Sets transaction status code.
   * 
   * @param transactionStatusCode the transaction status code
   */
  public void setTransactionStatusCode(String transactionStatusCode) {
    this.transactionStatusCode = transactionStatusCode;
  }

  /**
   * Gets transaction status description.
   * 
   * @return transactionStatusDescription the transaction status description
   */
  public String getTransactionStatusDescription() {
    return transactionStatusDescription;
  }
  
  /**
   * Sets transaction status description.
   * 
   * @param transactionStatusDescription the transaction status description
   */
  public void setTransactionStatusDescription(String transactionStatusDescription) {
    this.transactionStatusDescription = transactionStatusDescription;
  }

}
