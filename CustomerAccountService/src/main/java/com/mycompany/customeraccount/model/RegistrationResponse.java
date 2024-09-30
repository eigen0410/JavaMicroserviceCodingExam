package com.mycompany.customeraccount.model;

import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class RegistrationResponse
 */
@JsonInclude(Include.NON_EMPTY)
public class RegistrationResponse {

  @Nullable
  /** The customer number. */  
  private Long customerNumber;
  
  /** The transaction status code. */  
  private int transactionStatusCode;
  
  /** The transaction status description. */
  private String transactionStatusDescription;
  
  /**
   * Gets customer number.
   * 
   * @return customerNumber the customer number
   */
  public Long getCustomerNumber() {
    return customerNumber;
  }
  
  /**
   * Sets customer number.
   * 
   * @param customerNumber the customer number
   */
  public void setCustomerNumber(Long customerNumber) {
    this.customerNumber = customerNumber;
  }
  
  /**
   * Gets transaction status code.
   * 
   * @return transactionStatusCode the transaction status code
   */
  public int getTransactionStatusCode() {
    return transactionStatusCode;
  }
  
  /**
   * Sets transaction status code.
   * 
   * @param transactionStatusCode the transaction status code
   */
  public void setTransactionStatusCode(int transactionStatusCode) {
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
